CREATE SEQUENCE tb_livro_seq START 1 INCREMENT BY 50;

CREATE TABLE tb_livro (
    id BIGINT PRIMARY KEY DEFAULT nextval('tb_livro_seq'),
    autor VARCHAR(150) NOT NULL,
    data_lancamento TIMESTAMP(6) NOT NULL,
    preco NUMERIC(65,2) NOT NULL,
    titulo VARCHAR(100) NOT NULL
);
