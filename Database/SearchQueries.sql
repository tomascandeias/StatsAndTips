-- todas as equipas com o respetivo nome do clube
SELECT treinador, ngolos AS 'Numero de golos', nome, data_fundacao FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube) WHERE id_clube='C111'

-- nome de todos os clubes
SELECT nome FROM SAT.Clube

-- todos os jogadores
SELECT SAT.Jogador.nome , amarelos, vermelhos, njogos AS 'numero de jogos', nacionalidade, posicao AS 'Posição', SAT.Clube.nome AS 'Clube' FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON Equipaid=Equipaid) WHERE id_jogador='';

-- dado um nome de um cliente listar todos os clubes a que este deu favorito
SELECT SAT.Clube.nome, SAT.Equipa.ngolos, SAT.Equipa.epoca
FROM ((SAT.Cliente JOIN SAT.Equipa_Cliente ON id_cliente=client_id )
    JOIN SAT.Equipa ON equipa_id=id_equipa) JOIN SAT.Clube ON Clubeid_clube=id_clube
WHERE SAT.Cliente.nome='josefino'
;

-- recebe id_jogador e da informação
SELECT SAT.Jogador.nome , amarelos, vermelhos, njogos AS 'numero de jogos', nacionalidade, posicao AS 'Posição', SAT.Clube.nome AS 'Clube', SAT.Clube.id_clube as 'ID clube'
 FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON id_equipa=Equipaid) WHERE SAT.Jogador.id_jogador='J10';


-- Receber um id_clube e devolver os nomes dos jogadores e as suas posicoes
SELECT SAT.Jogador.nome , posicao AS 'Posição'
 FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON id_equipa=Equipaid) WHERE id_clube='';

/*SELECT * FROM SAT.Equipa
SELECT * FROM SAT.Equipa_Cliente
SELECT * FROM SAT.Cliente

INSERT INTO SAT.Equipa_Cliente(client_id, equipa_id) VALUES ( 'CL223', 'E238')
INSERT INTO SAT.Equipa_Cliente(client_id, equipa_id) VALUES ( 'CL223', 'E74')
INSERT INTO SAT.Equipa_Cliente(client_id, equipa_id) VALUES ( 'CL223', 'E888')*/