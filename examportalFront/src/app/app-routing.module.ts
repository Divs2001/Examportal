import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDasboardComponent } from './pages/admin/admin-dasboard/admin-dasboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserDasboardComponent } from './pages/user/user-dasboard/user-dasboard.component';
import { AdminGuard } from './services/Admin Guard/admin.guard';
import { NormalGuard } from './services/Normal Guard/normal.guard';


const routes: Routes = [
  {
    path:"signup",
    component:SignupComponent,
    pathMatch:"full"
  },
  {
    path:"login",
    component:LoginComponent,
    pathMatch:"full"
  },
  {
    path:"",
    component:HomeComponent,
    pathMatch:"full"
  },
  {
    path:"admin-dashboard",
    component:AdminDasboardComponent,
    pathMatch:"full",
    canActivate:[AdminGuard]
  },
  {
    path:"user-dashboard",
    component: UserDasboardComponent,
    pathMatch:"full",
    canActivate:[NormalGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
