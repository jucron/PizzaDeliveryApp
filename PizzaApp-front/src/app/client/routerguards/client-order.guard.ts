import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {catchError, map, Observable} from 'rxjs';
import {ClientService, Order} from "../client.service";

@Injectable({
  providedIn: 'root'
})
export class ClientOrderGuard implements CanActivate {
  public static order: Order;

  constructor(private clientService: ClientService) {  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.clientService.getOrder()
      .pipe(
        map(
          (order: Order) => {
            ClientOrderGuard.order = order;
            console.log('ClientOrderGuard: order found with status: '+order.status)
            return true;
          },
          catchError(this.clientService.handleError)
        ));
  };
}
