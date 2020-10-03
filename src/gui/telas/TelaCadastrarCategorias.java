/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.CategoriaDAO;
import dao.FuncionarioDAO;
import gui.tabelas.CategoriaTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Funcionario;

/**
 *
 * @author user
 */
public class TelaCadastrarCategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCategorias
     */
    public TelaCadastrarCategorias() {
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
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        CategoriaTableModel categoriaTableModel = (CategoriaTableModel) tbCategorias.getModel();

        try {
            categoriaTableModel.setDados(categoriaDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela de categórias.\n" + ex.getMessage(), "Categória", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                Categoria categoria = new Categoria();
                CategoriaDAO categoriaDAO = new CategoriaDAO();

                categoria.setNomeCategoria(txtNome.getText().trim());

                categoriaDAO.guardar(categoria);

                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Categória cadastrada com sucesso.\n", "Categória", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar a categória.\n" + ex.getMessage(), "Categória", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void actualizar() {
        if (validarFormulario()) {
            try {
                Categoria categoria = new Categoria();
                CategoriaDAO categoriaDAO = new CategoriaDAO();

                categoria.setIdCategoria(Integer.parseInt(ftfId.getText()));
                categoria.setNomeCategoria(txtNome.getText().trim());

                categoriaDAO.actualizar(categoria);

                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Categória actualizada com sucesso.\n", "Categória", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao actualizar a categória.\n" + ex.getMessage(), "Categória", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            Funcionario funcionario = new Funcionario();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            String nome = txtNome.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a categória " + nome + "?");
            if (opcao == 0) {
                funcionario.setIdFuncionario(Integer.parseInt(ftfId.getText()));
                funcionarioDAO.apagar(funcionario);

                limparFormulario();
                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);
                carregarTabela();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir a categória.\n" + ex.getMessage(), "Categória", JOptionPane.ERROR_MESSAGE);
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

        pnFormulario = new javax.swing.JPanel();
        pnCadastro = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        ftfId = new javax.swing.JFormattedTextField();
        lbNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        pnOperacoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnTabela = new javax.swing.JPanel();
        tabela = new javax.swing.JPanel();
        pnTabelaCategoria = new javax.swing.JPanel();
        spTabela = new javax.swing.JScrollPane();
        tbCategorias = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de categória");

        pnFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnFormulario.setPreferredSize(new java.awt.Dimension(230, 447));

        lbId.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbId.setText("Identificador único:");

        ftfId.setEditable(false);
        ftfId.setToolTipText("ID da categória");
        ftfId.setEnabled(false);
        ftfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftfIdActionPerformed(evt);
            }
        });

        lbNome.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNome.setText("* Nome:");

        txtNome.setToolTipText("Nome da categória [Campo de preenchimento obrigatório]");
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnCadastroLayout = new javax.swing.GroupLayout(pnCadastro);
        pnCadastro.setLayout(pnCadastroLayout);
        pnCadastroLayout.setHorizontalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfId)
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbId)
                            .addComponent(lbNome))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNome))
                .addContainerGap())
        );
        pnCadastroLayout.setVerticalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNovo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Habilitar formulário e botões");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Cadastrar categória");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setToolTipText("Actualizar categória");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir categória");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Cancelar operações");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnOperacoesLayout = new javax.swing.GroupLayout(pnOperacoes);
        pnOperacoes.setLayout(pnOperacoesLayout);
        pnOperacoesLayout.setHorizontalGroup(
            pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnOperacoesLayout.setVerticalGroup(
            pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesLayout.createSequentialGroup()
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

        pnOperacoesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnActualizar, btnCancelar, btnExcluir, btnGuardar, btnNovo});

        javax.swing.GroupLayout pnFormularioLayout = new javax.swing.GroupLayout(pnFormulario);
        pnFormulario.setLayout(pnFormularioLayout);
        pnFormularioLayout.setHorizontalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(pnFormulario, java.awt.BorderLayout.LINE_START);

        pnTabela.setLayout(new java.awt.BorderLayout());

        pnTabelaCategoria.setLayout(new java.awt.BorderLayout());

        tbCategorias.setAutoCreateRowSorter(true);
        tbCategorias.setModel(new CategoriaTableModel());
        tbCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCategoriasMouseClicked(evt);
            }
        });
        spTabela.setViewportView(tbCategorias);

        pnTabelaCategoria.add(spTabela, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout tabelaLayout = new javax.swing.GroupLayout(tabela);
        tabela.setLayout(tabelaLayout);
        tabelaLayout.setHorizontalGroup(
            tabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabelaLayout.setVerticalGroup(
            tabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnTabela.add(tabela, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnTabela, java.awt.BorderLayout.CENTER);

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

    private void tbCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCategoriasMouseClicked
        if (evt.getClickCount() == 2) {
            Categoria categoria = new Categoria();

            CategoriaTableModel categoriaTableModel = (CategoriaTableModel) tbCategorias.getModel();
            categoria = categoriaTableModel.getRowValue(tbCategorias.getRowSorter().convertRowIndexToModel(tbCategorias.getSelectedRow()));

            ftfId.setValue(categoria.getIdCategoria());
            txtNome.setText(categoria.getNomeCategoria());

            txtNome.setBackground(Color.blue);
            txtNome.setForeground(Color.white);

            habilitarFormulario();
            desabiliarBotoes();
            btnActualizar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tbCategoriasMouseClicked

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

    private void ftfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfIdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JFormattedTextField ftfId;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbNome;
    private javax.swing.JPanel pnCadastro;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JPanel pnOperacoes;
    private javax.swing.JPanel pnTabela;
    private javax.swing.JPanel pnTabelaCategoria;
    private javax.swing.JScrollPane spTabela;
    private javax.swing.JPanel tabela;
    private javax.swing.JTable tbCategorias;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
