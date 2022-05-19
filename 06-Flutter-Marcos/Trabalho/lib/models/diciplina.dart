import 'dart:convert';

import 'package:trabalho/models/models.dart';

class Diciplina extends Elemento {
  late String nome;
  late Professor professor;

  Diciplina.model() : super();

  Diciplina({
    id,
    required this.nome,
    required this.professor,
  }) : super(id: id);

  @override
  Map<String, Object?> toMap() {
    return {
      'id': id,
      'nome': nome,
      'professor': json.encode(professor.toMap()),
    };
  }

  @override
  Elemento fromMap(Map<String, dynamic> map) {
    Elemento model = Diciplina(
      nome: map['nome'] as String,
      professor: Professor.model().fromMap(json.decode(map['professor'])) as Professor,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
