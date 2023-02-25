import { AuthService } from 'src/app/services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recipe } from '../models/recipe';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  private url = environment.baseUrl;

  user: User | null = null;

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${this.auth.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return httpOptions;
  }

  index(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.url + 'api/recipes').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RecipeService.index(): error retrieving recipe list')
        );
      })
    );
  }

  create(recipe: Recipe) {
    return this.http
      .post<Recipe>(this.url + 'api/recipes', recipe, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('RecipeService.create(): error creating recipe')
          );
        })
      );
  }

  addToFavorites(userId: number, recipeId: number) {

    return this.http
      .put<void>(
        this.url + 'api/users/' + userId + '/favorites/' + recipeId, null,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('RecipeService.addtoFavorites(): error adding recipe to favorites')
          );
        })
      );
  }

  removeFromFavorites(userId: number, recipeId: number) {
    return this.http
      .delete<void>(
        this.url + 'api/users/' + userId + '/favorites/' + recipeId, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.error(err);
            return throwError(
              () => new Error('RecipeService.removeFromFavorites(): error removing recipe from favorites')
            );
          })
        )
     }


}
