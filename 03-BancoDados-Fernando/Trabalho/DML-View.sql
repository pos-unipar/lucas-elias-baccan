CREATE OR REPLACE VIEW public.alunos_aprovados
 AS
  SELECT
    a.nome AS aluno_nome,
    m.nome AS materia_nome,
    p.nota
  FROM
    alunos a
    INNER JOIN alunos_turmas at ON at.id_aluno = a.id_aluno
    INNER JOIN turmas t ON at.id_turma = t.id_turma
    INNER JOIN materias m ON m.id_materia = t.id_materia
    INNER JOIN provas p ON p.id_turma = t.id_turma AND (p.passou = 's' OR p.passou = 'S')
  ORDER BY
    a.nome,
    m.nome