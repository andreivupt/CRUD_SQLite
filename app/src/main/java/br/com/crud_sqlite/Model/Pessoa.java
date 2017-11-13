package br.com.crud_sqlite.Model;

/**
 * Created by andrei.vupt on 12/11/2017.
 */

public class Pessoa {

    private String nome;
    private int idade;
    private String ramo;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
