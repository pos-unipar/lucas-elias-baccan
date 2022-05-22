import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class CursoListTile extends StatefulWidget {
  Curso model;
  CursoListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<CursoListTile> createState() => _CursoListTileState();
}

class _CursoListTileState extends State<CursoListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: CustomDismissible(
        onDelete: () {
          CursoDatasource(Curso.model()).delete(widget.model);
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
                    Flexible(flex: 5, child: CampoTexto(controller: TextEditingController(text: widget.model.nome), texto: 'Nome', enabled: false)),
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
    Curso? curso = await Navigator.push(context, MaterialPageRoute(builder: (context) => CursoFormPage(model: widget.model)));
    if (curso != null) {
      widget.model = curso;
    }
    setState(() {});
  }
}
