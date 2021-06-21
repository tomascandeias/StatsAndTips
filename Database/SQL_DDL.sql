CREATE SCHEMA SAT
GO

CREATE TABLE SAT.Equipa (
  id_equipa varchar(10) NOT NULL, 
  treinador varchar(255) NULL, 
  ngolos    int NULL, 
  epoca     int NULL,
  Clubeid_clube varchar(10) NULL,
  PRIMARY KEY (id_equipa));

CREATE TABLE SAT.Jogador (
  Equipaid      varchar(10) NOT NULL, 
  id_jogador    varchar(10) NOT NULL, 
  nome          varchar(255) NULL, 
  amarelos      int NULL, 
  vermelhos     int NULL, 
  njogos        int NULL, 
  nacionalidade varchar(100) NULL, 
  posicao       VARCHAR(3) NULL,
  Jogoid_jogo   varchar(10) NOT NULL, 
  PRIMARY KEY (id_jogador));

CREATE TABLE SAT.Competicao (
  id_competicao int IDENTITY NOT NULL, 
  nome          varchar(10) NULL, 
  nequipas      int NULL, 
  PRIMARY KEY (id_competicao));

CREATE TABLE SAT.Odd (
  id_odd               varchar(10) NOT NULL, 
  vitoria              int NULL, 
  empate               int NULL, 
  derrota              int NULL, 
  vitoria_empate       int NULL, 
  total_golos          int NULL, 
  ambas_marcam         int NULL,
  Jogoid_jogo          varchar(10) NOT NULL, 
  PRIMARY KEY (id_odd));

CREATE TABLE SAT.Registo (
  nome        varchar(10) NOT NULL, 
  nfaltas     int NULL, 
  namarelos   int NULL, 
  ncantos     int NULL, 
  ngolos      int NULL, 
  Jogoid_jogo varchar(10) NOT NULL,
  Arbitroid_arbitro varchar(10) NOT NULL,
  PRIMARY KEY (nome));

CREATE TABLE SAT.Clube (
  id_clube      varchar(10) NOT NULL, 
  nome          varchar(50) NULL, 
  data_fundacao int NULL,
  PRIMARY KEY (id_clube));

CREATE TABLE SAT.Equipa_Competicao (
  Equipaid_equipa varchar(10) NOT NULL, 
  Competicaoid    int NOT NULL, 
  PRIMARY KEY (Equipaid_equipa, 
  Competicaoid));


CREATE TABLE SAT.Arbitro (
  id_arbitro varchar(10) NOT NULL, 
  nome       varchar(50) NULL,
  PRIMARY KEY(id_arbitro));

CREATE TABLE SAT.Liga (
  classificacoes          varchar(255) NULL, 
  Competicaoid_competicao int NOT NULL,
  PRIMARY KEY(Competicaoid_competicao));

CREATE TABLE SAT.Taca (
  rondas                  varchar(255) NULL, 
  Competicaoid_competicao int NOT NULL,
  PRIMARY KEY(Competicaoid_competicao));


CREATE TABLE SAT.Jogador_Jogo(
  Jogadorid_jogador varchar(10) NULL,
  Jogoid_jogo       varchar(10) NULL);

CREATE TABLE SAT.Jogo (
  localizacao            int NULL, 
  data_hora             varchar(10) NULL, 
  id_jogo          varchar(10) NOT NULL, 
  resultado        varchar(10) NULL, 
  Competicaoid     int NOT NULL, 
  Equipaid_equipa  varchar(10) NOT NULL, 
  Equipaid_equipa2 varchar(10) NOT NULL, 
  PRIMARY KEY (id_jogo));

CREATE TABLE SAT.Cliente (
  id_cliente    VARCHAR(10),
  nome          VARCHAR(100),
  email         VARCHAR(255),
  pass          VARCHAR(16),
  PRIMARY KEY (id_cliente)
);

CREATE TABLE SAT.Equipa_Cliente (
  client_id     VARCHAR(10),
  equipa_id     VARCHAR(10),
  PRIMARY KEY (client_id, equipa_id)
);

ALTER TABLE SAT.Jogador ADD CONSTRAINT FKJogadoreEquipaid FOREIGN KEY (Equipaid) REFERENCES SAT.Equipa (id_equipa);

ALTER TABLE SAT.Equipa ADD CONSTRAINT FKEquipa401218 FOREIGN KEY (Clubeid_clube) REFERENCES SAT.Clube (id_clube);

ALTER TABLE SAT.Equipa_Competicao ADD CONSTRAINT FKEquipa_Com999010 FOREIGN KEY (Equipaid_equipa) REFERENCES SAT.Equipa (id_equipa);
ALTER TABLE SAT.Equipa_Competicao ADD CONSTRAINT FKEquipa_Com810297 FOREIGN KEY (Competicaoid) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Jogador_Jogo ADD CONSTRAINT FKJogador_Jogo1 FOREIGN KEY (Jogadorid_jogador) REFERENCES SAT.Jogador (id_jogador);
ALTER TABLE SAT.Jogador_Jogo ADD CONSTRAINT FKJogador_Jogo2 FOREIGN KEY (Jogoid_jogo) REFERENCES SAT.Jogo (id_jogo);


ALTER TABLE SAT.Registo ADD CONSTRAINT FKRegisto532354 FOREIGN KEY (Arbitroid_arbitro) REFERENCES SAT.Arbitro (id_arbitro);
ALTER TABLE SAT.Registo ADD CONSTRAINT FKRegisto787259 FOREIGN KEY (Jogoid_jogo) REFERENCES SAT.Jogo (id_jogo);

ALTER TABLE SAT.Jogo ADD CONSTRAINT FKJogo557265 FOREIGN KEY (Competicaoid) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Odd ADD CONSTRAINT FKOdd68218 FOREIGN KEY (Jogoid_jogo) REFERENCES SAT.Jogo (id_jogo);

ALTER TABLE SAT.Liga ADD CONSTRAINT FKLiga446850 FOREIGN KEY (Competicaoid_competicao) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Taca ADD CONSTRAINT FKTaca681458 FOREIGN KEY (Competicaoid_competicao) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Jogador ADD CONSTRAINT FKJogador732794 FOREIGN KEY (Equipaid) REFERENCES SAT.Equipa (id_equipa);

ALTER TABLE SAT.Jogo ADD CONSTRAINT FKJogo252043 FOREIGN KEY (Equipaid_equipa) REFERENCES SAT.Equipa (id_equipa);
ALTER TABLE SAT.Jogo ADD CONSTRAINT FKJogo861332 FOREIGN KEY (Equipaid_equipa2) REFERENCES SAT.Equipa (id_equipa);
 
ALTER TABLE SAT.Equipa_Cliente ADD CONSTRAINT FKEquipaCliente001 FOREIGN KEY(client_id) REFERENCES SAT.Cliente (id_cliente)
ALTER TABLE SAT.Equipa_Cliente ADD CONSTRAINT FKEquipaCliente002 FOREIGN KEY(equipa_id) REFERENCES SAT.Equipa (id_equipa)

/*DROP TABLE SAT.Clube
DROP TABLE SAT.Equipa
DROP TABLE SAT.Jogador
DROP TABLE SAT.Jogo
DROP TABLE SAT.Odd
DROP TABLE SAT.Registo
DROP TABLE SAT.Arbitro

DROP TABLE SAT.Equipa_Competicao
DROP TABLE SAT.Competicao
DROP TABLE SAT.Liga
DROP TABLE SAT.Taca 
DROP TABLE SAT.Jogador_Jogo*/

ALTER TABLE SAT.Jogador ADD nome VARCHAR(255)
ALTER TABLE SAT.Jogador DROP COLUMN Jogoid_Jogo
