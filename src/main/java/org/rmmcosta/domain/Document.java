package org.rmmcosta.domain;

import java.util.Map;

public record Document(Map<String,String> attributes) {
    public String getAttributeValue(String attributeName) {
        return attributes.get(attributeName);
    }
}
