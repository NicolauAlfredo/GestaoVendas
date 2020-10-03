/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tabelas;

import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.ItemCompra;

/**
 *
 * @author user
 */
public class ItemCompraTableModel extends AbstractTableModel {

    private final NumberFormat NF = NumberFormat.getNumberInstance();
    private final NumberFormat NFC = NumberFormat.getCurrencyInstance();
    private String colunas[] = {"Produto", "Quantidade", "Valor Unitário"};
    private List<ItemCompra> dados;

    @Override
    public int getRowCount() {
        if (dados == null) {
            return 0;
        }
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int l, int c) {
        ItemCompra itemCompra = dados.get(l);
        switch (c) {
            case 0:
                return itemCompra.getProduto().getNomeProduto();
            case 1:
                return NF.format(itemCompra.getQuantidadeItemCompra());
            case 2:
                return NFC.format(itemCompra.getValorUnitarioItemCompra());
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente!");
        }
    }

    @Override
    public String getColumnName(int c) {
        return colunas[c];
    }

    @Override
    public boolean isCellEditable(int l, int c) {
        return false;
    }

    public void setDados(List<ItemCompra> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public ItemCompra getRowValue(int l) {
        return dados.get(l);
    }
}
