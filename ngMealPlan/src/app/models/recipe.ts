import { Ingredient } from './ingredient';
import { Instruction } from './instruction';
import { RecipeHasIngredient } from './recipe-has-ingredient';

export class Recipe {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  ingredients: RecipeHasIngredient[];
  instructions: Instruction[];

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    imageUrl: string ='',
    ingredients: RecipeHasIngredient[] = [],
    instructions: Instruction[] = []
  ) {
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }
}
