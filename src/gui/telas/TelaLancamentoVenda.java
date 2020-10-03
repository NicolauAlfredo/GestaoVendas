/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.ProdutoDAO;
import dao.VendaDAO;
import gui.tabelas.ItemVendaTableModel;
import gui.tabelas.VendaTableModel;
import java.awt.Color;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;
import util.Situacao;

/**
 *
 * @author user
 */
public class TelaLancamentoVenda extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaLancamentoVenda
     */
    private Venda venda = null;
    private VendaDAO vendaDAO = new VendaDAO();

    public TelaLancamentoVenda() {
        initComponents();
        habilitarFormulario(false);
        carregarTabelaVendas();
        carregarImagens();
    }

    private void carregarImagens() {
        ImageIcon novo = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovo.setIcon(novo);

        ImageIcon guardar = new ImageIcon(getClass().getResource("/gui/icones/guardar.png"));
        btnGuardar.setIcon(guardar);

        ImageIcon finalizar = new ImageIcon(getClass().getResource("/gui/icones/finalizar.png"));
        btnFinalizar.setIcon(finalizar);

        ImageIcon excluir = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnExcluir.setIcon(excluir);

        ImageIcon adicionar = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnAdicionarItem.setIcon(adicionar);

        ImageIcon remover = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnRemoverItem.setIcon(remover);

        ImageIcon cancelar = new ImageIcon(getClass().getResource("/gui/icones/cancelar.png"));
        btnCancelar.setIcon(cancelar);

        ImageIcon buscar1 = new ImageIcon(getClass().getResource("/gui/icones/buscar.png"));
        btnBuscarCliente.setIcon(buscar1);

        ImageIcon buscar = new ImageIcon(getClass().getResource("/gui/icones/buscar.png"));
        btnBuscarProduto.setIcon(buscar);
    }

    private void carregarTabelaVendas() {
        VendaTableModel tm = (VendaTableModel) tbVendas.getModel();
        try {
            tm.setDados(vendaDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tabela de vendas.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarVenda(boolean finalizar) {
        if (validarFormulario()) {
            venda.setCliente((Cliente) ftfCliente.getValue());
            venda.setDataVenda((Date) ftfDataVenda.getValue());
            if (finalizar) {
                venda.setSituacao(Situacao.FINALIZADA);
                ftfProduto.setBackground(Color.white);
                ftfProduto.setForeground(Color.black);

                ftfCliente.setBackground(Color.white);
                ftfCliente.setForeground(Color.black);
            } else {
                venda.setSituacao(Situacao.ABERTA);
                ftfProduto.setBackground(Color.white);
                ftfProduto.setForeground(Color.black);

                ftfCliente.setBackground(Color.white);
                ftfCliente.setForeground(Color.black);
            }

            if (venda.getIdVenda() == 0) {
                try {
                    vendaDAO.guardar(venda);
                    ftfProduto.setBackground(Color.white);
                    ftfProduto.setForeground(Color.black);

                    ftfCliente.setBackground(Color.white);
                    ftfCliente.setForeground(Color.black);
                    JOptionPane.showMessageDialog(null, "Venda registada com sucesso.", "Venda", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar a venda.\n" + ex.getMessage(), "Venda", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                try {
                    vendaDAO.actualizar(venda);
                    ftfProduto.setBackground(Color.white);
                    ftfProduto.setForeground(Color.black);

                    ftfCliente.setBackground(Color.white);
                    ftfCliente.setForeground(Color.black);
                    JOptionPane.showMessageDialog(null, "Venda actualizada com sucesso.", "Venda", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao actualizar a venda.\n" + ex.getMessage(), "Venda", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            habilitarFormulario(false);
            carregarTabelaVendas();
        }
    }

    public void excluirVenda() {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar a venda " + venda + "?");
        if (opcao == 0) {
            try {
                vendaDAO.apagar(venda);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir a venda.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            habilitarFormulario(false);
            carregarTabelaVendas();
        }
    }

    public void adicionarItem() {
        if (validarFormularioItens()) {
            ItemVenda iv = new ItemVenda();
            iv.setProduto((Produto) ftfProduto.getValue());
            iv.setVenda(venda);
            iv.setQuantidadeItemVenda((int) spQuantidade.getValue());
            iv.setValorUnitarioItemVenda((Double) ftfValorUnitario.getValue());

            venda.addItem(iv);

            ItemVendaTableModel ivtm = (ItemVendaTableModel) tbItensVenda.getModel();
            ivtm.setDados(venda.getItens());

            ftfValorTotal.setValue(venda.getValorTotalVenda());

            limpaFormularioItens();

            ftfProduto.setBackground(Color.white);
            ftfProduto.setForeground(Color.black);
        }
    }

    public void removerItem() {
        if (tbItensVenda.getSelectedRowCount() > 0) {
            int linhaSelecionada = tbItensVenda.getSelectedRow();
            ItemVendaTableModel ivtm = (ItemVendaTableModel) tbItensVenda.getModel();
            ItemVenda iv = ivtm.getRowValue(linhaSelecionada);

            if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o item " + iv + "?", "Confirmação", JOptionPane.YES_NO_OPTION) == 0) {
                venda.removeItem(iv);
                ivtm.setDados(venda.getItens());
                ftfProduto.setBackground(Color.white);
                ftfProduto.setForeground(Color.black);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setCliente(Cliente cliente) {
        ftfCliente.setValue(cliente);
    }

    public void setProduto(Produto produto) {
        ftfProduto.setValue(produto);
        ftfValorUnitario.setValue(produto.getPrecoVendaProduto());
    }

    private void habilitarFormulario(boolean ativo) {
        btnNovo.setEnabled(!ativo);
        btnGuardar.setEnabled(ativo);
        btnFinalizar.setEnabled(ativo);
        btnExcluir.setEnabled(ativo);
        btnCancelar.setEnabled(ativo);
        ftfCliente.setEnabled(ativo);
        btnBuscarCliente.setEnabled(ativo);
        ftfDataVenda.setEnabled(ativo);
        ftfValorTotal.setEnabled(ativo);
        ftfProduto.setEnabled(ativo);
        btnBuscarProduto.setEnabled(ativo);
        spQuantidade.setEnabled(ativo);
        ftfValorUnitario.setEnabled(ativo);
        btnAdicionarItem.setEnabled(ativo);
        btnRemoverItem.setEnabled(ativo);
        tbVendas.setEnabled(!ativo);

        if (!ativo) {
            limpaFormulario();
        }
    }

    private void limpaFormulario() {
        venda = null;
        ftfCliente.setValue(null);
        ftfDataVenda.setValue(new Date());
        ftfValorTotal.setValue(new Double(0));
        ftfProduto.setValue(null);
        spQuantidade.setValue(1);
        ftfValorUnitario.setValue(new Double(0));
        tbItensVenda.setModel(new ItemVendaTableModel());
        limpaFormularioItens();
    }

    private void limpaFormularioItens() {
        ftfProduto.setValue(null);
        spQuantidade.setValue(1);
        ftfValorUnitario.setValue(new Double(0));
    }

    private boolean validarFormulario() {
        if (ftfCliente.getValue() == null) {
            ftfCliente.setBackground(Color.red);
            ftfCliente.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Cliente inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            ftfCliente.requestFocus();
            return false;
        }
        if (venda.quantidadeItens() == 0) {
            JOptionPane.showMessageDialog(this, "Quantidade de itens inválida.", "Alerta", JOptionPane.WARNING_MESSAGE);
            tpConteudo.setSelectedIndex(1);
            return false;
        }
        return true;
    }

    private boolean validarFormularioItens() {
        if (ftfProduto.getValue() == null) {
            ftfProduto.setBackground(Color.red);
            ftfProduto.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Produto inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            ftfProduto.requestFocus();
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
        java.awt.GridBagConstraints gridBagConstraints;

        pnBarraFerramentas = new javax.swing.JPanel();
        barraFerramentas = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        tpConteudo = new javax.swing.JTabbedPane();
        pnDadosVenda = new javax.swing.JPanel();
        pnFormularioVenda = new javax.swing.JPanel();
        lbNomeCliente = new javax.swing.JLabel();
        ftfCliente = new javax.swing.JFormattedTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lbDataVenda = new javax.swing.JLabel();
        ftfDataVenda = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        ftfValorTotal = new javax.swing.JFormattedTextField();
        spTabelaVendas = new javax.swing.JScrollPane();
        tbVendas = new javax.swing.JTable();
        pnItensVenda = new javax.swing.JPanel();
        pnFormularioItensVenda = new javax.swing.JPanel();
        lbProduto = new javax.swing.JLabel();
        ftfProduto = new javax.swing.JFormattedTextField();
        btnBuscarProduto = new javax.swing.JButton();
        lbQuantidade = new javax.swing.JLabel();
        spQuantidade = new javax.swing.JSpinner();
        lbValorUnitario = new javax.swing.JLabel();
        ftfValorUnitario = new javax.swing.JFormattedTextField();
        btnAdicionarItem = new javax.swing.JButton();
        btnRemoverItem = new javax.swing.JButton();
        spTabelaItensVenda = new javax.swing.JScrollPane();
        tbItensVenda = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lançamento de Vendas");
        setToolTipText("");

        barraFerramentas.setFloatable(false);
        barraFerramentas.setRollover(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnNovo);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnGuardar);

        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/finalizar.png"))); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.setFocusable(false);
        btnFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnFinalizar);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnExcluir);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnCancelar);

        pnBarraFerramentas.add(barraFerramentas);

        getContentPane().add(pnBarraFerramentas, java.awt.BorderLayout.PAGE_START);

        pnDadosVenda.setLayout(new java.awt.BorderLayout());

        pnFormularioVenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario"));
        pnFormularioVenda.setMinimumSize(new java.awt.Dimension(604, 200));
        pnFormularioVenda.setPreferredSize(new java.awt.Dimension(604, 200));
        pnFormularioVenda.setLayout(new java.awt.GridBagLayout());

        lbNomeCliente.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioVenda.add(lbNomeCliente, gridBagConstraints);

        ftfCliente.setEditable(false);
        ftfCliente.setColumns(25);
        ftfCliente.setMinimumSize(new java.awt.Dimension(206, 25));
        ftfCliente.setPreferredSize(new java.awt.Dimension(206, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioVenda.add(ftfCliente, gridBagConstraints);

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/buscar.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        pnFormularioVenda.add(btnBuscarCliente, new java.awt.GridBagConstraints());

        lbDataVenda.setText("Data da venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioVenda.add(lbDataVenda, gridBagConstraints);

        ftfDataVenda.setEditable(false);
        ftfDataVenda.setColumns(10);
        ftfDataVenda.setMinimumSize(new java.awt.Dimension(86, 25));
        ftfDataVenda.setPreferredSize(new java.awt.Dimension(86, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioVenda.add(ftfDataVenda, gridBagConstraints);

        jLabel3.setText("Valor total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioVenda.add(jLabel3, gridBagConstraints);

        ftfValorTotal.setEditable(false);
        ftfValorTotal.setColumns(10);
        ftfValorTotal.setMinimumSize(new java.awt.Dimension(86, 25));
        ftfValorTotal.setPreferredSize(new java.awt.Dimension(86, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioVenda.add(ftfValorTotal, gridBagConstraints);

        pnDadosVenda.add(pnFormularioVenda, java.awt.BorderLayout.PAGE_START);

        tbVendas.setModel(new VendaTableModel());
        tbVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVendasMouseClicked(evt);
            }
        });
        spTabelaVendas.setViewportView(tbVendas);

        pnDadosVenda.add(spTabelaVendas, java.awt.BorderLayout.CENTER);

        tpConteudo.addTab("Dados da venda", pnDadosVenda);

        pnItensVenda.setLayout(new java.awt.BorderLayout());

        pnFormularioItensVenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario"));
        pnFormularioItensVenda.setMinimumSize(new java.awt.Dimension(604, 200));
        pnFormularioItensVenda.setName(""); // NOI18N
        pnFormularioItensVenda.setPreferredSize(new java.awt.Dimension(604, 200));
        pnFormularioItensVenda.setLayout(new java.awt.GridBagLayout());

        lbProduto.setText("Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(lbProduto, gridBagConstraints);

        ftfProduto.setEditable(false);
        ftfProduto.setColumns(25);
        ftfProduto.setMinimumSize(new java.awt.Dimension(206, 25));
        ftfProduto.setPreferredSize(new java.awt.Dimension(206, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(ftfProduto, gridBagConstraints);

        btnBuscarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/buscar.png"))); // NOI18N
        btnBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdutoActionPerformed(evt);
            }
        });
        pnFormularioItensVenda.add(btnBuscarProduto, new java.awt.GridBagConstraints());

        lbQuantidade.setText("Quantidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(lbQuantidade, gridBagConstraints);

        spQuantidade.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000000, 1));
        spQuantidade.setMinimumSize(new java.awt.Dimension(95, 25));
        spQuantidade.setPreferredSize(new java.awt.Dimension(95, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(spQuantidade, gridBagConstraints);

        lbValorUnitario.setText("Valor unitário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(lbValorUnitario, gridBagConstraints);

        ftfValorUnitario.setEditable(false);
        ftfValorUnitario.setColumns(10);
        ftfValorUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        ftfValorUnitario.setMinimumSize(new java.awt.Dimension(95, 25));
        ftfValorUnitario.setPreferredSize(new java.awt.Dimension(95, 25));
        ftfValorUnitario.setValue(new Double(0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(ftfValorUnitario, gridBagConstraints);

        btnAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnFormularioItensVenda.add(btnAdicionarItem, gridBagConstraints);

        btnRemoverItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensVenda.add(btnRemoverItem, gridBagConstraints);

        pnItensVenda.add(pnFormularioItensVenda, java.awt.BorderLayout.PAGE_START);

        tbItensVenda.setModel(new ItemVendaTableModel());
        spTabelaItensVenda.setViewportView(tbItensVenda);

        pnItensVenda.add(spTabelaItensVenda, java.awt.BorderLayout.CENTER);

        tpConteudo.addTab("Itens da venda", pnItensVenda);

        getContentPane().add(tpConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        venda = new Venda();
        habilitarFormulario(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarVenda(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente finalizar a venda?");
        if (opcao == 0) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            for (ItemVenda iv : venda.getItens()) {
                try {
                    if (iv.getQuantidadeItemVenda() > produtoDAO.buscarPorId(iv.getProduto().getIdProduto()).getQuantidadeEstoqueProduto()) {
                        JOptionPane.showMessageDialog(this, "Impossível finalizar.\nProduto " + iv.getProduto() + " em falta no estoque.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao consultar o estoque.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            guardarVenda(true);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirVenda();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarFormulario(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        TelaBuscarCliente telaBuscarCliente = new TelaBuscarCliente(this);
        telaBuscarCliente.setVisible(true);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        adicionarItem();
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        removerItem();
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void btnBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdutoActionPerformed
        TelaBuscarProduto telaBuscarProduto = new TelaBuscarProduto(this);
        telaBuscarProduto.setVisible(true);
    }//GEN-LAST:event_btnBuscarProdutoActionPerformed

    private void tbVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVendasMouseClicked
        if (evt.getClickCount() == 2) {
            int linhaSelecionada = tbVendas.getSelectedRow();
            VendaTableModel vtm = (VendaTableModel) tbVendas.getModel();
            venda = vtm.getRowValue(linhaSelecionada);

            if (venda.getSituacao() == Situacao.FINALIZADA) {
                JOptionPane.showMessageDialog(this, "Venda finalizada não pode ser alterada.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (venda.getSituacao() == Situacao.CANCELADA) {
                JOptionPane.showMessageDialog(this, "Venda cancelada não pode ser alterada.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ftfCliente.setValue(venda.getCliente());
            ftfDataVenda.setValue(venda.getDataVenda());
            ftfValorTotal.setValue(venda.getValorTotalVenda());

            ItemVendaTableModel ivtm = (ItemVendaTableModel) tbItensVenda.getModel();
            ivtm.setDados(venda.getItens());

            habilitarFormulario(true);

            ftfCliente.setBackground(Color.blue);
            ftfCliente.setForeground(Color.white);

            ftfDataVenda.setBackground(Color.blue);
            ftfDataVenda.setForeground(Color.white);

            ftfValorTotal.setBackground(Color.blue);
            ftfValorTotal.setForeground(Color.white);

            ftfProduto.setBackground(Color.blue);
            ftfProduto.setForeground(Color.white);

            ftfValorUnitario.setBackground(Color.blue);
            ftfValorUnitario.setForeground(Color.white);
        }
    }//GEN-LAST:event_tbVendasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerramentas;
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JFormattedTextField ftfCliente;
    private javax.swing.JFormattedTextField ftfDataVenda;
    private javax.swing.JFormattedTextField ftfProduto;
    private javax.swing.JFormattedTextField ftfValorTotal;
    private javax.swing.JFormattedTextField ftfValorUnitario;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbDataVenda;
    private javax.swing.JLabel lbNomeCliente;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lbValorUnitario;
    private javax.swing.JPanel pnBarraFerramentas;
    private javax.swing.JPanel pnDadosVenda;
    private javax.swing.JPanel pnFormularioItensVenda;
    private javax.swing.JPanel pnFormularioVenda;
    private javax.swing.JPanel pnItensVenda;
    private javax.swing.JSpinner spQuantidade;
    private javax.swing.JScrollPane spTabelaItensVenda;
    private javax.swing.JScrollPane spTabelaVendas;
    private javax.swing.JTable tbItensVenda;
    private javax.swing.JTable tbVendas;
    private javax.swing.JTabbedPane tpConteudo;
    // End of variables declaration//GEN-END:variables
}
