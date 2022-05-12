import 'package:flutter/material.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class DiciplinaListTile extends StatefulWidget {
  Diciplina model;
  DiciplinaListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<DiciplinaListTile> createState() => _DiciplinaListTileState();
}

class _DiciplinaListTileState extends State<DiciplinaListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
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
          Diciplina? curso = await Navigator.push(context, MaterialPageRoute(builder: (context) => DiciplinaFormPage(model: widget.model)));
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
