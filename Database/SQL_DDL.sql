CREATE SCHEMA SAT
GO

CREATE TABLE SAT.Equipa (
  id_equipa varchar(10) NOT NULL, 
  treinador varchar(10) NULL, 
  ngolos    int NULL, 
  epoca     int NULL,
  Clubeid_clube varchar(10) NULL,
  PRIMARY KEY (id_equipa));

CREATE TABLE SAT.Jogador (
  Equipaid      varchar(10) NOT NULL, 
  id_jogador    int IDENTITY NOT NULL, 
  nome          varchar(10) NULL, 
  amarelos      int NULL, 
  vermelhos     int NULL, 
  njogos        int NULL, 
  nacionalidade varchar(10) NULL, 
  posicao       int NULL,
  Jogoid_jogo   varchar(10) NOT NULL, 
  PRIMARY KEY (id_jogador));

CREATE TABLE SAT.Competicao (
  id_competicao int IDENTITY NOT NULL, 
  nome          varchar(10) NULL, 
  nequipas      int NULL, 
  PRIMARY KEY (id_competicao));

CREATE TABLE SAT.Odd (
  id_odd               int IDENTITY NOT NULL, 
  vitoria              int NULL, 
  empate               int NULL, 
  derrota              int NULL, 
  vitoria_empate       int NULL, 
  total_golos          int NULL, 
  ambas_marcam         int NULL, 
  SeletorOddid_seletor varchar(10) NOT NULL, 
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

CREATE TABLE SAT.SeletorOdd (
  id_seletor varchar(10) NOT NULL, 
  PRIMARY KEY (id_seletor));

CREATE TABLE SAT.Arbitro (
  id_arbitro varchar(10) NOT NULL, 
  nome       varchar(50) NULL,
  PRIMARY KEY(id_arbitro));

CREATE TABLE SAT.Administrador (
  id_admin             varchar(10) NOT NULL, 
  selecao              varchar(20) NULL, 
  SeletorOddid_seletor varchar(10) NOT NULL,
  PRIMARY KEY(id_admin));

CREATE TABLE SAT.CasaDeApostas (
  id_casa              varchar(10) NOT NULL, 
  SeletorOddid_seletor varchar(10) NOT NULL,
  PRIMARY KEY(id_casa));

CREATE TABLE SAT.Liga (
  classificacoes          varchar(255) NULL, 
  Competicaoid_competicao int NOT NULL,
  PRIMARY KEY(Competicaoid_competicao));

CREATE TABLE SAT.Taca (
  rondas                  varchar(255) NULL, 
  Competicaoid_competicao int NOT NULL,
  PRIMARY KEY(Competicaoid_competicao));

CREATE TABLE SAT.Jogo (
  localizacao            int NULL, 
  data_hora             varchar(10) NULL, 
  id_jogo          varchar(10) NOT NULL, 
  resultado        varchar(10) NULL, 
  Competicaoid     int NOT NULL, 
  Equipaid_equipa  varchar(10) NOT NULL, 
  Equipaid_equipa2 varchar(10) NOT NULL, 
  PRIMARY KEY (id_jogo));

ALTER TABLE SAT.Jogador ADD CONSTRAINT FKJogadoreEquipaid FOREIGN KEY (Equipaid) REFERENCES SAT.Equipa (id_equipa);

ALTER TABLE SAT.Equipa ADD CONSTRAINT FKEquipa401218 FOREIGN KEY (Clubeid_clube) REFERENCES SAT.Clube (id_clube);

ALTER TABLE SAT.Equipa_Competicao ADD CONSTRAINT FKEquipa_Com999010 FOREIGN KEY (Equipaid_equipa) REFERENCES SAT.Equipa (id_equipa);
ALTER TABLE SAT.Equipa_Competicao ADD CONSTRAINT FKEquipa_Com810297 FOREIGN KEY (Competicaoid) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Odd ADD CONSTRAINT FKOdd956964 FOREIGN KEY (SeletorOddid_seletor) REFERENCES SAT.SeletorOdd (id_seletor);

ALTER TABLE SAT.Administrador ADD CONSTRAINT FKAdmin919941 FOREIGN KEY (SeletorOddid_seletor) REFERENCES SAT.SeletorOdd (id_seletor);

ALTER TABLE SAT.CasaDeApostas ADD CONSTRAINT FKCasaDeApos51658 FOREIGN KEY (SeletorOddid_seletor) REFERENCES SAT.SeletorOdd (id_seletor);

ALTER TABLE SAT.Registo ADD CONSTRAINT FKRegisto532354 FOREIGN KEY (Arbitroid_arbitro) REFERENCES SAT.Arbitro (id_arbitro);
ALTER TABLE SAT.Registo ADD CONSTRAINT FKRegisto787259 FOREIGN KEY (Jogoid_jogo) REFERENCES SAT.Jogo (id_jogo);

ALTER TABLE SAT.Jogo ADD CONSTRAINT FKJogo557265 FOREIGN KEY (Competicaoid) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Odd ADD CONSTRAINT FKOdd68218 FOREIGN KEY (Jogoid_jogo) REFERENCES SAT.Jogo (id_jogo);

ALTER TABLE SAT.Liga ADD CONSTRAINT FKLiga446850 FOREIGN KEY (Competicaoid_competicao) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Taca ADD CONSTRAINT FKTaca681458 FOREIGN KEY (Competicaoid_competicao) REFERENCES SAT.Competicao (id_competicao);

ALTER TABLE SAT.Jogador ADD CONSTRAINT FKJogador732794 FOREIGN KEY (Jogoid_jogo) REFERENCES SAT.Jogo (id_jogo);

ALTER TABLE SAT.Jogo ADD CONSTRAINT FKJogo252043 FOREIGN KEY (Equipaid_equipa) REFERENCES SAT.Equipa (id_equipa);
ALTER TABLE SAT.Jogo ADD CONSTRAINT FKJogo861332 FOREIGN KEY (Equipaid_equipa2) REFERENCES SAT.Equipa (id_equipa);
