import 'package:trabalho/models/elemento.dart';

class Aluno extends Elemento {
  late String nome;

  Aluno.model() : super();

  Aluno({id, required this.nome}) : super(id: id);

  @override
  Map<String, Object?> toMap() {
    return {
      'id': id,
      'nome': nome,
    };
  }

  @override
  Elemento fromMap(Map<String, dynamic> map) {
    var curso = Aluno(
      nome: map['nome'] as String,
    );
    curso.id = map['id'] as int?;
    return curso;
  }
}
