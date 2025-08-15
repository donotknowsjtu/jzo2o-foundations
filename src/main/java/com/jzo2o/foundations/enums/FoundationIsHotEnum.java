package com.jzo2o.foundations.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FoundationIsHotEnum {
    NOT_HOT(0, "非热门"),
    HOT(1, "热门");

    private int isHot;
    private String description;

    public boolean equals(Integer isHot) {
        return this.isHot == isHot;
    }
    public boolean equals(FoundationIsHotEnum isHotEnum) {
        return isHotEnum != null && isHotEnum.isHot == this.getIsHot();
    }
}
