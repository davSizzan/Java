package com.utc.entity.doublepk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserServiceKey implements Serializable {

    @Column(name = "us_hsId",nullable = false)
    private int us_hsId;

    @Column(name = "us_bId",nullable = false)
    private int us_bId;
}
