// import 'package:trabalho/models/models.dart';

// class Turma extends Elemento {
//   late Curso curso;
//   late List<Aluno> alunos;
//   late List<Diciplina> diciplinas;

//   Turma.model() : super();

//   Turma({
//     id,
//     required this.curso,
//     required this.alunos,
//     required this.diciplinas,
//   });

//   @override
//   Elemento fromMap(Map<String, dynamic> map) {
//     Turma model = Turma(
//       curso: map['curso'] as Curso,
//       alunos: List<Aluno>.from(map['alunos'] as Iterable<dynamic>),
//       diciplinas: List<Diciplina>.from(map['diciplinas'] as Iterable<dynamic>),
//     );
//     model.id = map['id'] as int?;
//     return model;
//   }

//   @override
//   Map<String, dynamic> toMap() {}
// }
