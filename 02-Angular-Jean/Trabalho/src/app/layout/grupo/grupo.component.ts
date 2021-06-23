import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { debounceTime } from 'rxjs/operators';
import { GrupoModalComponent } from './componentes/grupo-modal/grupo-modal.component';
import { Grupo } from './models/grupo.models';
import { GrupoService } from './services/grupo.service';

@Component({
  selector: 'app-grupo',
  templateUrl: './grupo.component.html',
  styleUrls: ['./grupo.component.scss']
})
export class GrupoComponent implements OnInit {

  grupos: Grupo[] = [];
  gruposSearch: Grupo[] = [];
  searchControl: FormControl = new FormControl();

  constructor(
    private toastr: ToastrService,
    private grupoService: GrupoService,
    private modalService: NgbModal,
  ) {

    this.searchControl.valueChanges
      .pipe(debounceTime(500))
      .subscribe(value => {
        this.filtrarGrupos(value.toLocaleLowerCase());
      });
  }

  ngOnInit(): void {
    this.carregaGruposFromApi();
  }

  private filtrarGrupos(value: string): void {
    this.gruposSearch = this.grupos.filter(u =>
      u.descricao.toLocaleLowerCase().includes(value)
    );
  }

  private carregaGruposFromApi(): void {
    this.grupoService.buscarTodos()
      .subscribe(result => {
        this.grupos = result;
        this.filtrarGrupos('');
      }, error => {
        this.toastr.error(error.message, 'Ops.');
      });
  }

  public abrirModal(grupo: Grupo | undefined): void {
    const modalRef = this.modalService.open(GrupoModalComponent, { size: 'lg' });

    modalRef.componentInstance.grupo = grupo;

    modalRef.componentInstance.onSave.subscribe((result: Grupo) => {
      this.toastr.success('Grupo salvo com sucesso!');

      if (!grupo?.id) {
        this.grupos.push(result);
      } else {
        const idx = this.grupos.findIndex(u => u.id === result!.id);
        this.grupos.splice(idx, 1, result);
      }
      this.limpaPesquisa();
    });

    modalRef.componentInstance.onDelete.subscribe(() => {
      this.toastr.success('Grupo excluído com sucesso!');

      const idx = this.grupos.findIndex(u => u.id === grupo!.id);
      this.grupos.splice(idx, 1);
      this.limpaPesquisa();
    });
  }

  private limpaPesquisa(): void {
    this.searchControl?.setValue('');
  }

}
