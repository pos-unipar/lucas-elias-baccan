import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { ItemTodo } from './itemTodo.mode';

@Component({
  selector: 'app-table-todo',
  templateUrl: './table-todo.component.html',
  styleUrls: ['./table-todo.component.scss']
})
export class TableTodoComponent implements OnInit {
  texto?: string;

  @Input()
  lista: Array<ItemTodo> = [];

  @Input()
  minLength: number = 3;


  @Input()
  cor: string = 'danger';

  @Output()
  salvou: EventEmitter<ItemTodo> = new EventEmitter<ItemTodo>();


  constructor() { }

  ngOnInit(): void {
  }

  addTarefa() {
    if (this.texto !== undefined && this.texto.length >= this.minLength) {
      const item: ItemTodo = {
        done: false,
        texto: this.texto,
        id: this.lista.length + 1,
      }
      this.lista.push(item);
      this.texto = undefined;

      this.salvou.emit(item);
    }
  }

  removeTarefa(item: ItemTodo) {
    const idx = this.lista.findIndex(i => i.id === item.id);
    this.lista.splice(idx, 1);
  }

}
