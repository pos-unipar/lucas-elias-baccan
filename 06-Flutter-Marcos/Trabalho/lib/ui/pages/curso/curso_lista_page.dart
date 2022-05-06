import 'package:flutter/material.dart';
import 'package:trabalho/datasources/curso_datasource.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class CursoListaPage extends StatefulWidget {
  const CursoListaPage({Key? key}) : super(key: key);

  @override
  State<CursoListaPage> createState() => _CursoListaPageState();
}

class _CursoListaPageState extends State<CursoListaPage> {
  final CursoDatasource _datasource = CursoDatasource();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerenciar cursos'),
        actions: [
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () async {
              await Navigator.push(context, MaterialPageRoute(builder: (context) => const CursoFormPage()));
              setState(() {});
            },
          ),
        ],
      ),
      body: FutureBuilder(
        future: _datasource.getAll(),
        builder: (context, AsyncSnapshot<List<Elemento>> snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.none:
            case ConnectionState.waiting:
              return const Center(child: CircularProgressIndicator());
            default:
              if (snapshot.hasError) {
                return const Center(child: Text('Erro ao carregar dados'));
              }
              return ListView.builder(
                itemCount: snapshot.data!.length,
                itemBuilder: (context, index) {
                  Curso curso = snapshot.data![index] as Curso;
                  return CursoListTile(curso: curso);
                },
              );
          }
        },
      ),
    );
  }
}
