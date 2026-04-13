# 🛒 N&H Store - Sistema de Gestão 

Este projeto consiste em uma Aplicação Web MVC desenvolvida com Spring Boot para o gerenciamento completo de um marketplace. O sistema permite a administração integrada de Produtos, Lojas (Vendedores), Clientes e Pedidos, focando em um controle de estoque automatizado e uma interface visual amigável.

## 🚀 Tecnologias Utilizadas
* **Backend:** Java, Spring Boot, Spring Data JPA
* **Banco de Dados:** H2 Database (Em memória, facilitando testes)
* **Frontend:** Thymeleaf, HTML5, CSS3 (Bootstrap 5)
* **Ferramentas:** Lombok, Maven, Bean Validation

## 🛠️ Manual de Execução

Siga os passos abaixo para rodar a aplicação localmente:

**1. Preparação do Ambiente:**
* Recomendamos que o projeto seja extraído e executado fora de pastas sincronizadas em nuvem (como OneDrive/Dropbox) para evitar conflitos de permissão e bloqueios do banco de dados local.
* Certifique-se de que a porta `8080` está disponível (porta padrão do Tomcat no Spring Boot).

**2. Executando o Projeto:**
* Clone ou extraia o repositório em uma pasta local.
* Abra o projeto na sua IDE de preferência (VS Code, IntelliJ, Eclipse).
* Execute a classe principal `ApiApplication.java` ou rode o comando no terminal:
  `mvn spring-boot:run`

**3. Acessando a Aplicação:**
* Com o servidor iniciado, abra seu navegador e acesse:
  👉 **http://localhost:8080**
* Você será recebido pelo Dashboard inicial com atalhos para todas as áreas do sistema.

## 📂 Estrutura do Projeto (Entidades)
* **Vendedor:** Representa as lojas parceiras do marketplace, armazenando dados do responsável, nome da loja e status de aprovação.
* **Produto:** O catálogo de mercadorias, contendo descrição, preço, quantidade em estoque e a qual vendedor pertence.
* **Cliente:** Gerencia os dados, contatos e endereços dos compradores.
* **Pedido & ItemPedido:** Classe relacional que amarra a compra. Registra qual cliente comprou, a data, o status e a lista de itens com cálculos de subtotal.

## ⚙️ Regras de Negócio Implementadas
* **Interface Dinâmica & Layouts:** Telas geradas no lado do servidor utilizando fragmentos do Thymeleaf (`th:fragment`), garantindo reaproveitamento de cabeçalhos e rodapés em todo o sistema.
* **Estoque Transacional (ACID):** A baixa no estoque é feita automaticamente ao registrar um novo pedido. O sistema também possui uma funcionalidade de **Cancelamento** que estorna as unidades de volta ao estoque da loja de forma segura (`@Transactional`).
* **Validação de Formulários (Bean Validation):** Regras de preenchimento obrigatório e formatação (ex: e-mail) são validadas no backend e retornam alertas visuais em vermelho diretamente abaixo do input no HTML, sem perder os dados já digitados.
* **Integridade Relacional:** O sistema impede bloqueios abruptos de chaves estrangeiras. Se o usuário tentar excluir uma Loja que tem Produtos, ou um Cliente que tem Pedidos, a exclusão é interrompida suavemente e uma mensagem amigável é exibida.
* **Tratamento Global de Exceções:** Uso de `@ControllerAdvice` para capturar falhas internas e direcionar o usuário para páginas de erro customizadas (Erro 500 e Erro 404), mantendo a navegação segura.


## 📋 Próximos Passos (To-do)
- [ ] Implementar autenticação e login seguro para Vendedores e Clientes (Spring Security).
- [ ] Criar barra de busca de produtos por nome e filtros de preço.
- [ ] Adicionar upload de imagens para os produtos.
- [ ] Refatorar a exclusão de pedidos para um "Soft Delete" (arquivamento de histórico).
