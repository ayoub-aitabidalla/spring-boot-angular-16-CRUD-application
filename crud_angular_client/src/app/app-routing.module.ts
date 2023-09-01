import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListClientComponent } from './components/list-client/list-client.component';
import { authGuard } from './guards/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { AddClientComponent } from './components/add-client/add-client.component';


;

const routes: Routes = [
  {path: "", redirectTo: '/login', pathMatch:'full'},
  {path: "client", children:[
    {path: "",component:ListClientComponent},
  ],canActivate:[authGuard]
},
  {path: "login", component:LoginComponent},
  {path: "update-user/:userId",component:UpdateUserComponent,canActivate:[authGuard]},
  {path: "add-user",component:AddClientComponent,canActivate:[authGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }