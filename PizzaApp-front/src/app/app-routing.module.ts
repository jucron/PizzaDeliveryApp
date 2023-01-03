import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppStartComponent} from "./app-start/app-start.component";

const routes: Routes = [
  {path: '', component: AppStartComponent},
  {path: 'client', loadChildren: () =>
      import('./client/client.module').then(m => m.ClientModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
