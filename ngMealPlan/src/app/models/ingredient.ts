import { Recipe } from './recipe';
export class Ingredient {

  id: number;
  name: string;
  amount: string;

  recipe: Recipe;

constructor(
  id: number = 0,
  name: string = "",
  amount: string = "",
  recipe: Recipe = new Recipe()
){
  this.id = id;
  this.name = name;
  this.amount = amount;
  this.recipe = recipe;
}

}
