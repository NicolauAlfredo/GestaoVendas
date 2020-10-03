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
public class Municipio {

    private Integer idMunicipio;
    private String nomeMunicipio;
    private Provincia provincia;

    public Municipio() {
        this.idMunicipio = 0;
        this.nomeMunicipio = "";
        this.provincia = new Provincia();
    }

    public Municipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
        this.nomeMunicipio = "";
        this.provincia = new Provincia();
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return getNomeMunicipio();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Produto) {
            Municipio municipio = (Municipio) obj;
            if (municipio.getIdMunicipio() == this.getIdMunicipio()) {
                return true;
            }
        }
        return false;
    }
}
