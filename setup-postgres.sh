#!/bin/bash

# XPEHO Formation Spring - Setup Script
# This script starts PostgreSQL and the Spring Boot application

set -e

echo "================================"
echo "XPEHO Formation Spring"
echo "PostgreSQL Setup"
echo "================================"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

# Check if Docker Compose is installed
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose is not installed. Please install Docker Compose first."
    exit 1
fi

echo "✅ Docker and Docker Compose are installed."

# Start PostgreSQL
echo ""
echo "Starting PostgreSQL container..."
docker-compose up -d

# Wait for PostgreSQL to be healthy
echo ""
echo "Waiting for PostgreSQL to be ready..."
max_attempts=30
attempt=1

while [ $attempt -le $max_attempts ]; do
    if docker-compose exec postgres pg_isready -U postgres &> /dev/null; then
        echo "✅ PostgreSQL is ready!"
        break
    fi
    echo "⏳ Waiting... (attempt $attempt/$max_attempts)"
    sleep 2
    attempt=$((attempt + 1))
done

if [ $attempt -gt $max_attempts ]; then
    echo "❌ PostgreSQL failed to start. Check logs with: docker-compose logs"
    exit 1
fi

echo ""
echo "================================"
echo "PostgreSQL Setup Complete!"
echo "================================"
echo ""
echo "Connection Details:"
echo "  Host: localhost"
echo "  Port: 5432"
echo "  Database: xpeho_db"
echo "  Username: postgres"
echo "  Password: postgres"
echo ""
echo "Next steps:"
echo "1. Start Spring Boot: mvn clean spring-boot:run"
echo "2. Access API: http://localhost:8080/movies"
echo "3. Swagger UI: http://localhost:8080/api"
echo ""
echo "To stop PostgreSQL: docker-compose down"
echo "To view logs: docker-compose logs postgres"

