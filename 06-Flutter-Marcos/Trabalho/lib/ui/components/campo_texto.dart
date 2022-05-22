import 'package:flutter/material.dart';

class CampoTexto extends StatelessWidget {
  final TextEditingController controller;
  final String texto;
  final TextInputType? teclado;
  final bool enabled;

  const CampoTexto({required this.controller, required this.texto, this.teclado, this.enabled = true, Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10),
      child: TextField(
        enabled: enabled,
        controller: controller,
        keyboardType: teclado ?? TextInputType.text,
        decoration: InputDecoration(
          isDense: true,
          labelText: texto,
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
          ),
          contentPadding: const EdgeInsets.only(left: 10, top: 10, bottom: 10),
        ),
      ),
    );
  }
}
