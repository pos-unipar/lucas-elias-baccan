import 'package:flutter/material.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class CursoListTile extends StatefulWidget {
  Curso curso;
  CursoListTile({Key? key, required this.curso}) : super(key: key);

  @override
  State<CursoListTile> createState() => _CursoListTileState();
}

class _CursoListTileState extends State<CursoListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: GestureDetector(
        child: Card(
          child: Row(
            children: [
              Flexible(child: CampoTexto(controller: TextEditingController(text: widget.curso.id.toString()), texto: 'ID', enabled: false)),
              Flexible(flex: 5, child: CampoTexto(controller: TextEditingController(text: widget.curso.nome), texto: 'Nome', enabled: false)),
            ],
          ),
        ),
        onTap: () async {
          Curso? curso = await Navigator.push(context, MaterialPageRoute(builder: (context) => CursoFormPage(curso: widget.curso)));
          if (curso != null) {
            setState(() {
              widget.curso = curso;
            });
          }
        },
      ),
    );
  }
}
