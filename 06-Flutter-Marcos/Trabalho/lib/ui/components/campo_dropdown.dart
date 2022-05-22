import 'package:dropdown_search/dropdown_search.dart';
import 'package:flutter/material.dart';
import 'package:trabalho/models/models.dart';

class CampoDropdown<T extends Elemento> extends StatelessWidget {
  final String titulo;
  final String descricao;
  final Future<List<Elemento>> Function()? getAll;
  final Future<List<Elemento>>? listItens;
  final String Function(T?) itemAsString;
  final void Function(T?)? onChanged;
  final T? selecionado;
  final bool enabled;

  const CampoDropdown({
    Key? key,
    required this.titulo,
    required this.descricao,
    required this.itemAsString,
    this.getAll,
    this.listItens,
    this.onChanged,
    this.selecionado,
    this.enabled = true,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10),
      child: FutureBuilder(
        future: listItens ?? getAll!(),
        builder: (context, AsyncSnapshot<List<Elemento>> snapshot) {
          if (snapshot.hasData) {
            List<T> elementos = [];
            snapshot.data?.forEach((elemento) => elementos.add(elemento as T));
            return DropdownSearch<T>(
              showClearButton: true,
              dropdownBuilderSupportsNullItem: true,
              mode: Mode.MENU,
              enabled: enabled,
              items: elementos,
              itemAsString: itemAsString,
              onChanged: onChanged,
              selectedItem: selecionado,
              dropdownButtonProps: IconButtonProps(
                icon: enabled ? const Icon(Icons.arrow_drop_down) : const Icon(null),
              ),
              dropdownSearchDecoration: InputDecoration(
                labelText: titulo,
                hintText: descricao,
                border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
              ),
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
