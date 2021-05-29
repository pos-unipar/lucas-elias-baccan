import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { TableTodoComponent } from './table-todo/table-todo.component';
import { FormUsuarioComponent } from './form-usuario/form-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    TableTodoComponent,
    FormUsuarioComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
