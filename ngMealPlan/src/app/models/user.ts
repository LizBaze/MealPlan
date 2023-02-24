import { Recipe } from "./recipe";

export class User {

  id: number;
  username: string;
  password: string;
  email: string;
  enabled: boolean;
  recipes: Recipe[];
  createdRecipes: Recipe[];

  constructor (
    id: number = 0,
    username:string = "",
    password: string = "",
    email: string = "",
    enabled: boolean = true,
    recipes: Recipe[] = [],
    createdRecipes: Recipe[] = []
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.enabled = enabled;
    this.recipes = recipes;
    this.createdRecipes = createdRecipes;
  }
}
