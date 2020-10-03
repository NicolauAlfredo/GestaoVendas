/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.FornecedorDAO;
import gui.tabelas.FornecedorTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Fornecedor;

/**
 *
 * @author user
 */
public class TelaCadastrarFornecedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaFornecedores
     */
    public TelaCadastrarFornecedores() {
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
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        FornecedorTableModel fornecedorTableModel = (FornecedorTableModel) tbFornecedores.getModel();

        try {
            fornecedorTableModel.setDados(fornecedorDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tabela de fornecedores.\n" + ex.getMessage(), "Fornecedor", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                Fornecedor fornecedor = new Fornecedor();
                FornecedorDAO fornecedorDAO = new FornecedorDAO();

                fornecedor.setNomeFornecedor(txtNomeFornecedor.getText().trim());
                fornecedor.setNifFornecedor(txtNifFornecedor.getText().trim());
                fornecedor.setEnderecoFornecedor(txtEnderecoFornecedor.getText().trim());
                fornecedor.setTelefoneFornecedor(txtTelefoneFornecedor.getText().trim());

                fornecedorDAO.guardar(fornecedor);

                txtNomeFornecedor.setBackground(Color.white);
                txtNomeFornecedor.setForeground(Color.black);

                txtEnderecoFornecedor.setBackground(Color.white);
                txtEnderecoFornecedor.setForeground(Color.black);

                carregarTabela();
                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);

                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso.\n", "Cadastrar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o fornecedor.\n" + ex.getMessage(), "Fornecedor", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void actualizar() {
        if (validarFormulario()) {
            try {
                Fornecedor fornecedor = new Fornecedor();
                FornecedorDAO fornecedorDAO = new FornecedorDAO();

                fornecedor.setIdFornecedor(Integer.parseInt(ftfIdFornecedor.getText()));
                fornecedor.setNomeFornecedor(txtNomeFornecedor.getText().trim());
                fornecedor.setNifFornecedor(txtNifFornecedor.getText().trim());
                fornecedor.setEnderecoFornecedor(txtEnderecoFornecedor.getText().trim());
                fornecedor.setTelefoneFornecedor(txtTelefoneFornecedor.getText().trim());

                fornecedorDAO.actualizar(fornecedor);

                txtNomeFornecedor.setBackground(Color.white);
                txtNomeFornecedor.setForeground(Color.black);

                txtEnderecoFornecedor.setBackground(Color.white);
                txtEnderecoFornecedor.setForeground(Color.black);

                carregarTabela();
                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);

                JOptionPane.showMessageDialog(this, "Fornecedor actualizado com sucesso.\n", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao actualizar o fornecedor.\n" + ex.getMessage(), "Fornecedor", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            Fornecedor fornecedor = new Fornecedor();
            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            String nome = txtNomeFornecedor.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o fornecedor " + nome + "?");
            if (opcao == 0) {
                fornecedor.setIdFornecedor(Integer.parseInt(ftfIdFornecedor.getText()));
                fornecedorDAO.apagar(fornecedor);

                txtNomeFornecedor.setBackground(Color.white);
                txtNomeFornecedor.setForeground(Color.black);

                txtNifFornecedor.setBackground(Color.red);
                txtNifFornecedor.setForeground(Color.white);

                txtEnderecoFornecedor.setBackground(Color.red);
                txtEnderecoFornecedor.setForeground(Color.white);
                carregarTabela();
                limparFormulario();
            }
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o fornecedor.\n" + ex.getMessage(), "Fornecedor", JOptionPane.ERROR_MESSAGE);
            return;
        }
        carregarTabela();
    }

    private boolean validarFormulario() {
        if (txtNomeFornecedor.getText().trim().length() < 2) {
            txtNomeFornecedor.setBackground(Color.red);
            txtNomeFornecedor.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Nome inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNomeFornecedor.requestFocus();
            return false;
        }
        if (txtEnderecoFornecedor.getText().trim().length() < 2) {
            txtEnderecoFornecedor.setBackground(Color.red);
            txtEnderecoFornecedor.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "endereço inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtEnderecoFornecedor.requestFocus();
            return false;
        }
        return true;
    }

    private void limparFormulario() {
        ftfIdFornecedor.setText("");
        txtNomeFornecedor.setText("");
        txtNifFornecedor.setText("");
        txtEnderecoFornecedor.setText("");
        txtTelefoneFornecedor.setText("");
    }

    private void habilitarFormulario() {
        txtNomeFornecedor.setEnabled(true);
        txtNifFornecedor.setEnabled(true);
        txtEnderecoFornecedor.setEnabled(true);
        txtTelefoneFornecedor.setEnabled(true);
    }

    private void desabilitarFormulario() {
        txtNomeFornecedor.setEnabled(false);
        txtNifFornecedor.setEnabled(false);
        txtEnderecoFornecedor.setEnabled(false);
        txtTelefoneFornecedor.setEnabled(false);
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
        lbIdFornecedor = new javax.swing.JLabel();
        lbNomeFornecedor = new javax.swing.JLabel();
        ftfIdFornecedor = new javax.swing.JFormattedTextField();
        txtNomeFornecedor = new javax.swing.JTextField();
        lbNifFornecedor = new javax.swing.JLabel();
        txtNifFornecedor = new javax.swing.JTextField();
        lbEnderecoFornecedor = new javax.swing.JLabel();
        txtEnderecoFornecedor = new javax.swing.JTextField();
        lbTelefoneFornecedor = new javax.swing.JLabel();
        txtTelefoneFornecedor = new javax.swing.JTextField();
        pnOperacoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnTabela = new javax.swing.JPanel();
        pnTabelaForncedor = new javax.swing.JPanel();
        spTabela = new javax.swing.JScrollPane();
        tbFornecedores = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Fornecedores");

        pnFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnFormulario.setPreferredSize(new java.awt.Dimension(230, 481));

        lbIdFornecedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbIdFornecedor.setText("Identificador único:");

        lbNomeFornecedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNomeFornecedor.setText("* Nome:");
        lbNomeFornecedor.setToolTipText("");

        ftfIdFornecedor.setEditable(false);
        ftfIdFornecedor.setEnabled(false);

        txtNomeFornecedor.setToolTipText("Nome do fornecedor [Campo de preenchimento obrigatório]");
        txtNomeFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeFornecedorKeyPressed(evt);
            }
        });

        lbNifFornecedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNifFornecedor.setText("Número de identificação fiscal:");

        txtNifFornecedor.setToolTipText("NIF do fornecedor");
        txtNifFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNifFornecedorKeyPressed(evt);
            }
        });

        lbEnderecoFornecedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbEnderecoFornecedor.setText("* Endereço:");

        txtEnderecoFornecedor.setToolTipText("Endereço do fornecedor Nome do fornecedor [Campo de preenchimento obrigatório]");
        txtEnderecoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnderecoFornecedorKeyPressed(evt);
            }
        });

        lbTelefoneFornecedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbTelefoneFornecedor.setText("Terminal telefónico:");

        txtTelefoneFornecedor.setToolTipText("Nº do telefone do fornecedor");
        txtTelefoneFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefoneFornecedorKeyPressed(evt);
            }
        });

        pnOperacoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        btnGuardar.setToolTipText("Cadastrar fornecedor");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setToolTipText("Actualizar fornecedor");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir fornecedor");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnOperacoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActualizar, btnCancelar, btnExcluir, btnGuardar, btnNovo});

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
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtNomeFornecedor)
                    .addComponent(ftfIdFornecedor)
                    .addComponent(txtNifFornecedor)
                    .addComponent(txtEnderecoFornecedor)
                    .addComponent(txtTelefoneFornecedor)
                    .addGroup(pnFormularioLayout.createSequentialGroup()
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbIdFornecedor)
                            .addComponent(lbNomeFornecedor)
                            .addComponent(lbNifFornecedor)
                            .addComponent(lbEnderecoFornecedor)
                            .addComponent(lbTelefoneFornecedor))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIdFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfIdFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNomeFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNifFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNifFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEnderecoFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEnderecoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefoneFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnFormulario, java.awt.BorderLayout.LINE_START);

        pnTabelaForncedor.setLayout(new java.awt.BorderLayout());

        tbFornecedores.setAutoCreateRowSorter(true);
        tbFornecedores.setModel(new FornecedorTableModel());
        tbFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFornecedoresMouseClicked(evt);
            }
        });
        spTabela.setViewportView(tbFornecedores);

        pnTabelaForncedor.add(spTabela, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnTabelaLayout = new javax.swing.GroupLayout(pnTabela);
        pnTabela.setLayout(pnTabelaLayout);
        pnTabelaLayout.setHorizontalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaForncedor, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTabelaLayout.setVerticalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaForncedor, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );

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

    private void tbFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFornecedoresMouseClicked
        if (evt.getClickCount() == 2) {
            Fornecedor fornecedor = new Fornecedor();

            FornecedorTableModel fornecedorTableModel = (FornecedorTableModel) tbFornecedores.getModel();
            fornecedor = fornecedorTableModel.getRowValue(tbFornecedores.getRowSorter().convertRowIndexToModel(tbFornecedores.getSelectedRow()));

            ftfIdFornecedor.setValue(fornecedor.getIdFornecedor());
            txtNomeFornecedor.setText(fornecedor.getNomeFornecedor());
            txtNifFornecedor.setText(fornecedor.getNifFornecedor());
            txtEnderecoFornecedor.setText(fornecedor.getEnderecoFornecedor());
            txtTelefoneFornecedor.setText(fornecedor.getTelefoneFornecedor());

            txtNomeFornecedor.setBackground(Color.blue);
            txtNomeFornecedor.setForeground(Color.white);

            txtNifFornecedor.setBackground(Color.blue);
            txtNifFornecedor.setForeground(Color.white);

            txtEnderecoFornecedor.setBackground(Color.blue);
            txtEnderecoFornecedor.setForeground(Color.white);

            txtTelefoneFornecedor.setBackground(Color.blue);
            txtTelefoneFornecedor.setForeground(Color.white);

            habilitarFormulario();
            desabiliarBotoes();
            btnActualizar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tbFornecedoresMouseClicked

    private void txtNomeFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeFornecedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtNifFornecedor.requestFocus();
        }
    }//GEN-LAST:event_txtNomeFornecedorKeyPressed

    private void txtNifFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNifFornecedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtEnderecoFornecedor.requestFocus();
        }
    }//GEN-LAST:event_txtNifFornecedorKeyPressed

    private void txtEnderecoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnderecoFornecedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTelefoneFornecedor.requestFocus();
        }
    }//GEN-LAST:event_txtEnderecoFornecedorKeyPressed

    private void txtTelefoneFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneFornecedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (btnGuardar.isEnabled()) {
                btnGuardar.requestFocus();
                guardar();
            } else {
                btnActualizar.requestFocus();
                actualizar();
            }
        }
    }//GEN-LAST:event_txtTelefoneFornecedorKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JFormattedTextField ftfIdFornecedor;
    private javax.swing.JLabel lbEnderecoFornecedor;
    private javax.swing.JLabel lbIdFornecedor;
    private javax.swing.JLabel lbNifFornecedor;
    private javax.swing.JLabel lbNomeFornecedor;
    private javax.swing.JLabel lbTelefoneFornecedor;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JPanel pnOperacoes;
    private javax.swing.JPanel pnTabela;
    private javax.swing.JPanel pnTabelaForncedor;
    private javax.swing.JScrollPane spTabela;
    private javax.swing.JTable tbFornecedores;
    private javax.swing.JTextField txtEnderecoFornecedor;
    private javax.swing.JTextField txtNifFornecedor;
    private javax.swing.JTextField txtNomeFornecedor;
    private javax.swing.JTextField txtTelefoneFornecedor;
    // End of variables declaration//GEN-END:variables
}
