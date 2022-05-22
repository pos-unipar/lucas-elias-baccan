import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class LancamentoPresencaListTile extends StatefulWidget {
  LancamentoPresenca model;
  LancamentoPresencaListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<LancamentoPresencaListTile> createState() => _LancamentoPresencaListTileState();
}

class _LancamentoPresencaListTileState extends State<LancamentoPresencaListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: CustomDismissible(
        onDelete: () {
          LancamentoPresencaDatasource(LancamentoPresenca.model()).delete(widget.model);
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
                Row(
                  children: [
                    Flexible(
                      flex: 5,
                      child: CampoTexto(controller: TextEditingController(text: widget.model.aluno.nome), texto: 'Aluno', enabled: false),
                    ),
                    Flexible(child: Checkbox(value: widget.model.presenca, onChanged: null)),
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
    LancamentoPresenca? curso =
        await Navigator.push(context, MaterialPageRoute(builder: (context) => LancamentoPresencaFormPage(model: widget.model)));
    if (curso != null) {
      widget.model = curso;
    }
    setState(() {});
  }
}
