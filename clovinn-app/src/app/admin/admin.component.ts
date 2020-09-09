import { Component, OnInit } from "@angular/core";
import { ProductService } from "./../product.service";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.scss"],
})
export class AdminComponent implements OnInit {
  prodList = [];
  loading = true;
  length = 0;
  page = 0;
  size = [8];
  displayedColumns: string[] = [
    "image",
    "id",
    "name",
    "stock",
    "newStock",
    "action",
  ];
  dataSource = new MatTableDataSource<any>([]);

  constructor(
    private productService: ProductService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.productService.getProducts(this.page).subscribe(
      (response) => {
        this.length = response.totalElements;
        this.size = [response.size];

        this.dataSource = new MatTableDataSource<any>(response.content);
        this.loading = false;
      },
      (error) => {}
    );
  }

  changePage(e) {
    this.page = e.pageIndex;
    this.ngOnInit();
  }

  updateStock(p) {
    this.productService.updateStock(p.id, p.newStock).subscribe(
      (response) => {
        alert("Stock Actualizado !!!");
        p.stock = response.stock;
        p.newStock = null;
        this.loading = false;
      },
      (error) => {
        alert(error.error.message);
      }
    );
  }

  borrar(p) {
    if (confirm("Esta seguro que desea eliminar el producto?")) {
      this.productService.deleteProduct(p.id).subscribe(
        (response) => {
          alert("Producto borrado exitosamente !!!");
          this.ngOnInit();
        },
        (error) => {
          alert(error.error.message);
        }
      );
    }
  }

  goToAdd() {
    this.router.navigate(["/add"]);
  }
  goToHome() {
    this.router.navigate(["/home"]);
  }
  goToEdit(id) {
    this.router.navigate(["add"], { queryParams: { idProduct: id } });
  }
}
