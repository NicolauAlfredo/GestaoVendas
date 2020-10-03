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
public class ItemCompra {

    private int idItemCompra;
    private Compra compra;
    private Produto produto;
    private int quantidadeItemCompra;
    private Double valorUnitarioItemCompra;

    public ItemCompra() {
        this.idItemCompra = 0;
        this.compra = new Compra();
        this.produto = new Produto();
        this.quantidadeItemCompra = 0;
        this.valorUnitarioItemCompra = 0.0;
    }

    public ItemCompra(int idItemCompra) {
        this.idItemCompra = idItemCompra;
        this.compra = new Compra();
        this.produto = new Produto();
        this.quantidadeItemCompra = 0;
        this.valorUnitarioItemCompra = 0.0;
    }

    public int getIdItemCompra() {
        return idItemCompra;
    }

    public void setIdItemCompra(int idItemCompra) {
        this.idItemCompra = idItemCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadeItemCompra() {
        return quantidadeItemCompra;
    }

    public void setQuantidadeItemCompra(int quantidadeItemCompra) {
        this.quantidadeItemCompra = quantidadeItemCompra;
    }

    public Double getValorUnitarioItemCompra() {
        return valorUnitarioItemCompra;
    }

    public void setValorUnitarioItemCompra(Double valorUnitarioItemCompra) {
        this.valorUnitarioItemCompra = valorUnitarioItemCompra;
    }

    @Override
    public String toString() {
        return getProduto().getNomeProduto();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ItemCompra) {
            ItemCompra itemCompra = (ItemCompra) o;
            if (itemCompra.getIdItemCompra() == this.getIdItemCompra()) {
                return true;
            }
        }
        return false;
    }
}
