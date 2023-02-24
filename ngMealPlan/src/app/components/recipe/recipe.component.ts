import { IngredientService } from './../../services/ingredient.service';
import { Ingredient } from './../../models/ingredient';
import { Instruction } from './../../models/instruction';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeHasIngredient } from 'src/app/models/recipe-has-ingredient';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css'],
})
export class RecipeComponent implements OnInit {
  recipes: Recipe[] = [];
  ingredients: Ingredient[] = [];
  newRecipe: Recipe | null = null;
  selected : Recipe | null = null;

  constructor(private recipeService: RecipeService, private ingServ: IngredientService) {}

  ngOnInit() {
    this.index();
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
        console.log(ingredients);
      },
      error: (err: any) => {
        console.error(err);
      }
    })
  }

  create(recipe: Recipe) {
    this.recipeService.create(recipe).subscribe({
      next: (recipe: Recipe) => {
        this.selected = recipe;
      },
      error: (err: any) => {
        console.error(err);
      }
    })
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
}
