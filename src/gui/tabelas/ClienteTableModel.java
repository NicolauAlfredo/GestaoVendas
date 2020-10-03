/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Cliente;

/**
 *
 * @author user
 */
public class ClienteTableModel extends AbstractTableModel {

    private String colunas[] = {"Nome", "NIF", "Endereço", "Telefone"};
    private List<Cliente> dados;

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
        Cliente cliente = dados.get(l);
        switch (c) {
            case 0:
                return cliente.getNomeCliente();
            case 1:
                return cliente.getNifCliente();
            case 2:
                return cliente.getEnderecoCliente();
            case 3:
                return cliente.getTelefoneCliente();
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

    public void setDados(List<Cliente> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public Cliente getRowValue(int l) {
        return dados.get(l);
    }
}
