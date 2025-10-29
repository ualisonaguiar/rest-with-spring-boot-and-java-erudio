CREATE SEQUENCE tb_pessoa_seq START 1 INCREMENT BY 50;
CREATE TABLE tb_pessoa (
    id BIGINT PRIMARY KEY DEFAULT nextval('tb_pessoa_seq'),
    primeiro_nome VARCHAR(80) NOT NULL,
    ultimo_nome VARCHAR(80) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    genero VARCHAR(6) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);
