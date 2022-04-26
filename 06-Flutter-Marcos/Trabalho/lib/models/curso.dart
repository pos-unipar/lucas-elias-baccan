import 'package:trabalho/models/elemento.dart';

class Curso extends Elemento {
  String nome;

  Curso({
    required String id,
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
  Elemento fromMap(Map<String, Object?> map) {
    return Curso(
      id: map['id'] as String,
      nome: map['nome'] as String,
    );
  }
}
