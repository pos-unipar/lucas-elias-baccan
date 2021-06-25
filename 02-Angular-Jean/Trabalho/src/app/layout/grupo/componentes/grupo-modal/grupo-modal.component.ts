import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Endereco } from 'src/app/layout/cliente/models/cliente.models';
import { validateAllFormFields, hasErrors } from 'src/app/shared/helpers/iu.helper';
import { GenericValidator } from 'src/app/shared/helpers/validators.helper';
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
    private grupoService: GrupoService
  ) { }

  ngOnInit(): void {
    this.createForm(this.grupo || {} as Grupo);
  }

  createForm(grupo: Grupo) {
    this.formGroup = this.formBuilder.group({
      nome: [
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
    this.grupoService.excluir(this.grupo!.id!).subscribe(() => {
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
