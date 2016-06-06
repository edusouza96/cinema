CREATE TABLE assento (
  codigoAssento INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sessao_codigoSessao INTEGER UNSIGNED NOT NULL,
  assentosLivres INTEGER UNSIGNED NOT NULL,
  data DATE NULL,
  PRIMARY KEY(codigoAssento)
);

CREATE TABLE filme (
  codigoFilme INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeFilme VARCHAR(100) NOT NULL,
  genero VARCHAR(100) NOT NULL,
  sinopse TEXT NULL,
  PRIMARY KEY(codigoFilme)
);

CREATE TABLE sala (
  numeroSala INTEGER UNSIGNED NOT NULL,
  quantidadeAssento INT NULL,
  PRIMARY KEY(numeroSala)
);

CREATE TABLE sessao (
  codigoSessao INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sala_numeroSala INTEGER UNSIGNED NOT NULL,
  filme_codigoFilme INTEGER UNSIGNED NOT NULL,
  horario DATE NOT NULL,
  PRIMARY KEY(codigoSessao)
);

CREATE TABLE venda (
  registroVenda INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sessao_codigoSessao INTEGER UNSIGNED NOT NULL,
  data DATE NOT NULL,
  PRIMARY KEY(registroVenda)
);


