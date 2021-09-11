
CREATE DOMAIN public.dm_cpf AS character(11);

CREATE DOMAIN public.dm_passou_sim_nao AS character(1)
	CONSTRAINT dm_passou_sim_nao_check CHECK (((VALUE = 's'::bpchar) OR (VALUE = 'S'::bpchar) OR (VALUE = 'n'::bpchar) OR (VALUE = 'N'::bpchar)));

CREATE TABLE public.alunos (
    id_aluno integer NOT NULL,
    nome character varying(255) NOT NULL,
    data_nascimento date NOT NULL,
    cpf public.dm_cpf NOT NULL
);

CREATE TABLE public.alunos_turmas (
    id_aluno integer NOT NULL,
    id_turma integer NOT NULL
);

CREATE TABLE public.biblioteca (
    id_biblioteca integer NOT NULL,
    nome character varying(255) NOT NULL,
    autor character varying(255) NOT NULL,
    id_materia integer NOT NULL,
    ano_lancamento integer NOT NULL,
    data_devolucao date,
    id_aluno integer
);

CREATE TABLE public.materias (
    id_materia integer NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(999) NOT NULL
);

CREATE TABLE public.professores (
    id_professore integer NOT NULL,
    nome character varying(255) NOT NULL,
    data_nascimento date NOT NULL,
    cpf public.dm_cpf NOT NULL,
    salario numeric(11,2) NOT NULL
);

CREATE SEQUENCE public.professores_id_professore_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.provas (
    id_prova integer NOT NULL,
    id_aluno integer NOT NULL,
    id_turma integer NOT NULL,
    nota integer NOT NULL,
    data date NOT NULL,
    passou public.dm_passou_sim_nao
);

CREATE TABLE public.turmas (
    id_turma integer NOT NULL,
    id_professor integer NOT NULL,
    id_materia integer NOT NULL,
    numero_vagas integer NOT NULL,
    data_inicio date NOT NULL,
    data_colacao date NOT NULL
);

ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_cpf_key UNIQUE (cpf);

ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_pkey PRIMARY KEY (id_aluno);

ALTER TABLE ONLY public.alunos_turmas
    ADD CONSTRAINT alunos_turmas_pkey PRIMARY KEY (id_aluno, id_turma);

ALTER TABLE ONLY public.biblioteca
    ADD CONSTRAINT biblioteca_pkey PRIMARY KEY (id_biblioteca);

ALTER TABLE ONLY public.materias
    ADD CONSTRAINT materias_pkey PRIMARY KEY (id_materia);

ALTER TABLE ONLY public.professores
    ADD CONSTRAINT professores_cpf_key UNIQUE (cpf);

ALTER TABLE ONLY public.professores
    ADD CONSTRAINT professores_pkey PRIMARY KEY (id_professore);

ALTER TABLE ONLY public.provas
    ADD CONSTRAINT provas_pkey PRIMARY KEY (id_prova);

ALTER TABLE ONLY public.turmas
    ADD CONSTRAINT turmas_pkey PRIMARY KEY (id_turma);

CREATE INDEX biblioteca_id_materia_idx ON public.biblioteca USING btree (id_materia);
CREATE INDEX biblioteca_id_aluno_idx ON public.biblioteca USING btree (id_aluno);
CREATE INDEX provas_id_aluno_idx ON public.provas USING btree (id_aluno);
CREATE INDEX provas_id_turma_idx ON public.provas USING btree (id_turma);
CREATE INDEX turmas_id_materia_idx ON public.turmas USING btree (id_materia);
CREATE INDEX turmas_id_professor_idx ON public.turmas USING btree (id_professor);

ALTER TABLE ONLY public.alunos_turmas
    ADD CONSTRAINT fk_alunos_turmas__id_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id_aluno);
ALTER TABLE ONLY public.alunos_turmas
    ADD CONSTRAINT fk_alunos_turmas__id_turma FOREIGN KEY (id_turma) REFERENCES public.turmas(id_turma);
ALTER TABLE ONLY public.biblioteca
    ADD CONSTRAINT fk_biblioteca__id_materia FOREIGN KEY (id_materia) REFERENCES public.materias(id_materia);
ALTER TABLE ONLY public.biblioteca
    ADD CONSTRAINT fk_biblioteca__id_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id_aluno);
ALTER TABLE ONLY public.provas
    ADD CONSTRAINT fk_provas__id_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id_aluno);
ALTER TABLE ONLY public.provas
    ADD CONSTRAINT fk_provas__id_turma FOREIGN KEY (id_turma) REFERENCES public.turmas(id_turma);
ALTER TABLE ONLY public.turmas
    ADD CONSTRAINT fk_turmas__id_materia FOREIGN KEY (id_materia) REFERENCES public.materias(id_materia);
ALTER TABLE ONLY public.turmas
    ADD CONSTRAINT fk_turmas__id_professor FOREIGN KEY (id_professor) REFERENCES public.professores(id_professore);