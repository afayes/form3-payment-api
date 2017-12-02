# Based on from https://github.com/arun-gupta/docker-images/tree/master/couchbase-server
#!/bin/bash

set -m

/entrypoint.sh couchbase-server &

sleep 15

curl -v -X POST http://127.0.0.1:8091/pools/default -d memoryQuota=256 -d indexMemoryQuota=256 -d ftsMemoryQuota=256

curl -v http://127.0.0.1:8091/node/controller/setupServices -d services=kv%2Cn1ql%2Cindex%2Cfts

curl -v http://127.0.0.1:8091/settings/web -d port=8091 -d username=Administrator -d password=password

# curl -v -u Administrator:password -X POST http://127.0.0.1:8091/sampleBuckets/install -d '["travel-sample"]'

# find free port for bucket
BASE_PORT=65000
INCREMENT=1

port=$BASE_PORT
isfree=$(netstat -taln | grep $port)

while [[ -n "$isfree" ]]; do
    port=$[port+INCREMENT]
    isfree=$(netstat -taln | grep $port)
done

echo "using port for payment bucket: $port"

# create payment bucket
curl -X POST -u Administrator:password -d name=payment -d ramQuotaMB=100 -d authType=none -d proxyPort=$port http://127.0.0.1:8091/pools/default/buckets

curl -X POST -u Administrator:password http://127.0.0.1:8091/settings/indexes -d "storageMode=forestdb"

echo "Couchbase server has started"


fg 1


