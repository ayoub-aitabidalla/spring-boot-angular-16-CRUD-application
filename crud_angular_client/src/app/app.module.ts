import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import{MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';

import{MatButtonModule} from'@angular/material/button';
import{MatIconModule} from '@angular/material/icon';
import{MatExpansionModule} from '@angular/material/expansion';
import{MatListModule} from '@angular/material/list';
import {MatToolbarModule} from '@angular/material/toolbar';
import{MatBadgeModule} from '@angular/material/badge';
import{MatSnackBarModule} from '@angular/material/snack-bar';
import { AppRoutingModule } from './app-routing.module';
import { MatInputModule } from '@angular/material/input';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { ListClientComponent } from './components/list-client/list-client.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { JwtInterceptor } from './services/jwt.interceptor';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddClientComponent } from './components/add-client/add-client.component';



@NgModule({
  declarations: [
    AppComponent,
    UpdateUserComponent,
    ListClientComponent,
    LoginComponent,
    NavbarComponent,
    AddClientComponent,
    
    
  ],
  imports: [
    MatFormFieldModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatGridListModule,
    MatButtonModule,
    MatIconModule,
    MatExpansionModule,
    MatListModule,
    MatToolbarModule,
    MatBadgeModule,
    MatSnackBarModule,
    AppRoutingModule,
    MatInputModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  
    
    
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
