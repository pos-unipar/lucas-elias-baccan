import 'package:flutter/material.dart';

import 'components.dart';

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
        child: const Center(
          child: ListTile(
            trailing: Icon(Icons.delete),
            title: Text('Excluir', textAlign: TextAlign.end),
          ),
        ),
      ),
      background: Container(
        color: Colors.green,
        child: const Center(
          child: ListTile(
            leading: Icon(Icons.edit),
            title: Text('Editar', textAlign: TextAlign.start),
          ),
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
            builder: (context) => const ExcluirDialog(),
          );
        }
        return true;
      },
      child: child,
    );
  }
}
