import 'package:flutter/material.dart';
import 'package:trabalho/models/models.dart';

class FormElementos extends StatelessWidget {
  final String titulo;
  final List<Widget> elementos;
  final Elemento elemento;

  const FormElementos({
    Key? key,
    required this.titulo,
    required this.elementos,
    required this.elemento,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(titulo),
        centerTitle: true,
      ),
      body: ListView.builder(
        itemCount: elementos.length,
        itemBuilder: (context, index) => elementos[index],
      ),
    );
  }
}
