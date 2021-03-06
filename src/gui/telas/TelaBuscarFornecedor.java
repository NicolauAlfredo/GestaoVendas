/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.FornecedorDAO;
import gui.tabelas.FornecedorTableModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import modelo.Fornecedor;

/**
 *
 * @author user
 */
public class TelaBuscarFornecedor extends javax.swing.JDialog {

    /**
     * Creates new form TelaBuscaFornecedor
     */
    JInternalFrame parent;

    public TelaBuscarFornecedor(JInternalFrame parent) {
        super();
        this.parent = parent;
        initComponents();
        carregarTabela();
    }

    private void carregarTabela() {
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            FornecedorTableModel ctm = (FornecedorTableModel) tbFornecedores.getModel();
            ctm.setDados(fornecedorDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tabela de fornecedores.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnNome = new javax.swing.JPanel();
        lbNomeFornecedor = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        spTabelaFornecedores = new javax.swing.JScrollPane();
        tbFornecedores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de busca de forncedor");

        lbNomeFornecedor.setText("Nome:");
        pnNome.add(lbNomeFornecedor);

        txtNome.setToolTipText("Nome do fornecedor");
        txtNome.setMinimumSize(new java.awt.Dimension(300, 25));
        txtNome.setPreferredSize(new java.awt.Dimension(300, 25));
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });
        pnNome.add(txtNome);

        getContentPane().add(pnNome, java.awt.BorderLayout.PAGE_START);

        tbFornecedores.setAutoCreateRowSorter(true);
        tbFornecedores.setModel(new FornecedorTableModel());
        tbFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFornecedoresMouseClicked(evt);
            }
        });
        spTabelaFornecedores.setViewportView(tbFornecedores);

        getContentPane().add(spTabelaFornecedores, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(630, 341));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        TableRowSorter rs = (TableRowSorter) tbFornecedores.getRowSorter();
        rs.setRowFilter(RowFilter.regexFilter("(?i)" + txtNome.getText(), 0, 1));
    }//GEN-LAST:event_txtNomeKeyReleased

    private void tbFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFornecedoresMouseClicked
        if (evt.getClickCount() == 2) {
            FornecedorTableModel ctm = (FornecedorTableModel) tbFornecedores.getModel();
            int linhaSelecionada = tbFornecedores.getRowSorter().convertRowIndexToModel(tbFornecedores.getSelectedRow());
            Fornecedor fornecedor = ctm.getRowValue(linhaSelecionada);

            if (parent instanceof TelaLancamentoCompra) {
                TelaLancamentoCompra lc = (TelaLancamentoCompra) parent;
                lc.setFornecedor(fornecedor);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Campo de destino inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_tbFornecedoresMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbNomeFornecedor;
    private javax.swing.JPanel pnNome;
    private javax.swing.JScrollPane spTabelaFornecedores;
    private javax.swing.JTable tbFornecedores;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
