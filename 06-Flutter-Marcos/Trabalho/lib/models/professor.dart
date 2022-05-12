import 'package:trabalho/models/elemento.dart';

class Professor extends Elemento {
  late String nome;

  Professor.model() : super();

  Professor({
    id,
    required this.nome,
  }) : super(id: id);

  @override
  Map<String, Object?> toMap() {
    return {
      'id': id,
      'nome': nome,
    };
  }

  @override
  Elemento fromMap(Map<String, dynamic> map) {
    Elemento model = Professor(
      nome: map['nome'] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
