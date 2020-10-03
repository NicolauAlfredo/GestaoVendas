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
public class Provincia {

    private Integer idProvincia;
    private String nomeProvincia;

    public Provincia() {
        this.idProvincia = 0;
        this.nomeProvincia = "";
    }

    public Provincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
        this.nomeProvincia = "";
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    @Override
    public String toString() {
        return getNomeProvincia();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Produto) {
            Provincia provincia = (Provincia) obj;
            if (provincia.getIdProvincia() == this.getIdProvincia()) {
                return true;
            }
        }
        return false;
    }
}
