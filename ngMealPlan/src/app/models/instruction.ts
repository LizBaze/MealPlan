import { Recipe } from "./recipe";

export class Instruction {

  private id: number;
  private description: string;

  private recipe: Recipe;

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
