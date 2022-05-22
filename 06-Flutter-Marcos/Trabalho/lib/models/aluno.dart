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
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      'nome': nome,
      'ra': ra,
      'email': email,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = Aluno(
      nome: map['nome'] as String,
      ra: map['ra'] as int,
      email: map['email'] as String,
    );
    model.id = map['id'] as int?;
    return model;
  }

  @override
  String toString() {
    return "$id - $nome";
  }

  @override
  operator ==(other) => other is Aluno && id == other.id;

  @override
  int get hashCode => id.hashCode;
}
