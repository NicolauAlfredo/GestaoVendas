/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tabelas;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Compra;

/**
 *
 * @author user
 */
public class CompraTableModel extends AbstractTableModel {

    private final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private final NumberFormat NFC = NumberFormat.getCurrencyInstance();
    private String colunas[] = {"Fornecedor", "Data", "Valor", "Situação"};
    private List<Compra> dados;

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
        Compra compra = dados.get(l);
        switch (c) {
            case 0:
                return compra.getFornecedor().getNomeFornecedor();
            case 1:
                return SDF.format(compra.getDataCompra());
            case 2:
                return NFC.format(compra.getValorTotalCompra());
            case 3:
                return compra.getSituacaoCompra();
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

    public void setDados(List<Compra> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public Compra getRowValue(int l) {
        return dados.get(l);
    }
}
