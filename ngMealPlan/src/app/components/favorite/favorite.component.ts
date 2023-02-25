import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Recipe } from 'src/app/models/recipe';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css'],
})
export class FavoriteComponent implements OnInit {
  user: User | null = null;
  mealPlan: Recipe[] | null = null;
  numMeals: number = 0;

  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (loggedInUser) => {
        this.user = loggedInUser;
      },
      error: () => {
        console.error('not logged In');
      },
    });
  }

  initializeMealPlan() {
    this.mealPlan = [];
  }

  generateMealPlan(numMeals: number) {
    // keep track of recipes we've randomly chosen
    this.mealPlan = [];
    let selected: number[] = [];
    if (this.user) {
      if (numMeals > this.user.recipes.length + 1) {
        return null;
      }

      for (let i = 0; i < numMeals; i++) {
        // get a random number based on the length of the user's favorites list
        let x = this.getRandomInt(this.user.recipes.length);

        // insert the first random selection by default
        if (selected.length === 0) {
          selected[i] = x;
        }

        // check for duplicates before adding a random selection to our array
        let duplicate = false;
        for (let num of selected) {
          if (num === x) {
            duplicate = true;
            i--;
            break;
          }
        }
        // Add unique selections to the array
        if (!duplicate) {
          selected[i] = x;
        }
      }

      if (this.mealPlan) {
        for (let num of selected) {
          this.mealPlan.push(this.user.recipes[num])
        }
      }
      console.log(selected);
      console.log(this.mealPlan);
    }
    return null;
  }

  getRandomInt(max: number) {
    return Math.floor(Math.random() * max);
  }
}
