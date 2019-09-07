CREATE TABLE dependente
(
  uuid varchar(255) NOT NULL,
  nome varchar(255),
  datadenascimento date NOT NULL,
  CONSTRAINT dependente_pkey PRIMARY KEY (uuid)
);

CREATE TABLE pessoa
(
  id SERIAL,
  nome varchar(255) NOT NULL,
  cpf varchar(255) NOT NULL,
  dependente_id varchar(255),
  CONSTRAINT pessoa_pkey PRIMARY KEY (id),
  CONSTRAINT fk_dependente FOREIGN KEY (dependente_id) REFERENCES dependente (uuid)
);
