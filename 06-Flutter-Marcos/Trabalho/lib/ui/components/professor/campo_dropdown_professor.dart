import 'package:dropdown_search/dropdown_search.dart';
import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class CampoDropdownProfessor extends StatelessWidget {
  final Function(Professor professor) onChanged;
  final Professor? selecionado;

  const CampoDropdownProfessor({
    Key? key,
    required this.onChanged,
    this.selecionado,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10),
      child: FutureBuilder(
        future: ProfessorDatasource(Professor.model()).getAll(),
        builder: (context, AsyncSnapshot<List<Elemento>> snapshot) {
          if (snapshot.hasData) {
            List<Professor> professores = [];
            snapshot.data?.forEach((elemento) => professores.add(elemento as Professor));
            return DropdownSearch<Professor>(
              items: professores,
              itemAsString: (Professor? value) => value!.nome,
              mode: Mode.MENU,
              onChanged: (Professor? professor) => onChanged(professor!),
              selectedItem: selecionado,
            );
          } else {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
        },
      ),
    );
  }
}
