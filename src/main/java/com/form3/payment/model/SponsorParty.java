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
        "account_number",
        "bank_id",
        "bank_id_code"
})
public class SponsorParty {

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("bank_id_code")
    private String bankIdCode;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("account_number")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("bank_id")
    public String getBankId() {
        return bankId;
    }

    @JsonProperty("bank_id")
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @JsonProperty("bank_id_code")
    public String getBankIdCode() {
        return bankIdCode;
    }

    @JsonProperty("bank_id_code")
    public void setBankIdCode(String bankIdCode) {
        this.bankIdCode = bankIdCode;
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
