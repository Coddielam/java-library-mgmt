FROM postgres:17.2

COPY ./migration-scripts/* /docker-entrypoint-initdb.d/