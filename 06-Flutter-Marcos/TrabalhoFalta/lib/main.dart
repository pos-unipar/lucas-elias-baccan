import 'package:flutter/material.dart';
import 'package:trabalho_falta/ui/ui.dart';

void main() {
  runApp(
    MaterialApp(
      title: 'Trabalho Falta',
      theme: ThemeData(
        primarySwatch: Colors.amber,
      ),
      home: const HomePage(),
    ),
  );
}
