CREATE DOMAIN dm_passou_sim_nao CHAR(1) 
CHECK (
  VALUE = 's' OR
  VALUE = 'S' OR
  value = 'n' OR
  value = 'N'
)

-- Adicioanr dominio na tabela prova
ALTER TABLE public.provas
    ADD COLUMN passou dm_passou_sim_nao;

-- Inserir dados na coluna nova de notas de provas
UPDATE
	provas
SET
	passou = CASE
            WHEN nota >= 70 THEN
              'S'
            ELSE
              'N'
          END;

--------------------------------------------------------------------------------

CREATE DOMAIN dm_cpf CHAR(11) 

-- Alterar colunas para utilizar o dominio
ALTER TABLE public.alunos
    ALTER COLUMN cpf TYPE dm_cpf COLLATE pg_catalog."default";
ALTER TABLE public.professores
    ALTER COLUMN cpf TYPE dm_cpf COLLATE pg_catalog."default";