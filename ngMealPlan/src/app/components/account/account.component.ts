import { IngredientService } from './../../services/ingredient.service';
import { RecipeService } from './../../services/recipe.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { User } from 'src/app/models/user';
import { Instruction } from 'src/app/models/instruction';
import { RecipeHasIngredient } from 'src/app/models/recipe-has-ingredient';
import { Ingredient } from 'src/app/models/ingredient';
import { S3Service } from 'src/app/services/s3.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  recipes: Recipe[] | null = null;
  selected: Recipe | null = null;
  user: User | null = null;
  editRecipe: Recipe | null = null;
  ingredients: Ingredient[] | null = null;
  fileTooLarge: boolean = false;

  constructor(
    private auth: AuthService,
    private recipeServ: RecipeService,
    private ingServ: IngredientService,
    private s3: S3Service,
    private changeDetect: ChangeDetectorRef

    ) {}

  ngOnInit() {
    if (this.loggedIn()) {
      this.getUser();
    }
  }

  onFileSelect(e: any, recipe: Recipe) {
    if (e.target.files[0].size > 250000) {
      this.fileTooLarge = true;
      console.log(this.fileTooLarge);
      this.changeDetect.detectChanges();


      return;
    } else {
      this.fileTooLarge = false;
    }

    var newFile = null;
    if (this.user) {
      const fileName = this.user.username + recipe.name;
      recipe.imageUrl =
        'https://reciperylist.s3.us-east-2.amazonaws.com/' + fileName;

      newFile = new File([e.target.files[0]], fileName);
      this.s3.uploadFile(newFile);
    }
  }

  getIngredients() {
    this.ingServ.index().subscribe({
      next: (ingredients: Ingredient[]) => {
        this.ingredients = ingredients;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  getUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.user = user;
        this.myRecipes();
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  myRecipes() {
    if (this.user) {
      this.recipeServ.findByUser(this.user.id).subscribe({
        next: (recipes: Recipe[]) => {
          this.recipes = recipes;
        },
        error: (err: any) => {
          console.error(err);
        }
      })
    }
  }

  edit(recipe: Recipe) {
    this.recipeServ.edit(recipe).subscribe({
      next: (updatedRecipe: Recipe) => {
        this.editRecipe = null;
        this.selected = updatedRecipe;
        this.myRecipes();
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  selectRecipe(recipe:Recipe) {
    this.selected = recipe;
  }

  selectRecipeToEdit(recipe: Recipe) {
    this.editRecipe  = recipe;
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




}
