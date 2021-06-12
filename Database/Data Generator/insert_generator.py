import random

# SAT.Clube
def generate_clube(id_clube, nome, data_fundacao):
	return f"INSERT INTO SAT.Clube(id_equipa, nome_equipa) VALUES({id_clube}, {nome}, {data_fundacao})"

# SAT.Equipa
def generate_equipa(id_equipa, treinador, ngolos, epoca):
	return f"INSERT INTO SAT.Equipa(id_equipa, treinador, ngolos, epoca) VALUES({id_equipa}, {treinador}, {ngolos}, {epoca})"

# SAT.Competicao
def generate_competicao(id_competicao, nome, nequipas):
	return f"INSERT INTO SAT.Competicao(id_equipa, nome_equipa) VALUES({id_competicao}, {nome}, {nequipas})"

# SAT.Liga
def generate_liga(classificacoes):
	return f"INSERT INTO SAT.Liga(classificacoes) VALUES({classificacoes})"

# SAT.Taca
def generate_taca(rondas):
	return f"INSERT INTO SAT.Taca(rondas) VALUES({rondas})"

# SAT.Jogador
def generate_jogador(id_jogador, nome, amarelos, vermelhos, njogos, nacionalidade, posicao):
	return f"INSERT INTO SAT.Jogador(id_jogador, nome, amarelos, vermelhos, njogos, nacionalidade, posicao) VALUES({id_jogador}, {nome}, {amarelos}, {vermelhos}, {njogos}, {nacionalidade}, {posicao})"

# SAT.Jogo
def generate_jogo(id_jogo, localizacao, data_hora, resultado, ):
	return f"INSERT INTO SAT.Jogo(id_jogo, localizacao, data_hora, resultado) VALUES({id_jogo}, {localizacao}, {data_hora}, {resultado})"

# SAT.Registo
def generate_registo():
	pass

# SAT.Arbitro
def generate_arbitro():
	pass

# SAT.Odd
def generate_odd():
	pass


# SAT.SeletorOdd
def generate_seletorodd():
	pass

# SAT.Admin
def generate_admin():
	pass

# SAT.CasaDeApostas
def generate_casadeapostas():
	pass


def main():
	prefix_names = ["Football Club ", "Sporting Club " , "Association Deportive "]
	sufix_names = ["Budapest", "Benfica" , "Porto", "Lisbon", "Seville", "Madrid", "Liverpool", "Manchester"]

	for i in range(10):
		print(generate_clube(f"C {random.randint(0, 100)}",
		 prefix_names[random.randint(0, len(prefix_names)-1)] + sufix_names[random.randint(0, len(sufix_names)-1)], 1931))

# Call main function
main() 