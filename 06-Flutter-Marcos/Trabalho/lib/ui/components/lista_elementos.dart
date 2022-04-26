import 'package:flutter/material.dart';

class ListaElementos extends StatelessWidget {
  final String titulo;
  final List<Widget> elementos;
  final Function() novoElemento;

  const ListaElementos({
    Key? key,
    required this.titulo,
    required this.elementos,
    required this.novoElemento,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Controle Escolar'),
        centerTitle: true,
        actions: [
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: novoElemento,
          ),
        ],
      ),
      body: ListView.builder(
        itemCount: elementos.length,
        itemBuilder: (context, index) => elementos[index],
      ),
    );
  }
}
