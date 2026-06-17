# Sistema de Mercado

Projeto feito em Java para cadastrar e controlar informacoes basicas de
um mercado. A aplicacao foi organizada em MVC e usa arquivos locais para guardar
os dados entre uma execucao e outra.

## O que o sistema faz

- Cadastro, listagem, alteracao e exclusao de clientes, funcionarios,
  categorias, fornecedores e produtos.
- Cadastro separado para tres tipos de produto: alimenticio, limpeza e
  vestuario.
- Criacao e listagem de pedidos, vinculando um cliente a um ou mais produtos.
- Calculo automatico do valor final dos produtos com desconto.
- Persistencia em arquivos `.dat`, criados na pasta `dados/`.
- Registro de eventos e erros em `logs/log.txt`.

## Como o projeto esta organizado

```text
src/
|-- Main.java
|-- controller/
|   |-- ClienteController.java
|   |-- FuncionarioController.java
|   |-- CategoriaController.java
|   |-- FornecedorController.java
|   |-- ProdutoAlimenticioController.java
|   |-- ProdutoLimpezaController.java
|   |-- ProdutoVestuarioController.java
|   `-- PedidoController.java
|-- model/
|   |-- Pessoa.java
|   |-- Cliente.java
|   |-- Funcionario.java
|   |-- Produto.java
|   |-- ProdutoAlimenticio.java
|   |-- ProdutoLimpeza.java
|   |-- ProdutoVestuario.java
|   |-- Categoria.java
|   |-- Fornecedor.java
|   |-- Pedido.java
|   |-- ItemPedido.java
|   |-- interfaces/
|   |   `-- Descontavel.java
|   `-- exceptions/
|       `-- ValidacaoException.java
|-- util/
|   |-- LoggerService.java
|   `-- PersistenciaService.java
`-- view/
    |-- Leitura.java
    |-- ClienteView.java
    |-- FuncionarioView.java
    |-- CategoriaView.java
    |-- FornecedorView.java
    |-- ProdutoAlimenticioView.java
    |-- ProdutoLimpezaView.java
    |-- ProdutoVestuarioView.java
    `-- PedidoView.java
```

## Principais classes

As entidades principais ficam no pacote `model`. A classe `Pessoa` serve de base
para `Cliente` e `Funcionario`, enquanto `Produto` serve de base para
`ProdutoAlimenticio`, `ProdutoLimpeza` e `ProdutoVestuario`.

Cada tipo de produto implementa uma regra propria de desconto:

- Produto alimenticio: 10%
- Produto de limpeza: 5%
- Produto de vestuario: 15%

O pedido guarda um cliente e uma lista de itens. Cada item possui um produto e a
quantidade comprada, e o total do pedido é calculado a partir do preço final de
cada produto.

## Conceitos usados

- heranca, com `Pessoa` e `Produto`;
- classes e metodos abstratos;
- interface, por meio de `Descontavel`;
- sobrescrita de metodos nos tipos de produto;
- sobrecarga em metodos como `buscar` e `adicionarItem`;
- encapsulamento com atributos privados e getters/setters;
- colecoes com `List`;
- excecao personalizada para validacao de preco e quantidade;
- serializacao para salvar e carregar os dados;
- separacao em `model`, `view` e `controller`.

## Uso de Inteligência Artificial

Ferramentas de IA foram utilizadas pelo nosso grupo como apoio durante o projeto com as seguintes finalidades:

- **Planejamento e estruturação:** Para ajudar a organizar a ideia e estrutura geral do projeto, definindo que classes seriam criadas e estruturando a lógica inicial.
- **Revisão de código:** Para verificar o resultado final do programa codificado pelo grupo, a fim de identificar possíveis problemas, bugs e falhas lógicas, otimizando e garantindo um sistema funcional.
- **Documentação:** Para organizar as explicações deste arquivo `README`, deixando o texto mais claro e profissional.

## Como executar

E necessario ter o JDK instalado. O projeto nao depende de frameworks ou
bibliotecas externas.

No PowerShell, a partir da raiz do projeto:

```powershell
New-Item -ItemType Directory -Force bin | Out-Null
javac -encoding UTF-8 -d bin (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp bin Main
```

Em ambientes Linux ou macOS:

```bash
mkdir -p bin
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -cp bin Main
```

Tambem e possivel abrir a pasta em uma IDE, como IntelliJ IDEA ou VS Code, e
executar a classe `Main`.

## Arquivos gerados

Durante o uso, o sistema cria automaticamente:

- `dados/`, com os cadastros serializados em arquivos `.dat`;
- `logs/log.txt`, com registros simples de inicio, alteracoes, exclusoes e erros.

Essas pastas nao precisam existir antes da primeira execucao.

## Referencias

- Material das aulas de Desenvolvimento de Software.
- Documentacao oficial do Java: https://docs.oracle.com/en/java/