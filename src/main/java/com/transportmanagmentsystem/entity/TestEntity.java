package com.transportmanagmentsystem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TestEntity {

    private Long id;

    private String name;

    private TestInnerEntity testInnerEntity;

    private TestInnerEntity2 testInnerEntity2;
}
