import { Component, OnInit } from '@angular/core';
import { ItemTodo } from './table-todo/itemTodo.mode';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  minhaLista: Array<ItemTodo> = [];
  minhaLista2: Array<ItemTodo> = [];

  ngOnInit(): void {
    this.minhaLista = [
      {id: 1, texto: "Atividade 1", done: false},
      {id: 2, texto: "Atividade 2", done: false},
      {id: 3, texto: "Atividade 3", done: false},
      {id: 4, texto: "Atividade 4", done: false},
    ]

    this.minhaLista2 = [
      {id: 2, texto: "Atividade 2", done: false},
      {id: 4, texto: "Atividade 4", done: false},
    ]
  }

  funSalvou(item: ItemTodo) {
    alert(item.texto);
  }
}