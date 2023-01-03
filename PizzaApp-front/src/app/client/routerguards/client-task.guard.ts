import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {catchError, map, Observable} from 'rxjs';
import {ClientService, Response} from "../client.service";

@Injectable({
  providedIn: 'root'
})
export class ClientTaskGuard implements CanActivate {
  constructor(private clientService: ClientService) {  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.clientService.updateClientTask()
      .pipe(
      map(
        (response: Response) => {
          console.log('ClientTaskGuard: taskDef found: '+response.message)
          localStorage.setItem(this.clientService.clientTask_key, response.message);
          return true;
        },
        catchError(this.clientService.handleError)
      ));
  };
}
