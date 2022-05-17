import 'package:flutter/material.dart';
import 'package:trabalho_falta/model/emissora.dart';

class EmissoraDetalhe extends StatelessWidget {
  final Emissora emissora;

  const EmissoraDetalhe({Key? key, required this.emissora}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(emissora.nome),
      ),
      body: Center(
        child: ListView(
          shrinkWrap: true,
          children: <Widget>[
            Hero(
              tag: emissora.logo,
              child: AspectRatio(
                aspectRatio: 1,
                child: Image.network(emissora.logo),
              ),
            ),
            Center(
              child: Text(
                "Você está assistindo a emissora ${emissora.nome}",
                style: const TextStyle(fontSize: 20),
              ),
            ),
            Center(
              child: Text(
                "Seu código é ${emissora.codigo}",
                style: const TextStyle(fontSize: 20),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
