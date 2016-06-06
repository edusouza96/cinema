CREATE DATABASE IF NOT EXISTS cinema_java;
USE cinema_java;
CREATE TABLE IF NOT EXISTS assento (
  codigoAssento INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sessao_codigoSessao INTEGER UNSIGNED NOT NULL,
  assentosLivres INTEGER UNSIGNED NOT NULL,
  data DATE NULL,
  PRIMARY KEY(codigoAssento)
);

CREATE TABLE IF NOT EXISTS filme (
  codigoFilme INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeFilme VARCHAR(100) NOT NULL,
  genero VARCHAR(100) NOT NULL,
  sinopse TEXT NULL,
  PRIMARY KEY(codigoFilme)
);

CREATE TABLE IF NOT EXISTS sala (
  numeroSala INTEGER UNSIGNED NOT NULL,
  quantidadeAssento INT NULL,
  PRIMARY KEY(numeroSala)
);

CREATE TABLE IF NOT EXISTS sessao (
  codigoSessao INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sala_numeroSala INTEGER UNSIGNED NOT NULL,
  filme_codigoFilme INTEGER UNSIGNED NOT NULL,
  horario DATE NOT NULL,
  PRIMARY KEY(codigoSessao)
);

CREATE TABLE IF NOT EXISTS venda (
  registroVenda INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sessao_codigoSessao INTEGER UNSIGNED NOT NULL,
  data DATE NOT NULL,
  PRIMARY KEY(registroVenda)
);


