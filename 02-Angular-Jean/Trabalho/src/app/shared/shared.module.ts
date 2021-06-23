import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CpfOrCnpjPipe } from './pipes/cpf-pipe';
import { CepPipe } from './pipes/cep-pipe';

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [
    CepPipe,
    CpfOrCnpjPipe,
  ],
  exports: [
    CepPipe,
    CpfOrCnpjPipe,
  ]
})
export class SharedModule { }
