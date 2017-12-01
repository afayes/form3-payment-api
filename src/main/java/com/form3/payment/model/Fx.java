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

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Fx))
            return false;

        final Fx fx = (Fx) o;

        if (getContractReference() != null ? !getContractReference().equals(fx.getContractReference()) : fx.getContractReference() != null)
            return false;
        if (getExchangeRate() != null ? !getExchangeRate().equals(fx.getExchangeRate()) : fx.getExchangeRate() != null)
            return false;
        if (getOriginalAmount() != null ? !getOriginalAmount().equals(fx.getOriginalAmount()) : fx.getOriginalAmount() != null)
            return false;
        if (getOriginalCurrency() != null ? !getOriginalCurrency().equals(fx.getOriginalCurrency()) : fx.getOriginalCurrency() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(fx.getAdditionalProperties()) : fx.getAdditionalProperties() == null;
    }

    @Override
    public int hashCode() {
        int result = getContractReference() != null ? getContractReference().hashCode() : 0;
        result = 31 * result + (getExchangeRate() != null ? getExchangeRate().hashCode() : 0);
        result = 31 * result + (getOriginalAmount() != null ? getOriginalAmount().hashCode() : 0);
        result = 31 * result + (getOriginalCurrency() != null ? getOriginalCurrency().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

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
