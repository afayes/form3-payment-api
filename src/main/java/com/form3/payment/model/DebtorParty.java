package com.form3.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "account_name",
        "account_number",
        "account_number_code",
        "address",
        "bank_id",
        "bank_id_code",
        "name"
})
public class DebtorParty {

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_number_code")
    private String accountNumberCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("bank_id_code")
    private String bankIdCode;

    @JsonProperty("name")
    private String name;

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DebtorParty))
            return false;

        final DebtorParty that = (DebtorParty) o;

        if (getAccountName() != null ? !getAccountName().equals(that.getAccountName()) : that.getAccountName() != null)
            return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals(that.getAccountNumber()) : that.getAccountNumber() != null)
            return false;
        if (getAccountNumberCode() != null ? !getAccountNumberCode().equals(that.getAccountNumberCode()) : that.getAccountNumberCode() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null)
            return false;
        if (getBankId() != null ? !getBankId().equals(that.getBankId()) : that.getBankId() != null)
            return false;
        if (getBankIdCode() != null ? !getBankIdCode().equals(that.getBankIdCode()) : that.getBankIdCode() != null)
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getAccountName() != null ? getAccountName().hashCode() : 0;
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getAccountNumberCode() != null ? getAccountNumberCode().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getBankId() != null ? getBankId().hashCode() : 0);
        result = 31 * result + (getBankIdCode() != null ? getBankIdCode().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @JsonProperty("account_name")
    public String getAccountName() {
        return accountName;
    }

    @JsonProperty("account_name")
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @JsonProperty("account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("account_number")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("account_number_code")
    public String getAccountNumberCode() {
        return accountNumberCode;
    }

    @JsonProperty("account_number_code")
    public void setAccountNumberCode(String accountNumberCode) {
        this.accountNumberCode = accountNumberCode;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}