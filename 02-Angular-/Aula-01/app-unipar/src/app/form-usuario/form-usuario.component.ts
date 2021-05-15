import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Usuario from './usuario.model';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.scss']
})
export class FormUsuarioComponent implements OnInit {

  formGroup?: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    const usuario: any = {}
    this.createForm(usuario)
  }

  private createForm(usuario: Usuario) {
    this.formGroup = this.formBuilder.group({
      username: [
        { value: usuario.username, disabled: usuario.id !== undefined },
        Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(30)])
      ],
      password: [
        usuario.password,
        Validators.compose([Validators.required, Validators.minLength(5)])
      ],
      name: [
        usuario.name,
        Validators.compose([Validators.required, Validators.minLength(5)])
      ],
      email: [
        usuario.email,
        Validators.compose([Validators.required, Validators.email])
      ],
    });
  }

  salvar() {
    if (this.formGroup?.invalid) {
      alert("Campos inválidos ou não preenchidos!");
      return;
    }

    const usuario = this.formGroup?.getRawValue() as Usuario;
    alert("Pode salvar")

    //Quem faz isso aqui é a API
    usuario.id = 1
    usuario.dateInsert = new Date();
    usuario.dateUpdate = new Date();

  }

  get usename(){
    return this.formGroup?.get('username');
  }

}
