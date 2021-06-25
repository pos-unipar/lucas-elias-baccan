import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { min } from 'rxjs/operators';
import { Grupo } from 'src/app/layout/grupo/models/grupo.models';
import { hasErrors, validateAllFormFields } from 'src/app/shared/helpers/iu.helper';
import { Preco, Produto } from '../../models/produto.models';
import { ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-produto-modal',
  templateUrl: './produto-modal.component.html',
  styleUrls: ['./produto-modal.component.scss']
})
export class ProdutoModalComponent implements OnInit {

  @Input()
  produto: Produto | undefined;

  @Input()
  grupos: Grupo[] | undefined;

  @Output()
  onSave: EventEmitter<Produto> = new EventEmitter<Produto>();

  @Output()
  onDelete: EventEmitter<void> = new EventEmitter<void>();

  formGroup?: FormGroup;

  constructor(
    private activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private produtoService: ProdutoService
  ) { }

  ngOnInit(): void {
    this.createForm(this.produto || {} as Produto);
  }

  createForm(produto: Produto) {
    this.formGroup = this.formBuilder.group({
      descricao: [
        produto.descricao,
        Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(100)])
      ],
      ativo: [
        produto.ativo,
        Validators.compose([Validators.required])
      ],
      groupId: [
        produto.groupId,
        Validators.compose([Validators.required])
      ],
      estoque: [
        produto.estoque,
        Validators.compose([Validators.required, Validators.min(0), Validators.max(999999999)])
      ],
      preco: this.createFormPreco(produto.preco || {}),
    });
  }

  private createFormPreco(preco: Preco): FormGroup {
    return this.formBuilder.group({
      compra: [
        preco.compra,
        Validators.compose([Validators.required, Validators.min(0), Validators.max(999999999)])
        // Validators.compose([Validators.required, ])
      ],
      venda: [
        preco.venda,
        Validators.compose([Validators.required, Validators.min(0), Validators.max(999999999)])
        // Validators.compose([Validators.required, ])
      ],
    });
  }

  public salvar(): void {
    if (this.formGroup?.invalid) {
      this.toastr.error('Campos inválidos ou não preenchidos!');
      validateAllFormFields(this.formGroup);
      return;
    }

    const produtoForm = this.formGroup?.getRawValue();
    const produto = { ...this.produto, ...produtoForm };

    this.produtoService.salvar(produto)
      .subscribe(result => {
        this.onSave.emit(result);

        this.activeModal.close();
      }, error => {
        this.toastr.error(error.message);
      });

  }

  public excluir(): void {
    this.produtoService.excluir(this.produto!.id!).subscribe(() => {
      this.onDelete.emit();

      this.activeModal.close();
    }, error => {
      this.toastr.error(error.message);
    });
  }

  public getControl(controlName: string): AbstractControl {
    return this.formGroup?.get(controlName)!;
  }

  hasErrors = hasErrors;

}

