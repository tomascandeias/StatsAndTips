-- todas as equipas com o respetivo nome do clube
SELECT treinador, ngolos AS 'Numero de golos', nome, data_fundacao, id_clube FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube) WHERE id_clube='C111'

SELECT treinador, ngolos, nome, data_fundacao, id_clube FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube) WHERE id_clube='C111'

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
SELECT SAT.Jogador.nome , njogos, nacionalidade, posicao, amarelos, vermelhos, SAT.Clube.id_clube
 FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON id_equipa=Equipaid) WHERE SAT.Jogador.id_jogador='J10';


-- Receber um id_clube e devolver os nomes dos jogadores e as suas posicoes
SELECT SAT.Jogador.nome , posicao, id_jogador, Clubeid_clube
FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON id_equipa=Equipaid) WHERE id_clube='C111'

--Receber NADA dar oods
SELECT Equipaid_equipa, Equipaid_equipa2, localizacao, data_hora, vitoria, empate, derrota, vitoria_empate, total_golos, ambas_marcam 
FROM ((SAT.Odd JOIN SAT.Jogo ON Jogoid_jogo=id_jogo) JOIN SAT.Equipa ON Equipaid_equipa=id_equipa);

SELECT nome
FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube)
WHERE id_equipa='E177'



/*SELECT * FROM SAT.Equipa
SELECT * FROM SAT.Equipa_Cliente
SELECT * FROM SAT.Cliente

INSERT INTO SAT.Equipa_Cliente(client_id, equipa_id) VALUES ( 'CL223', 'E238')
INSERT INTO SAT.Equipa_Cliente(client_id, equipa_id) VALUES ( 'CL223', 'E74')
INSERT INTO SAT.Equipa_Cliente(client_id, equipa_id) VALUES ( 'CL223', 'E888')*/