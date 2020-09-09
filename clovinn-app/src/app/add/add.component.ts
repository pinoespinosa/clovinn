import { Component, OnInit } from "@angular/core";
import { ProductService } from "./../product.service";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-add",
  templateUrl: "./add.component.html",
  styleUrls: ["./add.component.scss"],
})
export class AddComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private productService: ProductService
  ) {
    this.route.queryParams.subscribe((params) => {
      this.productId = params["idProduct"] ? params["idProduct"] : null;
    });
  }

  loading = true;
  productId = null;

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: [
        "",
        [
          Validators.required,
          Validators.minLength(0),
          Validators.maxLength(250),
        ],
      ],
      image: [
        "",
        [
          Validators.required,
          Validators.minLength(0),
          Validators.maxLength(250),
        ],
      ],
      price: [
        "",
        [Validators.required, Validators.min(0), Validators.max(999)],
      ],
      stock: [
        "",
        [Validators.required, Validators.min(0), Validators.max(999)],
      ],
    });


    if (this.productId)
      this.productService.getProduct(this.productId).subscribe(
        (response) => {
          this.f.name.setValue(response.name);
          this.f.image.setValue(response.image);
          this.f.price.setValue(response.price);
          this.f.stock.setValue(response.stock);
        },
        (error) => {
          alert(error.error.message);
        }
      );
  }

  get f() {
    return this.registerForm.controls;
  }

  goToAdmin() {
    this.router.navigate(["/admin"]);
  }
  goToHome() {
    this.router.navigate(["/home"]);
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    let newProd = {
      image: this.f.image.value,
      name: this.f.name.value,
      price: this.f.price.value,
      stock: this.f.stock.value,
    };

    if (this.productId) {
      this.productService.editProducts(this.productId, newProd).subscribe(
        (response) => {
          alert("Producto Agregado Exitosamente !!!");
          this.onReset();
          this.loading = false;
        },
        (error) => {
          alert(error.error.message);
        }
      );
    } else {
      this.productService.createProducts(newProd).subscribe(
        (response) => {
          alert("Producto Agregado Exitosamente !!!");
          this.onReset();
          this.loading = false;
        },
        (error) => {
          alert(error.error.message);
        }
      );
    }
  }
}
