/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Categoria;

/**
 *
 * @author user
 */
public class CategoriaTableModel extends AbstractTableModel {

    private String colunas[] = {"Nome"};
    private List<Categoria> dados;

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
        Categoria categoria = dados.get(l);
        switch (c) {
            case 0:
                return categoria.getNomeCategoria();
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

    public void setDados(List<Categoria> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public Categoria getRowValue(int l) {
        return dados.get(l);
    }
}
