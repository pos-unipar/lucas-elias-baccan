import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/extensions/extensions.dart';

class Diciplina extends Elemento {
  late String nome;
  late Professor professor;

  Diciplina.model() : super();

  Diciplina({
    id,
    required this.nome,
    required this.professor,
  }) : super(id: id);

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      DiciplinaDatasource.columnNome: nome,
      DiciplinaDatasource.columnProfessor: professor.id,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = Diciplina(
      nome: map[DiciplinaDatasource.columnNome] as String,
      professor: await ProfessorDatasource(Professor.model()).find(map[DiciplinaDatasource.columnProfessor]) as Professor,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
