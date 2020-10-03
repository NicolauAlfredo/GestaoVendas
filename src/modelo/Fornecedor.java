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
public class Fornecedor {

    private Integer idFornecedor;
    private String nomeFornecedor;
    private String nifFornecedor;
    private String enderecoFornecedor;
    private String telefoneFornecedor;

    public Fornecedor() {
        this.idFornecedor = 0;
        this.nomeFornecedor = "";
        this.nifFornecedor = "";
        this.enderecoFornecedor = "";
        this.telefoneFornecedor = "";
    }

    public Fornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = "";
        this.nifFornecedor = "";
        this.enderecoFornecedor = "";
        this.telefoneFornecedor = "";
    }

    public String getTelefoneFornecedor() {
        return telefoneFornecedor;
    }

    public void setTelefoneFornecedor(String telefoneFornecedor) {
        this.telefoneFornecedor = telefoneFornecedor;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNifFornecedor() {
        return nifFornecedor;
    }

    public void setNifFornecedor(String nifFornecedor) {
        this.nifFornecedor = nifFornecedor;
    }

    public String getEnderecoFornecedor() {
        return enderecoFornecedor;
    }

    public void setEnderecoFornecedor(String enderecoFornecedor) {
        this.enderecoFornecedor = enderecoFornecedor;
    }

    @Override
    public String toString() {
        return getNomeFornecedor();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fornecedor) {
            Fornecedor fornecedor = (Fornecedor) obj;
            if (fornecedor.getIdFornecedor() == this.getIdFornecedor()) {
                return true;
            }
        }
        return false;
    }

}
