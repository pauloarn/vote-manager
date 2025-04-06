package com.nt.votemanager.enums;

public enum VoteEnum {
    SIM("SIM"),
    NAO("NAO");

    private String description;

    VoteEnum(String description) {
        this.description = description;
    }
}
