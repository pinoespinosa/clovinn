import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { AdminComponent } from "./admin/admin.component";
import { HomeComponent } from "./home/home.component";

import {
  MatInputModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatTableModule,
  MatSnackBarModule,
  MatPaginatorModule
} from "@angular/material";
import { AddComponent } from './add/add.component';

@NgModule({
  declarations: [AppComponent, AdminComponent, HomeComponent, AddComponent],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NoopAnimationsModule,

    //  ------ Material ------
    MatInputModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatTableModule,
    MatSnackBarModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent, ],
})
export class AppModule {}
