import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class TurmaFormPage extends StatefulWidget {
  final Turma? model;
  const TurmaFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<TurmaFormPage> createState() => _TurmaFormPageState();
}

class _TurmaFormPageState extends State<TurmaFormPage> {
  final TurmaDatasource _datasource = TurmaDatasource(Turma.model());

  Curso? cursoSelecionado;

  @override
  void initState() {
    super.initState();
    if (widget.model != null) {
      cursoSelecionado = widget.model!.curso;
      // _nomeController.text = widget.model!.nome;
      // _raController.text = widget.model!.ra.toString();
      // _emailController.text = widget.model!.email;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de turma'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                model = Turma(
                  curso: cursoSelecionado!,
                );
                _datasource.insert(model);
              } else {
                model = Turma(
                  id: widget.model!.id,
                  curso: cursoSelecionado!,
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
          CampoDropdown<Curso>(
            titulo: "Curso",
            descricao: "Selecione o curso",
            getAll: CursoDatasource(Curso.model()).getAll,
            itemAsString: itemAsString,
            onChanged: onCursoSelected,
            selecionado: widget.model?.curso,
          ),
          Row(
            children: [
              Expanded(
                child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 5),
                  child: ElevatedButton(
                      onPressed: () {
                        Navigator.push(context, MaterialPageRoute(builder: (context) => TurmaGerenciasAlunos(turma: widget.model!)));
                      },
                      child: const Text("Gerenciar alunos")),
                ),
              ),
              Expanded(
                child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 5),
                  child: ElevatedButton(
                      onPressed: () {
                        Navigator.push(context, MaterialPageRoute(builder: (context) => TurmaGerenciasDiciplina(turma: widget.model!)));
                      },
                      child: const Text("Gerenciar disciplinas")),
                ),
              ),
            ],
          )
        ],
      ),
    );
  }

  String itemAsString(Curso? curso) {
    return curso!.toString();
  }

  void onCursoSelected(Curso? curso) {
    cursoSelecionado = curso;
  }
}
