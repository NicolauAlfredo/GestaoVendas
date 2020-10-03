package gui.telas;

import dao.ProdutoDAO;
import gui.tabelas.ProdutoTableModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import modelo.Produto;

/**
 * Janela de consulta a dados do produto
 *
 * @author Juliano
 */
public class TelaBuscarProduto extends javax.swing.JDialog {

    JInternalFrame parent;

    public TelaBuscarProduto(JInternalFrame parent) {
        super();
        this.parent = parent;
        initComponents();
        carregarTabela();
    }

    private void carregarTabela() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ProdutoTableModel ptm = (ProdutoTableModel) tbProdutos.getModel();
            ptm.setDados(produtoDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tabela de produtos.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnFiltro = new javax.swing.JPanel();
        lbFiltro = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        spGrade = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de busca de produto");

        pnFiltro.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFiltro.setText("Código de barra:");
        pnFiltro.add(lbFiltro);

        txtCodigoBarra.setColumns(30);
        txtCodigoBarra.setToolTipText("Código de barra do produto");
        txtCodigoBarra.setMinimumSize(new java.awt.Dimension(246, 25));
        txtCodigoBarra.setPreferredSize(new java.awt.Dimension(246, 25));
        txtCodigoBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoBarraKeyReleased(evt);
            }
        });
        pnFiltro.add(txtCodigoBarra);

        getContentPane().add(pnFiltro, java.awt.BorderLayout.PAGE_START);

        tbProdutos.setAutoCreateRowSorter(true);
        tbProdutos.setModel(new ProdutoTableModel());
        tbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutosMouseClicked(evt);
            }
        });
        spGrade.setViewportView(tbProdutos);

        getContentPane().add(spGrade, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(631, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutosMouseClicked
        if (evt.getClickCount() == 2) {
            ProdutoTableModel ptm = (ProdutoTableModel) tbProdutos.getModel();
            int linhaSelecionada = tbProdutos.getRowSorter().convertRowIndexToModel(tbProdutos.getSelectedRow());
            Produto produto = ptm.getRowValue(linhaSelecionada);

            if (parent instanceof TelaLancamentoVenda) {
                TelaLancamentoVenda lv = (TelaLancamentoVenda) parent;
                lv.setProduto(produto);
                dispose();
            } else if (parent instanceof TelaLancamentoCompra) {
                TelaLancamentoCompra lc = (TelaLancamentoCompra) parent;
                lc.setProduto(produto);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Campo de destino inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_tbProdutosMouseClicked

    private void txtCodigoBarraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraKeyReleased
        TableRowSorter rs = (TableRowSorter) tbProdutos.getRowSorter();
        rs.setRowFilter(RowFilter.regexFilter("(?i)" + txtCodigoBarra.getText(), 0, 1));
    }//GEN-LAST:event_txtCodigoBarraKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JPanel pnFiltro;
    private javax.swing.JScrollPane spGrade;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtCodigoBarra;
    // End of variables declaration//GEN-END:variables
}
