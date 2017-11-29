package com.form3.payment;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

@Configuration
@EnableCouchbaseRepositories(basePackages = "com.form3.payment")
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "payment";
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }

    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, true, true);
    }
}
