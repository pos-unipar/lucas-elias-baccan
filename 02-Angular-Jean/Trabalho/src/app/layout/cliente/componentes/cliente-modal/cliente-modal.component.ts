import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { validateAllFormFields, hasErrors } from 'src/app/shared/helpers/iu.helper';
import { maskCEP, maskCPF } from 'src/app/shared/helpers/utils.helper';
import { GenericValidator } from 'src/app/shared/helpers/validators.helper';
import { Cliente, Endereco } from '../../models/cliente.models';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-cliente-modal',
  templateUrl: './cliente-modal.component.html',
  styleUrls: ['./cliente-modal.component.scss']
})
export class ClienteModalComponent implements OnInit {

  @Input()
  cliente: Cliente | undefined;

  @Output()
  onSave: EventEmitter<Cliente> = new EventEmitter<Cliente>();

  @Output()
  onDelete: EventEmitter<void> = new EventEmitter<void>();

  formGroup?: FormGroup;

  public maskCPF = maskCPF;
  public maskCEP = maskCEP;

  constructor(
    private activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private clienteService: ClienteService
  ) { }

  ngOnInit(): void {
    this.createForm(this.cliente || {} as Cliente);
  }

  createForm(cliente: Cliente) {
    this.formGroup = this.formBuilder.group({
      nome: [
        cliente.nome,
        Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(100)])
      ],
      cpf: [
        cliente.cpf,
        Validators.compose([Validators.required, GenericValidator.isValidCpf()])
      ],
      dataNascimento: [
        cliente.dataNascimento,
        Validators.compose([Validators.required, Validators.max(new Date().getTime())])
      ],
      endereco: this.createFormEndereco(cliente.endereco || {})
    });
  }

  private createFormEndereco(endereco: Endereco): FormGroup {
    return this.formBuilder.group({
      logradouro: [
        endereco.logradouro,
        Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(100)])
      ],
      numero: [
        endereco.numero,
        Validators.compose([Validators.required, Validators.min(0), Validators.max(999999999)])
      ],
      bairro: [
        endereco.bairro,
        Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(100)])
      ],
      cidade: [
        endereco.cidade,
        Validators.compose([Validators.required])
      ],
      cep: [
        endereco.cep,
        Validators.compose([Validators.required, GenericValidator.isValidCep()])
      ]
    });
  }

  public salvar(): void {
    if (this.formGroup?.invalid) {
      this.toastr.error('Campos inválidos ou não preenchidos!');
      validateAllFormFields(this.formGroup);
      return;
    }

    const clienteForm = this.formGroup?.getRawValue();
    const cliente = { ...this.cliente, ...clienteForm };

    this.clienteService.salvar(cliente)
      .subscribe(result => {
        this.onSave.emit(result);

        this.activeModal.close();
      }, error => {
        this.toastr.error(error.message);
      });

  }

  public excluir(): void {
    this.clienteService.excluir(this.cliente!.id!).subscribe(() => {
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
