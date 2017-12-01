package com.form3.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * A service that allows you to check the health status of Couchbase buckets.
 */
@Service
public class CouchbaseHealthCheckService {

    /**
     * get bucket URL format is of the form: <protocol>://<host>:<port><bucket_path>/<bucket_name>
     */
    private static final String GET_BUCKET_URL_FORMAT = "%s://%s:%s%s%s";

    private static final String GET_BUCKET_PATH = "/pools/default/buckets/";

    private static final String HEALTHY_VALUE = "healthy";

    private final RestTemplate restTemplate;

    @Value("#{'${couchbase.protocol:http}'}")
    private String couchbaseProtocol;

    @Value("#{'${couchbase.hosts}'.split(',')}")
    private List<String> couchbaseHosts;

    @Value("#{'${couchbase.port:8091}'}")
    private String couchbasePort;

    public CouchbaseHealthCheckService() {
        restTemplate = new RestTemplate();
    }

    public boolean bucketIsHealthy(String bucketName, int retryAttemptsOnFailure, int retryInterval) throws InterruptedException {
        for (int i = 0; i < retryAttemptsOnFailure; i++) {
                for (String couchbaseHost : couchbaseHosts) {
                    try {
                        String getBucketUrl = String.format(GET_BUCKET_URL_FORMAT, couchbaseProtocol, couchbaseHost, couchbasePort, GET_BUCKET_PATH, bucketName);
                        if (bucketIsHealthy(getBucketUrl))
                            return true;

                    } catch (RestClientException e) {
                        // todo log error
                    }
                }

                Thread.sleep(retryInterval);
        }

        return false;
    }

    private boolean bucketIsHealthy(final String getBucketUrl) {
        ResponseEntity<BucketResponse> response = restTemplate.getForEntity(getBucketUrl, BucketResponse.class);

        HttpStatus statusCode = response.getStatusCode();
        BucketResponse body = response.getBody();

        for (BucketNode node : body.getNodes()) {
            if (statusCode.equals(HttpStatus.OK) && node.getStatus().equals(HEALTHY_VALUE)) {
                return true;
            }
        }
        return false;
    }

    public static class BucketResponse {
        List<BucketNode> nodes;

        public List<BucketNode> getNodes() {
            return nodes;
        }

        public void setNodes(final List<BucketNode> nodes) {
            this.nodes = nodes;
        }

        @Override
        public String toString() {
            return "BucketResponse{" +
                    "nodes=" + nodes +
                    '}';
        }
    }

    private static class BucketNode {
        String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(final String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "BucketNode{" +
                    "status='" + status + '\'' +
                    '}';
        }
    }
}
