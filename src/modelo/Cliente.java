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
public class Cliente {

    Integer idCliente;
    String nomeCliente;
    String nifCliente;
    String enderecoCliente;
    String telefoneCliente;

    public Cliente() {
        this.idCliente = 0;
        this.nomeCliente = "";
        this.nifCliente = "";
        this.enderecoCliente = "";
        this.telefoneCliente = "";
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = "";
        this.nifCliente = "";
        this.enderecoCliente = "";
        this.telefoneCliente = "";
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    @Override
    public String toString() {
        return getNomeCliente();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            if (cliente.getIdCliente() == this.getIdCliente()) {
                return true;
            }
        }
        return false;
    }
}
