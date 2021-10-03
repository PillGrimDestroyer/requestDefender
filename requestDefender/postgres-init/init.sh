#!/usr/bin/env bash
set -e

#
#  ВНИМАНИЕ!  Этот файл запускается только тогда,
#             когда папка volumes отсутствует при запуске docker-compose up
#

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL

    ALTER ROLE postgres WITH ENCRYPTED PASSWORD '111';

    CREATE USER request_defender WITH ENCRYPTED PASSWORD '111';
    CREATE DATABASE request_defender WITH OWNER request_defender;
    GRANT ALL ON DATABASE request_defender TO request_defender;

EOSQL
