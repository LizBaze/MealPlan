import { Ingredient } from './ingredient';
import { Instruction } from './instruction';

export class Recipe {
  id: number;
  name: string;
  description: string;
  ingredients: Ingredient[];
  instructions: Instruction[];

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    ingredients: Ingredient[] = [],
    instructions: Instruction[] = []
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }
}
