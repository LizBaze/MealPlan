import { AuthService } from 'src/app/services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ingredient } from '../models/ingredient';

@Injectable({
  providedIn: 'root',
})
export class IngredientService {
  private url = environment.baseUrl;

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

  index(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.url + 'api/ingredients').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error(
              'IngredientService.index(): error retrieving ingredients list'
            )
        );
      })
    );
  }

  getIngredientsAndMeasurements(): Observable<[][]> {
    return this.http.get<[][]>(this.url + 'api/measurements').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error(
              'IngredientService.getIngredientsAndMeasurements(): error retrieving ingredients and measurements list'
            )
        );
      })
    );
  }
}
