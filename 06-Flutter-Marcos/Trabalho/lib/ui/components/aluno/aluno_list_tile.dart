import 'package:flutter/material.dart';
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
      child: GestureDetector(
        child: Card(
          child: Row(
            children: [
              Flexible(child: CampoTexto(controller: TextEditingController(text: widget.model.id.toString()), texto: 'ID', enabled: false)),
              Flexible(flex: 5, child: CampoTexto(controller: TextEditingController(text: widget.model.nome), texto: 'Nome', enabled: false)),
            ],
          ),
        ),
        onTap: () async {
          Aluno? curso = await Navigator.push(context, MaterialPageRoute(builder: (context) => AlunoFormPage(model: widget.model)));
          if (curso != null) {
            setState(() {
              widget.model = curso;
            });
          }
        },
      ),
    );
  }
}
