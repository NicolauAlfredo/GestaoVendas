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
public class Funcionario {

    private Integer idFuncionario;
    private String nomeFuncionario;
    private String biFuncionario;
    private String telefoneFuncionario;
    private Municipio municipio;

    public Funcionario() {
        this.idFuncionario = 0;
        this.nomeFuncionario = "";
        this.biFuncionario = "";
        this.telefoneFuncionario = "";
        this.municipio = new Municipio();
    }

    public Funcionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = "";
        this.biFuncionario = "";
        this.telefoneFuncionario = "";
        this.municipio = new Municipio();
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getBiFuncionario() {
        return biFuncionario;
    }

    public void setBiFuncionario(String biFuncionario) {
        this.biFuncionario = biFuncionario;
    }

    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    @Override
    public String toString() {
        return getNomeFuncionario();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) obj;
            if (funcionario.getIdFuncionario() == this.getIdFuncionario()) {
                return true;
            }
        }
        return false;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
