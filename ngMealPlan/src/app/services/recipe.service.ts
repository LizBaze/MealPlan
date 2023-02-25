import { AuthService } from 'src/app/services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recipe } from '../models/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private url = environment.baseUrl;

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${this.auth.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return httpOptions;
  }

  index(): Observable<Recipe[]>{
    return this.http.get<Recipe[]>(this.url + "api/recipes").pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error("RecipeService.index(): error retrieving recipe list")
        )
      })
    )
  }

  create(recipe: Recipe){
    return this.http.post<Recipe>(this.url + "api/recipes", recipe, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error("RecipeService.create(): error creating recipe")
        )
      })
    )
  }

  addToFavorites(recipe: Recipe) {

  }







}
