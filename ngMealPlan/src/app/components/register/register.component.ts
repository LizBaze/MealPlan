import { Component } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user: User = new User();
  logInUser: User = new User();
  confirmPassword: string = '';
  passwordMatch: boolean = true;
  loginFailure = false;

  constructor(private auth: AuthService, private router: Router) {}

  register(user: User) {
    if (user.password !== this.confirmPassword) {
      this.passwordMatch = false;
    } else {
      this.auth.register(user).subscribe({
        next: (regUser: User) => {
          this.auth.login(user.username, user.password).subscribe({
            next: (loggedInUser) => {
              this.confirmPassword = "";
              this.router.navigateByUrl("/home");
            },
            error: (problem) => {
              console.error(
                'RegisterComponent.register(): Error logging in user:'
              );
              console.error(problem);
            },
          });
          this.user = new User();
        },
      });
    }
  }

  logIn(username: string, password: string) {
    this.auth.login(username, password).subscribe({
      next: (loggedInUser) => {

      },
      error: (problem) => {
        console.error('RegisterComponent.register(): Error logging in user:');
        console.error(problem);
        this.loginFailure = true;
      },
    });
  }
}
