import 'package:flutter/material.dart';

class CustomDismissible extends StatelessWidget {
  final Widget child;
  final Function() onDelete;
  final Function() onEdit;

  const CustomDismissible({
    Key? key,
    required this.child,
    required this.onDelete,
    required this.onEdit,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Dismissible(
      key: UniqueKey(),
      direction: DismissDirection.horizontal,
      secondaryBackground: Container(
        color: Colors.red,
        child: const Align(
          alignment: Alignment.centerRight,
          child: Icon(Icons.delete, color: Colors.white, size: 40),
        ),
      ),
      background: Container(
        color: Colors.green,
        child: const Align(
          alignment: Alignment.centerLeft,
          child: Icon(Icons.check, color: Colors.white, size: 40),
        ),
      ),
      onDismissed: (DismissDirection direction) async {
        if (direction == DismissDirection.startToEnd) {
          await onEdit();
        } else if (direction == DismissDirection.endToStart) {
          onDelete();
        }
      },
      confirmDismiss: (DismissDirection direction) async {
        if (direction == DismissDirection.endToStart) {
          return await showDialog(
            context: context,
            builder: (context) => AlertDialog(
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
            ),
          );
        }
        return true;
      },
      child: child,
    );
  }
}
