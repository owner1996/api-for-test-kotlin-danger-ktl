#!/usr/bin/env bash

gradle clean assemble

echo "Copy files..."

scp -i ~/.ssh/id_rsa \
    build/libs/api-for-test-kotlin-danger-ktl-0.0.1-SNAPSHOT.jar \
    root@31.41.154.185:/home/owner/

echo "Restart server..."

ssh -tt -i ~/.ssh/id_rsa root@31.41.154.185 <<EOF

pgrep java | xargs kill -9
nohup java -jar /home/owner/api-for-test-kotlin-danger-ktl-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'