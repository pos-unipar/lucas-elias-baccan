import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class TurmaListaPage extends StatefulWidget {
  const TurmaListaPage({Key? key}) : super(key: key);

  @override
  State<TurmaListaPage> createState() => _TurmaListaPageState();
}

class _TurmaListaPageState extends State<TurmaListaPage> {
  final TurmaDatasource _datasource = TurmaDatasource(Turma.model());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerenciar turma'),
        actions: [
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () async {
              await Navigator.push(context, MaterialPageRoute(builder: (context) => const TurmaFormPage()));
              setState(() {});
            },
          ),
        ],
      ),
      body: FutureBuilder(
        future: _datasource.getAll(completo: true),
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
                  Turma curso = snapshot.data![index] as Turma;
                  return TurmaListTile(model: curso);
                },
              );
          }
        },
      ),
    );
  }
}
