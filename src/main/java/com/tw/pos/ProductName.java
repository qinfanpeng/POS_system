package com.tw.pos;

public enum ProductName {
    PEAR("pear"),
    APPLE("apple");

    private String name;

    ProductName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
