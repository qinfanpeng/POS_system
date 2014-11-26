package com.tw.pos.promotionRules;

public interface Promotion<T> {
    T apply(T value);
}
