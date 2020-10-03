/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author user
 */
public class Produto {

    private Integer idProduto;
    private String codigoBarraProduto;
    private String nomeProduto;
    private Double precoVendaProduto;
    private Double precoCompraProduto;
    private String dataValidadeProduto;
    private Integer quantidadeEstoqueProduto;
    private Categoria categoria;
    private Unidade unidade;

    public Produto() {
        this.idProduto = 0;
        this.codigoBarraProduto = "";
        this.nomeProduto = "";
        this.precoVendaProduto = 0.0;
        this.precoCompraProduto = 0.0;
        this.quantidadeEstoqueProduto = 0;
        this.dataValidadeProduto = "";
        this.unidade = new Unidade();
        this.categoria = new Categoria();
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
        this.codigoBarraProduto = "";
        this.nomeProduto = "";
        this.precoVendaProduto = 0.0;
        this.precoCompraProduto = 0.0;
        this.quantidadeEstoqueProduto = 0;
        this.dataValidadeProduto = "";
        this.unidade = new Unidade();
        this.categoria = new Categoria();
    }

    public Double getPrecoCompraProduto() {
        return precoCompraProduto;
    }

    public void setPrecoCompraProduto(Double precoCompraProduto) {
        this.precoCompraProduto = precoCompraProduto;
    }

    public String getDataValidadeProduto() {
        return dataValidadeProduto;
    }

    public void setDataValidadeProduto(String dataValidadeProduto) {
        this.dataValidadeProduto = dataValidadeProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodigoBarraProduto() {
        return codigoBarraProduto;
    }

    public void setCodigoBarraProduto(String codigoBarraProduto) {
        this.codigoBarraProduto = codigoBarraProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPrecoVendaProduto() {
        return precoVendaProduto;
    }

    public void setPrecoVendaProduto(Double precoVendaProduto) {
        this.precoVendaProduto = precoVendaProduto;
    }

    public Integer getQuantidadeEstoqueProduto() {
        return quantidadeEstoqueProduto;
    }

    public void setQuantidadeEstoqueProduto(Integer quantidadeEstoqueProduto) {
        this.quantidadeEstoqueProduto = quantidadeEstoqueProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return getNomeProduto();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Produto) {
            Produto produto = (Produto) obj;
            if (produto.getIdProduto() == this.getIdProduto()) {
                return true;
            }
        }
        return false;
    }

}
