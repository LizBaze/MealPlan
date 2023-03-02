import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  recipes: Recipe[] | null = null;
  selected: Recipe | null = null;
  user: User | null = null;
  editRecipe: Recipe | null = null;

  constructor(private auth: AuthService) {}

  ngOnInit() {
    if (this.loggedIn()) {
      this.getUser();
    }
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  getUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.user = user;
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  myRecipes() {
    if (this.user) {
      this.recipes = this.user.recipes;
    }
  }

  selectRecipe(recipe:Recipe) {
    this.selected = recipe;
  }

  selectRecipeToEdit(recipe: Recipe) {
    this.editRecipe  = recipe;
  }
}
