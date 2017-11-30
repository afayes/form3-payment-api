package com.form3.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

@Configuration
@EnableCouchbaseRepositories(basePackages = "com.form3.payment")
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("#{'${couchbase.hosts}'.split(',')}")
    private List<String> bootstrapHosts;

    @Value("${couchbase.bucket}")
    private String bucket;

    @Value("${couchbase.password}")
    private String password;

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
