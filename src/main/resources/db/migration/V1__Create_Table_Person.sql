CREATE TABLE IF NOT EXISTS tb_pessoa (
    id SERIAL PRIMARY KEY,
    primeiro_nome VARCHAR(80) NOT NULL,
    ultimo_nome VARCHAR(80) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    genero VARCHAR(6) NOT NULL
);
