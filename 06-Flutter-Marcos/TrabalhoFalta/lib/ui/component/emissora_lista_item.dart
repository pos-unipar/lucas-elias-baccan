import 'package:flutter/material.dart';
import 'package:trabalho_falta/model/model.dart';
import 'package:trabalho_falta/ui/ui.dart';

class EmissoraListaItem extends StatelessWidget {
  final Emissora emissora;
  const EmissoraListaItem({
    Key? key,
    required this.emissora,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Hero(
        tag: emissora.logo,
        child: CircleAvatar(
          backgroundImage: NetworkImage(emissora.logo),
        ),
      ),
      title: Text(emissora.nome),
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) {
            return EmissoraDetalhe(emissora: emissora);
          }),
        );
      },
    );
  }
}
