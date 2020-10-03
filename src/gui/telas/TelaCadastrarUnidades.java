/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.UnidadeDAO;
import gui.tabelas.UnidadeTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Unidade;

/**
 *
 * @author user
 */
public class TelaCadastrarUnidades extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastroUnidade
     */
    public TelaCadastrarUnidades() {
        initComponents();
        carregarTabela();
        desabiliarBotoes();
        desabilitarFormulario();
        btnNovo.setEnabled(true);
        carregarImagens();
    }

    private void carregarImagens() {
        ImageIcon novo = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovo.setIcon(novo);

        ImageIcon guardar = new ImageIcon(getClass().getResource("/gui/icones/guardar.png"));
        btnGuardar.setIcon(guardar);

        ImageIcon actualizar = new ImageIcon(getClass().getResource("/gui/icones/actualizar.png"));
        btnActualizar.setIcon(actualizar);

        ImageIcon excluir = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnExcluir.setIcon(excluir);

        ImageIcon cancelar = new ImageIcon(getClass().getResource("/gui/icones/cancelar.png"));
        btnCancelar.setIcon(cancelar);
    }

    private void carregarTabela() {
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        UnidadeTableModel unidadeTableModel = (UnidadeTableModel) tbUnidades.getModel();

        try {
            unidadeTableModel.setDados(unidadeDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela de unidades.\n" + ex.getMessage(), "Unidade", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                Unidade unidade = new Unidade();
                UnidadeDAO unidadeDAO = new UnidadeDAO();

                unidade.setNomeUnidade(txtNome.getText().trim());

                unidadeDAO.guardar(unidade);

                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);

                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Unidade cadastrada com sucesso.\n", "Unidade", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar a unidade.\n" + ex.getMessage(), "Unidade", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void actualizar() {
        if (validarFormulario()) {
            try {
                Unidade unidade = new Unidade();
                UnidadeDAO unidadeDAO = new UnidadeDAO();

                unidade.setIdUnidade(Integer.parseInt(ftfId.getText()));
                unidade.setNomeUnidade(txtNome.getText().trim());

                unidadeDAO.actualizar(unidade);

                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);

                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Unidade actualizada com sucesso.\n", "Unidade", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar a unidade.\n" + ex.getMessage(), "Unidade", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            Unidade unidade = new Unidade();
            UnidadeDAO unidadeDAO = new UnidadeDAO();

            String nome = txtNome.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a unidade " + nome + "?");
            if (opcao == 0) {
                unidade.setIdUnidade(Integer.parseInt(ftfId.getText()));
                unidadeDAO.apagar(unidade);

                limparFormulario();
                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);
                carregarTabela();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir a unidade.\n" + ex.getMessage(), "Unidade", JOptionPane.ERROR_MESSAGE);
            return;
        }
        carregarTabela();
    }

    private boolean validarFormulario() {
        if (txtNome.getText().trim().length() < 2) {
            txtNome.setBackground(Color.red);
            txtNome.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Nome inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return false;
        }
        return true;
    }

    private void limparFormulario() {
        ftfId.setText("");
        txtNome.setText("");
    }

    private void habilitarFormulario() {
        txtNome.setEnabled(true);
    }

    private void desabilitarFormulario() {
        txtNome.setEnabled(false);
    }

    private void habilitarBotoes() {
        btnNovo.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void desabiliarBotoes() {
        btnNovo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ftfId = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUnidades = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de unidades");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulário"));
        jPanel1.setPreferredSize(new java.awt.Dimension(230, 422));

        jLabel1.setText("Identificador único:");

        ftfId.setEditable(false);
        ftfId.setToolTipText("ID da unidade");
        ftfId.setEnabled(false);

        jLabel2.setText("* Nome:");

        txtNome.setToolTipText("Nome da unidade [Campo de preenchimento obrigatório]");
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Habilitar formulário e botões");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Cadastrar unidade");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setToolTipText("Actualizar unidade");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir unidade");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Cancelar operações");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfId)
                    .addComponent(txtNome)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        tbUnidades.setAutoCreateRowSorter(true);
        tbUnidades.setModel(new UnidadeTableModel());
        tbUnidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUnidadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUnidades);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitarBotoes();
        habilitarFormulario();
        btnNovo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparFormulario();
        desabiliarBotoes();
        desabilitarFormulario();
        btnNovo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tbUnidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUnidadesMouseClicked
        if (evt.getClickCount() == 2) {
            Unidade unidade = new Unidade();

            UnidadeTableModel categoriaTableModel = (UnidadeTableModel) tbUnidades.getModel();
            unidade = categoriaTableModel.getRowValue(tbUnidades.getRowSorter().convertRowIndexToModel(tbUnidades.getSelectedRow()));

            ftfId.setValue(unidade.getIdUnidade());
            txtNome.setText(unidade.getNomeUnidade());

            txtNome.setBackground(Color.blue);
            txtNome.setForeground(Color.white);

            habilitarFormulario();
            desabiliarBotoes();
            btnActualizar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tbUnidadesMouseClicked

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (btnGuardar.isEnabled()) {
                btnGuardar.requestFocus();
                guardar();
            } else {
                btnActualizar.requestFocus();
                actualizar();
            }
        }
    }//GEN-LAST:event_txtNomeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JFormattedTextField ftfId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
