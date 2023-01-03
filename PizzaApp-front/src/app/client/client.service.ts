import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, throwError} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseFlowableClientUrl = 'http://localhost:8081/process/client/';
  private baseAccountUrl = 'http://localhost:8081/accounts/';
  private caseKey = 'pizzaOrderCase';
  clientTask_key = 'clientTask';
  private mainUsername_key = 'mainUsername';

  constructor(private http: HttpClient,
              private router: Router) { }

  getClientTask() {
    return localStorage.getItem(this.clientTask_key);
  }

  getMainUsername() {
    return localStorage.getItem(this.mainUsername_key);
  }

  getCurrentUrl() {
    return this.router.url;
  }

  redirectTo(uri:string, hideLocation: boolean){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
      this.router.navigate([uri], {skipLocationChange: hideLocation}));
  }

  executeLogin(credentials: Account) {
    console.log('executeLogin worked')
    this.http.post(this.baseAccountUrl+'login', credentials)
      .subscribe(
        (response: Response) => {
          if (response==null) {
            alert("ERROR: account not found")
            //todo: handle username/password not correct (Security Impl)
          } else {
            localStorage.setItem(this.mainUsername_key,credentials.username)
            this.redirectTo('/client/client-action', true);
          }
        },
        catchError(this.handleError)
        );
  }

  executeLogOUT(username: string) {
    console.log('logOUT worked')
    this.http.post(this.baseAccountUrl+'logout', {username: username})
      .subscribe(
        () => {
            localStorage.clear();
        },
        catchError(this.handleError)
      );
  }

  isUserLogged() {
    console.log('isUserLogged: worked');
    return this.http.get<Response>(this.baseAccountUrl + this.getMainUsername() + "/checkLogin");
  }

  updateClientTask() {
    console.log('updateClientTask worked ')
    return this.http.get<Response>(this.baseFlowableClientUrl+this.getMainUsername()+'/taskDef');
  }

  startProcess() {
    console.log('startProcess worked');
    // @ts-ignore
    this.http.post(this.baseFlowableClientUrl+this.caseKey+'/'+this.getMainUsername())
      .subscribe(()=> {
        this.redirectTo('/client', false);
        },
        catchError(this.handleError)
      );
  }

  completeTask(object: Object) {
    console.log('completeTask worked')
    this.http.post(this.baseFlowableClientUrl+this.getMainUsername(),object)
      .subscribe(()=> {
        console.log('Order created successfully');
        this.redirectTo('/client',false);
      },
        catchError(this.handleError)
      );
  }

  getOrder() {
    console.log('getOrder worked')
    return this.http.get<Order>(this.baseFlowableClientUrl+this.getMainUsername()+'/order');

  }

  handleError(error: HttpErrorResponse) {

    return throwError('A problem happened, try again.');
  }

}

export interface Account {
  username: string;
  password: string;
  orderId: string;
  loginStatus: string;
  taskStatus: string;
}
export interface Response {
  message: string;
}
export interface Order {
  id: string;
  clientName: string;
  pizzaFlavor: string;
  address: string;
  status: string;
  orderTime: string;
  paid: boolean;
}
