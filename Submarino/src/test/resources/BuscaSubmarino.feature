Feature: Compra no site Submarino

	@compra @estorno
	Scenario: Busca por produto
		Given que acesso o site do Submarino
		When preencho o termo "smartphone" e clico na lupa
		Then exibe a lista de produtos
		
	@estorno
	Scenario: Busca por produto Não encontrado
		Given que acesso o site do Submarino
		When preencho o termo "QWEQWEQWEADASDASDASDASD" e clico na lupa
		Then exibe a mensagem de produto nao encontrado