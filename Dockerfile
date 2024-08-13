# Usar uma imagem base do OpenJDK 8 com Alpine para um tamanho de imagem reduzido
FROM openjdk:8-jdk-alpine

# Instalar MySQL e outras dependências necessárias
RUN apk add --no-cache mysql mysql-client bash

# Criar diretórios para MySQL
RUN mkdir -p /var/lib/mysql /var/run/mysqld && \
    chown -R mysql:mysql /var/lib/mysql /var/run/mysqld

# Copiar o script de inicialização do MySQL
COPY docker-entrypoint.sh /usr/local/bin/

# Definir permissões para o script de inicialização
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

# Criar um diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o .jar da aplicação Spring Boot para o diretório de trabalho
COPY target/biblioteca-admin-api-final.jar /app/biblioteca-admin-api-final.jar

# Definir as variáveis de ambiente para o MySQL
ENV MYSQL_ROOT_PASSWORD=root \
    MYSQL_DATABASE=biblioteca \
    MYSQL_USER=root \
    MYSQL_PASSWORD=root

# Expor as portas padrão do MySQL e Spring Boot
EXPOSE 3306 8080

# Comando de entrada do container, que primeiro inicializa o MySQL e depois a aplicação Spring Boot
ENTRYPOINT ["docker-entrypoint.sh"]

