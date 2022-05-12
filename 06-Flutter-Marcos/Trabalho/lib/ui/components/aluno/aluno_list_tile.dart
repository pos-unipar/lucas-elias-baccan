import 'package:flutter/material.dart';
import 'package:trabalho/datasources/aluno_datasource.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class AlunoListTile extends StatefulWidget {
  Aluno model;
  AlunoListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<AlunoListTile> createState() => _AlunoListTileState();
}

class _AlunoListTileState extends State<AlunoListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: CustomDismissible(
        onDelete: () {
          AlunoDatasource(Aluno.model()).delete(widget.model);
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
                    Flexible(flex: 3, child: CampoTexto(controller: TextEditingController(text: widget.model.nome), texto: 'Nome', enabled: false)),
                  ],
                ),
                Row(
                  children: [
                    Flexible(child: CampoTexto(controller: TextEditingController(text: widget.model.ra.toString()), texto: 'RA', enabled: false)),
                    Flexible(flex: 3, child: CampoTexto(controller: TextEditingController(text: widget.model.email), texto: 'Email', enabled: false)),
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
    Aluno? curso = await Navigator.push(context, MaterialPageRoute(builder: (context) => AlunoFormPage(model: widget.model)));
    if (curso != null) {
      widget.model = curso;
    }
    setState(() {});
  }
}
