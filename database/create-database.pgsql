CREATE DATABASE bookadaisicalDatabase;
CREATE USER bookadaisicalBackend WITH PASSWORD 'have1NiceB00k';
ALTER ROLE bookadaisicalBackend SET client_encoding TO 'utf8';
ALTER ROLE bookadaisicalBackend SET default_transaction_isolation TO 'read committed';
ALTER ROLE bookadaisicalBackend SET timezone TO 'UTC';
GRANT ALL PRIVILEGES ON DATABASE bookadaisicalDatabase TO bookadaisicalBackend;