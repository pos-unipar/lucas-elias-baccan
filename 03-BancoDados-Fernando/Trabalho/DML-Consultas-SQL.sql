-- -- 10 consultas que utilizem os recursos estudados, 
-- como Inner Join, Left e right join, Group By, Having, 
-- Subconsulta, etc ou as funções e views criadas pelo grupo. 
-- Escreva o que se espera retornar antes da consulta.

-- 1) Trazer dados dos alunos que estão em pelo menos uma turma (Joins)
SELECT DISTINCT
  alunos.nome,
  alunos.data_nascimento,
  alunos.cpf 
FROM
  alunos
  INNER JOIN alunos_turmas ON alunos.id_aluno = alunos_turmas.id_aluno
  INNER JOIN turmas ON alunos_turmas.id_turma = turmas.id_turma
ORDER BY
  alunos.nome;

-- 2) Os 10 livros mais antigos (Limit)
SELECT 
  nome,
  autor,
  ano_lancamento
FROM
  biblioteca
ORDER BY
  ano_lancamento ASC,
  nome ASC
LIMIT 10;

-- 3) Somatorio da quantidade total de vagas (Sum/Group by)
SELECT 
  sum(numero_vagas) 
FROM 
  turmas;

-- 4) Quantidade de alunos por turma (Count/Group by)
SELECT 
  turmas.id_turma,
  count(alunos_turmas.id_aluno) AS quantidade
FROM 
  turmas
  LEFT JOIN alunos_turmas ON turmas.id_turma = alunos_turmas.id_turma
GROUP BY
  turmas.id_turma
ORDER BY
  quantidade DESC;

-- 5) Turmas que não tem alunos (Having)
SELECT 
  turmas.id_turma,
  count(alunos_turmas.id_aluno) AS quantidade
FROM 
  turmas
  LEFT JOIN alunos_turmas ON turmas.id_turma = alunos_turmas.id_turma
GROUP BY
  turmas.id_turma
HAVING
  count(alunos_turmas.id_aluno) = 0
ORDER BY
  quantidade DESC;

-- 6) Quantos alunos um professor tem
SELECT
  professores.id_professore,
  professores.nome,
  count(turmas.id_professor) AS quantidade_alunos
FROM 
  professores
  LEFT JOIN turmas ON professores.id_professore = turmas.id_professor
  LEFT JOIN alunos_turmas ON turmas.id_turma = alunos_turmas.id_turma
GROUP BY
  professores.id_professore,
  professores.nome
ORDER BY
  quantidade_alunos DESC;

-- 7.1) Marcar livros aleatoriamente como emprestados
-- Utilize essa função para gerar dados para a consulta abaixo
UPDATE biblioteca SET id_aluno = (SELECT floor(random() * 1000 + 1)::int) 
WHERE id_biblioteca = (SELECT floor(random() * 1000 + 1)::int);

-- 7.2) Materias que tem livros emprestados (Subquery)
SELECT
  materias.id_materia,
  materias.nome
FROM
  materias
WHERE
  id_materia IN (SELECT DISTINCT id_materia FROM biblioteca WHERE id_aluno IS NOT NULL);

-- 8) Totos os alunos sem turma, e todas as turmas sem alunos (Full Outer Join)
SELECT
  alunos.id_aluno,
  turmas.id_turma
FROM
  alunos
  FULL OUTER JOIN alunos_turmas ON alunos.id_aluno = alunos_turmas.id_aluno
  FULL OUTER JOIN turmas ON alunos_turmas.id_turma = turmas.id_turma
WHERE
  alunos.id_aluno IS NULL OR turmas.id_turma IS NULL
ORDER BY
  alunos.nome;

-- 9) Todos os alunos que não tem nenhuma turma (Right Join)
SELECT DISTINCT
  professores.id_professore,
  professores.nome
FROM
  professores
  RIGHT JOIN turmas ON professores.id_professore = turmas.id_professor
ORDER BY
  professores.nome;

-- 10.1) Todos alunos e livros atrasados (Where)
UPDATE biblioteca SET data_devolucao = current_date - 60 WHERE id_aluno < 500;
-- Não tem livro atrasado, pois quando criamos, 
-- a trigger calculou 15 dias pra frente a partir da data de hoje
-- Caso precise testar, rode o UPDATE ACIMA
-- 10.2 Todos alunos e livros atrasados (Where)
SELECT
  alunos.id_aluno,
  alunos.nome,
  biblioteca.id_biblioteca,
  biblioteca.nome,
  biblioteca.data_devolucao
FROM
  biblioteca
  INNER JOIN alunos ON biblioteca.id_aluno = alunos.id_aluno
WHERE
  biblioteca.data_devolucao < now();


-- 11) Todos os alunos aprovados (view)
-- A view traz todos os alunos que tiveram nota acima de 70
SELECT DISTINCT 
  aluno_nome 
FROM 
  alunos_aprovados;

-- 12) Todos os alunos com CPF errado
-- Função que nós fizemos
SELECT DISTINCT
  alunos.id_aluno,
  alunos.nome,
  alunos.cpf
FROM
  alunos
WHERE
	fc_valida_tamanho_cpf(alunos.cpf) IS FALSE;

-- OU 
-- Função que nós pegamos pronta que valida o CPF, não só o tamanho
SELECT DISTINCT
  alunos.id_aluno,
  alunos.nome,
  alunos.cpf
FROM
  alunos
WHERE
	fc_valida_cpf(alunos.cpf) IS FALSE;

