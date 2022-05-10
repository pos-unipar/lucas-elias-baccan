import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class ProfessorListaPage extends StatefulWidget {
  const ProfessorListaPage({Key? key}) : super(key: key);

  @override
  State<ProfessorListaPage> createState() => _ProfessorListaPageState();
}

class _ProfessorListaPageState extends State<ProfessorListaPage> {
  final ProfessorDatasource _datasource = ProfessorDatasource(Professor.model());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerenciar professores'),
        actions: [
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () async {
              await Navigator.push(context, MaterialPageRoute(builder: (context) => const ProfessorFormPage()));
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
                  Professor curso = snapshot.data![index] as Professor;
                  return ProfessorListTile(model: curso);
                },
              );
          }
        },
      ),
    );
  }
}
