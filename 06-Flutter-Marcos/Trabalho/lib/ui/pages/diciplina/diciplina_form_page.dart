import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class DiciplinaFormPage extends StatefulWidget {
  final Diciplina? model;
  const DiciplinaFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<DiciplinaFormPage> createState() => _DiciplinaFormPageState();
}

class _DiciplinaFormPageState extends State<DiciplinaFormPage> {
  final DiciplinaDatasource _datasource = DiciplinaDatasource(Diciplina.model());
  final TextEditingController _nomeController = TextEditingController();
  late Professor professorSelecionado;

  @override
  void initState() {
    super.initState();
    if (widget.model != null) {
      _nomeController.text = widget.model!.nome;
      professorSelecionado = widget.model!.professor;
    }
  }

  void onProfessorSelected(Professor professor) {
    professorSelecionado = professor;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de disciplina'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                model = Diciplina(
                  nome: _nomeController.text,
                  professor: professorSelecionado,
                );
                _datasource.insert(model);
              } else {
                model = Diciplina(
                  id: widget.model!.id,
                  nome: _nomeController.text,
                  professor: professorSelecionado,
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
          CampoDropdownProfessor(
            onChanged: onProfessorSelected,
            selecionado: professorSelecionado,
          ),
        ],
      ),
    );
  }
}
