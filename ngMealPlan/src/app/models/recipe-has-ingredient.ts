import { Ingredient } from "./ingredient";
import { Recipe } from "./recipe";

export class RecipeHasIngredient {

  recipe: Recipe;
  ingredient: Ingredient;
  amount: string;


  constructor(
    recipe: Recipe = new Recipe(),
    ingredient: Ingredient = new Ingredient(),
    amount: string = ""
  ) {
    this.recipe = recipe;
    this.ingredient = ingredient;
    this.amount = amount;
  }
}
