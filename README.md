docker network create toxin-network

docker run --network=toxin-network -p 5432:5432 --name toxin-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=database -d postgres

docker build -t toxin-backend .\toxin-backend\

docker run -p 8080:8080 -d --name toxin-backend --network=toxin-network -e SPRING_DATASOURCE_HOST=toxin-postgres toxin-backend

docker build -t toxin-frontend .\toxin-frontend\

docker run -p 4200:80 -d --name toxin-frontend --network=toxin-network toxin-frontend

docker compose -f docker-compose.yaml -p toxin-studio up -d --build
