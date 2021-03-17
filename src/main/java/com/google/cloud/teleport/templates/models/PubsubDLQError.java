package com.google.cloud.teleport.templates.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class PubsubDLQError {

    String timestamp;
    String payloadString;
    String payloadBytes;
    String pubsubTopicOrSubscription;
    List<Attribute> attributes;

    public PubsubDLQError(String timestamp, String payloadString, String payloadBytes, List<Attribute> attributes, String pubsubTopic) {
        this.pubsubTopicOrSubscription = pubsubTopic;
        this.timestamp = timestamp;
        this.payloadString = payloadString;
        this.payloadBytes = payloadBytes;
        this.attributes = attributes;
    }

    public String getPubsubTopicOrSubscription() {
        return pubsubTopicOrSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PubsubDLQError)) return false;

        PubsubDLQError that = (PubsubDLQError) o;

        return new EqualsBuilder()
                .append(getPubsubTopicOrSubscription(), that.getPubsubTopicOrSubscription())
                .append(getTimestamp(), that.getTimestamp())
                .append(getPayloadString(), that.getPayloadString())
                .append(getPayloadBytes(), that.getPayloadBytes())
                .append(getAttributes(), that.getAttributes())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPubsubTopicOrSubscription())
                .append(getTimestamp())
                .append(getPayloadString())
                .append(getPayloadBytes())
                .append(getAttributes())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "PubsubDLQError{" +
                "timestamp=" + timestamp +
                ", PayloadString='" + payloadString + '\'' +
                ", payloadBytes=" + payloadBytes +
                ", attributes=" + attributes +
                '}';
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPayloadString() {
        return payloadString;
    }

    public String getPayloadBytes() {
        return payloadBytes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}

