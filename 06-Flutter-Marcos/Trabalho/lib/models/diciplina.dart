import 'package:trabalho/models/elemento.dart';

class Diciplina extends Elemento {
  late String nome;

  Diciplina.model() : super();

  Diciplina({id, required this.nome}) : super(id: id);

  @override
  Map<String, Object?> toMap() {
    return {
      'id': id,
      'nome': nome,
    };
  }

  @override
  Elemento fromMap(Map<String, dynamic> map) {
    var curso = Diciplina(
      nome: map['nome'] as String,
    );
    curso.id = map['id'] as int?;
    return curso;
  }
}
