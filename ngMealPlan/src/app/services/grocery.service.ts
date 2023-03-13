import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Grocery } from '../models/grocery';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GroceryService {

  private url = environment.baseUrl;

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


  index(): Observable<Grocery[]> {
    return this.http.get<Grocery[]>(this.url + "api/groceries", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GroceryService.index(): error retrieving grocery list: ' + err)
        );
      })
    );
  }

  create(grocery: Grocery): Observable<Grocery> {
    return this.http.post<Grocery>(this.url  + "api/groceries", grocery, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GroceryService.create(): error creating grocery item: ' + err)
        );
      })
    )
  }

  update(grocery: Grocery): Observable<Grocery> {
    return this.http.put<Grocery>(this.url + "api/groceries" + "/" + grocery.id, grocery, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GroceryService.update(): error updating grocery item: ' + err)
        );
      })
    )
  }

  createList(groceries: Grocery[]): Observable<Grocery[]> {
    return this.http.post<Grocery[]>(this.url  + "api/grocerylist", groceries, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GroceryService.createList(): error creating grocery list: ' + err)
        );
      })
    )
  }


}
