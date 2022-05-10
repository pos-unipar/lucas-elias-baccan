import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class ProfessorFormPage extends StatefulWidget {
  final Professor? model;
  const ProfessorFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<ProfessorFormPage> createState() => _ProfessorFormPageState();
}

class _ProfessorFormPageState extends State<ProfessorFormPage> {
  final ProfessorDatasource _datasource = ProfessorDatasource(Professor.model());
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
        title: const Text('Cadastro de professor'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                model = Professor(nome: _nomeController.text);
                _datasource.insert(model);
              } else {
                model = Professor(
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
