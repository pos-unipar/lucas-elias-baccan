import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { BaseRestService } from 'src/app/shared/services/base-rest.service';
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

  public salvar(cliente: Grupo): Observable<Grupo> {
    this.countSaved++;
    // Verifica se o cliente já tem ID, se tiver chama o PUT para atual, senão o POST para inserir
    if (cliente.id) {
      cliente.dateUpdate = new Date();
      return this.put<Grupo>(`grupos/${cliente.id}`, cliente);
    } else {
      cliente.dateInsert = new Date();
      return this.post<Grupo>('grupos', cliente);
    }
  }

  public excluir(id: number): Observable<void> {
    return this.delete(`grupos/${id}`).pipe(take(1));
  }
}
