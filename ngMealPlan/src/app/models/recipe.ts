import { Ingredient } from './ingredient';
import { Instruction } from './instruction';
import { RecipeHasIngredient } from './recipe-has-ingredient';
import { User } from './user';

export class Recipe {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  ingredients: RecipeHasIngredient[];
  instructions: Instruction[];
  user: User | null;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    imageUrl: string ='',
    ingredients: RecipeHasIngredient[] = [],
    instructions: Instruction[] = [],
    user: User | null = null
  ) {
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.user = user;
  }
}
