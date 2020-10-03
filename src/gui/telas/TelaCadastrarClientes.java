/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.ClienteDAO;
import gui.tabelas.ClienteTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author user
 */
public class TelaCadastrarClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaClientes
     */
    public TelaCadastrarClientes() {
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
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteTableModel clienteTableModel = (ClienteTableModel) tbClientes.getModel();

        try {
            clienteTableModel.setDados(clienteDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela de clientes.\n" + ex.getMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();

                cliente.setNomeCliente(txtNome.getText().trim());
                cliente.setNifCliente(txtNif.getText().trim());
                cliente.setEnderecoCliente(txtEndereco.getText().trim());
                cliente.setTelefoneCliente(txtTelefone.getText().trim());
                clienteDAO.guardar(cliente);

                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);

                txtEndereco.setBackground(Color.white);
                txtEndereco.setForeground(Color.black);
                carregarTabela();
                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);

                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.\n", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao guardar o cliente.\n" + ex.getMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void actualizar() {
        if (validarFormulario()) {
            try {
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();

                cliente.setIdCliente(Integer.parseInt(ftfId.getText()));
                cliente.setNomeCliente(txtNome.getText().trim());
                cliente.setNifCliente(txtNif.getText().trim());
                cliente.setEnderecoCliente(txtEndereco.getText().trim());
                cliente.setTelefoneCliente(txtTelefone.getText().trim());

                clienteDAO.actualizar(cliente);

                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);

                txtEndereco.setBackground(Color.white);
                txtEndereco.setForeground(Color.black);
                carregarTabela();
                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);

                JOptionPane.showMessageDialog(this, "Cliente actualizado com sucesso.\n", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao actualizar o cliente.\n" + ex.getMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();

            String nome = txtNome.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cliente " + nome + "?");
            if (opcao == 0) {
                cliente.setIdCliente(Integer.parseInt(ftfId.getText()));
                clienteDAO.apagar(cliente);

                txtNome.setBackground(Color.white);
                txtNome.setForeground(Color.black);

                txtNif.setBackground(Color.red);
                txtNif.setForeground(Color.white);

                txtEndereco.setBackground(Color.red);
                txtEndereco.setForeground(Color.white);
                carregarTabela();
                limparFormulario();
            }
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o cliente.\n" + ex.getMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
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
        if (txtEndereco.getText().trim().length() < 2) {
            txtEndereco.setBackground(Color.red);
            txtEndereco.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "endereço inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtEndereco.requestFocus();
            return false;
        }
        return true;
    }

    private void limparFormulario() {
        ftfId.setText("");
        txtNome.setText("");
        txtNif.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
    }

    private void habilitarFormulario() {
        txtNome.setEnabled(true);
        txtNif.setEnabled(true);
        txtEndereco.setEnabled(true);
        txtTelefone.setEnabled(true);
    }

    private void desabilitarFormulario() {
        txtNome.setEnabled(false);
        txtNif.setEnabled(false);
        txtEndereco.setEnabled(false);
        txtTelefone.setEnabled(false);
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
        lbId = new javax.swing.JLabel();
        ftfId = new javax.swing.JFormattedTextField();
        lbNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtNif = new javax.swing.JTextField();
        lbNif = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lbEndereco = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        lbTelefone = new javax.swing.JLabel();
        pnOperacoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        pnTabela = new javax.swing.JPanel();
        pnTabelaClientes = new javax.swing.JPanel();
        spTabela = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de clientes");

        pnFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnFormulario.setPreferredSize(new java.awt.Dimension(230, 510));

        lbId.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbId.setText("Identificador único:");

        ftfId.setEditable(false);
        ftfId.setToolTipText("ID do cliente");
        ftfId.setEnabled(false);

        lbNome.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNome.setText("* Nome:");

        txtNome.setToolTipText("Nome do cliente [Campo de preenchimento obrigatório]");
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        txtNif.setToolTipText("NIF do cliente");
        txtNif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNifKeyPressed(evt);
            }
        });

        lbNif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNif.setText("Número de identificação fiscal:");

        txtEndereco.setToolTipText("Endereço do cliente [Campo de preenchimento obrigatório]");
        txtEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnderecoKeyPressed(evt);
            }
        });

        lbEndereco.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbEndereco.setText("* Endereço:");

        txtTelefone.setToolTipText("Nº do telefone do cliente");
        txtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefoneKeyPressed(evt);
            }
        });

        lbTelefone.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbTelefone.setText("Terminal telefónico :");

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
        btnGuardar.setToolTipText("Cadastrar categória");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        btnActualizar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setToolTipText("Actualizar categória");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
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
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ftfId, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnFormularioLayout.createSequentialGroup()
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbId)
                            .addComponent(lbNome)
                            .addComponent(lbNif)
                            .addComponent(lbEndereco)
                            .addComponent(lbTelefone))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(txtNif, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnFormulario, java.awt.BorderLayout.LINE_START);

        pnTabelaClientes.setLayout(new java.awt.BorderLayout());

        tbClientes.setAutoCreateRowSorter(true);
        tbClientes.setModel(new ClienteTableModel());
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientesMouseClicked(evt);
            }
        });
        spTabela.setViewportView(tbClientes);

        pnTabelaClientes.add(spTabela, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnTabelaLayout = new javax.swing.GroupLayout(pnTabela);
        pnTabela.setLayout(pnTabelaLayout);
        pnTabelaLayout.setHorizontalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTabelaLayout.setVerticalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
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

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked
        if (evt.getClickCount() == 2) {
            Cliente cliente = new Cliente();

            ClienteTableModel clienteTableModel = (ClienteTableModel) tbClientes.getModel();
            cliente = clienteTableModel.getRowValue(tbClientes.getRowSorter().convertRowIndexToModel(tbClientes.getSelectedRow()));

            ftfId.setValue(cliente.getIdCliente());
            txtNome.setText(cliente.getNomeCliente());
            txtNif.setText(cliente.getNifCliente());
            txtEndereco.setText(cliente.getEnderecoCliente());
            txtTelefone.setText(cliente.getTelefoneCliente());

            txtNome.setBackground(Color.blue);
            txtNome.setForeground(Color.white);

            txtNif.setBackground(Color.blue);
            txtNif.setForeground(Color.white);

            txtEndereco.setBackground(Color.blue);
            txtEndereco.setForeground(Color.white);

            txtTelefone.setBackground(Color.blue);
            txtTelefone.setForeground(Color.white);

            habilitarFormulario();
            desabiliarBotoes();
            btnActualizar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tbClientesMouseClicked

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtNif.requestFocus();
        }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnderecoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTelefone.requestFocus();
        }
    }//GEN-LAST:event_txtEnderecoKeyPressed

    private void txtTelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (btnGuardar.isEnabled()) {
                btnGuardar.requestFocus();
                guardar();
            } else {
                btnActualizar.requestFocus();
                actualizar();
            }
        }
    }//GEN-LAST:event_txtTelefoneKeyPressed

    private void txtNifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNifKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtEndereco.requestFocus();
        }
    }//GEN-LAST:event_txtNifKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JFormattedTextField ftfId;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbNif;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JPanel pnOperacoes;
    private javax.swing.JPanel pnTabela;
    private javax.swing.JPanel pnTabelaClientes;
    private javax.swing.JScrollPane spTabela;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNif;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
