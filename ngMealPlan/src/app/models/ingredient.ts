import { Recipe } from './recipe';
export class Ingredient {

  private id: number;
  private name: string;

  private recipe: Recipe;

constructor(
  id: number = 0,
  name: string = "",
  recipe: Recipe = new Recipe()
){
  this.id = id;
  this.name = name;
  this.recipe = recipe;
}

}
