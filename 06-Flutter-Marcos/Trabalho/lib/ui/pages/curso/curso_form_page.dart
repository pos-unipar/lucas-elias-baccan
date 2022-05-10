import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class CursoFormPage extends StatefulWidget {
  final Curso? model;
  const CursoFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<CursoFormPage> createState() => _CursoFormPageState();
}

class _CursoFormPageState extends State<CursoFormPage> {
  final CursoDatasource _datasource = CursoDatasource(Curso.model());
  final TextEditingController _nomeController = TextEditingController();

  @override
  void initState() {
    super.initState();
    if (widget.model != null) {
      _nomeController.text = widget.model!.nome;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de curso'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                model = Curso(nome: _nomeController.text);
                _datasource.insert(model);
              } else {
                model = Curso(
                  id: widget.model!.id,
                  nome: _nomeController.text,
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
          CampoTexto(controller: _nomeController, texto: 'Nome'),
        ],
      ),
    );
  }
}
