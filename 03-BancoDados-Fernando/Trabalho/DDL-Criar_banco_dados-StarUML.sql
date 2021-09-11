CREATE TABLE public.alunos (
    id_aluno SERIAL NOT NULL,
    nome varchar(255) NOT NULL,
    data_nascimento date NOT NULL,
    cpf varchar(11) NOT NULL,
    PRIMARY KEY (id_aluno)
);

ALTER TABLE public.alunos
    ADD UNIQUE (cpf);


CREATE TABLE public.materias (
    id_materia SERIAL NOT NULL,
    nome varchar(255) NOT NULL,
    descricao varchar(999) NOT NULL,
    PRIMARY KEY (id_materia)
);


CREATE TABLE public.turmas (
    id_turma SERIAL NOT NULL,
    id_professor integer NOT NULL,
    id_materia integer NOT NULL,
    numero_vagas integer NOT NULL,
    data_inicio date NOT NULL,
    data_colacao date NOT NULL,
    PRIMARY KEY (id_turma)
);

CREATE INDEX ON public.turmas
    (id_professor);
CREATE INDEX ON public.turmas
    (id_materia);


CREATE TABLE public.provas (
    id_prova SERIAL NOT NULL,
    id_aluno integer NOT NULL,
    id_turma integer NOT NULL,
    nota integer NOT NULL,
    data date NOT NULL,
    PRIMARY KEY (id_prova)
);

CREATE INDEX ON public.provas
    (id_aluno);
CREATE INDEX ON public.provas
    (id_turma);


CREATE TABLE public.biblioteca (
    id_biblioteca SERIAL NOT NULL,
    nome varchar(255) NOT NULL,
    autor varchar(255) NOT NULL,
    id_materia integer NOT NULL,
    ano_lancamento integer NOT NULL,
    data_devolucao date,
    id_aluno integer,
    PRIMARY KEY (id_biblioteca)
);

CREATE INDEX ON public.biblioteca
    (id_materia);
CREATE INDEX ON public.biblioteca
    (id_aluno);


CREATE TABLE public.professores (
    id_professore SERIAL NOT NULL,
    nome varchar(255) NOT NULL,
    data_nascimento date NOT NULL,
    cpf varchar(11) NOT NULL,
    salario numeric(11,2) NOT NULL,
    PRIMARY KEY (id_professore)
);

ALTER TABLE public.professores
    ADD UNIQUE (cpf);


CREATE TABLE public.alunos_turmas (
    id_aluno integer NOT NULL,
    id_turma integer NOT NULL,
    PRIMARY KEY (id_aluno, id_turma)
);


ALTER TABLE public.turmas ADD CONSTRAINT FK_turmas__id_professor FOREIGN KEY (id_professor) REFERENCES public.professores(id_professore);
ALTER TABLE public.turmas ADD CONSTRAINT FK_turmas__id_materia FOREIGN KEY (id_materia) REFERENCES public.materias(id_materia);
ALTER TABLE public.provas ADD CONSTRAINT FK_provas__id_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id_aluno);
ALTER TABLE public.provas ADD CONSTRAINT FK_provas__id_turma FOREIGN KEY (id_turma) REFERENCES public.turmas(id_turma);
ALTER TABLE public.biblioteca ADD CONSTRAINT FK_biblioteca__id_materia FOREIGN KEY (id_materia) REFERENCES public.materias(id_materia);
ALTER TABLE public.biblioteca ADD CONSTRAINT FK_biblioteca__id_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id_aluno);
ALTER TABLE public.alunos_turmas ADD CONSTRAINT FK_alunos_turmas__id_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id_aluno);
ALTER TABLE public.alunos_turmas ADD CONSTRAINT FK_alunos_turmas__id_turma FOREIGN KEY (id_turma) REFERENCES public.turmas(id_turma);