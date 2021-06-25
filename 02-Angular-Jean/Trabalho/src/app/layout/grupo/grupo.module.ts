import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GrupoRoutingModule } from './grupo-routing.module';
import { GrupoComponent } from './grupo.component';
import { GrupoModalComponent } from './componentes/grupo-modal/grupo-modal.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    GrupoComponent,
    GrupoModalComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    GrupoRoutingModule,
    SharedModule,
  ]
})
export class GrupoModule { }
