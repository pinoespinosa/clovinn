import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }


  public getProducts(numberPage): Observable<any> {
    let idUser = localStorage.getItem('idUsuario');
    return this.http.get<any>(environment.server + "/product?&page=" + numberPage );
  }

  public getProduct(id): Observable<any> {
    return this.http.get<any>(environment.server + "/product/" + id );
  }

  public editProducts(id, p): Observable<any> {
    return this.http.put<any>(environment.server + "/product/" + id, p );
  }

  public buyProducts(id, cant): Observable<any> {
    let ammount = { "ammount": cant };
    return this.http.post<any>(environment.server + "/product/"+id+"/buy",ammount );
  }

  public updateStock(id, cant): Observable<any> {
    let ammount = { "ammount": cant };
    return this.http.post<any>(environment.server + "/product/"+id+"/stock",ammount );
  }

  public createProducts(p): Observable<any> {
    return this.http.post<any>(environment.server + "/product",p );
  }

  public deleteProduct(id): Observable<any> {
    return this.http.delete<any>(environment.server + "/product/" + id );
  }

}
