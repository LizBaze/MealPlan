import { S3Service } from './../../services/s3.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { IngredientService } from './../../services/ingredient.service';
import { Ingredient } from './../../models/ingredient';
import { Instruction } from './../../models/instruction';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeHasIngredient } from 'src/app/models/recipe-has-ingredient';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css'],
})
export class RecipeComponent implements OnInit {
  recipes: Recipe[] = [];
  ingredients: Ingredient[] = [];
  newRecipe: Recipe | null = null;
  editRecipe: Recipe | null = null;
  selected: Recipe | null = null;
  user: User | null = null;

  constructor(
    private recipeService: RecipeService,
    private ingServ: IngredientService,
    private auth: AuthService,
    private router: Router,
    private s3: S3Service
  ) {}

  ngOnInit() {
    this.index();
    if (this.loggedIn()) {
      this.getUser();
    }
  }

  onFileSelect(e: any, recipe: Recipe) {
    var newFile = null;
    if (this.user) {
      const fileName = this.user.username + recipe.name;
      recipe.imageUrl =
        'https://reciperylist.s3.us-east-2.amazonaws.com/' + fileName;

      newFile = new File([e.target.files[0]], fileName);
      this.s3.uploadFile(newFile);
    }
  }

  checkIfRecipeInFavorites(recipe: Recipe) {
    if (this.user) {
      for (let i = 0; i < this.user.recipes.length; i++) {
        if (this.user.recipes[i].id === recipe.id) {
          return true;
        }
      }
    }
    return false;
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  getUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.user = user;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  index() {
    this.recipeService.index().subscribe({
      next: (recipes: Recipe[]) => {
        this.recipes = recipes;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
    this.ingServ.index().subscribe({
      next: (ingredients: Ingredient[]) => {
        this.ingredients = ingredients;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  create(recipe: Recipe) {
    this.recipeService.create(recipe).subscribe({
      next: (recipe: Recipe) => {
        this.selected = recipe;
        this.newRecipe = null;
        this.index();
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  addToFavorites(userId: number, recipeId: number) {
    this.recipeService.addToFavorites(userId, recipeId).subscribe({
      next: () => {
        this.router.navigateByUrl('/favorites');
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  removeFromFavorites(userId: number, recipeId: number) {
    this.recipeService.removeFromFavorites(userId, recipeId).subscribe({
      next: () => {
        this.router.navigateByUrl('/favorites');
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  edit(recipe: Recipe) {
    this.recipeService.edit(recipe).subscribe({
      next: (updatedRecipe: Recipe) => {
        this.editRecipe = null;
        this.selected = updatedRecipe;
        this.index();
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  initializeNewRecipe() {
    this.newRecipe = new Recipe();
    this.newRecipe.instructions.push(new Instruction());
    this.newRecipe.ingredients.push(new RecipeHasIngredient());
  }

  addNewInstructionToRecipe(recipe: Recipe) {
    recipe.instructions.push(new Instruction());
  }

  removeInstructionFromRecipe(instruction: Instruction, recipe: Recipe) {
    recipe.instructions = recipe.instructions.filter((obj) => {
      return obj !== instruction;
    });
  }

  addNewIngredientToRecipe(recipe: Recipe) {
    recipe.ingredients.push(new RecipeHasIngredient());
  }

  removeIngredientFromRecipe(ingredient: RecipeHasIngredient, recipe: Recipe) {
    recipe.ingredients = recipe.ingredients.filter((obj) => {
      return obj !== ingredient;
    });
  }

  selectRecipe(recipe: Recipe) {
    this.selected = recipe;
  }

  selectRecipeToEdit(recipe: Recipe) {
    this.selected = null;
    this.editRecipe = recipe;
  }
}
