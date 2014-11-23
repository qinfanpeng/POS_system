package com.tw.pos.promotionRules;

import com.tw.pos.Promotable;

public interface Promotion {
    void apply(Promotable goods);
}
