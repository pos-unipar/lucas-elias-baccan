import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';
import 'package:trabalho/ui/pages/pages.dart';

class LancamentoPresencaListaPage extends StatefulWidget {
  const LancamentoPresencaListaPage({Key? key}) : super(key: key);

  @override
  State<LancamentoPresencaListaPage> createState() => _LancamentoPresencaListaPageState();
}

class _LancamentoPresencaListaPageState extends State<LancamentoPresencaListaPage> {
  final LancamentoPresencaDatasource _datasource = LancamentoPresencaDatasource(LancamentoPresenca.model());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerenciar presenças'),
        actions: [
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () async {
              await Navigator.push(context, MaterialPageRoute(builder: (context) => const LancamentoPresencaFormPage()));
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
                  LancamentoPresenca curso = snapshot.data![index] as LancamentoPresenca;
                  return LancamentoPresencaListTile(model: curso);
                },
              );
          }
        },
      ),
    );
  }
}
