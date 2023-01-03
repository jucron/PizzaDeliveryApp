import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {map, Observable} from 'rxjs';
import {ClientService} from "../client.service";

@Injectable({
  providedIn: 'root'
})
export class NoAuthGuard implements CanActivate {
  constructor(private clientService: ClientService,
              private router: Router) {  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | boolean | UrlTree {

    if (localStorage.getItem('mainUsername') == null) {
      console.log('NoAuthGuard: not logged in Angular, redirecting to Login Page')
      return this.router.createUrlTree([''])
    };

    return this.clientService.isUserLogged()
      .pipe(
        map(
        (response: Response) => {
          let loginStatus = response.message;
          if (loginStatus == 'not_logged') {
            localStorage.clear();
            console.log('NoAuthGuard: user not logged in server, clearing localStorage and redirecting');
            return this.router.createUrlTree([''])
          } else {
            console.log('NoAuthGuard: user logged in both points, staying in this page');
            return true;
          }
        }
      ));
  }
}
interface Response {
  message: string;
  messageB: string;
}
