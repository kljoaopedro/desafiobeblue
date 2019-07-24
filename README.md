 # Desafio técnico - Engenheiro back-end Beblue
 
 ###Como utilizar as API's:

O projeto contém a pasta com todas as requisições, basta usar da maneira que quiser.

####Primeiramente deve-se usar a API de init

http://localhost:8080/spotify/init

####Demais API's:
- ##Vendas.

**Nova Venda** : http://localhost:8080/vendas/

**Paramentros**: {"quantidade":5,"generoEnum":"ROCK"}

Onde quantidade deve ser inteiro e o Genero deve ser escrito totalmente em Maiusculo.

**SearchById** : http://localhost:8080/vendas/{/id}

**Paramentros**: Trocar o {/id} pelo ID da venda.


**Busca Paginada** : http://localhost:8080/vendas/?resultado=25&data-inicial=2019-07-23&data-final=2019-07-25

**Paramentros**: resultado tem que ser inteiro, datas no formato YYYY-MM-DD


- ##Discos 
**SearchById** : http://localhost:8080/disco/{/id}

**Paramentros**: Trocar o {/id} pelo ID do Disco.

**Busca Paginada** : http://localhost:8080/disco/?resultado=25&genero=CLASSIC

**Paramentros**: resultado tem que ser inteiro, genêro escrito em maiusculo.