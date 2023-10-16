FROM postgres

ENV POSTGRES_USER songs_user
ENV POSTGRES_PASSWORD songs_password
ENV POSTGRES_DB songs


COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432
