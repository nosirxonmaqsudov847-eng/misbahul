#!/bin/sh
# Convert postgresql:// to jdbc:postgresql:// if needed
if [ -n "$DATABASE_URL" ]; then
  JDBC_URL=$(echo "$DATABASE_URL" | sed 's|^postgresql://|jdbc:postgresql://|; s|^postgres://|jdbc:postgresql://|')
  export DATABASE_URL="$JDBC_URL"
  echo "DATABASE_URL=$DATABASE_URL"
fi

exec java -jar app.jar
