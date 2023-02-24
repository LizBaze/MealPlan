import { RecipeHasIngredient } from './recipe-has-ingredient';
import { Recipe } from './recipe';
export class Ingredient {

  id: number;
  name: string;
  amount: string;

  recipes: RecipeHasIngredient[];

constructor(
  id: number = 0,
  name: string = "",
  amount: string = "",
  recipes: RecipeHasIngredient[] = []
){
  this.id = id;
  this.name = name;
  this.amount = amount;
  this.recipes = recipes;
}

}
