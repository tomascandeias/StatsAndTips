CREATE SCHEMA SAT
GO


CREATE TABLE SAT.Equipa (
  id_equipa varchar(10) NOT NULL, 
  treinador varchar(10) NULL, 
  ngolos    int NULL, 
  epoca     int NULL, 
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
  Jogocodigo    varchar(10) NOT NULL, 
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
  derrota_empate       int NULL, 
  total_golos          int NULL, 
  ambas_marcam         int NULL, 
  SeletorOddid_seletor varchar(10) NOT NULL, 
  Jogoid_jogo          varchar(10) NOT NULL, 
  PRIMARY KEY (id_odd));

CREATE TABLE SAT.Registo (
  nome        varchar(10) NULL, 
  nfaltas     int NULL, 
  namarelos   int NULL, 
  ncantos     int NULL, 
  ngolos      int NULL, 
  Jogoid_jogo varchar(10) NOT NULL);

CREATE TABLE SAT.Clube (
  id_clube      varchar(10) NULL, 
  nome          varchar(50) NULL, 
  data_fundacao int NULL);

CREATE TABLE SAT.Equipa_Competicao (
  Equipaid_equipa varchar(10) NOT NULL, 
  Competicaoid    int NOT NULL, 
  PRIMARY KEY (Equipaid_equipa, 
  Competicaoid));

CREATE TABLE SAT.SeletorOdd (
  id_seletor varchar(10) NOT NULL, 
  PRIMARY KEY (id_seletor));

CREATE TABLE SAT.Arbitro (
  id_arbitro varchar(10) NULL, 
  nome       varchar(50) NULL);

CREATE TABLE SAT.Administrador (
  id_admin             varchar(10) NULL, 
  selecao              varchar(20) NULL, 
  SeletorOddid_seletor varchar(10) NOT NULL);

CREATE TABLE SAT.CasaDeApostas (
  id_casa              varchar(10) NULL, 
  SeletorOddid_seletor varchar(10) NOT NULL);

CREATE TABLE SAT.Liga (
  classificacoes          varchar(255) NULL, 
  Competicaoid_competicao int NOT NULL);

CREATE TABLE SAT.Taça (
  rondas                  varchar(255) NULL, 
  Competicaoid_competicao int NOT NULL);

CREATE TABLE SAT.Jogo (
  local            int NULL, 
  data             varchar(10) NULL, 
  id_jogo          varchar(10) NOT NULL, 
  resultado        varchar(10) NULL, 
  idCompeticao     int NOT NULL, 
  Admincodigo      int NOT NULL, 
  Competicaoid     int NOT NULL, 
  Equipaid_equipa  varchar(10) NOT NULL, 
  Equipaid_equipa2 varchar(10) NOT NULL, 
  PRIMARY KEY (id_jogo));

ALTER TABLE SAT.Jogador ADD CONSTRAINT Constitui FOREIGN KEY (Equipaid) REFERENCES Equipa (id_equipa);

ALTER TABLE Equipa ADD CONSTRAINT FKEquipa401218 FOREIGN KEY () REFERENCES Clube ();

ALTER TABLE Equipa_Competicao ADD CONSTRAINT FKEquipa_Com999010 FOREIGN KEY (Equipaid_equipa) REFERENCES Equipa (id_equipa);
ALTER TABLE Equipa_Competicao ADD CONSTRAINT FKEquipa_Com810297 FOREIGN KEY (Competicaoid) REFERENCES Competicao (id_competicao);

ALTER TABLE Odd ADD CONSTRAINT FKOdd956964 FOREIGN KEY (SeletorOddid_seletor) REFERENCES SeletorOdd (id_seletor);

ALTER TABLE Admin ADD CONSTRAINT FKAdmin919941 FOREIGN KEY (SeletorOddid_seletor) REFERENCES SeletorOdd (id_seletor);

ALTER TABLE CasaDeApostas ADD CONSTRAINT FKCasaDeApos51658 FOREIGN KEY (SeletorOddid_seletor) REFERENCES SeletorOdd (id_seletor);

ALTER TABLE Registo ADD CONSTRAINT FKRegisto532354 FOREIGN KEY () REFERENCES Arbitro ();
ALTER TABLE Registo ADD CONSTRAINT FKRegisto787259 FOREIGN KEY (Jogoid_jogo) REFERENCES Jogo (id_jogo);

ALTER TABLE Jogo ADD CONSTRAINT FKJogo557265 FOREIGN KEY (Competicaoid) REFERENCES Competicao (id_competicao);

ALTER TABLE Odd ADD CONSTRAINT FKOdd68218 FOREIGN KEY (Jogoid_jogo) REFERENCES Jogo (id_jogo);

ALTER TABLE Liga ADD CONSTRAINT FKLiga446850 FOREIGN KEY (Competicaoid_competicao) REFERENCES Competicao (id_competicao);

ALTER TABLE Taça ADD CONSTRAINT FKTaça681458 FOREIGN KEY (Competicaoid_competicao) REFERENCES Competicao (id_competicao);

ALTER TABLE Jogador ADD CONSTRAINT FKJogador732794 FOREIGN KEY (Jogoid_jogo) REFERENCES Jogo (id_jogo);

ALTER TABLE Jogo ADD CONSTRAINT FKJogo252043 FOREIGN KEY (Equipaid_equipa) REFERENCES Equipa (id_equipa);
ALTER TABLE Jogo ADD CONSTRAINT FKJogo861332 FOREIGN KEY (Equipaid_equipa2) REFERENCES Equipa (id_equipa);
