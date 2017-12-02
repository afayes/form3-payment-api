# taken from https://github.com/arun-gupta/docker-images/tree/master/couchbase-server
FROM couchbase/server:enterprise-4.5.0-DP1

COPY configure-node.sh /opt/couchbase

CMD ["/opt/couchbase/configure-node.sh"]

