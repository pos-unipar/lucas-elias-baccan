import 'package:flutter/material.dart';

class HomePageButtom extends StatelessWidget {
  final String texto;
  final IconData icon;
  final Function() onPressed;

  const HomePageButtom({
    Key? key,
    required this.texto,
    required this.icon,
    required this.onPressed,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onPressed,
      child: Row(
        children: [
          Icon(icon),
          const SizedBox(width: 8),
          const Spacer(),
          Text(texto),
          const Spacer()
        ],
      ),
    );
  }
}
