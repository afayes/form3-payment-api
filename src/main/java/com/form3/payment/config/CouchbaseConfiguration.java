package com.form3.payment.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.form3.payment.service.CouchbaseHealthCheckService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

/**
 * Couchbase configuration class.
 */
@Configuration
@EnableCouchbaseRepositories(basePackages = "com.form3.payment")
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Inject
    private CouchbaseHealthCheckService couchbaseHealthCheckService;

    @Value("#{'${couchbase.hosts}'.split(',')}")
    private List<String> bootstrapHosts;

    @Value("${couchbase.bucket}")
    private String bucket;

    @Value("${couchbase.password}")
    private String password;

    @Value("${couchbase.bucketHealthCheckRetry:10}")
    private int bucketHealthCheckRetry;

    @Value("${couchbase.bucketHealthCheckInterval:5000}")
    private int bucketHealthCheckInterval;

    @PostConstruct
    public void init() throws InterruptedException {
        // bucket must be ready for Spring Data to bootstrap indexes and views, otherwise Spring will throw an exception
        boolean healthy = couchbaseHealthCheckService.bucketIsHealthy(bucket, bucketHealthCheckRetry, bucketHealthCheckInterval);
        if (!healthy) {
            throw new CouchbaseConfigurationException("Bucket " + bucket + " is not healthy");
        }
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return bootstrapHosts;
    }

    @Override
    protected String getBucketName() {
        return bucket;
    }

    @Override
    protected String getBucketPassword() {
        return password;
    }

    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, true, true);
    }
}
