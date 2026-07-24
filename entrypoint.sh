#!/bin/sh
# Parse postgresql://user:pass@host/dbname or postgresql://user:pass@host:port/dbname
if [ -n "$DATABASE_URL" ]; then
  # Remove postgresql:// prefix
  REST="${DATABASE_URL#postgresql://}"
  REST="${REST#postgres://}"

  # Extract user (before :)
  DB_USER="${REST%%:*}"
  # Extract rest after user:
  REST="${REST#*:}"
  # Extract password (before @)
  DB_PASS="${REST%%@*}"
  # Extract rest after @
  REST="${REST#*@}"

  # Check if host contains :port
  if echo "$REST" | grep -q ':'; then
    DB_HOST="${REST%%:*}"
    DB_PORT=$(echo "$REST" | sed 's|:\([^/]*\)/.*|\1|')
  else
    DB_HOST="${REST%%/*}"
    DB_PORT="5432"
  fi

  DB_NAME="${REST#*/}"
  DB_NAME="${DB_NAME%%\?*}"

  export DB_USER="$DB_USER"
  export DB_PASS="$DB_PASS"
  export DATABASE_URL="jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}"

  echo "=== DB PARSED ==="
  echo "HOST=$DB_HOST"
  echo "PORT=$DB_PORT"
  echo "NAME=$DB_NAME"
  echo "USER=$DB_USER"
  echo "URL=$DATABASE_URL"
  echo "================="
fi

exec java -jar app.jar
