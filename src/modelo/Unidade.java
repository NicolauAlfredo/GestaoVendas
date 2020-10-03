/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author user
 */
public class Unidade {

    private Integer idUnidade;
    private String nomeUnidade;

    public Unidade() {
        this.idUnidade = idUnidade;
        this.nomeUnidade = "";
    }

    public Unidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
        this.nomeUnidade = "";
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    @Override
    public String toString() {
        return getNomeUnidade();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Unidade) {
            Unidade unidade = (Unidade) obj;
            if (unidade.getIdUnidade() == this.getIdUnidade()) {
                return true;
            }
        }
        return false;
    }
}
