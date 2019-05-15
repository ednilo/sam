--Create tables
-- Table: public."TB_PERFIL"

-- DROP TABLE public."TB_PERFIL";

CREATE TABLE public."TB_PERFIL"
(
    id bigserial NOT NULL,
    nome character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "TB_PERFIL_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."TB_PERFIL"
    OWNER to postgres;
COMMENT ON TABLE public."TB_PERFIL"
    IS 'Tabela com os possíveis perfis do sistema';
    
-- Table: public."TB_USUARIO"

-- DROP TABLE public."TB_USUARIO";

CREATE TABLE public."TB_USUARIO"
(
    id bigserial NOT NULL,
    username character varying(12) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(150) COLLATE pg_catalog."default",
    senha character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT "TB_USUARIO_pkey" PRIMARY KEY (id),
    CONSTRAINT uniques_field UNIQUE (username, email, cpf)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."TB_USUARIO"
    OWNER to postgres;
COMMENT ON TABLE public."TB_USUARIO"
    IS 'Tabela com todos os usuarios do sistema';
    
-- Table: public."TB_USUARIO_PERFIL"

-- DROP TABLE public."TB_USUARIO_PERFIL";

CREATE TABLE public."TB_USUARIO_PERFIL"
(
    id bigserial NOT NULL,
    id_usuario bigint NOT NULL,
    id_perfil bigint NOT NULL,
    CONSTRAINT "TB_USUARIO_PERFIL_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_perfil FOREIGN KEY (id_perfil)
        REFERENCES public."TB_PERFIL" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario)
        REFERENCES public."TB_USUARIO" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."TB_USUARIO_PERFIL"
    OWNER to postgres;
COMMENT ON TABLE public."TB_USUARIO"
    IS 'Tabela com ligaca NxN entre perfil e usuario';

INSERT INTO "TB_PERFIL" (NOME)
VALUES ('ADMINISTRADOR');

INSERT INTO "TB_PERFIL" (NOME)
VALUES ('VISITANTE');

--senha 12345
INSERT INTO "TB_USUARIO" (USERNAME, EMAIL, CPF, NOME, SENHA)
VALUES ('ednilo', 'ednilo.pinheiro@gmail.com', '03671695331', 'EDNILO DE CASTRO PINHEIRO', '827ccb0eea8a706c4c34a16891f84e7b');

--ednilo com perfil ADMINISTRADOR
INSERT INTO "TB_USUARIO_PERFIL" (ID_USUARIO, ID_PERFIL)
VALUES (1, 1);