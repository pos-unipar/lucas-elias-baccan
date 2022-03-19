package com.example.cadastroalunos.helpers;

import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.CursoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.enums.PeriodoEnum;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Curso;
import com.example.cadastroalunos.model.Professor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.bloco.faker.Faker;

public class FakerHelper {
    private static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static Faker faker = new Faker(); // https://github.com/blocoio/faker

    public static int getIndex(int length) {
        return faker.number.between(0, length - 1);
    }

    public static Curso gerarCursoFake(boolean salvar) {
        Curso model = new Curso(faker.commerce.productName());
        if (salvar) {
            CursoDAO.salvar(model);
        }
        return model;
    }

    public static Aluno gerarAlunoFake(boolean salvar, Curso curso) {
        Aluno model = new Aluno(
                Integer.parseInt(String.valueOf(faker.number.between(11111l, 99999l))),
                faker.name.name(),
                String.valueOf(faker.number.between(11111111111l, 99999999999l)),
                formatter.format(faker.date.birthday(-20, -50)),
                formatter.format(faker.date.birthday(0, -4)),
                curso,
                PeriodoEnum.values()[getIndex(PeriodoEnum.values().length)]
        );
        if (salvar) {
            AlunoDAO.salvar(model);
        }
        return model;
    }

    public static Aluno gerarAlunoFake(boolean salvar) {
        return gerarAlunoFake(salvar, gerarCursoFake(salvar));
    }

    public static Professor gerarProfessorFake(boolean salvar) {
        Professor model = new Professor(
                Integer.parseInt(String.valueOf(faker.number.between(11111l, 99999l))),
                faker.name.name(),
                String.valueOf(faker.number.between(11111111111l, 99999999999l)),
                formatter.format(faker.date.birthday(-20, -50)),
                formatter.format(faker.date.birthday(0, -4))
        );
        if (salvar) {
            ProfessorDAO.salvar(model);
        }
        return model;
    }
}
