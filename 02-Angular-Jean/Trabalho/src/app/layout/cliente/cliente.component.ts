import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { debounceTime } from 'rxjs/operators';
import { ClienteModalComponent } from './componentes/cliente-modal/cliente-modal.component';
import { Cliente } from './models/cliente.models';
import { ClienteService } from './services/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {

  clientes: Cliente[] = [];
  clientesSearch: Cliente[] = [];
  searchControl: FormControl = new FormControl();

  constructor(
    private toastr: ToastrService,
    private clienteService: ClienteService,
    private modalService: NgbModal,
  ) {

    this.searchControl.valueChanges
      .pipe(debounceTime(500))
      .subscribe(value => {
        this.filtrarClientes(value.toLocaleLowerCase());
      });
  }

  ngOnInit(): void {
    this.carregaClientesFromApi();
  }

  private filtrarClientes(value: string): void {
    this.clientesSearch = this.clientes.filter(u =>
      u.nome.toLocaleLowerCase().includes(value)
    );
  }

  private carregaClientesFromApi(): void {
    this.clienteService.buscarTodos()
      .subscribe(result => {
        this.clientes = result;
        this.filtrarClientes('');
      }, error => {
        this.toastr.error(error.message, 'Ops.');
      });
  }

  public abrirModal(cliente: Cliente | undefined): void {
    const modalRef = this.modalService.open(ClienteModalComponent, { size: 'lg' });

    modalRef.componentInstance.cliente = cliente;

    modalRef.componentInstance.onSave.subscribe((result: Cliente) => {
      this.toastr.success('Cliente salvo com sucesso!');

      if (!cliente?.id) {
        this.clientes.push(result);
      } else {
        const idx = this.clientes.findIndex(u => u.id === result!.id);
        this.clientes.splice(idx, 1, result);
      }
      this.limpaPesquisa();
    });

    modalRef.componentInstance.onDelete.subscribe(() => {
      this.toastr.success('Cliente excluído com sucesso!');

      const idx = this.clientes.findIndex(u => u.id === cliente!.id);
      this.clientes.splice(idx, 1);
      this.limpaPesquisa();
    });
  }

  private limpaPesquisa(): void {
    this.searchControl?.setValue('');
  }

}
