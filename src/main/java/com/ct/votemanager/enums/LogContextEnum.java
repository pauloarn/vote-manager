package com.ct.votemanager.enums;

public enum LogContextEnum {
    EXTERNAL_CONTEXT("external"),
    API_CONTEXT("api");


    private String descricao;

    LogContextEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getDescricao(String arg) {
        return this.descricao + " :: " + arg;
    }
}
