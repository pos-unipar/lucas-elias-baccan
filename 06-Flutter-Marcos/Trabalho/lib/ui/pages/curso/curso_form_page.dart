import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class CursoFormPage extends StatefulWidget {
  final Curso? curso;
  const CursoFormPage({Key? key, this.curso}) : super(key: key);

  @override
  State<CursoFormPage> createState() => _CursoFormPageState();
}

class _CursoFormPageState extends State<CursoFormPage> {
  final CursoDatasource _datasource = CursoDatasource();
  final TextEditingController _nomeController = TextEditingController();

  @override
  void initState() {
    super.initState();
    if (widget.curso != null) {
      _nomeController.text = widget.curso!.nome;
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
              Curso curso;
              if (widget.curso == null) {
                curso = Curso(nome: _nomeController.text);
                _datasource.insert(curso);
              } else {
                curso = Curso(
                  id: widget.curso!.id,
                  nome: _nomeController.text,
                );
                _datasource.update(curso);
              }
              Navigator.pop(context, curso);
            },
          ),
        ],
      ),
      body: ListView(
        children: [CampoTexto(controller: _nomeController, texto: 'Nome')],
      ),
    );
  }
}
