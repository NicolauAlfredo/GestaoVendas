/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Funcionario;

/**
 *
 * @author user
 */
public class FuncionarioTableModel extends AbstractTableModel {

    private String colunas[] = {"ID", "Nome", "Bilhete", "Telefone"};
    private List<Funcionario> dados;

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
        Funcionario funcionario = dados.get(l);
        switch (c) {
            case 0:
                return funcionario.getIdFuncionario();
            case 1:
                return funcionario.getNomeFuncionario();
            case 2:
                return funcionario.getBiFuncionario();
            case 3:
                return funcionario.getTelefoneFuncionario();
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

    public void setDados(List<Funcionario> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public Funcionario getRowValue(int l) {
        return dados.get(l);
    }
}
