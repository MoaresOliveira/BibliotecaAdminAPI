# BibliotecaAdminAPI
API para gerenciamento de Clientes e Funcionários, utilizando a API do ViaCEP.

## ⚙ Funcionalidades
- Cadastrar clientes e funcionários
- Atualizar dados de clientes e funcionários
- Apagar funcionários e clientes
- Apresentar o CEP com a maior quantidade de funcionários, buscando as informações pela api ViaCEP
- Listar funcionários e clientes utilizando paginação.

## 🚀 Tecnologias
- **Java 8**
- **Spring Boot**
- **MySQL**
- **Maven**

## 🖥 Rodando a aplicação

Esta aplicação possui 5 variáveis de ambiente relacionado a conexão com banco de dados (MySQL), sendo 4 delas com valores padrões:
1. **MYSQL_HOST** (valor padrão: localhost)
2. **MYSQL_PORT** (valor padrão: 3306)
3. **MYSQL_DATABASE** (valor padrão: biblioteca)
4. **MYSQL_USER** (valor padrão: root)
5. **MYSQL_USER_PASSWORD** (sem valor padrão)

As variáveis devem ser definidas de acordo com o ambiente para o funcionamento correto da aplicação.

Após executada a aplicação ela rodará por padrão na porta `8080`. 

### Então poderá ter acesso ao Swagger da aplicação através da url:

- [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

![image](https://user-images.githubusercontent.com/80995654/211151184-3547bd68-0e53-4868-93b4-766c7de29453.png)
![image](https://user-images.githubusercontent.com/80995654/211151166-f03b38ff-10a2-4ab1-beaa-d54e64074619.png)
