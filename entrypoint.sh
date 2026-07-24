#!/bin/sh
# Parse postgresql:// URL and convert to JDBC format
# postgresql://user:pass@host:port/dbname -> jdbc:postgresql://host:port/dbname
if [ -n "$DATABASE_URL" ]; then
  # Extract components from postgresql://user:pass@host:port/dbname
  DB_USER=$(echo "$DATABASE_URL" | sed -n 's|.*://\([^:]*\):.*|\1|p')
  DB_PASS=$(echo "$DATABASE_URL" | sed -n 's|.*://[^:]*:\([^@]*\)@.*|\1|p')
  DB_HOST=$(echo "$DATABASE_URL" | sed -n 's|.*@\([^:]*\):.*|\1|p')
  DB_PORT=$(echo "$DATABASE_URL" | sed -n 's|.*:\([0-9]*\)/.*|\1|p')
  DB_NAME=$(echo "$DATABASE_URL" | sed -n 's|.*/\([^?]*\).*|\1|p')

  export DB_USER="$DB_USER"
  export DB_PASS="$DB_PASS"
  export DATABASE_URL="jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}"

  echo "Parsed DB: host=$DB_HOST port=$DB_PORT name=$DB_NAME user=$DB_USER"
fi

exec java -jar app.jar
