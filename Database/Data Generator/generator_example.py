def gen_team_queries(prefix_names, sufix_names, num_queries):
    club_names = list(set([x+y for x in prefix_names for y in sufix_names]))
    queries = []

    for i in range(num_queries):
        if i == len(club_names):
            return queries
        queries.append(f"INSERT INTO Equipa( id_equipa , nome_equipa) VALUES ({i+1} , \'{club_names[i]}\')")

    return queries



def gen_txt(queries, destination_table):

    with open(f"QUERY_FOR_{destination_table}.txt", mode="w") as f:
        for q in queries:
            f.write(q + "\n")


def main():
	prefix_names = ["Football Club ", "Sporting Club " , "Association Deportive "]
	sufix_names = ["Budapest", "Benfica" , "Porto", "Lisbon", "Seville", "Madrid", "Liverpool", "Manchester"]

	queries = gen_team_queries(prefix_names, sufix_names, 18)

	gen_txt(queries, "TEAMS")
    

main()