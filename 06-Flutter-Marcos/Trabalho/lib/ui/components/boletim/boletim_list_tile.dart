import 'package:flutter/material.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class BoletimListTile extends StatefulWidget {
  Boletim model;
  BoletimListTile({Key? key, required this.model}) : super(key: key);

  @override
  State<BoletimListTile> createState() => _BoletimListTileState();
}

class _BoletimListTileState extends State<BoletimListTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        elevation: 5,
        child: Column(
          children: [
            Row(
              children: [
                Flexible(
                  child: CampoTexto(
                    controller: TextEditingController(text: widget.model.media.toString()),
                    texto: 'Média',
                    enabled: false,
                  ),
                ),
                Flexible(
                  child: CampoTexto(
                    controller: TextEditingController(text: "${widget.model.faltas.toString()}/${widget.model.presencas}"),
                    texto: 'Faltas/Presença',
                    enabled: false,
                  ),
                ),
                Flexible(
                  child: CampoTexto(
                    controller: TextEditingController(text: widget.model.aprovado() ? "Sim" : "Não"),
                    texto: 'Aprovado?',
                    enabled: false,
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
