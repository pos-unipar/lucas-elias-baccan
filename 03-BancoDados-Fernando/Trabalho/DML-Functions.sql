-- https://medium.com/@andersonantunes/fun%C3%A7%C3%B5es-de-valida%C3%A7%C3%A3o-de-cpf-e-cnpj-em-pl-pgsql-5720d49b4215
-- Validar se o CPF é valido
create or replace function fc_valida_cpf
(
    p_cpf in character varying, 
    p_valida_nulo in boolean default false
)
returns boolean as 
$$
declare
    
    v_cpf_invalidos character varying[10] 
    default array['00000000000', '11111111111',
                  '22222222222', '33333333333',
                  '44444444444', '55555555555',
                  '66666666666', '77777777777',
                  '88888888888', '99999999999'];
                  
    v_cpf_quebrado smallint[];
    
    c_posicao_dv1 constant smallint default 10;    
    v_arranjo_dv1 smallint[9] default array[10,9,8,7,6,5,4,3,2];
    v_soma_dv1 smallint default 0;
    v_resto_dv1 double precision default 0;
    
    c_posicao_dv2 constant smallint default 11;
    v_arranjo_dv2 smallint[10] default array[11,10,9,8,7,6,5,4,3,2];
    v_soma_dv2 smallint default 0;
    v_resto_dv2 double precision default 0;
    
begin
    if p_valida_nulo and nullif(p_cpf, '') is null then
        return true;
    end if;
    if (not (p_cpf ~* '^([0-9]{11})$' or 
             p_cpf ~* '^([0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2})$')
        ) or
        p_cpf = any (v_cpf_invalidos) or
        p_cpf is null
    then
        return false;    
    end if;
    
v_cpf_quebrado := regexp_split_to_array(
                    regexp_replace(p_cpf, '[^0-9]', '', 'g'), '');
    -------------------------------- Digito Verificador 1
    for t in 1..9 loop
        v_soma_dv1 := v_soma_dv1 + 
                     (v_cpf_quebrado[t] * v_arranjo_dv1[t]);
    end loop;
    v_resto_dv1 := ((10 * v_soma_dv1) % 11) % 10;
        
    if (v_resto_dv1 != v_cpf_quebrado[c_posicao_dv1]) 
    then
        return false;
    end if;
    
    -------------------------------- Digito Verificador 2
    for t in 1..10 loop
        v_soma_dv2 := v_soma_dv2 + 
                     (v_cpf_quebrado[t] * v_arranjo_dv2[t]);
    end loop;
    v_resto_dv2 := ((10 * v_soma_dv2) % 11) % 10;
    
    return (v_resto_dv2 = v_cpf_quebrado[c_posicao_dv2]);    
    
end;
$$ language plpgsql;

--------------------------------------------------------------------------------
-- Validar tamanho do CPF
CREATE OR REPLACE FUNCTION fc_valida_tamanho_cpf
(
    p_cpf in character varying
)
RETURNS boolean
AS
$$
BEGIN
	RETURN (length(p_cpf) = 11);
END;
$$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------
-- Retornar quantidade livros por materia
CREATE OR REPLACE FUNCTION quantidade_livros_por_materia ()
RETURNS TABLE (
  nome varchar,
  quantidade bigint
) 
AS $$
BEGIN
  RETURN QUERY
    SELECT
      materias.nome AS nome,
      COUNT(biblioteca.id_biblioteca) AS quantidade
    FROM 
      biblioteca
      INNER JOIN materias ON materias.id_materia = biblioteca.id_materia
    GROUP BY
      materias.nome
    ORDER BY
      quantidade DESC;
END;
$$ LANGUAGE plpgsql;

-- Testar depois se funcionou
-- SELECT * FROM quantidade_livros_por_materia()
