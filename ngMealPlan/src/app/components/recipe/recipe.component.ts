import { Ingredient } from './../../models/ingredient';
import { Instruction } from './../../models/instruction';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css'],
})
export class RecipeComponent implements OnInit {
  recipes: Recipe[] = [];
  newRecipe: Recipe | null = null;

  constructor(private recipeService: RecipeService) {}

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
  }

  initializeNewRecipe() {
    this.newRecipe = new Recipe();
    this.newRecipe.instructions.push(new Instruction());
    this.newRecipe.ingredients.push(new Ingredient());
  }

  addNewInstructionToRecipe() {
    if (this.newRecipe) {
      this.newRecipe.instructions.push(new Instruction());
    }
  }

  addNewIngredientToRecipe() {

    if (this.newRecipe) {
      this.newRecipe.ingredients.push(new Ingredient());
    }
  }
}
