import 'package:flutter/material.dart';
import 'package:trabalho_falta/model/model.dart';
import 'package:trabalho_falta/service/service.dart';
import 'package:trabalho_falta/ui/ui.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Trabalho Falta'),
      ),
      body: Center(
        child: FutureBuilder(
          future: EmissoraService.getEmissoras(),
          builder: (BuildContext context, AsyncSnapshot<List<Emissora>> snapshot) {
            if (snapshot.hasData) {
              return ListView.builder(
                itemCount: snapshot.data!.length,
                itemBuilder: (BuildContext context, int index) {
                  Emissora emissora = snapshot.data![index];
                  return EmissoraListaItem(emissora: emissora);
                },
              );
            } else {
              return const CircularProgressIndicator();
            }
          },
        ),
      ),
    );
  }
}
