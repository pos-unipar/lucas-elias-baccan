import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class Turma extends Elemento {
  late Curso curso;
  List<Aluno> _alunos = [];
  List<Diciplina> _diciplinas = [];

  Turma.model() : super();

  Turma({
    id,
    required this.curso,
  }) : super(id: id);

  List<Aluno> get alunos => _alunos;
  set alunos(List<Aluno> value) {
    _alunos = value;
  }

  List<Diciplina> get diciplinas => _diciplinas;
  set diciplinas(List<Diciplina> value) {
    _diciplinas = value;
  }

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      TurmaDatasource.columnCurso: curso.id,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = Turma(
      curso: await CursoDatasource(Curso.model()).find(map[TurmaDatasource.columnCurso]) as Curso,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
