import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { filter, map, take } from 'rxjs/operators';
import { BaseRestService } from 'src/app/shared/services/base-rest.service';
import { Produto } from '../models/produto.models';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService extends BaseRestService {

  public countSaved: number = 0;

  public buscarTodos(): Observable<Produto[]> {
    return this.getter<Produto[]>('produtos').pipe(take(1));
  }

  public buscarPorId(id: number): Observable<Produto> {
    return this.getter<Produto>(`produtos/${id}`).pipe(take(1));
  }

  public salvar(cliente: Produto): Observable<Produto> {
    this.countSaved++;
    // Verifica se o cliente já tem ID, se tiver chama o PUT para atual, senão o POST para inserir
    if (cliente.id) {
      cliente.dateUpdate = new Date();
      return this.put<Produto>(`produtos/${cliente.id}`, cliente);
    } else {
      cliente.dateInsert = new Date();
      return this.post<Produto>('produtos', cliente);
    }
  }

  public excluir(id: number): Observable<void> {
    return this.delete(`produtos/${id}`).pipe(take(1));
  }

  public canDeleteGroup(groupId: number): void {
    // const produtos: Produto[] = this.buscarTodos().subscribe(value => {return value});

    // console.log(produtos)
  }
}
