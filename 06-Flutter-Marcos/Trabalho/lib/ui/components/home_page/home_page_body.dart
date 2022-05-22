import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/helpers/faker.dart';
import 'package:trabalho/ui/components/components.dart';

import 'package:trabalho/ui/pages/pages.dart';

class HomePageBody extends StatelessWidget {
  const HomePageBody({Key? key}) : super(key: key);

  List<Widget> _listaBotoes(BuildContext context) {
    List<Widget> botoes = [];
    // Cursos
    botoes.add(
      HomePageButtom(
        texto: 'Cursos',
        icon: Icons.book,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const CursoListaPage()));
        },
      ),
    );
    // Alunos
    botoes.add(
      HomePageButtom(
        texto: 'Alunos',
        icon: Icons.people,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const AlunoListaPage()));
        },
      ),
    );
    // Professores
    botoes.add(
      HomePageButtom(
        texto: 'Professores',
        icon: Icons.person,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const ProfessorListaPage()));
        },
      ),
    );
    // Disciplinas
    botoes.add(
      HomePageButtom(
        texto: 'Disciplinas',
        icon: Icons.bookmark,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const DiciplinaListaPage()));
        },
      ),
    );
    // Turmas
    botoes.add(
      HomePageButtom(
        texto: 'Turmas',
        icon: Icons.co_present_sharp,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const TurmaListaPage()));
        },
      ),
    );
    // Frequências
    botoes.add(
      HomePageButtom(
        texto: 'Frequências',
        icon: Icons.assignment,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const LancamentoPresencaListaPage()));
        },
      ),
    );
    // Notas
    botoes.add(
      HomePageButtom(
        texto: 'Notas',
        icon: Icons.assignment_turned_in,
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const LancamentoNotaListaPage()));
        },
      ),
    );
    // Boletim
    botoes.add(
      HomePageButtom(
        texto: 'Boletim',
        icon: Icons.assignment_ind,
        onPressed: () {},
      ),
    );
    // Limpar banco de dados
    botoes.add(
      HomePageButtom(
        texto: 'Limpar banco de dados',
        icon: Icons.delete,
        onPressed: () async {
          // show snackbar
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(
              content: Text('Limpando banco de dados...'),
              duration: Duration(seconds: 1),
            ),
          );
          // limpar banco de dados
          await BancoDados().deletar();
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(
              content: Text('Banco de dados deletado!'),
              duration: Duration(seconds: 1),
            ),
          );
        },
      ),
    );
    // Gerar dados falsos
    botoes.add(
      HomePageButtom(
        texto: 'Gerar dados falsos',
        icon: Icons.change_circle,
        onPressed: () {
          Faker.gerarDadosFake();
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(
              content: Text('Dados gerados!'),
              duration: Duration(seconds: 1),
            ),
          );
        },
      ),
    );

    return botoes;
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: IntrinsicWidth(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            ..._listaBotoes(context),
          ],
        ),
      ),
    );
  }
}
