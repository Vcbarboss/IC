ap - formata o csv em JSON
	api1 - coleta as árvores de Autores e Variabilidades
	api2 - coleta as árvores de Arquivos e Commits
	ApiArqFinal - teste
	ApiAuthorFinal - teste
	ApiVarsFinal - teste
	ApiTesteFinal - teste
	Horario - Converse o csv para horarios UTC

core - coletor de dados, parser e gerador de CSV
	Runner - clona o repositório e extrai variabilidades
	Info - executa o parser
	Vector - removedor de comentários
	Reader - teste do extrator de variabilidades
	demais arquivos são auxiliares do Info para o parser ou geração da tabela.csv

metric - 	
	Violin - gerador de arquivo auxiliar do grafico de violino
	Removedor - auxiliar na remoção de incoerencias no JSON e substituicao de "virg" por "," (a virgula causa conflito)
	Metricas - coletor de metricas dos projetos
	Medidas - auxiliar coletor de medidas dos projetos
	FULL - coletor de medidas dos projetos
	
repos.txt - link do repositorio para clone