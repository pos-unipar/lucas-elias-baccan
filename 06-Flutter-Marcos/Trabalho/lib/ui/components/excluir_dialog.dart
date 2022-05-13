import 'package:flutter/material.dart';

class ExcluirDialog extends StatelessWidget {
  const ExcluirDialog({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text('Deseja realmente excluir?'),
      actions: [
        OutlinedButton(
          onPressed: () => Navigator.pop(context, false),
          child: const Text('Não'),
        ),
        ElevatedButton(
          onPressed: () => Navigator.pop(context, true),
          child: const Text('Sim'),
        ),
      ],
    );
  }
}
