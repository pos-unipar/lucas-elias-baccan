import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class TurmaGerenciasDiciplina extends StatefulWidget {
  final Turma turma;
  const TurmaGerenciasDiciplina({
    Key? key,
    required this.turma,
  }) : super(key: key);

  @override
  State<TurmaGerenciasDiciplina> createState() => _TurmaGerenciasDiciplinaState();
}

class _TurmaGerenciasDiciplinaState extends State<TurmaGerenciasDiciplina> {
  final TurmaDatasource _datasource = TurmaDatasource(Turma.model());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerenciar dicipina'),
      ),
      body: Column(
        children: [
          CampoDropdown<Diciplina>(
            titulo: "Disciplina",
            descricao: "Selecione a dicipina",
            getAll: DiciplinaDatasource(Diciplina.model()).getAll,
            itemAsString: itemAsString,
            onChanged: onSelected,
            // selecionado: widget.model?.professor,
          ),
          const Divider(thickness: 2),
          SingleChildScrollView(
            child: Column(
              children: widget.turma.diciplinas.map((diciplina) => itemLista(diciplina)).toList(),
            ),
          )
        ],
      ),
    );
  }

  String itemAsString(Diciplina? diciplina) {
    return diciplina.toString();
  }

  Widget itemLista(Diciplina diciplina) {
    return ListTile(
      title: Text(diciplina.toString()),
      trailing: IconButton(
        icon: const Icon(Icons.delete),
        onPressed: () {
          widget.turma.alunos.remove(diciplina);
          _datasource.update(widget.turma);
          setState(() {});
        },
      ),
    );
  }

  void onSelected(Diciplina? diciplina) {
    if (diciplina != null && !widget.turma.diciplinas.contains(diciplina)) {
      widget.turma.diciplinas.add(diciplina);
      _datasource.update(widget.turma);
      setState(() {});
    }
  }
}
