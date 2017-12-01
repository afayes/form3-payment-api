package com.form3.payment.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contract_reference",
        "exchange_rate",
        "original_amount",
        "original_currency"
})
public class Fx {

    @JsonProperty("contract_reference")
    private String contractReference;

    @JsonProperty("exchange_rate")
    private String exchangeRate;

    @JsonProperty("original_amount")
    private String originalAmount;

    @JsonProperty("original_currency")
    private String originalCurrency;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contract_reference")
    public String getContractReference() {
        return contractReference;
    }

    @JsonProperty("contract_reference")
    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    @JsonProperty("exchange_rate")
    public String getExchangeRate() {
        return exchangeRate;
    }

    @JsonProperty("exchange_rate")
    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @JsonProperty("original_amount")
    public String getOriginalAmount() {
        return originalAmount;
    }

    @JsonProperty("original_amount")
    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    @JsonProperty("original_currency")
    public String getOriginalCurrency() {
        return originalCurrency;
    }

    @JsonProperty("original_currency")
    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
