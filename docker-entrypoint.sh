#!/bin/bash
set -e

# Inicia o MySQL em segundo plano
mysqld_safe &

# Espera o MySQL iniciar
while ! mysqladmin ping --silent; do
    echo "Esperando MySQL iniciar..."
    sleep 2
done

# Executa qualquer script SQL de inicialização que você tenha (opcional)
# mysql -u root -p${MYSQL_ROOT_PASSWORD} ${MYSQL_DATABASE} < /path/to/init.sql

# Inicia a aplicação Spring Boot
exec java -jar /app/biblioteca-admin-api-final.jar
