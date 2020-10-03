/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        carregarImagens();
        URL caminhoIcon = getClass().getResource("/gui/icones/principal.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoIcon);
        this.setIconImage(iconeTitulo);
    }

    private void carregarImagens() {
        //Imagem Botão Pré-Inscrição
        ImageIcon venda = new ImageIcon(getClass().getResource("/gui/icones/venda.png"));
        btnVendas.setIcon(venda);

        //Imagem Botão Inscrição
        ImageIcon compra = new ImageIcon(getClass().getResource("/gui/icones/compra.png"));
        btnCompras.setIcon(compra);

        //Imagem Botão Pesquisas
        ImageIcon produto = new ImageIcon(getClass().getResource("/gui/icones/produto.png"));
        btnProdutos.setIcon(produto);

        //Imagem Botão Actividades
        ImageIcon cliente = new ImageIcon(getClass().getResource("/gui/icones/cliente.png"));
        btnClientes.setIcon(cliente);

        //Imagem Botão Escuteiros
        ImageIcon fornecedor = new ImageIcon(getClass().getResource("/gui/icones/fornecedor.png"));
        btnFornecedores.setIcon(fornecedor);

        //Imagem Botão Sair
        ImageIcon sair = new ImageIcon(getClass().getResource("/gui/icones/sair.png"));
        btnSair.setIcon(sair);
    }

    protected void carregarTelaFuncionarios() {
        TelaCadastrarFuncionarios telaFuncionarios = new TelaCadastrarFuncionarios();
        desktopPanePrincipal.add(telaFuncionarios);
        telaFuncionarios.setVisible(true);

        try {
            telaFuncionarios.setSelected(true); //diz que a janela interna é maximizável 
            telaFuncionarios.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaFuncionarios.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela funcionários: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaProdutos() {
        TelaCadastrarProdutos telaProdutos = new TelaCadastrarProdutos();
        desktopPanePrincipal.add(telaProdutos);
        telaProdutos.setVisible(true);

        try {
            telaProdutos.setSelected(true); //diz que a janela interna é maximizável 
            telaProdutos.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaProdutos.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela produtos: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaUnidades() {
        TelaCadastrarUnidades telaCadastrarUnidade = new TelaCadastrarUnidades();
        desktopPanePrincipal.add(telaCadastrarUnidade);
        telaCadastrarUnidade.setVisible(true);

        try {
            telaCadastrarUnidade.setSelected(true); //diz que a janela interna é maximizável 
            telaCadastrarUnidade.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaCadastrarUnidade.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela unidade: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaCategoria() {
        TelaCadastrarCategorias telaCategorias = new TelaCadastrarCategorias();
        desktopPanePrincipal.add(telaCategorias);
        telaCategorias.setVisible(true);

        try {
            telaCategorias.setSelected(true); //diz que a janela interna é maximizável 
            telaCategorias.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaCategorias.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela categorias: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaCompras() {
        TelaLancamentoCompra telaLancamentoCompra = new TelaLancamentoCompra();
        desktopPanePrincipal.add(telaLancamentoCompra);
        telaLancamentoCompra.setVisible(true);

        try {
            telaLancamentoCompra.setSelected(true); //diz que a janela interna é maximizável 
            telaLancamentoCompra.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaLancamentoCompra.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela de compras: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaVendas() {
        TelaLancamentoVenda telaLancamentoVenda = new TelaLancamentoVenda();
        desktopPanePrincipal.add(telaLancamentoVenda);
        telaLancamentoVenda.setVisible(true);

        try {
            telaLancamentoVenda.setSelected(true); //diz que a janela interna é maximizável 
            telaLancamentoVenda.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaLancamentoVenda.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela de vendas: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaUsuarios() {
        TelaCadastrarUsuarios telaVendas = new TelaCadastrarUsuarios();
        desktopPanePrincipal.add(telaVendas);
        telaVendas.setVisible(true);

        try {
            telaVendas.setSelected(true); //diz que a janela interna é maximizável 
            telaVendas.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaVendas.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela usuários: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaClientes() {
        TelaCadastrarClientes telaClientes = new TelaCadastrarClientes();
        desktopPanePrincipal.add(telaClientes);
        telaClientes.setVisible(true);

        try {
            telaClientes.setSelected(true); //diz que a janela interna é maximizável 
            telaClientes.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaClientes.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela clientes: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaForncedores() {
        TelaCadastrarFornecedores telaFornecedores = new TelaCadastrarFornecedores();
        desktopPanePrincipal.add(telaFornecedores);
        telaFornecedores.setVisible(true);

        try {
            telaFornecedores.setSelected(true); //diz que a janela interna é maximizável 
            telaFornecedores.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaFornecedores.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela fornecedores: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaMovimentoVendas() {
        TelaMovimentoVendas telaMovimentoVendas = new TelaMovimentoVendas();
        desktopPanePrincipal.add(telaMovimentoVendas);
        telaMovimentoVendas.setVisible(true);

        try {
            telaMovimentoVendas.setSelected(true); //diz que a janela interna é maximizável 
            telaMovimentoVendas.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaMovimentoVendas.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela movimento de vendas: " + e.getLocalizedMessage());
        }
    }

    protected void carregarTelaMovimentoCompras() {
        TelaMovimentoCompras TelaMovimentoCompras = new TelaMovimentoCompras();
        desktopPanePrincipal.add(TelaMovimentoCompras);
        TelaMovimentoCompras.setVisible(true);

        try {
            TelaMovimentoCompras.setSelected(true); //diz que a janela interna é maximizável 
            TelaMovimentoCompras.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            TelaMovimentoCompras.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela movimento de compras: " + e.getLocalizedMessage());
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

        barraFerramentas = new javax.swing.JToolBar();
        btnVendas = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        btnProdutos = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnFornecedores = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        desktopPanePrincipal = new javax.swing.JDesktopPane();
        barraMenus = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        menuItemCadastroCategorias = new javax.swing.JMenuItem();
        menuItemCadastroClientes = new javax.swing.JMenuItem();
        menuItemCadastroFornecedores = new javax.swing.JMenuItem();
        menuItemCadastrosFuncionarios = new javax.swing.JMenuItem();
        menuItemCadastrosProdutos = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuItemCadastrosUsuarios = new javax.swing.JMenuItem();
        menuRegistos = new javax.swing.JMenu();
        menuItemRegistarCompras = new javax.swing.JMenuItem();
        menuItemRegistarVendas = new javax.swing.JMenuItem();
        menuMovimentos = new javax.swing.JMenu();
        menuItemMovimentoCompra = new javax.swing.JMenuItem();
        menuItemMovimentoVenda = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        menuItemRelatoriosCompras = new javax.swing.JMenuItem();
        menuItemRelatoriosVendas = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        menuItemSistemaContactos = new javax.swing.JMenuItem();
        menuItemSistemaSobre = new javax.swing.JMenuItem();
        menuItemSistemaSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestão de vendas");

        barraFerramentas.setFloatable(false);
        barraFerramentas.setRollover(true);
        barraFerramentas.setPreferredSize(new java.awt.Dimension(100, 80));

        btnVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/venda.png"))); // NOI18N
        btnVendas.setText("Venda");
        btnVendas.setFocusable(false);
        btnVendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVendas.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnVendas.setMaximumSize(new java.awt.Dimension(65, 60));
        btnVendas.setMinimumSize(new java.awt.Dimension(65, 60));
        btnVendas.setPreferredSize(new java.awt.Dimension(65, 60));
        btnVendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendasActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnVendas);

        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/compra.png"))); // NOI18N
        btnCompras.setText("Compra");
        btnCompras.setFocusable(false);
        btnCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompras.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnCompras.setMaximumSize(new java.awt.Dimension(65, 60));
        btnCompras.setMinimumSize(new java.awt.Dimension(65, 60));
        btnCompras.setPreferredSize(new java.awt.Dimension(65, 60));
        btnCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnCompras);

        btnProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/produto.png"))); // NOI18N
        btnProdutos.setText("Produto");
        btnProdutos.setFocusable(false);
        btnProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProdutos.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnProdutos.setMaximumSize(new java.awt.Dimension(65, 60));
        btnProdutos.setMinimumSize(new java.awt.Dimension(65, 60));
        btnProdutos.setPreferredSize(new java.awt.Dimension(65, 60));
        btnProdutos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutosActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnProdutos);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cliente.png"))); // NOI18N
        btnClientes.setText("Cliente");
        btnClientes.setFocusable(false);
        btnClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClientes.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnClientes.setMaximumSize(new java.awt.Dimension(65, 60));
        btnClientes.setMinimumSize(new java.awt.Dimension(65, 60));
        btnClientes.setPreferredSize(new java.awt.Dimension(65, 60));
        btnClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnClientes);

        btnFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/fornecedor.png"))); // NOI18N
        btnFornecedores.setText("Fornecedor");
        btnFornecedores.setFocusable(false);
        btnFornecedores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFornecedores.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnFornecedores.setMaximumSize(new java.awt.Dimension(83, 60));
        btnFornecedores.setMinimumSize(new java.awt.Dimension(83, 60));
        btnFornecedores.setPreferredSize(new java.awt.Dimension(83, 60));
        btnFornecedores.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedoresActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnFornecedores);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setFocusable(false);
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSair.setMaximumSize(new java.awt.Dimension(65, 60));
        btnSair.setMinimumSize(new java.awt.Dimension(65, 60));
        btnSair.setPreferredSize(new java.awt.Dimension(65, 60));
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnSair);

        getContentPane().add(barraFerramentas, java.awt.BorderLayout.PAGE_START);

        desktopPanePrincipal.setOpaque(false);

        javax.swing.GroupLayout desktopPanePrincipalLayout = new javax.swing.GroupLayout(desktopPanePrincipal);
        desktopPanePrincipal.setLayout(desktopPanePrincipalLayout);
        desktopPanePrincipalLayout.setHorizontalGroup(
            desktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        desktopPanePrincipalLayout.setVerticalGroup(
            desktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        getContentPane().add(desktopPanePrincipal, java.awt.BorderLayout.CENTER);

        menuCadastros.setText("Cadastros");

        menuItemCadastroCategorias.setText("Categória");
        menuItemCadastroCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroCategoriasActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastroCategorias);

        menuItemCadastroClientes.setText("Cliente");
        menuItemCadastroClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroClientesActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastroClientes);

        menuItemCadastroFornecedores.setText("Fornecedor");
        menuItemCadastroFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroFornecedoresActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastroFornecedores);

        menuItemCadastrosFuncionarios.setText("Funcionário");
        menuItemCadastrosFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosFuncionariosActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosFuncionarios);

        menuItemCadastrosProdutos.setText("Produto");
        menuItemCadastrosProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosProdutosActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosProdutos);

        jMenuItem1.setText("Unidade");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuCadastros.add(jMenuItem1);

        menuItemCadastrosUsuarios.setText("Usuário");
        menuItemCadastrosUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosUsuariosActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosUsuarios);

        barraMenus.add(menuCadastros);

        menuRegistos.setText("Registos");

        menuItemRegistarCompras.setText("Compra");
        menuItemRegistarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRegistarComprasActionPerformed(evt);
            }
        });
        menuRegistos.add(menuItemRegistarCompras);

        menuItemRegistarVendas.setText("Venda");
        menuItemRegistarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRegistarVendasActionPerformed(evt);
            }
        });
        menuRegistos.add(menuItemRegistarVendas);

        barraMenus.add(menuRegistos);

        menuMovimentos.setText("Movimentos");

        menuItemMovimentoCompra.setText("Compra");
        menuItemMovimentoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemMovimentoCompraActionPerformed(evt);
            }
        });
        menuMovimentos.add(menuItemMovimentoCompra);

        menuItemMovimentoVenda.setText("Venda");
        menuItemMovimentoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemMovimentoVendaActionPerformed(evt);
            }
        });
        menuMovimentos.add(menuItemMovimentoVenda);

        barraMenus.add(menuMovimentos);

        menuRelatorios.setText("Relatórios");

        menuItemRelatoriosCompras.setText("Compra");
        menuItemRelatoriosCompras.setEnabled(false);
        menuItemRelatoriosCompras.setFocusable(true);
        menuItemRelatoriosCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatoriosComprasActionPerformed(evt);
            }
        });
        menuRelatorios.add(menuItemRelatoriosCompras);

        menuItemRelatoriosVendas.setText("Venda");
        menuItemRelatoriosVendas.setEnabled(false);
        menuItemRelatoriosVendas.setFocusable(true);
        menuItemRelatoriosVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatoriosVendasActionPerformed(evt);
            }
        });
        menuRelatorios.add(menuItemRelatoriosVendas);

        barraMenus.add(menuRelatorios);

        menuSistema.setText("Sistema");

        menuItemSistemaContactos.setText("Contactos");
        menuItemSistemaContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSistemaContactosActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemSistemaContactos);

        menuItemSistemaSobre.setText("Sobre");
        menuItemSistemaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSistemaSobreActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemSistemaSobre);

        menuItemSistemaSair.setText("Sair");
        menuItemSistemaSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSistemaSairActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemSistemaSair);

        barraMenus.add(menuSistema);

        setJMenuBar(barraMenus);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCadastrosFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosFuncionariosActionPerformed
        carregarTelaFuncionarios();
    }//GEN-LAST:event_menuItemCadastrosFuncionariosActionPerformed

    private void menuItemCadastrosProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosProdutosActionPerformed
        carregarTelaProdutos();
    }//GEN-LAST:event_menuItemCadastrosProdutosActionPerformed

    private void menuItemCadastrosUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosUsuariosActionPerformed
        carregarTelaUsuarios();
    }//GEN-LAST:event_menuItemCadastrosUsuariosActionPerformed

    private void menuItemRegistarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRegistarComprasActionPerformed
        carregarTelaCompras();
    }//GEN-LAST:event_menuItemRegistarComprasActionPerformed

    private void menuItemRegistarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRegistarVendasActionPerformed
        carregarTelaVendas();
    }//GEN-LAST:event_menuItemRegistarVendasActionPerformed

    private void menuItemRelatoriosComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatoriosComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemRelatoriosComprasActionPerformed

    private void menuItemRelatoriosVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatoriosVendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemRelatoriosVendasActionPerformed

    private void menuItemSistemaContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSistemaContactosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemSistemaContactosActionPerformed

    private void menuItemSistemaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSistemaSobreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemSistemaSobreActionPerformed

    private void menuItemSistemaSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSistemaSairActionPerformed
        // Exibe uma caixa de dialogo
        Integer sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuItemSistemaSairActionPerformed

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendasActionPerformed
        carregarTelaVendas();
    }//GEN-LAST:event_btnVendasActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        carregarTelaCompras();
    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosActionPerformed
        carregarTelaProdutos();
    }//GEN-LAST:event_btnProdutosActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // Exibe uma caixa de dialogo
        Integer sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void menuItemCadastroCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroCategoriasActionPerformed
        carregarTelaCategoria();
    }//GEN-LAST:event_menuItemCadastroCategoriasActionPerformed

    private void menuItemCadastroClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroClientesActionPerformed
        carregarTelaClientes();
    }//GEN-LAST:event_menuItemCadastroClientesActionPerformed

    private void menuItemCadastroFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroFornecedoresActionPerformed
        carregarTelaForncedores();
    }//GEN-LAST:event_menuItemCadastroFornecedoresActionPerformed

    private void menuItemMovimentoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemMovimentoCompraActionPerformed
        carregarTelaMovimentoCompras();
    }//GEN-LAST:event_menuItemMovimentoCompraActionPerformed

    private void menuItemMovimentoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemMovimentoVendaActionPerformed
        carregarTelaMovimentoVendas();
    }//GEN-LAST:event_menuItemMovimentoVendaActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        carregarTelaClientes();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedoresActionPerformed
        carregarTelaForncedores();
    }//GEN-LAST:event_btnFornecedoresActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        carregarTelaUnidades();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerramentas;
    private javax.swing.JMenuBar barraMenus;
    public static javax.swing.JButton btnClientes;
    public static javax.swing.JButton btnCompras;
    public static javax.swing.JButton btnFornecedores;
    public static javax.swing.JButton btnProdutos;
    private javax.swing.JButton btnSair;
    public static javax.swing.JButton btnVendas;
    public static javax.swing.JDesktopPane desktopPanePrincipal;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JMenu menuCadastros;
    public static javax.swing.JMenuItem menuItemCadastroCategorias;
    public static javax.swing.JMenuItem menuItemCadastroClientes;
    public static javax.swing.JMenuItem menuItemCadastroFornecedores;
    public static javax.swing.JMenuItem menuItemCadastrosFuncionarios;
    public static javax.swing.JMenuItem menuItemCadastrosProdutos;
    public static javax.swing.JMenuItem menuItemCadastrosUsuarios;
    public static javax.swing.JMenuItem menuItemMovimentoCompra;
    public static javax.swing.JMenuItem menuItemMovimentoVenda;
    public static javax.swing.JMenuItem menuItemRegistarCompras;
    public static javax.swing.JMenuItem menuItemRegistarVendas;
    public static javax.swing.JMenuItem menuItemRelatoriosCompras;
    public static javax.swing.JMenuItem menuItemRelatoriosVendas;
    public static javax.swing.JMenuItem menuItemSistemaContactos;
    public static javax.swing.JMenuItem menuItemSistemaSair;
    public static javax.swing.JMenuItem menuItemSistemaSobre;
    public static javax.swing.JMenu menuMovimentos;
    public static javax.swing.JMenu menuRegistos;
    public static javax.swing.JMenu menuRelatorios;
    public static javax.swing.JMenu menuSistema;
    // End of variables declaration//GEN-END:variables
}
