/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.CompraDAO;
import gui.tabelas.CompraTableModel;
import gui.tabelas.ItemCompraTableModel;
import java.awt.Color;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Compra;
import modelo.Fornecedor;
import modelo.ItemCompra;
import modelo.Produto;
import util.Situacao;

/**
 *
 * @author user
 */
public class TelaLancamentoCompra extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaRegistoCompra
     */
    Compra compra = null;
    CompraDAO compraDAO = new CompraDAO();

    public TelaLancamentoCompra() {
        initComponents();
        carregarTabelaCompras();
        habilitarFormulario(false);
        carregarImagens();
    }

    private void carregarTabelaCompras() {
        CompraTableModel compraTableModel = (CompraTableModel) tbCompra.getModel();
        try {
            compraTableModel.setDados(compraDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tabela de compras.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarImagens() {
        ImageIcon novo = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovo.setIcon(novo);

        ImageIcon adicionar = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnAdicionarItem.setIcon(adicionar);

        ImageIcon remover = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnRemoverItem.setIcon(remover);

        ImageIcon guardar = new ImageIcon(getClass().getResource("/gui/icones/guardar.png"));
        btnGuardar.setIcon(guardar);

        ImageIcon actualizar = new ImageIcon(getClass().getResource("/gui/icones/finalizar.png"));
        btnFinalizar.setIcon(actualizar);

        ImageIcon excluir = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnExcluir.setIcon(excluir);

        ImageIcon cancelar = new ImageIcon(getClass().getResource("/gui/icones/cancelar.png"));
        btnCancelar.setIcon(cancelar);

        ImageIcon buscar1 = new ImageIcon(getClass().getResource("/gui/icones/buscar.png"));
        btnBuscaFornecedor.setIcon(buscar1);

        ImageIcon buscar = new ImageIcon(getClass().getResource("/gui/icones/buscar.png"));
        btnBuscarProduto.setIcon(buscar);
    }

    private void GuardarCompra(boolean finalizar) {
        if (validarFormulario()) {
            compra.setFornecedor((Fornecedor) ftfFornecedor.getValue());
            compra.setDataCompra((Date) ftfDataCompra.getValue());
            if (finalizar) {
                compra.setSituacaoCompra(Situacao.FINALIZADA);
                ftfProduto.setBackground(Color.white);
                ftfProduto.setForeground(Color.black);

                ftfFornecedor.setBackground(Color.white);
                ftfFornecedor.setForeground(Color.black);
            } else {
                compra.setSituacaoCompra(Situacao.ABERTA);
                ftfProduto.setBackground(Color.white);
                ftfProduto.setForeground(Color.black);

                ftfFornecedor.setBackground(Color.white);
                ftfFornecedor.setForeground(Color.black);
            }

            if (compra.getIdCompra() == 0) {
                try {
                    compraDAO.guardar(compra);
                    ftfProduto.setBackground(Color.white);
                    ftfProduto.setForeground(Color.black);

                    ftfFornecedor.setBackground(Color.white);
                    ftfFornecedor.setForeground(Color.black);
                    JOptionPane.showMessageDialog(null, "Compra registada com sucesso.", "Compra", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao registar a compra.\n" + ex.getMessage(), "Compra", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                try {
                    compraDAO.actualizar(compra);
                    ftfProduto.setBackground(Color.white);
                    ftfProduto.setForeground(Color.black);

                    ftfFornecedor.setBackground(Color.white);
                    ftfFornecedor.setForeground(Color.black);
                    JOptionPane.showMessageDialog(null, "Compra actualizada com sucesso.", "Compra", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao actualizar a compra.\n" + ex.getMessage(), "Compra", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            habilitarFormulario(false);
            carregarTabelaCompras();
        }
    }

    public void excluirCompra() {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar a compra " + compra + "?");
        if (opcao == 0) {
            try {
                compraDAO.apagar(compra);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir a compra.\n" + ex.getMessage(), "Compra", JOptionPane.ERROR_MESSAGE);
                return;
            }

            habilitarFormulario(false);
            carregarTabelaCompras();
        }
    }

    public void adicionarItem() {
        if (validarFormularioItens()) {
            ItemCompra iv = new ItemCompra();
            iv.setProduto((Produto) ftfProduto.getValue());
            iv.setCompra(compra);
            iv.setQuantidadeItemCompra((int) spQuantidade.getValue());
            iv.setValorUnitarioItemCompra((Double) ftfValorUnitario.getValue());

            compra.addItem(iv);

            ItemCompraTableModel ivtm = (ItemCompraTableModel) tbItensCompra.getModel();
            ivtm.setDados(compra.getItens());

            ftfValorTotal.setValue(compra.getValorTotalCompra());

            ftfProduto.setBackground(Color.white);
            ftfProduto.setForeground(Color.black);

            limpaFormularioItens();
        }
    }

    public void removerItem() {
        if (tbItensCompra.getSelectedRowCount() > 0) {
            int linhaSelecionada = tbItensCompra.getSelectedRow();
            ItemCompraTableModel ivtm = (ItemCompraTableModel) tbItensCompra.getModel();
            ItemCompra iv = ivtm.getRowValue(linhaSelecionada);

            if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o item " + iv + "?", "Confirmação", JOptionPane.YES_NO_OPTION) == 0) {
                compra.removeItem(iv);
                ivtm.setDados(compra.getItens());
                ftfProduto.setBackground(Color.white);
                ftfProduto.setForeground(Color.black);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setFornecedor(Fornecedor fornecedor) {
        ftfFornecedor.setValue(fornecedor);
    }

    public void setProduto(Produto produto) {
        ftfProduto.setValue(produto);
        ftfValorUnitario.setValue(produto.getPrecoCompraProduto());
    }

    private void habilitarFormulario(boolean ativo) {
        btnNovo.setEnabled(!ativo);
        btnGuardar.setEnabled(ativo);
        btnFinalizar.setEnabled(ativo);
        btnExcluir.setEnabled(ativo);
        btnCancelar.setEnabled(ativo);
        ftfFornecedor.setEnabled(ativo);
        btnBuscaFornecedor.setEnabled(ativo);
        ftfDataCompra.setEnabled(ativo);
        ftfValorTotal.setEnabled(ativo);
        ftfProduto.setEnabled(ativo);
        btnBuscarProduto.setEnabled(ativo);
        spQuantidade.setEnabled(ativo);
        ftfValorUnitario.setEnabled(ativo);
        btnAdicionarItem.setEnabled(ativo);
        btnRemoverItem.setEnabled(ativo);
        tbCompra.setEnabled(!ativo);

        if (!ativo) {
            limpaFormulario();
        }
    }

    private void limpaFormulario() {
        compra = null;
        ftfFornecedor.setValue(null);
        ftfDataCompra.setValue(new Date());
        ftfValorTotal.setValue(new Double(0));
        ftfProduto.setValue(null);
        spQuantidade.setValue(1);
        ftfValorUnitario.setValue(new Double(0));
        tbItensCompra.setModel(new ItemCompraTableModel());
        limpaFormularioItens();
    }

    private void limpaFormularioItens() {
        ftfProduto.setValue(null);
        spQuantidade.setValue(1);
        ftfValorUnitario.setValue(new Double(0));
    }

    private boolean validarFormulario() {
        if (ftfFornecedor.getValue() == null) {
            ftfFornecedor.setBackground(Color.red);
            ftfFornecedor.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Fornecedor inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            ftfFornecedor.requestFocus();
            return false;
        }
        if (compra.quantidadeItens() == 0) {
            spQuantidade.setBackground(Color.red);
            spQuantidade.setForeground(Color.white);
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
        pnCompra = new javax.swing.JPanel();
        pnFormularioCompra = new javax.swing.JPanel();
        lbFornecedor = new javax.swing.JLabel();
        ftfFornecedor = new javax.swing.JFormattedTextField();
        btnBuscaFornecedor = new javax.swing.JButton();
        lbDataCompra = new javax.swing.JLabel();
        ftfDataCompra = new javax.swing.JFormattedTextField();
        lbValorTotal = new javax.swing.JLabel();
        ftfValorTotal = new javax.swing.JFormattedTextField();
        spTabelaCompra = new javax.swing.JScrollPane();
        tbCompra = new javax.swing.JTable();
        pnItensCompra = new javax.swing.JPanel();
        pnFormularioItensCompra = new javax.swing.JPanel();
        lbProduto = new javax.swing.JLabel();
        ftfProduto = new javax.swing.JFormattedTextField();
        btnBuscarProduto = new javax.swing.JButton();
        lbQuantidade = new javax.swing.JLabel();
        spQuantidade = new javax.swing.JSpinner();
        lbValorUnitario = new javax.swing.JLabel();
        ftfValorUnitario = new javax.swing.JFormattedTextField();
        btnAdicionarItem = new javax.swing.JButton();
        btnRemoverItem = new javax.swing.JButton();
        spTabelaItensCompra = new javax.swing.JScrollPane();
        tbItensCompra = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lançamento de Compras");
        setToolTipText("");

        barraFerramentas.setFloatable(false);
        barraFerramentas.setRollover(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Habilitar formulário e botões");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnNovo);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Guardar compra");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnGuardar);

        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/finalizar.png"))); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.setToolTipText("Finalizar a compra");
        btnFinalizar.setFocusable(false);
        btnFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizar.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btnFinalizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnFinalizar);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir compra");
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnExcluir);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Cancelar operações");
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnCancelar);

        pnBarraFerramentas.add(barraFerramentas);

        getContentPane().add(pnBarraFerramentas, java.awt.BorderLayout.PAGE_START);

        pnCompra.setLayout(new java.awt.BorderLayout());

        pnFormularioCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulário"));
        pnFormularioCompra.setMinimumSize(new java.awt.Dimension(604, 200));
        pnFormularioCompra.setOpaque(false);
        pnFormularioCompra.setPreferredSize(new java.awt.Dimension(604, 200));
        pnFormularioCompra.setLayout(new java.awt.GridBagLayout());

        lbFornecedor.setText("* Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioCompra.add(lbFornecedor, gridBagConstraints);

        ftfFornecedor.setEditable(false);
        ftfFornecedor.setColumns(25);
        ftfFornecedor.setToolTipText("Fornecedor da compra [Campo de preenchimento obrigatório]");
        ftfFornecedor.setMinimumSize(new java.awt.Dimension(206, 25));
        ftfFornecedor.setPreferredSize(new java.awt.Dimension(206, 25));
        ftfFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftfFornecedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioCompra.add(ftfFornecedor, gridBagConstraints);

        btnBuscaFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/buscar.png"))); // NOI18N
        btnBuscaFornecedor.setToolTipText("Buscar fornecedor");
        btnBuscaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaFornecedorActionPerformed(evt);
            }
        });
        pnFormularioCompra.add(btnBuscaFornecedor, new java.awt.GridBagConstraints());

        lbDataCompra.setText("* Data da compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioCompra.add(lbDataCompra, gridBagConstraints);

        ftfDataCompra.setEditable(false);
        ftfDataCompra.setColumns(10);
        ftfDataCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftfDataCompra.setToolTipText("Data da compra [Campo de preenchimento obrigatório]");
        ftfDataCompra.setMinimumSize(new java.awt.Dimension(206, 25));
        ftfDataCompra.setPreferredSize(new java.awt.Dimension(206, 25));
        ftfDataCompra.setValue(new Date());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioCompra.add(ftfDataCompra, gridBagConstraints);

        lbValorTotal.setText("* Valor total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioCompra.add(lbValorTotal, gridBagConstraints);

        ftfValorTotal.setEditable(false);
        ftfValorTotal.setColumns(10);
        ftfValorTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        ftfValorTotal.setToolTipText("Valor total da compra [Campo de preenchimento obrigatório]");
        ftfValorTotal.setMinimumSize(new java.awt.Dimension(206, 25));
        ftfValorTotal.setName(""); // NOI18N
        ftfValorTotal.setPreferredSize(new java.awt.Dimension(206, 25));
        ftfValorTotal.setValue(new Double(0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioCompra.add(ftfValorTotal, gridBagConstraints);

        pnCompra.add(pnFormularioCompra, java.awt.BorderLayout.PAGE_START);

        tbCompra.setModel(new CompraTableModel());
        tbCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCompraMouseClicked(evt);
            }
        });
        spTabelaCompra.setViewportView(tbCompra);

        pnCompra.add(spTabelaCompra, java.awt.BorderLayout.CENTER);

        tpConteudo.addTab("Dados da compra", pnCompra);

        pnItensCompra.setLayout(new java.awt.BorderLayout());

        pnFormularioItensCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulário"));
        pnFormularioItensCompra.setMinimumSize(new java.awt.Dimension(604, 200));
        pnFormularioItensCompra.setPreferredSize(new java.awt.Dimension(604, 200));
        pnFormularioItensCompra.setLayout(new java.awt.GridBagLayout());

        lbProduto.setText("* Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensCompra.add(lbProduto, gridBagConstraints);

        ftfProduto.setEditable(false);
        ftfProduto.setColumns(25);
        ftfProduto.setToolTipText("Produto da compra [Campo de preenchimento obrigatório]");
        ftfProduto.setMinimumSize(new java.awt.Dimension(206, 26));
        ftfProduto.setPreferredSize(new java.awt.Dimension(206, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensCompra.add(ftfProduto, gridBagConstraints);

        btnBuscarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/buscar.png"))); // NOI18N
        btnBuscarProduto.setToolTipText("Buscar produto");
        btnBuscarProduto.setPreferredSize(new java.awt.Dimension(49, 215));
        btnBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdutoActionPerformed(evt);
            }
        });
        pnFormularioItensCompra.add(btnBuscarProduto, new java.awt.GridBagConstraints());

        lbQuantidade.setText("* Quantidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensCompra.add(lbQuantidade, gridBagConstraints);
        lbQuantidade.getAccessibleContext().setAccessibleName("");

        spQuantidade.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000000, 1));
        spQuantidade.setToolTipText("Quantidade de compra [Campo de preenchimento obrigatório]");
        spQuantidade.setMinimumSize(new java.awt.Dimension(95, 25));
        spQuantidade.setPreferredSize(new java.awt.Dimension(95, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensCompra.add(spQuantidade, gridBagConstraints);
        spQuantidade.getAccessibleContext().setAccessibleName("");

        lbValorUnitario.setText("* Valor unitário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensCompra.add(lbValorUnitario, gridBagConstraints);

        ftfValorUnitario.setEditable(false);
        ftfValorUnitario.setColumns(10);
        ftfValorUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        ftfValorUnitario.setToolTipText("Valor de cada unidade [Campo de preenchimento obrigatório]");
        ftfValorUnitario.setMinimumSize(new java.awt.Dimension(95, 25));
        ftfValorUnitario.setPreferredSize(new java.awt.Dimension(95, 25));
        ftfValorUnitario.setValue(new Double(0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnFormularioItensCompra.add(ftfValorUnitario, gridBagConstraints);

        btnAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnAdicionarItem.setToolTipText("Adicionar produto ao carrinho de compras");
        btnAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnFormularioItensCompra.add(btnAdicionarItem, gridBagConstraints);

        btnRemoverItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnRemoverItem.setToolTipText("Remover item do carrinho de compras");
        btnRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        pnFormularioItensCompra.add(btnRemoverItem, gridBagConstraints);

        pnItensCompra.add(pnFormularioItensCompra, java.awt.BorderLayout.PAGE_START);

        tbItensCompra.setModel(new ItemCompraTableModel());
        spTabelaItensCompra.setViewportView(tbItensCompra);

        pnItensCompra.add(spTabelaItensCompra, java.awt.BorderLayout.CENTER);

        tpConteudo.addTab("Itens da compra", pnItensCompra);

        getContentPane().add(tpConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ftfFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfFornecedorActionPerformed

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        adicionarItem();
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        compra = new Compra();
        habilitarFormulario(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        GuardarCompra(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente finalizar a compra?");
        if (opcao == 0) {
            GuardarCompra(true);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirCompra();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarFormulario(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdutoActionPerformed
        TelaBuscarProduto telaBuscaProduto = new TelaBuscarProduto(this);
        telaBuscaProduto.setVisible(true);
    }//GEN-LAST:event_btnBuscarProdutoActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        removerItem();
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void btnBuscaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaFornecedorActionPerformed
        TelaBuscarFornecedor telaBuscaFornecedor = new TelaBuscarFornecedor(this);
        telaBuscaFornecedor.setVisible(true);
    }//GEN-LAST:event_btnBuscaFornecedorActionPerformed

    private void tbCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCompraMouseClicked
        if (evt.getClickCount() == 2) {
            int linhaSelecionada = tbCompra.getSelectedRow();
            CompraTableModel vtm = (CompraTableModel) tbCompra.getModel();
            compra = vtm.getRowValue(linhaSelecionada);

            if (compra.getSituacaoCompra() == Situacao.FINALIZADA) {
                JOptionPane.showMessageDialog(this, "Compra finalizada não pode ser alterada.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (compra.getSituacaoCompra() == Situacao.CANCELADA) {
                JOptionPane.showMessageDialog(this, "Compra cancelada não pode ser alterada.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ftfFornecedor.setValue(compra.getFornecedor());
            ftfDataCompra.setValue(compra.getDataCompra());
            ftfValorTotal.setValue(compra.getValorTotalCompra());

            ItemCompraTableModel ivtm = (ItemCompraTableModel) tbItensCompra.getModel();
            ivtm.setDados(compra.getItens());

            habilitarFormulario(true);

            ftfFornecedor.setBackground(Color.blue);
            ftfFornecedor.setForeground(Color.white);

            ftfDataCompra.setBackground(Color.blue);
            ftfDataCompra.setForeground(Color.white);

            ftfValorTotal.setBackground(Color.blue);
            ftfValorTotal.setForeground(Color.white);

            ftfProduto.setBackground(Color.blue);
            ftfProduto.setForeground(Color.white);

            ftfValorUnitario.setBackground(Color.blue);
            ftfValorUnitario.setForeground(Color.white);
        }
    }//GEN-LAST:event_tbCompraMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerramentas;
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnBuscaFornecedor;
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JFormattedTextField ftfDataCompra;
    private javax.swing.JFormattedTextField ftfFornecedor;
    private javax.swing.JFormattedTextField ftfProduto;
    private javax.swing.JFormattedTextField ftfValorTotal;
    private javax.swing.JFormattedTextField ftfValorUnitario;
    private javax.swing.JLabel lbDataCompra;
    private javax.swing.JLabel lbFornecedor;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lbValorTotal;
    private javax.swing.JLabel lbValorUnitario;
    private javax.swing.JPanel pnBarraFerramentas;
    private javax.swing.JPanel pnCompra;
    private javax.swing.JPanel pnFormularioCompra;
    private javax.swing.JPanel pnFormularioItensCompra;
    private javax.swing.JPanel pnItensCompra;
    private javax.swing.JSpinner spQuantidade;
    private javax.swing.JScrollPane spTabelaCompra;
    private javax.swing.JScrollPane spTabelaItensCompra;
    private javax.swing.JTable tbCompra;
    private javax.swing.JTable tbItensCompra;
    private javax.swing.JTabbedPane tpConteudo;
    // End of variables declaration//GEN-END:variables
}
