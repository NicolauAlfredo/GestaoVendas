/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import dao.UnidadeDAO;
import gui.tabelas.ProdutoTableModel;
import static gui.telas.TelaPrincipal.desktopPanePrincipal;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import modelo.Categoria;
import modelo.Produto;
import modelo.Unidade;

/**
 *
 * @author user
 */
public class TelaCadastrarProdutos extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaProdutos
     */
    public TelaCadastrarProdutos() {
        initComponents();
        carregarTabela();
        carregarComboBoxCategoria();
        carregarComboBoxUnidade();
        desabilitarFormulario();
        desabiliarBotoes();
        btnNovo.setEnabled(true);
        carregarImagens();
    }

    private void carregarImagens() {
        ImageIcon novo = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovo.setIcon(novo);

        ImageIcon guardar = new ImageIcon(getClass().getResource("/gui/icones/guardar.png"));
        menuItemGuardar.setIcon(guardar);

        ImageIcon actualizar = new ImageIcon(getClass().getResource("/gui/icones/actualizar.png"));
        menuItemActualizar.setIcon(actualizar);

        ImageIcon excluir = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        menuItemExcluir.setIcon(excluir);

        ImageIcon cancelar = new ImageIcon(getClass().getResource("/gui/icones/cancelar.png"));
        menuItemCancelar.setIcon(cancelar);

        ImageIcon nova = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovaCategoria.setIcon(nova);

        ImageIcon nova1 = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovaUnidade.setIcon(nova1);

        ImageIcon recarregar = new ImageIcon(getClass().getResource("/gui/icones/recarregar.png"));
        btnRefresh.setIcon(recarregar);
    }

    private void carregarTabela() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoTableModel pm = (ProdutoTableModel) tbProdutos.getModel();
        try {
            pm.setDados(produtoDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela de produtos.\n" + ex.getMessage(), "Produto", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarComboBoxCategoria() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.BuscarTodos();
        cbCategoria.removeAllItems();
        for (Categoria categoria : categorias) {
            cbCategoria.addItem(categoria.getNomeCategoria());
        }
    }

    private void carregarComboBoxUnidade() {
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        List<Unidade> unidades = unidadeDAO.BuscarTodos();
        cbUnidades.removeAllItems();
        for (Unidade unidade : unidades) {
            cbUnidades.addItem(unidade.getNomeUnidade());
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = new Produto();

                produto.setCodigoBarraProduto(txtCodigoBarra.getText());
                produto.setNomeProduto(txtNomeProduto.getText());
                produto.setPrecoVendaProduto(Double.parseDouble(ftfPrecoVendaProduto.getText()));
                produto.setPrecoCompraProduto(Double.parseDouble(ftfPrecoCompra.getText()));
                produto.setQuantidadeEstoqueProduto(Integer.parseInt(spQuantidadeProduto.getValue().toString()));
                produto.setDataValidadeProduto(ftfDataValidade.getText());

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                List<Categoria> categorias = categoriaDAO.BuscarTodos();

                int idCategoria = 0;
                for (int i = 0; i < categorias.size(); i++) {
                    idCategoria = categorias.get(cbCategoria.getSelectedIndex()).getIdCategoria();
                }

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);

                produto.setCategoria(categoria);

                UnidadeDAO unidadeDAO = new UnidadeDAO();
                List<Unidade> unidades = unidadeDAO.BuscarTodos();

                int idUnidade = 0;
                for (int i = 0; i < unidades.size(); i++) {
                    idUnidade = unidades.get(cbUnidades.getSelectedIndex()).getIdUnidade();
                }

                Unidade unidade = new Unidade();
                unidade.setIdUnidade(idUnidade);

                produto.setUnidade(unidade);

                produtoDAO.guardar(produto);

                txtCodigoBarra.setBackground(Color.white);
                txtCodigoBarra.setForeground(Color.black);

                txtNomeProduto.setBackground(Color.white);
                txtNomeProduto.setForeground(Color.black);

                ftfPrecoCompra.setBackground(Color.white);
                ftfPrecoCompra.setForeground(Color.black);

                ftfPrecoVendaProduto.setBackground(Color.white);
                ftfPrecoVendaProduto.setForeground(Color.black);

                carregarTabela();
                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso.\n", "Produto", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto.\n" + ex.getLocalizedMessage(), "Produto", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void actualizar() {
        if (validarFormulario()) {
            try {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = new Produto();

                produto.setIdProduto(Integer.parseInt(ftfId.getText()));
                produto.setCodigoBarraProduto(txtCodigoBarra.getText());
                produto.setNomeProduto(txtNomeProduto.getText());
                produto.setPrecoVendaProduto(Double.parseDouble(ftfPrecoVendaProduto.getText()));
                produto.setPrecoCompraProduto(Double.parseDouble(ftfPrecoCompra.getText()));
                produto.setQuantidadeEstoqueProduto(Integer.parseInt(spQuantidadeProduto.getValue().toString()));
                produto.setDataValidadeProduto(ftfDataValidade.getText());

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                List<Categoria> categorias = categoriaDAO.BuscarTodos();

                int idCategoria = 0;
                for (int i = 0; i < categorias.size(); i++) {
                    idCategoria = categorias.get(cbCategoria.getSelectedIndex()).getIdCategoria();
                }

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);

                produto.setCategoria(categoria);

                UnidadeDAO unidadeDAO = new UnidadeDAO();
                List<Unidade> unidades = unidadeDAO.BuscarTodos();

                int idUnidade = 0;
                for (int i = 0; i < unidades.size(); i++) {
                    idUnidade = unidades.get(cbUnidades.getSelectedIndex()).getIdUnidade();
                }

                Unidade unidade = new Unidade();
                unidade.setIdUnidade(idUnidade);

                produto.setUnidade(unidade);

                produtoDAO.actualizar(produto);

                txtCodigoBarra.setBackground(Color.white);
                txtCodigoBarra.setForeground(Color.black);

                txtNomeProduto.setBackground(Color.white);
                txtNomeProduto.setForeground(Color.black);

                ftfPrecoCompra.setBackground(Color.white);
                ftfPrecoCompra.setForeground(Color.black);

                ftfPrecoVendaProduto.setBackground(Color.white);
                ftfPrecoVendaProduto.setForeground(Color.black);

                carregarTabela();
                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Produto actualizado com sucesso.\n", "Produto", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(this, "Erro ao actualizar o produto.\n" + ex.getMessage(), "Produto", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            String nome = txtNomeProduto.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o produto " + nome + "?");
            if (opcao == 0) {
                produto.setIdProduto(Integer.parseInt(ftfId.getText()));
                produtoDAO.apagar(produto);
                carregarTabela();
                limparFormulario();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o produto.\n" + ex.getMessage(), "Produto", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private boolean validarFormulario() {
        if (txtCodigoBarra.getText().trim().length() < 1) {
            txtCodigoBarra.setBackground(Color.red);
            txtCodigoBarra.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Código de barra inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtCodigoBarra.requestFocus();
            return false;
        }
        if (txtNomeProduto.getText().trim().length() < 2) {
            txtNomeProduto.setBackground(Color.red);
            txtNomeProduto.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Nome inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNomeProduto.requestFocus();
            return false;
        }
        if ((Double.parseDouble(ftfPrecoCompra.getText()) <= 0)) {
            ftfPrecoCompra.setBackground(Color.red);
            ftfPrecoCompra.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Preço de compra inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            ftfPrecoCompra.requestFocus();
            return false;
        }
        if ((Double.parseDouble(ftfPrecoVendaProduto.getText()) <= 0)) {
            ftfPrecoVendaProduto.setBackground(Color.red);
            ftfPrecoVendaProduto.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Preço de venda inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            ftfPrecoVendaProduto.requestFocus();
            return false;
        }
        if ((Double.parseDouble(ftfPrecoVendaProduto.getText())) < ((Double.parseDouble(ftfPrecoCompra.getText())))) {
            ftfPrecoVendaProduto.setBackground(Color.red);
            ftfPrecoVendaProduto.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Preço de venda menor que preço de compra.", "Alerta", JOptionPane.WARNING_MESSAGE);
            ftfPrecoVendaProduto.requestFocus();
            return false;
        }
        return true;
    }

    private void limparFormulario() {
        ftfPrecoCompra.setText("");
        txtNomeProduto.setText("");
        ftfId.setText("");
        txtCodigoBarra.setText("");
        ftfPrecoVendaProduto.setText("");
        txtBuscar.setText("");
    }

    private void habilitarFormulario() {
        txtCodigoBarra.setEnabled(true);
        txtNomeProduto.setEnabled(true);
        txtCodigoBarra.setEnabled(true);
        ftfPrecoVendaProduto.setEnabled(true);
        ftfPrecoCompra.setEnabled(true);
        ftfDataValidade.setEnabled(true);
        spQuantidadeProduto.setEnabled(true);
    }

    private void desabilitarFormulario() {
        txtCodigoBarra.setEnabled(false);
        txtNomeProduto.setEnabled(false);
        txtCodigoBarra.setEnabled(false);
        ftfPrecoCompra.setEnabled(false);
        ftfDataValidade.setEnabled(false);
        ftfPrecoVendaProduto.setEnabled(false);
        spQuantidadeProduto.setEnabled(false);
    }

    private void habilitarBotoes() {
        btnNovo.setEnabled(true);
        menuOperacoes.setEnabled(true);
        menuItemActualizar.setEnabled(true);
        menuItemExcluir.setEnabled(true);
        menuItemCancelar.setEnabled(true);
    }

    private void desabiliarBotoes() {
        btnNovo.setEnabled(false);
        menuOperacoes.setEnabled(false);
        menuItemActualizar.setEnabled(false);
        menuItemExcluir.setEnabled(false);
        menuItemCancelar.setEnabled(false);
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
        lbCodigoBarra = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        lbPreco = new javax.swing.JLabel();
        lbQuantidade = new javax.swing.JLabel();
        spQuantidadeProduto = new javax.swing.JSpinner();
        pnOperacoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        ftfPrecoVendaProduto = new javax.swing.JFormattedTextField();
        txtCodigoBarra = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        ftfPrecoCompra = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        btnNovaCategoria = new javax.swing.JButton();
        lbUnidade = new javax.swing.JLabel();
        cbUnidades = new javax.swing.JComboBox<>();
        btnNovaUnidade = new javax.swing.JButton();
        ftfDataValidade = new javax.swing.JFormattedTextField();
        pnTabela = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pnBuscarProduto = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        ftfId = new javax.swing.JFormattedTextField();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spTabelaProduto = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        barraMenu = new javax.swing.JMenuBar();
        menuOperacoes = new javax.swing.JMenu();
        menuItemGuardar = new javax.swing.JMenuItem();
        menuItemActualizar = new javax.swing.JMenuItem();
        menuItemExcluir = new javax.swing.JMenuItem();
        menuItemCancelar = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de produtos");

        pnConteudo.setLayout(new java.awt.BorderLayout());

        pnFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnFormulario.setPreferredSize(new java.awt.Dimension(300, 437));

        lbCodigoBarra.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbCodigoBarra.setText("* Código de barra:");

        lbNome.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNome.setText("* Nome:");

        txtNomeProduto.setColumns(200);
        txtNomeProduto.setToolTipText("Nome do produto [Campo de preenchimento obrigatório]");
        txtNomeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeProdutoKeyPressed(evt);
            }
        });

        lbPreco.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbPreco.setText("* Preço de venda:");

        lbQuantidade.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbQuantidade.setText("* Quantidade:");

        spQuantidadeProduto.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000000, 1));
        spQuantidadeProduto.setToolTipText("Quantidade de entrada em stock [Campo de preenchimento obrigatório]");
        spQuantidadeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spQuantidadeProdutoKeyPressed(evt);
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

        javax.swing.GroupLayout pnOperacoesLayout = new javax.swing.GroupLayout(pnOperacoes);
        pnOperacoes.setLayout(pnOperacoesLayout);
        pnOperacoesLayout.setHorizontalGroup(
            pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnOperacoesLayout.setVerticalGroup(
            pnOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ftfPrecoVendaProduto.setColumns(10);
        ftfPrecoVendaProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftfPrecoVendaProduto.setToolTipText("Preço de venda do produto [Campo de preenchimento obrigatório]");
        ftfPrecoVendaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ftfPrecoVendaProdutoKeyPressed(evt);
            }
        });

        txtCodigoBarra.setColumns(200);
        txtCodigoBarra.setToolTipText("Código de barra do produto [Campo de preenchimento obrigatório]");
        txtCodigoBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarraKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("* Categória:");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategoria.setToolTipText("Categória do produto [Campo de preenchimento obrigatório]");
        cbCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbCategoriaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("* Preço de compra:");

        ftfPrecoCompra.setColumns(10);
        ftfPrecoCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftfPrecoCompra.setToolTipText("Preço de compra do produto [Campo de preenchimento obrigatório]");
        ftfPrecoCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ftfPrecoCompraKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Data de validade:");

        btnNovaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovaCategoria.setToolTipText("Cadastrar nova categória");
        btnNovaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaCategoriaActionPerformed(evt);
            }
        });

        lbUnidade.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbUnidade.setText("* Unidade:");

        cbUnidades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbUnidades.setToolTipText("Unidade do produto [Campo de preenchimento obrigatório]");

        btnNovaUnidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovaUnidade.setToolTipText("Cadastrar nova unidade");
        btnNovaUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaUnidadeActionPerformed(evt);
            }
        });

        ftfDataValidade.setColumns(10);
        try {
            ftfDataValidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataValidade.setToolTipText("Data de validade do produto");

        javax.swing.GroupLayout pnFormularioLayout = new javax.swing.GroupLayout(pnFormulario);
        pnFormulario.setLayout(pnFormularioLayout);
        pnFormularioLayout.setHorizontalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfDataValidade)
                    .addComponent(txtNomeProduto)
                    .addComponent(pnOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoBarra)
                    .addComponent(ftfPrecoCompra)
                    .addComponent(ftfPrecoVendaProduto)
                    .addComponent(spQuantidadeProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFormularioLayout.createSequentialGroup()
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbUnidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNovaCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNovaUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnFormularioLayout.createSequentialGroup()
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigoBarra)
                            .addComponent(lbNome)
                            .addComponent(jLabel3)
                            .addComponent(lbPreco)
                            .addComponent(lbQuantidade)
                            .addComponent(jLabel4)
                            .addComponent(lbUnidade)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCodigoBarra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfPrecoVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQuantidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfDataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUnidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovaUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovaCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pnConteudo.add(pnFormulario, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        pnBuscarProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnBuscarProduto.setPreferredSize(new java.awt.Dimension(521, 80));

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/recarregar.png"))); // NOI18N
        btnRefresh.setToolTipText("Recarregar a tabela, unidade e categória");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        ftfId.setEditable(false);
        ftfId.setToolTipText("ID do produto");
        ftfId.setEnabled(false);

        txtBuscar.setToolTipText("Buscar produto pelo código de barra");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel2.setText("Código de barra:");

        javax.swing.GroupLayout pnBuscarProdutoLayout = new javax.swing.GroupLayout(pnBuscarProduto);
        pnBuscarProduto.setLayout(pnBuscarProdutoLayout);
        pnBuscarProdutoLayout.setHorizontalGroup(
            pnBuscarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBuscarProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(ftfId, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh)
                .addContainerGap())
        );
        pnBuscarProdutoLayout.setVerticalGroup(
            pnBuscarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBuscarProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBuscarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(ftfId, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel3.add(pnBuscarProduto, java.awt.BorderLayout.PAGE_START);

        tbProdutos.setAutoCreateRowSorter(true);
        tbProdutos.setModel(new ProdutoTableModel());
        tbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutosMouseClicked(evt);
            }
        });
        spTabelaProduto.setViewportView(tbProdutos);

        jPanel3.add(spTabelaProduto, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnTabelaLayout = new javax.swing.GroupLayout(pnTabela);
        pnTabela.setLayout(pnTabelaLayout);
        pnTabelaLayout.setHorizontalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTabelaLayout.setVerticalGroup(
            pnTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnConteudo.add(pnTabela, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnConteudo, java.awt.BorderLayout.CENTER);

        menuOperacoes.setText("Operações");
        menuOperacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOperacoesActionPerformed(evt);
            }
        });

        menuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        menuItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        menuItemGuardar.setText("Guardar");
        menuItemGuardar.setToolTipText("Cadastrar produto");
        menuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGuardarActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuItemGuardar);

        menuItemActualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        menuItemActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/actualizar.png"))); // NOI18N
        menuItemActualizar.setText("Actualizar");
        menuItemActualizar.setToolTipText("Actualizar produto");
        menuItemActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActualizarActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuItemActualizar);

        menuItemExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        menuItemExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        menuItemExcluir.setText("Excluir");
        menuItemExcluir.setToolTipText("Excluir produto");
        menuItemExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExcluirActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuItemExcluir);

        menuItemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuItemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cancelar.png"))); // NOI18N
        menuItemCancelar.setText("Cancelar");
        menuItemCancelar.setToolTipText("Cancelar operações");
        menuItemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCancelarActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuItemCancelar);

        barraMenu.add(menuOperacoes);

        setJMenuBar(barraMenu);

        setBounds(0, 0, 918, 583);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitarBotoes();
        habilitarFormulario();
        menuItemActualizar.setEnabled(false);
        menuItemExcluir.setEnabled(false);
        menuItemCancelar.setEnabled(true);
        btnNovo.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutosMouseClicked
        if (evt.getClickCount() == 2) {
            Produto produto = new Produto();
            ProdutoTableModel tm = (ProdutoTableModel) tbProdutos.getModel();
            produto = tm.getRowValue(tbProdutos.getRowSorter().convertRowIndexToModel(tbProdutos.getSelectedRow()));

            ftfId.setValue(produto.getIdProduto());
            txtCodigoBarra.setValue(produto.getCodigoBarraProduto());
            txtNomeProduto.setText(produto.getNomeProduto());
            ftfPrecoCompra.setValue(produto.getPrecoCompraProduto());
            ftfPrecoVendaProduto.setValue(produto.getPrecoVendaProduto());
            cbUnidades.setSelectedItem(produto.getUnidade().getNomeUnidade());
            spQuantidadeProduto.setValue(produto.getQuantidadeEstoqueProduto());
            cbCategoria.setSelectedItem(produto.getCategoria().getNomeCategoria());

            txtCodigoBarra.setBackground(Color.blue);
            txtCodigoBarra.setForeground(Color.white);

            txtNomeProduto.setBackground(Color.blue);
            txtNomeProduto.setForeground(Color.white);

            ftfPrecoCompra.setBackground(Color.blue);
            ftfPrecoCompra.setForeground(Color.white);

            ftfPrecoVendaProduto.setBackground(Color.blue);
            ftfPrecoVendaProduto.setForeground(Color.white);

            cbUnidades.setBackground(Color.blue);
            cbUnidades.setForeground(Color.white);

            ftfDataValidade.setBackground(Color.blue);
            ftfDataValidade.setForeground(Color.white);

            cbCategoria.setBackground(Color.blue);
            cbCategoria.setForeground(Color.white);

            habilitarFormulario();
            desabiliarBotoes();
            menuOperacoes.setEnabled(true);
            menuItemGuardar.setEnabled(false);
            menuItemActualizar.setEnabled(true);
            menuItemExcluir.setEnabled(true);
            menuItemCancelar.setEnabled(true);

        }
    }//GEN-LAST:event_tbProdutosMouseClicked

    private void menuOperacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOperacoesActionPerformed

    }//GEN-LAST:event_menuOperacoesActionPerformed

    private void menuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_menuItemGuardarActionPerformed

    private void menuItemActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_menuItemActualizarActionPerformed

    private void menuItemExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_menuItemExcluirActionPerformed

    private void menuItemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCancelarActionPerformed
        desabiliarBotoes();
        desabilitarFormulario();
        limparFormulario();
        btnNovo.setEnabled(true);
    }//GEN-LAST:event_menuItemCancelarActionPerformed

    private void btnNovaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaCategoriaActionPerformed
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
    }//GEN-LAST:event_btnNovaCategoriaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        carregarTabela();
        carregarComboBoxCategoria();
        carregarComboBoxUnidade();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtCodigoBarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtNomeProduto.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoBarraKeyPressed

    private void txtNomeProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeProdutoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            ftfPrecoCompra.requestFocus();
        }
    }//GEN-LAST:event_txtNomeProdutoKeyPressed

    private void ftfPrecoCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftfPrecoCompraKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            ftfPrecoVendaProduto.requestFocus();
        }
    }//GEN-LAST:event_ftfPrecoCompraKeyPressed

    private void ftfPrecoVendaProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftfPrecoVendaProdutoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            ftfDataValidade.requestFocus();
        }
    }//GEN-LAST:event_ftfPrecoVendaProdutoKeyPressed

    private void spQuantidadeProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spQuantidadeProdutoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (menuItemGuardar.isEnabled()) {
                menuItemGuardar.requestFocus();
            } else {
                menuItemActualizar.requestFocus();
            }
        }
    }//GEN-LAST:event_spQuantidadeProdutoKeyPressed

    private void cbCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCategoriaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaKeyPressed

    private void btnNovaUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaUnidadeActionPerformed
        TelaCadastrarUnidades telaCadastrarUnidades = new TelaCadastrarUnidades();
        desktopPanePrincipal.add(telaCadastrarUnidades);
        telaCadastrarUnidades.setVisible(true);

        try {
            telaCadastrarUnidades.setSelected(true); //diz que a janela interna é maximizável 
            telaCadastrarUnidades.setMaximizable(true); //set o tamanho máximo dela, que depende da janela pai 
            telaCadastrarUnidades.setMaximum(true);
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Erro na tela categorias: " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnNovaUnidadeActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        TableRowSorter rs = (TableRowSorter) tbProdutos.getRowSorter();
        rs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), 0, 1));
    }//GEN-LAST:event_txtBuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btnNovaCategoria;
    private javax.swing.JButton btnNovaUnidade;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbUnidades;
    private javax.swing.JFormattedTextField ftfDataValidade;
    private javax.swing.JFormattedTextField ftfId;
    private javax.swing.JFormattedTextField ftfPrecoCompra;
    private javax.swing.JFormattedTextField ftfPrecoVendaProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbCodigoBarra;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbPreco;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lbUnidade;
    private javax.swing.JMenuItem menuItemActualizar;
    private javax.swing.JMenuItem menuItemCancelar;
    private javax.swing.JMenuItem menuItemExcluir;
    private javax.swing.JMenuItem menuItemGuardar;
    private javax.swing.JMenu menuOperacoes;
    private javax.swing.JPanel pnBuscarProduto;
    private javax.swing.JPanel pnConteudo;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JPanel pnOperacoes;
    private javax.swing.JPanel pnTabela;
    private javax.swing.JSpinner spQuantidadeProduto;
    private javax.swing.JScrollPane spTabelaProduto;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JFormattedTextField txtCodigoBarra;
    private javax.swing.JTextField txtNomeProduto;
    // End of variables declaration//GEN-END:variables
}
