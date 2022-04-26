import 'package:flutter/material.dart';

import '../components.dart';

class HomePageBody extends StatelessWidget {
  HomePageBody({Key? key}) : super(key: key);

  final List<Widget> _listaBotoes = [
    // Cursos
    HomePageButtom(
      texto: 'Cursos',
      icon: Icons.book,
      onPressed: () {},
    ),
    // Alunos
    HomePageButtom(
      texto: 'Alunos',
      icon: Icons.people,
      onPressed: () {},
    ),
    // Professores
    HomePageButtom(
      texto: 'Professores',
      icon: Icons.person,
      onPressed: () {},
    ),
    // Disciplinas
    HomePageButtom(
      texto: 'Disciplinas',
      icon: Icons.bookmark,
      onPressed: () {},
    ),
    // Turmas
    HomePageButtom(
      texto: 'Turmas',
      icon: Icons.co_present_sharp,
      onPressed: () {},
    ),
    // Frequências
    HomePageButtom(
      texto: 'Frequências',
      icon: Icons.assignment,
      onPressed: () {},
    ),
    // Notas
    HomePageButtom(
      texto: 'Notas',
      icon: Icons.assignment_turned_in,
      onPressed: () {},
    ),
    // Boletim
    HomePageButtom(
      texto: 'Boletim',
      icon: Icons.assignment_ind,
      onPressed: () {},
    ),
    // Limpar banco de dados
    HomePageButtom(
      texto: 'Limpar banco de dados',
      icon: Icons.delete,
      onPressed: () {},
    ),
    // Gerar dados falsos
    HomePageButtom(
      texto: 'Gerar dados falsos',
      icon: Icons.change_circle,
      onPressed: () {},
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Center(
      child: IntrinsicWidth(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            ..._listaBotoes,
          ],
        ),
      ),
    );
  }
}
