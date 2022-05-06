import 'package:trabalho/models/elemento.dart';

class Curso extends Elemento {
  final String nome;

  Curso({
    int? id,
    required this.nome,
  }) : super(id: id);

  factory Curso.fromMap(Map<String, Object?> map) {
    return Curso(
      id: map['id'] as int,
      nome: map['nome'] as String,
    );
  }

  @override
  Map<String, Object?> toMap() {
    return {
      'id': id,
      'nome': nome,
    };
  }
}
