/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Situacao;

/**
 *
 * @author user
 */
public class Compra {

    private int idCompra;
    private Fornecedor fornecedor;
    private Date dataCompra;
    private Double valorTotalCompra;
    private Situacao situacaoCompra;
    private List<ItemCompra> itens;
    private List<ItemCompra> itensRemover;

    public Compra() {
        this.idCompra = 0;
        this.fornecedor = new Fornecedor();
        this.dataCompra = new Date();
        this.valorTotalCompra = 0.0;
        this.itens = new ArrayList<>();
        this.itensRemover = new ArrayList<>();
    }

    public Compra(int idCompra) {
        this.idCompra = idCompra;
        this.fornecedor = new Fornecedor();
        this.dataCompra = new Date();
        this.valorTotalCompra = 0.0;
        this.itens = new ArrayList<>();
        this.itensRemover = new ArrayList<>();
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotalCompra() {
        double total = 0;
        for (ItemCompra itemCompra : itens) {
            total += (itemCompra.getValorUnitarioItemCompra() * itemCompra.getQuantidadeItemCompra());
        }
        return total;
    }

    public void setValorTotalCompra(Double valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }

    public Situacao getSituacaoCompra() {
        return situacaoCompra;
    }

    public void setSituacaoCompra(Situacao situacaoCompra) {
        this.situacaoCompra = situacaoCompra;
    }

    public void setSituacao(int situacao) {
        if (situacao == Situacao.ABERTA.getId()) {
            setSituacaoCompra(Situacao.ABERTA);
        } else if (situacao == Situacao.FINALIZADA.getId()) {
            setSituacaoCompra(Situacao.FINALIZADA);
        } else if (situacao == Situacao.CANCELADA.getId()) {
            setSituacaoCompra(Situacao.CANCELADA);
        }
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public List<ItemCompra> getItensRemover() {
        return itensRemover;
    }

    public void addItem(ItemCompra itemCompra) {
        itens.add(itemCompra);
    }

    public void removeItem(ItemCompra itemCompra) {
        itens.remove(itemCompra);
        if (itemCompra.getIdItemCompra() != 0) {
            itensRemover.add(itemCompra);
        }
    }

    public int quantidadeItens() {
        return itens.size();
    }

    @Override
    public String toString() {
        return String.valueOf(this.idCompra);
    }

}
