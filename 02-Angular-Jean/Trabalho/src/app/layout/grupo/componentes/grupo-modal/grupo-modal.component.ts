import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ProdutoService } from 'src/app/layout/produto/services/produto.service';
import { hasErrors, validateAllFormFields } from 'src/app/shared/helpers/iu.helper';
import { Grupo } from '../../models/grupo.models';
import { GrupoService } from '../../services/grupo.service';

@Component({
  selector: 'app-grupo-modal',
  templateUrl: './grupo-modal.component.html',
  styleUrls: ['./grupo-modal.component.scss']
})
export class GrupoModalComponent implements OnInit {

  @Input()
  grupo: Grupo | undefined;

  @Output()
  onSave: EventEmitter<Grupo> = new EventEmitter<Grupo>();

  @Output()
  onDelete: EventEmitter<void> = new EventEmitter<void>();

  formGroup?: FormGroup;


  constructor(
    private activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private grupoService: GrupoService,
    private produtoService: ProdutoService
  ) { }

  ngOnInit(): void {
    this.createForm(this.grupo || {} as Grupo);
  }

  createForm(grupo: Grupo) {
    this.formGroup = this.formBuilder.group({
      descricao: [
        grupo.descricao,
        Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(20)])
      ],
    });
  }

  public salvar(): void {
    if (this.formGroup?.invalid) {
      this.toastr.error('Campos inválidos ou não preenchidos!');
      validateAllFormFields(this.formGroup);
      return;
    }

    const grupoForm = this.formGroup?.getRawValue();
    const grupo = { ...this.grupo, ...grupoForm };

    this.grupoService.salvar(grupo)
      .subscribe(result => {
        this.onSave.emit(result);

        this.activeModal.close();
      }, error => {
        this.toastr.error(error.message);
      });

  }

  public excluir(): void {
    this.produtoService.existeProdutoPorGrupoId(this.grupo?.id!).subscribe(
      result => {
        // Se existir algum produto com o grupo, retorna TRUE, então NÃO pode deletar
        if (!result) {
          this.grupoService.excluir(this.grupo!.id!).subscribe(() => {
            this.onDelete.emit();
            this.activeModal.close();
          }, error => {
            this.toastr.error(error.message);
          });
        } else {
          this.toastr.error("Grupo não pode ser deletado por estar em uso em um produto!");
        }
      }
    );


  }

  public getControl(controlName: string): AbstractControl {
    return this.formGroup?.get(controlName)!;
  }

  hasErrors = hasErrors;

}
