import 'package:trabalho/models/elemento.dart';

class Curso extends Elemento {
  late String nome;

  Curso.model() : super();

  Curso({
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
    Elemento model = Curso(
      nome: map['nome'] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
