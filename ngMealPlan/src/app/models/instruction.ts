import { Recipe } from "./recipe";

export class Instruction {

  id: number;
  description: string;

  recipe: Recipe;

constructor(
  id: number = 0,
  description: string = "",
  recipe: Recipe = new Recipe()
){
  this.id = id;
  this.description = description;
  this.recipe = recipe;
}

}
