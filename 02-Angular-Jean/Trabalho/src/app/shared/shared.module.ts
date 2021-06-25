import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CpfOrCnpjPipe } from './pipes/cpf-pipe';
import { CepPipe } from './pipes/cep-pipe';
import { MessageValidControlComponent } from './message-valid-control/message-valid-control.component';

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [
    CepPipe,
    CpfOrCnpjPipe,
    MessageValidControlComponent,
  ],
  exports: [
    CepPipe,
    CpfOrCnpjPipe,
    MessageValidControlComponent,
  ],
  entryComponents: [
    MessageValidControlComponent
  ]
})
export class SharedModule { }
