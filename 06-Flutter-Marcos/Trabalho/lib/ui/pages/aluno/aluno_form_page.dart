import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/extensions/extensions.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class AlunoFormPage extends StatefulWidget {
  final Aluno? model;
  const AlunoFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<AlunoFormPage> createState() => _AlunoFormPageState();
}

class _AlunoFormPageState extends State<AlunoFormPage> {
  final AlunoDatasource _datasource = AlunoDatasource(Aluno.model());
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _raController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();

  @override
  void initState() {
    super.initState();
    if (widget.model != null) {
      _nomeController.text = widget.model!.nome;
      _raController.text = widget.model!.ra.toString();
      _emailController.text = widget.model!.email;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de aluno'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                model = Aluno(
                  nome: _nomeController.text,
                  ra: _raController.text.toInt(),
                  email: _emailController.text,
                );
                _datasource.insert(model);
              } else {
                model = Aluno(
                  id: widget.model!.id,
                  nome: _nomeController.text,
                  ra: _raController.text.toInt(),
                  email: _emailController.text,
                );
                _datasource.update(model);
              }
              Navigator.pop(context, model);
            },
          ),
        ],
      ),
      body: ListView(
        children: [
          CampoTexto(controller: _raController, texto: 'RA', teclado: TextInputType.number),
          CampoTexto(controller: _nomeController, texto: 'Nome'),
          CampoTexto(controller: _emailController, texto: 'Email', teclado: TextInputType.emailAddress),
        ],
      ),
    );
  }
}
