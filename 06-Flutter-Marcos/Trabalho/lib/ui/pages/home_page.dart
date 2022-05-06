import 'package:flutter/material.dart';
import 'package:trabalho/ui/components/components.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Controle escolar'),
        centerTitle: true,
      ),
      body: const HomePageBody(),
    );
  }
}
