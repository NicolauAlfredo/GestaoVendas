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
public class ItemVenda {

    private int idItemVenda;
    private Venda venda;
    private Produto produto;
    private int quantidadeItemVenda;
    private Double valorUnitarioItemVenda;

    public ItemVenda() {
        this.idItemVenda = 0;
        this.venda = new Venda();
        this.produto = new Produto();
        this.quantidadeItemVenda = 0;
        this.valorUnitarioItemVenda = 0.0;
    }

    public ItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
        this.venda = new Venda();
        this.produto = new Produto();
        this.quantidadeItemVenda = 0;
        this.valorUnitarioItemVenda = 0.0;
    }

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadeItemVenda() {
        return quantidadeItemVenda;
    }

    public void setQuantidadeItemVenda(int quantidadeItemVenda) {
        this.quantidadeItemVenda = quantidadeItemVenda;
    }

    public Double getValorUnitarioItemVenda() {
        return valorUnitarioItemVenda;
    }

    public void setValorUnitarioItemVenda(Double valorUnitarioItemVenda) {
        this.valorUnitarioItemVenda = valorUnitarioItemVenda;
    }

    @Override
    public String toString() {
        return getProduto().getNomeProduto();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemVenda) {
            ItemVenda iv = (ItemVenda) obj;
            if (iv.getIdItemVenda() == this.getIdItemVenda()) {
                return true;
            }
        }
        return false;
    }
}
