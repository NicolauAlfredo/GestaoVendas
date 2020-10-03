/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import gui.tabelas.UsuarioTableModel;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.Usuario;

/**
 *
 * @author user
 */
public class TelaCadastrarUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaVendas
     */
    public TelaCadastrarUsuarios() {
        initComponents();
        carregarTabela();
        carregarComboBoxFuncionarios();
        carregarImagens();
    }

    private void carregarImagens() {

        ImageIcon guardar = new ImageIcon(getClass().getResource("/gui/icones/guardar.png"));
        btnAdicionar.setIcon(guardar);

        ImageIcon excluir = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnRemover.setIcon(excluir);

    }

    private void carregarTabela() {
        UsuarioTableModel um = (UsuarioTableModel) tbUsuarios.getModel();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            um.setDados(usuarioDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela de usuários.\n" + ex.getMessage(), "Usuário", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarComboBoxFuncionarios() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        List<Funcionario> funcionarios = funcionarioDAO.BuscarTodos();
        cbFuncionario.removeAllItems();
        for (Funcionario funcionario : funcionarios) {
            cbFuncionario.addItem(funcionario.getNomeFuncionario());
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                Usuario usuario = new Usuario();
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                String login, senha;
                login = txtLogin.getText();
                senha = txtSenha.getText();
                String perfil = (String) cbPerfil.getSelectedItem();

                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                List<Funcionario> funcionarios = funcionarioDAO.BuscarTodos();

                int idFuncionario = 0;
                for (int i = 0; i < funcionarios.size(); i++) {
                    idFuncionario = funcionarios.get(cbFuncionario.getSelectedIndex()).getIdFuncionario();
                }

                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(idFuncionario);

                usuario.setFuncionario(funcionario);
                usuario.setLoginUsuario(login);
                usuario.setSenhaUsuario(senha);
                usuario.setPerfilUsuario(perfil);

                usuarioDAO.guardar(usuario);

                txtLogin.setBackground(Color.white);
                txtLogin.setForeground(Color.black);

                txtSenha.setBackground(Color.white);
                txtSenha.setForeground(Color.black);

                limparFormulario();
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.\n", "Usuário", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o usuário.\n" + ex.getMessage(), "Usuário", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = new Usuario();

            String login = txtLogin.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o usuário " + login + "?");
            if (opcao == 0) {
                usuario.setIdUsuario((int) ftfId.getValue());
                usuarioDAO.apagar(usuario);
                limparFormulario();
                carregarTabela();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o usuário.\n" + ex.getMessage(), "Usuário", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void limparFormulario() {
        txtLogin.setText("");
        txtSenha.setText("");
    }

    private void descolorirFormulario() {
        cbFuncionario.setBackground(Color.white);
        cbFuncionario.setForeground(Color.black);

        cbPerfil.setBackground(Color.white);
        cbPerfil.setForeground(Color.black);

        txtLogin.setBackground(Color.white);
        txtLogin.setForeground(Color.black);

        txtSenha.setBackground(Color.white);
        txtSenha.setForeground(Color.black);
    }

    private boolean validarFormulario() {
        if (txtLogin.getText().trim().length() < 6) {
            txtLogin.setBackground(Color.red);
            txtLogin.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Login inválido. \nO login deve conter no mínimo 6 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtLogin.requestFocus();
            return false;
        }
        if (txtSenha.getText().trim().length() < 6) {
            txtSenha.setBackground(Color.red);
            txtSenha.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Senha inválida. \nA senha deve conter no mínimo 6 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtSenha.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnConteudo = new javax.swing.JPanel();
        pnFormulario = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        ftfId = new javax.swing.JFormattedTextField();
        lbFuncionario = new javax.swing.JLabel();
        cbFuncionario = new javax.swing.JComboBox<>();
        lbPerfil = new javax.swing.JLabel();
        cbPerfil = new javax.swing.JComboBox<>();
        lbLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        lbSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        pnOperacoes = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnTabela = new javax.swing.JPanel();
        pnTabelaUsuarios = new javax.swing.JPanel();
        spTabelaUsuarios = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de usuários");

        pnConteudo.setLayout(new java.awt.BorderLayout());

        pnFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnFormulario.setPreferredSize(new java.awt.Dimension(190, 478));

        lbId.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbId.setText("Identificador único:");

        ftfId.setEditable(false);
        ftfId.setToolTipText("ID do usuário");
        ftfId.setEnabled(false);
        ftfId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ftfIdKeyPressed(evt);
            }
        });

        lbFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbFuncionario.setText("* Funcionário:");

        cbFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFuncionario.setToolTipText("Funcionário [Campo de seleção obrigatória]");
        cbFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbFuncionarioKeyPressed(evt);
            }
        });

        lbPerfil.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbPerfil.setText("* Perfil:");

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Caixa", "Gestor de stock", "Tessoureiro" }));
        cbPerfil.setToolTipText("Perfil do usuário [Campo de seleção obrigatória]");
        cbPerfil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbPerfilKeyPressed(evt);
            }
        });

        lbLogin.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbLogin.setText("* Login:");

        txtLogin.setToolTipText("Nome de usuário [Campo de preenchimento obrigatório]");
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });

        lbSenha.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbSenha.setText("* Senha:");

        txtSenha.setToolTipText("Senha de usuário [Campo de preenchimento obrigatório]");
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        pnOperacoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnAdicionar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setToolTipText("Cadastrar usuário");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.setToolTipText("Remover usuário");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
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

        javax.swing.GroupLayout pnOperacoesLayout = new javax.swing.GroupLayout(pnOperacoes);
        pnOperacoes.setLayout(pnOperacoesLayout);
        pnOperacoesLayout.setHorizontalGroup(
            pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnOperacoesLayout.setVerticalGroup(
            pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnFormularioLayout = new javax.swing.GroupLayout(pnFormulario);
        pnFormulario.setLayout(pnFormularioLayout);
        pnFormularioLayout.setHorizontalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFuncionario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLogin)
                    .addComponent(txtSenha)
                    .addComponent(pnOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnFormularioLayout.createSequentialGroup()
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFuncionario)
                            .addComponent(lbPerfil)
                            .addComponent(lbLogin)
                            .addComponent(lbSenha)
                            .addComponent(lbId))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ftfId))
                .addContainerGap())
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbId)
                .addGap(5, 5, 5)
                .addComponent(ftfId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pnConteudo.add(pnFormulario, java.awt.BorderLayout.LINE_START);

        pnTabelaUsuarios.setLayout(new java.awt.BorderLayout());

        tbUsuarios.setAutoCreateRowSorter(true);
        tbUsuarios.setModel(new UsuarioTableModel());
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        spTabelaUsuarios.setViewportView(tbUsuarios);

        pnTabelaUsuarios.add(spTabelaUsuarios, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnTabelaLayout = new javax.swing.GroupLayout(pnTabela);
        pnTabela.setLayout(pnTabelaLayout);
        pnTabelaLayout.setHorizontalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTabelaLayout.setVerticalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabelaUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnConteudo.add(pnTabela, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked
        if (evt.getClickCount() == 2) {
            Usuario usuario = new Usuario();
            UsuarioTableModel tm = (UsuarioTableModel) tbUsuarios.getModel();
            usuario = tm.getRowValue(tbUsuarios.getRowSorter().convertRowIndexToModel(tbUsuarios.getSelectedRow()));

            ftfId.setValue(usuario.getIdUsuario());
            cbFuncionario.setSelectedItem(usuario.getFuncionario().getNomeFuncionario());
            txtLogin.setText(usuario.getLoginUsuario());
            txtSenha.setText(usuario.getSenhaUsuario());
            cbPerfil.setSelectedItem(usuario.getPerfilUsuario());

            cbFuncionario.setBackground(Color.blue);
            cbFuncionario.setForeground(Color.white);

            txtLogin.setBackground(Color.blue);
            txtLogin.setForeground(Color.white);

            txtSenha.setBackground(Color.blue);
            txtSenha.setForeground(Color.white);

            cbPerfil.setBackground(Color.blue);
            cbPerfil.setForeground(Color.white);
        }
    }//GEN-LAST:event_tbUsuariosMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        excluir();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void ftfIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftfIdKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            cbFuncionario.requestFocus();
        }
    }//GEN-LAST:event_ftfIdKeyPressed

    private void cbFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbFuncionarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            cbPerfil.requestFocus();
        }
    }//GEN-LAST:event_cbFuncionarioKeyPressed

    private void cbPerfilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbPerfilKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtLogin.requestFocus();
        }
    }//GEN-LAST:event_cbPerfilKeyPressed

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtSenha.requestFocus();
        }
    }//GEN-LAST:event_txtLoginKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnAdicionar.requestFocus();
            guardar();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparFormulario();
        descolorirFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cbFuncionario;
    private javax.swing.JComboBox<String> cbPerfil;
    private javax.swing.JFormattedTextField ftfId;
    private javax.swing.JLabel lbFuncionario;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbPerfil;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JPanel pnConteudo;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JPanel pnOperacoes;
    private javax.swing.JPanel pnTabela;
    private javax.swing.JPanel pnTabelaUsuarios;
    private javax.swing.JScrollPane spTabelaUsuarios;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
