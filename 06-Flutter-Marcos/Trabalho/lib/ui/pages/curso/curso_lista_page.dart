import 'package:flutter/material.dart';
import 'package:trabalho/ui/components/lista_elementos.dart';
import 'package:trabalho/ui/pages/pages.dart';

class CursoListaPage extends StatelessWidget {
  const CursoListaPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListaElementos(
      titulo: 'Cursos',
      elementos: const [
        ListTile(
          title: Text('Curso 1'),
          subtitle: Text('Descrição do curso 1'),
          trailing: Icon(Icons.arrow_forward_ios),
        ),
        ListTile(
          title: Text('Curso 2'),
          subtitle: Text('Descrição do curso 2'),
          trailing: Icon(Icons.arrow_forward_ios),
        ),
        ListTile(
          title: Text('Curso 3'),
          subtitle: Text('Descrição do curso 3'),
          trailing: Icon(Icons.arrow_forward_ios),
        ),
      ],
      novoElemento: () {
        Navigator.push(context, MaterialPageRoute(builder: (context) => const CursoFormPage()));
      },
    );
  }
}
