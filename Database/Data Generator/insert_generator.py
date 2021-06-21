import random

def generate_random_name(prefix_names, suffix_names):
	return prefix_names[random.randint(0, len(prefix_names)-1)] + " " + suffix_names[random.randint(0, len(suffix_names)-1)]

def generate_ID(pref):
	return pref + str(random.randint(0, 1000))

# SAT.Clube
def generate_clube(id_clube, nome, data_fundacao):
	return f"INSERT INTO SAT.Clube(id_clubes, nome, data_fundacao) VALUES(\'{id_clube}\', \'{nome}\', {data_fundacao})"

# SAT.Equipa
def generate_equipa(id_equipa, treinador, ngolos, epoca, clube_id):
	return f"INSERT INTO SAT.Equipa(id_equipa, treinador, ngolos, epoca, Clubeid_clube) VALUES(\'{id_equipa}\', \'{treinador}\', {ngolos}, \'{epoca}\', \'{clube_id}\')"

# SAT.Competicao
def generate_competicao(id_competicao, nome, nequipas):
	return f"INSERT INTO SAT.Competicao(id_equipa, nome_equipa) VALUES(\'{id_competicao}\', \'{nome}\', {nequipas})"

# SAT.Liga
def generate_liga(classificacoes):
	return f"INSERT INTO SAT.Liga(classificacoes) VALUES(\'{classificacoes}\')"

# SAT.Taca
def generate_taca(rondas):
	return f"INSERT INTO SAT.Taca(rondas) VALUES(\'{rondas}\')"

# SAT.Jogador
def generate_jogador(equipa_id ,id_jogador, nome, amarelos, vermelhos, njogos, nacionalidade, posicao):
	return f"INSERT INTO SAT.Jogador( Equipaid , id_jogador, nome, amarelos, vermelhos, njogos, nacionalidade, posicao) VALUES(\'{equipa_id}\', \'{id_jogador}\', \'{nome}\', {amarelos}, {vermelhos}, {njogos}, \'{nacionalidade}\', \'{posicao}\')"

# SAT.Jogo
def generate_jogo(id_jogo, localizacao, data_hora, resultado, ):
	return f"INSERT INTO SAT.Jogo(id_jogo, localizacao, data_hora, resultado) VALUES(\'{id_jogo}\', \'{localizacao}\', \'{data_hora}\', \'{resultado}\')"

# SAT.Registo
def generate_registo(nome, nfaltas, namarelos, ncantos, ngolos):
	return f"INSERT INTO SAT.Registo(nome, nfaltas, namarelos, ncantos, ngolos) VALUES(\'{nome}\', {nfaltas}, {namarelos}, {ncantos}, {ngolos})"

# SAT.Arbitro
def generate_arbitro(id_arbitro, nome):
	return f"INSERT INTO SAT.Arbitro(id_arbitro, nome) VALUES(\'{id_arbitro}\', \'{nome}\')"

# SAT.Odd
def generate_odd(id_odd, vitoria, empate, derrota, vitoria_empate, total_golos, ambas_marcam):
	return f"INSERT INTO SAT.Odd(id_odd, vitoria, empate, derrota, vitoria_empate, total_golos, ambas_marcam) VALUES(\'{id_odd}\', {vitoria}, {empate}, {derrota}, {vitoria_empate}, {total_golos}, {ambas_marcam})"


# SAT.SeletorOdd
def generate_seletorodd(id_seletor):
	return f"INSERT INTO SAT.SeletorOdd(id_seletor) VALUES(\'{id_seletor}\')"

# SAT.Admin
def generate_admin(id_admin, selecao):
	return f"INSERT INTO SAT.Administrador(id_admin, selecao) VALUES(\'{id_admin}\', \'{selecao}\')"

# SAT.CasaDeApostas
def generate_casadeapostas(id_casa):
	return f"INSERT INTO SAT.CasaDeApostas(id_casa) VALUES(\'{id_casa}\')"


# SAT.Cliente
def generate_cliente(id_cliente, nome, email, password):
	return f"INSERT INTO SAT.Cliente(id_cliente, nome, email, pass) VALUES(\'{id_cliente}\', \'{nome}\', \'{email}\', \'{password}\')"

def writeInserIntoFile(fname, lst):
	with open(fname, "w") as f:
		for insert in lst:
			f.write(insert + "\n")


def main():

	# Lists of inserts
	competicao = list()
	clubes = list()
	equipas = list()
	jogadores = list()
	arbitros = list()
	admin = list()
	casadeapostas = list()

	# Sets
	id_clube = set()
	id_equipa = set()
	id_jogador = set()
	id_arbitros = set()
	nomes = set()
	
	
	prefix_clube = ["Football Club", "Sport Club"]
	sufix_clube = ["Braga", "Porto", "Aveiro", "Coimbra", "Leiria", "Lisboa", "Setubal", "Evora", "Beja", "Faro"]
	
	prefix_name = ["Antonio", "Eduardo", "Tomas", "Afonso", "Goncalo", "Francisco", "Andre", "Carlos", "Xavier", "Joaquim", "Joao", "Caetano"]
	sufix_name = ["Oliveira", "Silva", "Souza", "Santos", "Vieira", "Rodrigues", "Goncalves", "Ferreira", "Pereira", "Gomes", "Alves", "Almeida", "Carvalho", "Pinhao", "Silveira", "Vilarinho"]

	
	# Competicao
	print("Generating SAT.Competicao...")
	competicao.append(generate_competicao("LIGA PT", "Liga Portugal", 10))


	# Arbitro
	print("Generating SAT.Arbitro...")
	for i in range(3):
		while True:
			id = generate_ID("AR")
			nome = generate_random_name(prefix_name, sufix_name)
			if nome not in nomes and id not in id_arbitros:
				nomes.add(nome)
				id_arbitros.add(id)
				arbitros.append(generate_arbitro(id, nome))
				break
	
	# Admin
	id = generate_ID("A")
	nome = generate_random_name(prefix_name, sufix_name)
	admin.append(generate_admin(f"id", None))

	# CaseDeApostas
	casadeapostas.append(generate_casadeapostas(f"BETCLIC"))

	for i in range(10):
		# Clube
		print("Generating SAT.Clube")
		while True:
			id = generate_ID("C")
			if id not in id_clube:
				id_clube.add(id)
				break

		id = f"C{id}"	
		name = generate_random_name(prefix_name, sufix_clube)
		data_fundacao = random.randint(1890, 1950)
		
		clubes.append(generate_clube(id, name, data_fundacao))
		
		# Equipa
		print("Generating SAT.Equipa...")
		while True:
			id = generate_ID("E")
			treinador = generate_random_name(prefix_name, sufix_name)
			if id not in id_equipa and treinador not in nomes:
				id_equipa.add(id)
				nomes.add(treinador)
				break
		
		
		equipas.append(generate_equipa(id, treinador, 0, "2020/2021"))
		
		# Jogador
		print("Generating SAT.Jogador...")
		for i in range(11):
			while True:
				id = generate_ID("J")
				nome = generate_random_name(prefix_name, sufix_name)
				if id not in id_jogador and nome not in nomes:
					id_jogador.add(id)
					nomes.add(nome)
					break

			amarelos = random.randint(0,5)
			vermelhos = random.randint(0,2)
			njogos = 18
			nacionalidade = ["Portugues", "Brasileiro", "Columbiano", "Argentino", "Espanhol", "Frances"][random.randint(0, 5)]
			
			if 0 <= i < 1:
				jogadores.append(generate_jogador(id, nome, amarelos, vermelhos, njogos, nacionalidade, "GR"))
			elif 1 <= i < 5:
				jogadores.append(generate_jogador(id, nome, amarelos, vermelhos, njogos, nacionalidade, "DEF"))
			elif 5 <= i < 9:
				jogadores.append(generate_jogador(id, nome, amarelos, vermelhos, njogos, nacionalidade, "MED"))
			else:
				jogadores.append(generate_jogador(id, nome, amarelos, vermelhos, njogos, nacionalidade, "AV"))
			
	
	
		

	# Save inserts into files
	# writeInserIntoFile("Insert_Competicao.sql", competicao)
	# writeInserIntoFile("Insert_Clube.sql", clubes)
	# writeInserIntoFile("Insert_Equipas.sql", equipas)
	# writeInserIntoFile("Insert_Jogador.sql", jogadores)

# Call main function
main() 