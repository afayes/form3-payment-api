package com.form3.payment.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bearer_code",
        "sender_charges",
        "receiver_charges_amount",
        "receiver_charges_currency"
})
public class ChargesInformation {

    @JsonProperty("bearer_code")
    private String bearerCode;

    @JsonProperty("sender_charges")
    private List<SenderCharge> senderCharges = null;

    @JsonProperty("receiver_charges_amount")
    private String receiverChargesAmount;

    @JsonProperty("receiver_charges_currency")
    private String receiverChargesCurrency;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bearer_code")
    public String getBearerCode() {
        return bearerCode;
    }

    @JsonProperty("bearer_code")
    public void setBearerCode(String bearerCode) {
        this.bearerCode = bearerCode;
    }

    @JsonProperty("sender_charges")
    public List<SenderCharge> getSenderCharges() {
        return senderCharges;
    }

    @JsonProperty("sender_charges")
    public void setSenderCharges(List<SenderCharge> senderCharges) {
        this.senderCharges = senderCharges;
    }

    @JsonProperty("receiver_charges_amount")
    public String getReceiverChargesAmount() {
        return receiverChargesAmount;
    }

    @JsonProperty("receiver_charges_amount")
    public void setReceiverChargesAmount(String receiverChargesAmount) {
        this.receiverChargesAmount = receiverChargesAmount;
    }

    @JsonProperty("receiver_charges_currency")
    public String getReceiverChargesCurrency() {
        return receiverChargesCurrency;
    }

    @JsonProperty("receiver_charges_currency")
    public void setReceiverChargesCurrency(String receiverChargesCurrency) {
        this.receiverChargesCurrency = receiverChargesCurrency;
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
