import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { AddComponent } from './add/add.component';

const routes: Routes = [

  { path: "home", component: HomeComponent },
  { path: "admin", component: AdminComponent },
  { path: "add", component: AddComponent },

  { path: "", component: HomeComponent },
  {
    path: '*',
    redirectTo: '/home',
    pathMatch: 'full'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
