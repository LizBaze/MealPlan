import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccountComponent } from './components/account/account.component';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { GroceryComponent } from './components/grocery/grocery.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    RecipeComponent,
    HomeComponent,
    NavbarComponent,
    AccountComponent,
    FavoriteComponent,
    GroceryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
