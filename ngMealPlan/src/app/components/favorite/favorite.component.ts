import { Router } from '@angular/router';
import { RecipeService } from './../../services/recipe.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Recipe } from 'src/app/models/recipe';
import { GroceryService } from 'src/app/services/grocery.service';
import { Grocery } from 'src/app/models/grocery';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css'],
})
export class FavoriteComponent implements OnInit {
  user: User | null = null;
  favorites: Recipe[] | null = null;
  mealPlan: Recipe[] | null = null;
  numMeals: number = 0;
  selected: Recipe | null = null;
  userSearchTerm: string = '';
  makeMealPlan: boolean = false;
  notEnoughFavorites: boolean = false;

  constructor(
    private auth: AuthService,
    private recipeService: RecipeService,
    private groceryService: GroceryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getUser();
  }

  getFavorites() {
    this.recipeService.findFavorites().subscribe({
      next: (recipes: Recipe[]) => {
        this.mealPlan = null;
        this.favorites = recipes;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  getUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (loggedInUser) => {
        this.user = loggedInUser;
        this.getFavorites();
        // this.getMealPlan();
      },
      error: () => {
        console.error('not logged In');
      },
    });
  }

  initializeMealPlan() {
    this.mealPlan = [];
  }

  selectRecipe(recipe: Recipe) {
    this.selected = recipe;
  }

  checkIfRecipeInFavorites(recipe: Recipe) {
    if (this.user) {
      for (let i = 0; i < this.user.recipes.length; i++) {
        if (this.user.recipes[i].id === recipe.id) {
          return true;
        }
      }
    }
    return false;
  }

  addToFavorites(userId: number, recipeId: number) {
    this.recipeService.addToFavorites(userId, recipeId).subscribe({
      next: () => {
        this.getFavorites();
        this.getUser();
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  removeFromFavorites(userId: number, recipeId: number) {
    this.recipeService.removeFromFavorites(userId, recipeId).subscribe({
      next: () => {
        this.getFavorites();
        this.getUser();
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  search(searchTerm: string) {
    this.recipeService.searchFavorites(searchTerm).subscribe({
      next: (recipes: Recipe[]) => {
        this.favorites = recipes;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  generateMealPlan(numMeals: number) {

    if (this.user && numMeals > this.user.recipes.length) {
      this.notEnoughFavorites = true;
      // this.mealPlan = null;
    } else if (this.user) {
      this.makeMealPlan = false;
      this.favorites = null;
      this.notEnoughFavorites = false;
      this.mealPlan = [];
      // create copy of user's favorites
      let faves = this.user.recipes.slice(0);

      for (let i = 0; i < numMeals; i++) {
        //select a random index of the array and get that recipe
        let index = Math.floor(Math.random() * faves.length);
        var recipe = faves[index];
        //remove the recipe from the copy and insert it into mealPlan
        faves.splice(index, 1);
        this.mealPlan.push(recipe);
      }
      this.uploadMealPlan(this.mealPlan);
    }
  }

  uploadMealPlan(recipes: Recipe[]) {
    if (this.user) {
      this.recipeService.createMealPlan(recipes, this.user.username).subscribe({
        next: (recipes: Recipe[]) => {
          this.mealPlan = recipes;
          this.generateGroceryList();
        },
        error: (err: any) => {
          console.error(err);
        },
      });
    }
  }

  generateGroceryList() {
    if (this.mealPlan) {
      let groceries: Grocery[] = [];
      for (let i = 0; i < this.mealPlan.length; i++) {
        for (let j = 0; j < this.mealPlan[i].ingredients.length; j++) {
          let grocery = new Grocery();
          grocery.name = this.mealPlan[i].ingredients[j].ingredient.name;
          groceries.push(grocery)
        }
      }

      this.groceryService.createList(groceries).subscribe({
        next: (groceryList: Grocery[]) => {

        },
        error: (err: any) => {
          console.error(err);
        }
      })

    }

  }

  getMealPlan() {
    this.recipeService.getMealPlan().subscribe({
      next: (recipes: Recipe[]) => {
        this.favorites = null;
        this.mealPlan = recipes;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }


}
