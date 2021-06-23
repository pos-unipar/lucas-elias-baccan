import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { debounceTime } from 'rxjs/operators';
import { ProdutoModalComponent } from './componentes/produto-modal/produto-modal.component';
import { Produto } from './models/produto.models';
import { ProdutoService } from './services/produto.service';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.scss']
})
export class ProdutoComponent implements OnInit {

  produtos: Produto[] = [];
  produtosSearch: Produto[] = [];
  searchControl: FormControl = new FormControl();

  constructor(
    private toastr: ToastrService,
    private produtoService: ProdutoService,
    private modalService: NgbModal,
  ) {

    this.searchControl.valueChanges
      .pipe(debounceTime(500))
      .subscribe(value => {
        this.filtrarProdutos(value.toLocaleLowerCase());
      });
  }

  ngOnInit(): void {
    this.carregaProdutosFromApi();
  }

  private filtrarProdutos(value: string): void {
    this.produtosSearch = this.produtos.filter(u =>
      u.descricao.toLocaleLowerCase().includes(value)
    );
  }

  private carregaProdutosFromApi(): void {
    this.produtoService.buscarTodos()
      .subscribe(result => {
        this.produtos = result;
        this.filtrarProdutos('');
      }, error => {
        this.toastr.error(error.message, 'Ops.');
      });
  }

  public abrirModal(produto: Produto | undefined): void {
    const modalRef = this.modalService.open(ProdutoModalComponent, { size: 'lg' });

    modalRef.componentInstance.produto = produto;

    modalRef.componentInstance.onSave.subscribe((result: Produto) => {
      this.toastr.success('Produto salvo com sucesso!');

      if (!produto?.id) {
        this.produtos.push(result);
      } else {
        const idx = this.produtos.findIndex(u => u.id === result!.id);
        this.produtos.splice(idx, 1, result);
      }
      this.limpaPesquisa();
    });

    modalRef.componentInstance.onDelete.subscribe(() => {
      this.toastr.success('Produto excluído com sucesso!');

      const idx = this.produtos.findIndex(u => u.id === produto!.id);
      this.produtos.splice(idx, 1);
      this.limpaPesquisa();
    });
  }

  private limpaPesquisa(): void {
    this.searchControl?.setValue('');
  }

}
