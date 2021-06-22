import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./layout/layout.module').then(m => m.LayoutModule) },
  { path: 'grupo', loadChildren: () => import('./layout/grupo/grupo.module').then(m => m.GrupoModule) },
  { path: 'produto', loadChildren: () => import('./layout/produto/produto.module').then(m => m.ProdutoModule) },
  { path: 'cliente', loadChildren: () => import('./layout/cliente/cliente.module').then(m => m.ClienteModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
