import 'package:flutter/material.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

import '../../../datasources/datasources.dart';

class TurmaListTile extends StatefulWidget {
  Turma model;
  TurmaListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<TurmaListTile> createState() => _TurmaListTileState();
}

class _TurmaListTileState extends State<TurmaListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: CustomDismissible(
        onDelete: () {
          TurmaDatasource(Turma.model()).delete(widget.model);
        },
        onEdit: () {
          _editar(context);
        },
        child: GestureDetector(
          child: Card(
            elevation: 5,
            child: Column(
              children: [
                Row(
                  children: [
                    Flexible(
                      child: CampoTexto(
                        controller: TextEditingController(text: widget.model.id.toString()),
                        texto: 'ID',
                        enabled: false,
                      ),
                    ),
                    Flexible(
                      flex: 3,
                      child: CampoTexto(
                        controller: TextEditingController(text: widget.model.curso.nome),
                        texto: 'Curso',
                        enabled: false,
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Flexible(
                      child: CampoTexto(
                        controller: TextEditingController(text: widget.model.alunos.length.toString()),
                        texto: 'Qtd. Alunos',
                        enabled: false,
                      ),
                    ),
                    Flexible(
                      child: CampoTexto(
                        controller: TextEditingController(text: widget.model.diciplinas.length.toString()),
                        texto: 'Qtd. Diciplinas',
                        enabled: false,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          onTap: () async {
            await _editar(context);
          },
        ),
      ),
    );
  }

  Future<void> _editar(BuildContext context) async {
    Turma? turma = await Navigator.push(context, MaterialPageRoute(builder: (context) => TurmaFormPage(model: widget.model)));
    if (turma != null) {
      widget.model = turma;
    }
    setState(() {});
  }
}
