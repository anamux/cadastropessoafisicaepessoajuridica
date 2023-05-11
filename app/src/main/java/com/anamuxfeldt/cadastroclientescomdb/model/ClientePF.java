package com.anamuxfeldt.cadastroclientescomdb.model;

public class ClientePF extends Cliente{
    private int fk;
    private String cpf;
    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
