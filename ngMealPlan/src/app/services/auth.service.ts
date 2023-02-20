import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { Buffer } from "buffer";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.baseUrl;

  constructor(private http: HttpClient) {}

  register(user: User): Observable<User> {
    return this.http.post<User>(this.url + 'api/register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.register(): error registering user.')
        );
      })
    );
  }

  login(username: string, password: string): Observable<User> {
    // Make credentials
    console.log(username);
    console.log(password);
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    console.log(credentials);

    // Create GET request to authenticate credentials
    return this.http.get<User>(this.url + 'api/authenticate', httpOptions).pipe(
      tap((newUser: User) => {
        // While credentials are stored in browser localStorage, we consider
        // ourselves logged in.
        localStorage.setItem('credentials', credentials);
        return newUser;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.login(): error logging in user.')
        );
      })
    );
  }

  generateBasicAuthCredentials(username: string, password: string): string {
    return Buffer.from(`${username}:${password}`).toString('base64');
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }

}
