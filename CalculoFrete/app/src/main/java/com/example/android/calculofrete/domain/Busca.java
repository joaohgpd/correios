package com.example.android.calculofrete.domain;

/**
 * Created by joao on 04/03/2016.
 */
public class Busca {
    int cep_origem;
    int cep_dest;
    float peso;
    float valor;
    float sedex;
    float pac;

    public int getCep_dest() {
        return cep_dest;
    }

    public void setCep_dest(int cep_dest) {
        this.cep_dest = cep_dest;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }


    public int getCep_origem() {
        return cep_origem;
    }

    public void setCep_origem(int cep_origem) {
        this.cep_origem = cep_origem;
    }

    public float getSedex() {
        return sedex;
    }

    public void setSedex(float sedex) {
        this.sedex = sedex;
    }

    public float getPac() {
        return pac;
    }

    public void setPac(float pac) {
        this.pac = pac;
    }

    public String toString(){
        String value = "";
        value = "[cep_origem] "+this.cep_origem+"\n[cep_destino] "+this.cep_dest+"\n[peso] "+this.peso+"\n[valor] "+this.valor+"\n[Pac] "+this.pac+"\n[Sedex] "+this.sedex;
        return value;
    }
}
