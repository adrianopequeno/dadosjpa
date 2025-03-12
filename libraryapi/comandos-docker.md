==============Matando os container criados e criando da forma certa====================
docker ps: lista os container que estão up
docker ps -a: mostra todos os precessos e os containers que estão parados ou ups
COMANDOS:
	Parar container: docker stop + COTAINERID/NOMEDOCONTAINE
	Deletar/excluir um container: docker container rm NOMEDOCONTAINER/CONTAINERID

Criando uma rede/network dentro do docker
	1- docker network create NOME-DA-NETWORK : docker network create library-network

Depois que cria a rede/network usa os comandos anteriores para subir uma nova imagem do Postgres e do PGAdmin4 passando tbm a rede/network criada
docker run --name nome_do_container -p postaPC:postaContainerPadrãoDoPostgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=nome_do_banco --network NOMDE-DA-NETWORK-CRIADA nomdeEversãodaImagem
docker run --name libradydb -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=library --network library-network postgres:16.8
docker run --name pgadmin4 -p 15432:80 -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=admin --network library-network dpage/pgadmin4:9

-p = Posta
-e = Variáveis de ambiente

A porta do pc pode ser qualquer porta estando disponível. Ex: -p 3333:5432

===========Comandos Windows para ver se uma porta esta ocupada=================
Comando:
	Mostra a porta especifica
		netstat -aof | findstr NUMERO_DA_PORTA. EX: 5432
	se tiver resultado a porta ta ocupada. Caso contrário ta livre e pode usar

	Mostra todas as portas utilizadas
		netstat -aof


# Passo a Passo para Verificar e Liberar uma Porta no Windows

## **1. Verificar se a Porta Está em Uso**

Abra o **Prompt de Comando (CMD)** e execute o seguinte comando:

```sh
netstat -ano | findstr :5432
```

- Se houver um resultado, significa que a porta **5432** está em uso.
- O último número na linha de saída representa o **PID** do processo.

### **Exemplo de saída:**

```
TCP    0.0.0.0:5432           0.0.0.0:0              LISTENING       5276
```

Neste exemplo, o **PID** do processo que está utilizando a porta 5432 é **5276**.

---

## **2. Encerrar o Processo que Está Usando a Porta**

Com o **PID** identificado, tente encerrar o processo executando o seguinte comando:

```sh
TASKKILL /PID 5276 /F
```

**Explicação:**

- `TASKKILL` → Comando para encerrar um processo.
- `/PID 5276` → Especifica o **ID do processo** que será encerrado.
- `/F` → Força o encerramento do processo.

Se o processo puder ser encerrado, o CMD informará que ele foi finalizado com sucesso.

---

## **3. Caso o Processo Não Possa Ser Encerrado**

Se o comando `TASKKILL` falhar, siga os passos abaixo:

### **3.1 Listar Todos os Processos em Execução**

```sh
tasklist
```

Esse comando exibirá todos os processos ativos no sistema, juntamente com seus respectivos **PIDs**.

### **3.2 Identificar o Nome do Processo**

1. Copie todo o resultado do comando `tasklist`.
2. Cole em um novo arquivo no **VSCode**.
3. Utilize a ferramenta de busca do VSCode (`CTRL + F`) para procurar pelo **PID** identificado anteriormente.
4. O nome do processo será exibido ao lado do PID.

---

## **4. Encerrar o Processo Manualmente pelo Gerenciador de Tarefas**

1. **Abra o Gerenciador de Tarefas** (`CTRL + SHIFT + ESC`).
2. Vá até a aba **Processos**.
3. Encontre o processo correspondente ao **PID** identificado.
4. Clique com o botão direito sobre ele e selecione **Finalizar Tarefa**.

---

## **5. Verificar se a Porta Foi Liberada**

Após finalizar o processo, execute novamente o comando:

```sh
netstat -ano | findstr :5432
```

- Se **nenhum resultado** for exibido, a porta foi liberada com sucesso.
- Caso ainda apareça algum processo utilizando a porta, repita os passos anteriores para encontrar e encerrar o processo correto.

---

Seguindo este guia, você conseguirá identificar e liberar qualquer porta em uso no seu sistema Windows de maneira eficiente.

