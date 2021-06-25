import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { ProdutoComponent } from './produto.component';
import { ProdutoModalComponent } from './componentes/produto-modal/produto-modal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { TextMaskModule } from 'angular2-text-mask';


@NgModule({
  declarations: [
    ProdutoComponent,
    ProdutoModalComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    ProdutoRoutingModule,
    SharedModule,
    CurrencyMaskModule,
    TextMaskModule
  ]
})
export class ProdutoModule { }
