import { Ingredient } from "./ingredient";
import { Recipe } from "./recipe";

export class RecipeHasIngredient {

  recipe: Recipe;
  ingredient: Ingredient;
  amount: string;
  measurement: string;


  constructor(
    recipe: Recipe = new Recipe(),
    ingredient: Ingredient = new Ingredient(),
    amount: string = "",
    measurement: string = ""
  ) {
    this.recipe = recipe;
    this.ingredient = ingredient;
    this.amount = amount;
    this.measurement = measurement;
  }
}
