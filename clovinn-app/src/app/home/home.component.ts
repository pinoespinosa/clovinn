import { Component, OnInit } from "@angular/core";
import { ProductService } from "./../product.service";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  loading = true;

  length = 0;
  page = 0;
  size = [0];

  prodList = [];

  constructor(
    private productService: ProductService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.productService.getProducts(this.page).subscribe(
      (response) => {
        this.prodList = response.content;
        this.length = response.totalElements;
        this.size = [response.size];
        this.loading = false;
      },
      (error) => {
        alert(error.error.message)

      }
    );
  }

  buy(p) {
    this.productService.buyProducts(p.id, p.buyAmmount).subscribe(
      (response) => {
        alert("Compra Exitosa !!!")
        p.stock = response.stock;
        p.buyAmmount = ''
        this.loading = false;
      },
      (error) => {
        alert(error.error.message)
        this.ngOnInit();
      }
    );
  }

  disabledToBuy(p){
    return !(p.buyAmmount > 0 && p.buyAmmount <= p.stock);
  }

  goToAdmin() {
    this.router.navigate(["/admin"]);
  }

  goToAdd() {
    this.router.navigate(["/add"]);
  }

  changePage(e){
    this.page = e.pageIndex;
    this.ngOnInit()
  }

}
