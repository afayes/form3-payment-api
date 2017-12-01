package com.form3.payment.model;

import java.util.List;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ChargesInformation))
            return false;

        final ChargesInformation that = (ChargesInformation) o;

        if (getBearerCode() != null ? !getBearerCode().equals(that.getBearerCode()) : that.getBearerCode() != null)
            return false;
        if (getSenderCharges() != null ? !getSenderCharges().equals(that.getSenderCharges()) : that.getSenderCharges() != null)
            return false;
        if (getReceiverChargesAmount() != null ? !getReceiverChargesAmount().equals(that.getReceiverChargesAmount()) : that.getReceiverChargesAmount() != null)
            return false;
        return getReceiverChargesCurrency() != null ? getReceiverChargesCurrency().equals(that.getReceiverChargesCurrency()) : that.getReceiverChargesCurrency() == null;
    }

    @Override
    public int hashCode() {
        int result = getBearerCode() != null ? getBearerCode().hashCode() : 0;
        result = 31 * result + (getSenderCharges() != null ? getSenderCharges().hashCode() : 0);
        result = 31 * result + (getReceiverChargesAmount() != null ? getReceiverChargesAmount().hashCode() : 0);
        result = 31 * result + (getReceiverChargesCurrency() != null ? getReceiverChargesCurrency().hashCode() : 0);
        return result;
    }

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
}
