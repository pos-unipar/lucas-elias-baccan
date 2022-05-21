import 'package:trabalho/models/elemento.dart';

class Professor extends Elemento {
  late String nome;

  Professor.model() : super();

  Professor({
    id,
    required this.nome,
  }) : super(id: id);

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      'nome': nome,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = Professor(
      nome: map['nome'] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
