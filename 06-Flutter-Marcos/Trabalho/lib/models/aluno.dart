import 'package:trabalho/models/elemento.dart';

class Aluno extends Elemento {
  late String nome;
  late int ra;
  late String email;

  Aluno.model() : super();

  Aluno({
    id,
    required this.nome,
    required this.ra,
    required this.email,
  }) : super(id: id);

  @override
  Map<String, Object?> toMap() {
    return {
      'id': id,
      'nome': nome,
      'ra': ra,
      'email': email,
    };
  }

  @override
  Elemento fromMap(Map<String, dynamic> map) {
    Elemento model = Aluno(
      nome: map['nome'] as String,
      ra: map['ra'] as int,
      email: map['email'] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
