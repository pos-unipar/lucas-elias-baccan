import 'package:trabalho/models/elemento.dart';

class Curso extends Elemento {
  late String nome;

  Curso.model() : super();

  Curso({
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
    Elemento model = Curso(
      nome: map['nome'] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }

  @override
  String toString() {
    return "$id - $nome";
  }

  @override
  operator ==(other) => other is Curso && id == other.id;

  @override
  int get hashCode => id.hashCode;
}
