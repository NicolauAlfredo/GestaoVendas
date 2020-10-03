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
public class Categoria {

    private Integer idCategoria;
    private String nomeCategoria;

    public Categoria() {
        this.idCategoria = 0;
        this.nomeCategoria = "";
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = "";
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return getNomeCategoria();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Categoria) {
            Categoria categoria = (Categoria) obj;
            if (categoria.getIdCategoria() == this.getIdCategoria()) {
                return true;
            }
        }
        return false;
    }
}
