import 'package:flutter/material.dart';

import '../components/components.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Controle Escolar'),
        centerTitle: true,
      ),
      body: HomePageBody(),
    );
  }
}
