import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class TurmaGerenciasAlunos extends StatefulWidget {
  final Turma turma;
  const TurmaGerenciasAlunos({
    Key? key,
    required this.turma,
  }) : super(key: key);

  @override
  State<TurmaGerenciasAlunos> createState() => _TurmaGerenciasAlunosState();
}

class _TurmaGerenciasAlunosState extends State<TurmaGerenciasAlunos> {
  final TurmaDatasource _datasource = TurmaDatasource(Turma.model());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerenciar alunos'),
      ),
      body: Column(
        children: [
          CampoDropdown<Aluno>(
            titulo: "Aluno",
            descricao: "Selecione o aluno",
            getAll: AlunoDatasource(Aluno.model()).getAll,
            itemAsString: itemAsString,
            onChanged: onSelected,
            // selecionado: widget.model?.professor,
          ),
          const Divider(thickness: 2),
          SingleChildScrollView(
            child: Column(
              children: widget.turma.alunos.map((aluno) => itemLista(aluno)).toList(),
            ),
          )
        ],
      ),
    );
  }

  String itemAsString(Aluno? aluno) {
    return aluno.toString();
  }

  Widget itemLista(Aluno aluno) {
    return ListTile(
      title: Text(aluno.toString()),
      trailing: IconButton(
        icon: const Icon(Icons.delete),
        onPressed: () {
          widget.turma.alunos.remove(aluno);
          _datasource.update(widget.turma);
          setState(() {});
        },
      ),
    );
  }

  void onSelected(Aluno? aluno) {
    if (aluno != null && !widget.turma.alunos.contains(aluno)) {
      widget.turma.alunos.add(aluno);
      _datasource.update(widget.turma);
      setState(() {});
    }
  }
}
