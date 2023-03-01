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
    this.getUser();
  }

  onFileSelect(e: any) {
    var newFile = null;
    if (this.user && this.newRecipe) {

      const fileName = this.user.username + this.newRecipe.name;
      newFile = new File([e.target.files[0]], fileName);
      this.s3.uploadFile(newFile);
      this.newRecipe.image = fileName;
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

  initializeNewRecipe() {
    this.newRecipe = new Recipe();
    this.newRecipe.instructions.push(new Instruction());
    this.newRecipe.ingredients.push(new RecipeHasIngredient());
  }

  addNewInstructionToRecipe() {
    if (this.newRecipe) {
      this.newRecipe.instructions.push(new Instruction());
    }
  }

  addNewIngredientToRecipe() {
    if (this.newRecipe) {
      this.newRecipe.ingredients.push(new RecipeHasIngredient());
    }
  }

  selectRecipe(recipe: Recipe) {
    this.selected = recipe;
  }
}
