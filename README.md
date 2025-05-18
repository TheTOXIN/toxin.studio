docker run --network=toxin-network -p 5432:5432 --name toxin-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=toxin -d postgres

docker build -t toxin-backend .

docker network create toxin-network

docker run -p 8080:8080 -d --name toxin-backend --network=toxin-network -e SPRING_DATASOURCE_HOST=toxin-postgres toxin-backend