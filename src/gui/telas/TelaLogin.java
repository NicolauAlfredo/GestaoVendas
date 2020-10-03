/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.Conexao;

/**
 *
 * @author user
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaLogin
     */
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    Conexao conexao = new Conexao();

    public TelaLogin() {
        initComponents();
        carregarImagens();
    }

    private void carregarImagens() {
        ImageIcon login = new ImageIcon(getClass().getResource("/gui/icones/login.png"));
        btnAcessar.setIcon(login);

        ImageIcon exit = new ImageIcon(getClass().getResource("/gui/icones/exit.png"));
        btnCancelar.setIcon(exit);
    }

    private void logar() {
        String sql = "SELECT * FROM usuario WHERE login_usuario = ? and senha_usuario = ?";
        try {
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, txtUsuario.getText());
            ps.setString(2, pfSenha.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                String perfil = rs.getString(4);
                if (perfil.equals("Administrador")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    principal.menuRegistos.setEnabled(true);
                    principal.menuMovimentos.setEnabled(true);
                    principal.menuRelatorios.setEnabled(true);
                    this.dispose();
                } else if (perfil.equals("Caixa")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    principal.menuItemCadastroCategorias.setEnabled(false);
                    principal.menuItemCadastroFornecedores.setEnabled(false);
                    principal.menuItemCadastrosFuncionarios.setEnabled(false);
                    principal.menuItemCadastrosUsuarios.setEnabled(false);
                    principal.menuRegistos.setEnabled(true);
                    principal.menuItemRegistarVendas.setEnabled(true);
                    principal.btnCompras.setEnabled(false);
                    principal.btnProdutos.setEnabled(false);
                    principal.btnFornecedores.setEnabled(false);
                    this.dispose();
                } else if (perfil.equals("Gestor de stock")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    principal.menuItemCadastroClientes.setEnabled(false);
                    principal.menuItemCadastrosFuncionarios.setEnabled(false);
                    principal.menuItemCadastrosUsuarios.setEnabled(false);
                    principal.btnVendas.setEnabled(false);
                    principal.btnClientes.setEnabled(false);
                    this.dispose();
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void sair() {
        // Exibe uma caixa de dialogo
        Integer sair = JOptionPane.showConfirmDialog(null, "Tem certesa que deseja sair ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
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

        pnFormulario = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        lbSenha = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        btnAcessar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pfSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        pnFormulario.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createTitledBorder("Formulário")));

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        lbSenha.setText("Senha:");

        lbUsuario.setText("Usuário:");

        btnAcessar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/login.png"))); // NOI18N
        btnAcessar.setText("Acessar");
        btnAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessarActionPerformed(evt);
            }
        });
        btnAcessar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAcessarKeyPressed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/exit.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        pfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfSenhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnFormularioLayout = new javax.swing.GroupLayout(pnFormulario);
        pnFormulario.setLayout(pnFormularioLayout);
        pnFormularioLayout.setHorizontalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnFormularioLayout.createSequentialGroup()
                        .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pfSenha))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnFormularioLayout.createSequentialGroup()
                        .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAcessar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAcessar)
                    .addComponent(btnCancelar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(pnFormulario, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessarActionPerformed
        logar();
    }//GEN-LAST:event_btnAcessarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        sair();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            pfSenha.requestFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void pfSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfSenhaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnAcessar.requestFocus();
        }
    }//GEN-LAST:event_pfSenhaKeyPressed

    private void btnAcessarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAcessarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            logar();
        }
    }//GEN-LAST:event_btnAcessarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcessar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPasswordField pfSenha;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}