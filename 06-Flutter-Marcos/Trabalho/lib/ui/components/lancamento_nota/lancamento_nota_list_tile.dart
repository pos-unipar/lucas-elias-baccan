import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class LancamentoNotaListTile extends StatefulWidget {
  LancamentoNota model;
  LancamentoNotaListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<LancamentoNotaListTile> createState() => _LancamentoNotaListTileState();
}

class _LancamentoNotaListTileState extends State<LancamentoNotaListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: CustomDismissible(
        onDelete: () {
          LancamentoNotaDatasource(LancamentoNota.model()).delete(widget.model);
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
                    Flexible(child: CampoTexto(controller: TextEditingController(text: widget.model.id.toString()), texto: 'ID', enabled: false)),
                    Flexible(
                      flex: 2,
                      child: CampoTexto(controller: TextEditingController(text: widget.model.turma.toString()), texto: 'Turma', enabled: false),
                    ),
                    Flexible(
                      flex: 2,
                      child:
                          CampoTexto(controller: TextEditingController(text: widget.model.diciplina.toString()), texto: 'Disciplina', enabled: false),
                    ),
                  ],
                ),
                CampoTexto(controller: TextEditingController(text: widget.model.aluno.nome), texto: 'Aluno', enabled: false),
                Row(
                  children: [
                    Flexible(
                      child: CampoTexto(controller: TextEditingController(text: widget.model.nota1.toString()), texto: "Nota 1", enabled: false),
                    ),
                    Flexible(
                      child: CampoTexto(controller: TextEditingController(text: widget.model.nota2.toString()), texto: "Nota 2", enabled: false),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Flexible(
                      child: CampoTexto(controller: TextEditingController(text: widget.model.nota3.toString()), texto: "Nota 3", enabled: false),
                    ),
                    Flexible(
                      child: CampoTexto(controller: TextEditingController(text: widget.model.nota4.toString()), texto: "Nota 4", enabled: false),
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
    LancamentoNota? curso = await Navigator.push(context, MaterialPageRoute(builder: (context) => LancamentoNotaFormPage(model: widget.model)));
    if (curso != null) {
      widget.model = curso;
    }
    setState(() {});
  }
}
