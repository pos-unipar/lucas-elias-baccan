CREATE OR REPLACE FUNCTION devolver_livro() RETURNS trigger AS $$
DECLARE
BEGIN
    UPDATE public.biblioteca SET data_devolucao = null WHERE id_biblioteca = NEW.id_biblioteca AND NEW.id_aluno IS NULL;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS devolver_livro
    ON public.biblioteca;
CREATE TRIGGER devolver_livro
    AFTER UPDATE OF id_aluno
    ON public.biblioteca
    FOR EACH ROW
    EXECUTE FUNCTION devolver_livro();

-----------------------------------------------------
CREATE OR REPLACE FUNCTION pegar_livro() RETURNS trigger AS $$
DECLARE
BEGIN
    UPDATE public.biblioteca SET data_devolucao = current_date + 15 WHERE id_biblioteca = NEW.id_biblioteca AND NEW.id_aluno IS NOT NULL;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS pegar_livro
    ON public.biblioteca;
CREATE TRIGGER pegar_livro
    AFTER UPDATE OF id_aluno
    ON public.biblioteca
    FOR EACH ROW
    EXECUTE FUNCTION pegar_livro();

-----------------------------------------------------
CREATE OR REPLACE FUNCTION aprovar_reprovar_campo() RETURNS trigger AS $$
DECLARE
BEGIN
    UPDATE
        provas
    SET
        passou = CASE
            WHEN nota >= 70 THEN
            'S'
            ELSE
            'N'
            END;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS aprovar_reprovar_campo
    ON public.provas;
CREATE TRIGGER aprovar_reprovar_campo
    AFTER UPDATE OF nota
    ON public.provas
    FOR EACH ROW
    EXECUTE FUNCTION aprovar_reprovar_campo();