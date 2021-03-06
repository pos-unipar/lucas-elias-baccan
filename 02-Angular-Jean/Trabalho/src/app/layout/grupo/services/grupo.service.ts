import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { BaseRestService } from 'src/app/shared/services/base-rest.service';
import { ProdutoService } from '../../produto/services/produto.service';
import { Grupo } from '../models/grupo.models';

@Injectable({
  providedIn: 'root'
})
export class GrupoService extends BaseRestService {

  public countSaved: number = 0;

  public buscarTodos(): Observable<Grupo[]> {
    return this.getter<Grupo[]>('grupos').pipe(take(1));
  }

  public buscarPorId(id: number): Observable<Grupo> {
    return this.getter<Grupo>(`grupos/${id}`).pipe(take(1));
  }

  public salvar(grupo: Grupo): Observable<Grupo> {
    this.countSaved++;
    if (grupo.id) {
      grupo.dateUpdate = new Date();
      return this.put<Grupo>(`grupos/${grupo.id}`, grupo);
    } else {
      grupo.dateInsert = new Date();
      return this.post<Grupo>('grupos', grupo);
    }
  }

  public excluir(id: number): Observable<void> {


    return this.delete(`grupos/${id}`).pipe(take(1));
  }
}
