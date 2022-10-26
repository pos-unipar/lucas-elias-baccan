#!/bin/bash
set -e

rake db:create
rake db:migrate

rm -f /app/tmp/pids/server.pid

exec "$@"