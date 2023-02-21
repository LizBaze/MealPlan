import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recipe } from '../models/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private url = environment.baseUrl;

  constructor(private http: HttpClient) { }

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







}
