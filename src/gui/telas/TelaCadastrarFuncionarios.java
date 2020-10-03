/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.telas;

import dao.FuncionarioDAO;
import dao.MunicipioDAO;
import dao.ProvinciaDAO;
import gui.tabelas.FuncionarioTableModel;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.Municipio;
import modelo.Provincia;

/**
 *
 * @author user
 */
public class TelaCadastrarFuncionarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaFuncionarios
     */
    public TelaCadastrarFuncionarios() {
        initComponents();
        carregarTabela();
        carregarComboBoxProvincias();
        desabilitarFormulario();
        desabiliarBotoes();
        btnNovo.setEnabled(true);
        carregarImagens();
    }

    private void carregarImagens() {
        ImageIcon novo = new ImageIcon(getClass().getResource("/gui/icones/novo.png"));
        btnNovo.setIcon(novo);

        ImageIcon guardar = new ImageIcon(getClass().getResource("/gui/icones/guardar.png"));
        btnGuardarFuncionario.setIcon(guardar);

        ImageIcon actualizar = new ImageIcon(getClass().getResource("/gui/icones/actualizar.png"));
        btnActualizar.setIcon(actualizar);

        ImageIcon excluir = new ImageIcon(getClass().getResource("/gui/icones/excluir.png"));
        btnExcluir.setIcon(excluir);

        ImageIcon cancelar = new ImageIcon(getClass().getResource("/gui/icones/cancelar.png"));
        btnCancelar.setIcon(cancelar);
    }

    private void carregarTabela() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        FuncionarioTableModel fm = (FuncionarioTableModel) tbFuncionarios.getModel();

        try {
            fm.setDados(funcionarioDAO.BuscarTodos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela de funcionários.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarComboBoxMunicipios() {
        ProvinciaDAO provinciaDAO = new ProvinciaDAO();
        List<Provincia> provincias = provinciaDAO.BuscarTodos();

        int idProvincia = 0;
        for (int i = 0; i < provincias.size(); i++) {
            idProvincia = provincias.get(cbProvinciaFuncionario.getSelectedIndex()).getIdProvincia();
        }

        MunicipioDAO municipioDAO = new MunicipioDAO();
        List<Municipio> municipios = municipioDAO.buscarPorProvincia(idProvincia);
        cbMunicipioFuncionario.removeAllItems();
        for (Municipio municipio : municipios) {
            cbMunicipioFuncionario.addItem(municipio.getNomeMunicipio());
        }
    }

    private void carregarComboBoxProvincias() {
        ProvinciaDAO provinciaDAO = new ProvinciaDAO();
        List<Provincia> provincias = provinciaDAO.BuscarTodos();
        cbProvinciaFuncionario.removeAllItems();
        for (Provincia provincia : provincias) {
            cbProvinciaFuncionario.addItem(provincia.getNomeProvincia());
        }
    }

    private void guardar() {
        if (validarFormulario()) {
            try {
                Funcionario funcionario = new Funcionario();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

                funcionario.setNomeFuncionario(txtNomeFuncionario.getText().trim());
                funcionario.setBiFuncionario(txtBilheteIdentidadeFuncionario.getText().trim());
                funcionario.setTelefoneFuncionario(txtTelefoneFuncionario.getText().trim());

                MunicipioDAO municipioDAO = new MunicipioDAO();
                List<Municipio> municipios = municipioDAO.BuscarTodos();

                int idMunicipio = 0;
                for (int i = 0; i < municipios.size(); i++) {
                    idMunicipio = municipios.get(cbMunicipioFuncionario.getSelectedIndex()).getIdMunicipio();
                }

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(idMunicipio);

                funcionario.setMunicipio(municipio);

                ProvinciaDAO provinciaDAO = new ProvinciaDAO();
                List<Provincia> provincias = provinciaDAO.BuscarTodos();

                int idProvincia = 0;
                for (int i = 0; i < provincias.size(); i++) {
                    idProvincia = provincias.get(cbProvinciaFuncionario.getSelectedIndex()).getIdProvincia();
                }

                Provincia provincia = new Provincia();
                provincia.setIdProvincia(idProvincia);

                funcionarioDAO.guardar(funcionario);

                txtNomeFuncionario.setBackground(Color.white);
                txtNomeFuncionario.setForeground(Color.black);

                txtBilheteIdentidadeFuncionario.setBackground(Color.white);
                txtBilheteIdentidadeFuncionario.setForeground(Color.black);

                txtTelefoneFuncionario.setBackground(Color.white);
                txtTelefoneFuncionario.setForeground(Color.black);

                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                limparFormulario();
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso.\n", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o funcionario.\n" + ex.getMessage(), "Funcionário", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void actualizar() {
        if (validarFormulario()) {
            try {
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                Funcionario funcionario = new Funcionario();

                funcionario.setIdFuncionario(Integer.parseInt(ftfIdFuncionario.getText()));
                funcionario.setNomeFuncionario(txtNomeFuncionario.getText().trim());
                funcionario.setBiFuncionario(txtBilheteIdentidadeFuncionario.getText().trim());
                funcionario.setTelefoneFuncionario(txtTelefoneFuncionario.getText().trim());

                MunicipioDAO municipioDAO = new MunicipioDAO();
                List<Municipio> municipios = municipioDAO.BuscarTodos();

                int idMunicipio = 0;
                for (int i = 0; i < municipios.size(); i++) {
                    idMunicipio = municipios.get(cbMunicipioFuncionario.getSelectedIndex()).getIdMunicipio();
                }

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(idMunicipio);

                funcionario.setMunicipio(municipio);

                ProvinciaDAO provinciaDAO = new ProvinciaDAO();
                List<Provincia> provincias = provinciaDAO.BuscarTodos();

                int idProvincia = 0;
                for (int i = 0; i < provincias.size(); i++) {
                    idProvincia = provincias.get(cbProvinciaFuncionario.getSelectedIndex()).getIdProvincia();
                }

                Provincia provincia = new Provincia();
                provincia.setIdProvincia(idProvincia);

                funcionarioDAO.actualizar(funcionario);

                txtNomeFuncionario.setBackground(Color.white);
                txtNomeFuncionario.setForeground(Color.black);

                txtBilheteIdentidadeFuncionario.setBackground(Color.white);
                txtBilheteIdentidadeFuncionario.setForeground(Color.black);

                limparFormulario();
                desabiliarBotoes();
                desabilitarFormulario();
                btnNovo.setEnabled(true);
                carregarTabela();

                JOptionPane.showMessageDialog(this, "Funcionário actualizado com sucesso.\n", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao actualizar o funcionario.\n" + ex.getMessage(), "Funcionário", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void excluir() {
        try {
            Funcionario funcionario = new Funcionario();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            String nome = txtNomeFuncionario.getText();

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o funcionário " + nome + "?");
            if (opcao == 0) {
                funcionario.setIdFuncionario(Integer.parseInt(ftfIdFuncionario.getText()));
                funcionarioDAO.apagar(funcionario);

                txtNomeFuncionario.setBackground(Color.white);
                txtNomeFuncionario.setForeground(Color.black);

                txtBilheteIdentidadeFuncionario.setBackground(Color.white);
                txtBilheteIdentidadeFuncionario.setForeground(Color.black);

                txtTelefoneFuncionario.setBackground(Color.white);
                txtTelefoneFuncionario.setForeground(Color.black);

                limparFormulario();
                carregarTabela();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o funcionario.\n" + ex.getMessage(), "Funcionário", JOptionPane.ERROR_MESSAGE);
            return;
        }
        carregarTabela();
    }

    private boolean validarFormulario() {
        if (txtNomeFuncionario.getText().trim().length() < 2) {
            txtNomeFuncionario.setBackground(Color.red);
            txtNomeFuncionario.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Nome inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNomeFuncionario.requestFocus();
            return false;
        }
        if (txtBilheteIdentidadeFuncionario.getText().trim().length() < 14) {
            txtBilheteIdentidadeFuncionario.setBackground(Color.red);
            txtBilheteIdentidadeFuncionario.setForeground(Color.white);
            JOptionPane.showMessageDialog(this, "Nº do Biblhete de identidade inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtBilheteIdentidadeFuncionario.requestFocus();
            return false;
        }
        return true;
    }

    private void limparFormulario() {
        ftfIdFuncionario.setText("");
        txtNomeFuncionario.setText("");
        txtBilheteIdentidadeFuncionario.setText("");
        txtTelefoneFuncionario.setText("");
    }

    private void habilitarFormulario() {
        ftfIdFuncionario.setEditable(false);
        txtNomeFuncionario.setEnabled(true);
        txtBilheteIdentidadeFuncionario.setEnabled(true);
        txtTelefoneFuncionario.setEnabled(true);
    }

    private void desabilitarFormulario() {
        ftfIdFuncionario.setEditable(false);
        txtNomeFuncionario.setEnabled(false);
        txtBilheteIdentidadeFuncionario.setEnabled(false);
        txtTelefoneFuncionario.setEnabled(false);
    }

    private void habilitarBotoes() {
        btnGuardarFuncionario.setEnabled(true);
        btnLimparFuncionario.setEnabled(true);
        btnNovo.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void desabiliarBotoes() {
        btnGuardarFuncionario.setEnabled(false);
        btnLimparFuncionario.setEnabled(false);
        btnNovo.setEnabled(false);
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

        pnBarraFerramentas = new javax.swing.JPanel();
        barraFerramentas = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnConteudo = new javax.swing.JPanel();
        pnFormulario = new javax.swing.JPanel();
        lbIdFuncionario = new javax.swing.JLabel();
        ftfIdFuncionario = new javax.swing.JFormattedTextField();
        lbNomeFuncionario = new javax.swing.JLabel();
        txtNomeFuncionario = new javax.swing.JTextField();
        lbBilheteIdentidadeFuncionario = new javax.swing.JLabel();
        txtBilheteIdentidadeFuncionario = new javax.swing.JTextField();
        lbTelefoneFuncionario = new javax.swing.JLabel();
        txtTelefoneFuncionario = new javax.swing.JTextField();
        lbMunicipioFuncionario = new javax.swing.JLabel();
        cbMunicipioFuncionario = new javax.swing.JComboBox<>();
        pnOperacoesFuncionario = new javax.swing.JPanel();
        btnGuardarFuncionario = new javax.swing.JButton();
        btnLimparFuncionario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnBuscarMunicipios = new javax.swing.JButton();
        cbProvinciaFuncionario = new javax.swing.JComboBox<>();
        pnTabelaFuncionarios = new javax.swing.JPanel();
        pnTabela = new javax.swing.JPanel();
        spTabelaFuncionarios = new javax.swing.JScrollPane();
        tbFuncionarios = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de funcionários");

        pnBarraFerramentas.setOpaque(false);
        pnBarraFerramentas.setPreferredSize(new java.awt.Dimension(264, 50));

        barraFerramentas.setFloatable(false);
        barraFerramentas.setRollover(true);

        btnNovo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/novo.png"))); // NOI18N
        btnNovo.setToolTipText("Habilitar formulário e botões");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnNovo.setPreferredSize(new java.awt.Dimension(63, 40));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnNovo);

        btnActualizar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/actualizar.png"))); // NOI18N
        btnActualizar.setToolTipText("Actualizar funcionário");
        btnActualizar.setFocusable(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnActualizar.setPreferredSize(new java.awt.Dimension(63, 40));
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnActualizar);

        btnExcluir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir funcionário");
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnExcluir.setPreferredSize(new java.awt.Dimension(63, 40));
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnExcluir);

        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("Cancelar operações");
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnCancelar.setPreferredSize(new java.awt.Dimension(63, 40));
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnCancelar);

        pnBarraFerramentas.add(barraFerramentas);

        getContentPane().add(pnBarraFerramentas, java.awt.BorderLayout.PAGE_START);

        pnConteudo.setLayout(new java.awt.BorderLayout());

        pnFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnFormulario.setPreferredSize(new java.awt.Dimension(230, 477));

        lbIdFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbIdFuncionario.setText("Identificador único:");

        ftfIdFuncionario.setEditable(false);
        ftfIdFuncionario.setToolTipText("ID do funcionário");
        ftfIdFuncionario.setEnabled(false);

        lbNomeFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNomeFuncionario.setText("* Nome:");

        txtNomeFuncionario.setToolTipText("Nome do funcionário [Campo de preenchimento obrigatório]");
        txtNomeFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeFuncionarioKeyPressed(evt);
            }
        });

        lbBilheteIdentidadeFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbBilheteIdentidadeFuncionario.setText("* Nº do Bilhete de Identidade:");
        lbBilheteIdentidadeFuncionario.setToolTipText("");

        txtBilheteIdentidadeFuncionario.setToolTipText("Nº do BI do funcinároo [Campo de preenchimento obrigatório]");
        txtBilheteIdentidadeFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBilheteIdentidadeFuncionarioKeyPressed(evt);
            }
        });

        lbTelefoneFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbTelefoneFuncionario.setText("Telefone:");

        txtTelefoneFuncionario.setToolTipText("Nº do telefone do funcionário");
        txtTelefoneFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefoneFuncionarioKeyPressed(evt);
            }
        });

        lbMunicipioFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbMunicipioFuncionario.setText("* Município:");

        cbMunicipioFuncionario.setToolTipText("Naturalidade [Campo de preenchimento obrigatório]");
        cbMunicipioFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbMunicipioFuncionarioKeyPressed(evt);
            }
        });

        pnOperacoesFuncionario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnGuardarFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnGuardarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/guardar.png"))); // NOI18N
        btnGuardarFuncionario.setText("Guardar");
        btnGuardarFuncionario.setToolTipText("Cadastrar funcionário");
        btnGuardarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFuncionarioActionPerformed(evt);
            }
        });

        btnLimparFuncionario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLimparFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icones/excluir.png"))); // NOI18N
        btnLimparFuncionario.setText("Limpar");
        btnLimparFuncionario.setToolTipText("Limpar o formulário");
        btnLimparFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnOperacoesFuncionarioLayout = new javax.swing.GroupLayout(pnOperacoesFuncionario);
        pnOperacoesFuncionario.setLayout(pnOperacoesFuncionarioLayout);
        pnOperacoesFuncionarioLayout.setHorizontalGroup(
            pnOperacoesFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOperacoesFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimparFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnOperacoesFuncionarioLayout.setVerticalGroup(
            pnOperacoesFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOperacoesFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimparFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("* Província:");

        btnBuscarMunicipios.setText("Buscar municípios");
        btnBuscarMunicipios.setToolTipText("Buscar muncípios por província");
        btnBuscarMunicipios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMunicipiosActionPerformed(evt);
            }
        });
        btnBuscarMunicipios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarMunicipiosKeyPressed(evt);
            }
        });

        cbProvinciaFuncionario.setToolTipText("Naturalidade [Campo de preenchimento obrigatório]");
        cbProvinciaFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbProvinciaFuncionarioKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnFormularioLayout = new javax.swing.GroupLayout(pnFormulario);
        pnFormulario.setLayout(pnFormularioLayout);
        pnFormularioLayout.setHorizontalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarMunicipios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ftfIdFuncionario)
                    .addComponent(txtNomeFuncionario)
                    .addGroup(pnFormularioLayout.createSequentialGroup()
                        .addGroup(pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbIdFuncionario)
                            .addComponent(lbTelefoneFuncionario)
                            .addComponent(lbMunicipioFuncionario)
                            .addComponent(jLabel1)
                            .addComponent(lbNomeFuncionario)
                            .addComponent(lbBilheteIdentidadeFuncionario))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(txtBilheteIdentidadeFuncionario)
                    .addComponent(txtTelefoneFuncionario)
                    .addComponent(cbProvinciaFuncionario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMunicipioFuncionario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnOperacoesFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnFormularioLayout.setVerticalGroup(
            pnFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIdFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNomeFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBilheteIdentidadeFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBilheteIdentidadeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefoneFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProvinciaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarMunicipios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMunicipioFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMunicipioFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOperacoesFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pnConteudo.add(pnFormulario, java.awt.BorderLayout.LINE_START);

        pnTabela.setLayout(new java.awt.BorderLayout());

        tbFuncionarios.setAutoCreateRowSorter(true);
        tbFuncionarios.setModel(new FuncionarioTableModel());
        tbFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFuncionariosMouseClicked(evt);
            }
        });
        spTabelaFuncionarios.setViewportView(tbFuncionarios);

        pnTabela.add(spTabelaFuncionarios, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnTabelaFuncionariosLayout = new javax.swing.GroupLayout(pnTabelaFuncionarios);
        pnTabelaFuncionarios.setLayout(pnTabelaFuncionariosLayout);
        pnTabelaFuncionariosLayout.setHorizontalGroup(
            pnTabelaFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTabelaFuncionariosLayout.setVerticalGroup(
            pnTabelaFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTabelaFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnConteudo.add(pnTabelaFuncionarios, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitarFormulario();
        habilitarBotoes();
        btnActualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnNovo.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnBuscarMunicipiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMunicipiosActionPerformed
        carregarComboBoxMunicipios();
    }//GEN-LAST:event_btnBuscarMunicipiosActionPerformed

    private void btnGuardarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFuncionarioActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarFuncionarioActionPerformed

    private void btnLimparFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFuncionarioActionPerformed
        limparFormulario();
    }//GEN-LAST:event_btnLimparFuncionarioActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        btnNovo.setEnabled(true);
        desabiliarBotoes();
        desabilitarFormulario();
        limparFormulario();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desabiliarBotoes();
        desabilitarFormulario();
        limparFormulario();
        btnNovo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tbFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFuncionariosMouseClicked
        if (evt.getClickCount() == 2) {
            Funcionario funcionario = new Funcionario();

            FuncionarioTableModel fm = (FuncionarioTableModel) tbFuncionarios.getModel();
            funcionario = fm.getRowValue(tbFuncionarios.getRowSorter().convertRowIndexToModel(tbFuncionarios.getSelectedRow()));

            ftfIdFuncionario.setValue(funcionario.getIdFuncionario());
            txtNomeFuncionario.setText(funcionario.getNomeFuncionario());
            txtBilheteIdentidadeFuncionario.setText(funcionario.getBiFuncionario());
            txtTelefoneFuncionario.setText(funcionario.getTelefoneFuncionario());
            cbMunicipioFuncionario.setSelectedItem(funcionario.getMunicipio().getNomeMunicipio());

            txtNomeFuncionario.setBackground(Color.blue);
            txtNomeFuncionario.setForeground(Color.white);

            txtBilheteIdentidadeFuncionario.setBackground(Color.blue);
            txtBilheteIdentidadeFuncionario.setForeground(Color.white);

            txtTelefoneFuncionario.setBackground(Color.blue);
            txtTelefoneFuncionario.setForeground(Color.white);

            cbProvinciaFuncionario.setBackground(Color.blue);
            cbProvinciaFuncionario.setForeground(Color.white);

            cbMunicipioFuncionario.setBackground(Color.blue);
            cbMunicipioFuncionario.setForeground(Color.white);

            habilitarFormulario();
            desabiliarBotoes();
            btnExcluir.setEnabled(true);
            btnActualizar.setEnabled(true);
            btnCancelar.setEnabled(true);
            btnLimparFuncionario.setEnabled(true);
        }
    }//GEN-LAST:event_tbFuncionariosMouseClicked

    private void txtNomeFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeFuncionarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtBilheteIdentidadeFuncionario.requestFocus();
        }
    }//GEN-LAST:event_txtNomeFuncionarioKeyPressed

    private void txtBilheteIdentidadeFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBilheteIdentidadeFuncionarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTelefoneFuncionario.requestFocus();
        }
    }//GEN-LAST:event_txtBilheteIdentidadeFuncionarioKeyPressed

    private void txtTelefoneFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneFuncionarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            cbProvinciaFuncionario.requestFocus();
        }
    }//GEN-LAST:event_txtTelefoneFuncionarioKeyPressed

    private void cbProvinciaFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbProvinciaFuncionarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnBuscarMunicipios.requestFocus();
        }
    }//GEN-LAST:event_cbProvinciaFuncionarioKeyPressed

    private void cbMunicipioFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbMunicipioFuncionarioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (btnGuardarFuncionario.isEnabled()) {
                btnGuardarFuncionario.requestFocus();
                guardar();
            } else {
                btnActualizar.requestFocus();
                actualizar();
            }
        }
    }//GEN-LAST:event_cbMunicipioFuncionarioKeyPressed

    private void btnBuscarMunicipiosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarMunicipiosKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            cbMunicipioFuncionario.requestFocus();
        }
    }//GEN-LAST:event_btnBuscarMunicipiosKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerramentas;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarMunicipios;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGuardarFuncionario;
    private javax.swing.JButton btnLimparFuncionario;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cbMunicipioFuncionario;
    private javax.swing.JComboBox<String> cbProvinciaFuncionario;
    private javax.swing.JFormattedTextField ftfIdFuncionario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbBilheteIdentidadeFuncionario;
    private javax.swing.JLabel lbIdFuncionario;
    private javax.swing.JLabel lbMunicipioFuncionario;
    private javax.swing.JLabel lbNomeFuncionario;
    private javax.swing.JLabel lbTelefoneFuncionario;
    private javax.swing.JPanel pnBarraFerramentas;
    private javax.swing.JPanel pnConteudo;
    private javax.swing.JPanel pnFormulario;
    private javax.swing.JPanel pnOperacoesFuncionario;
    private javax.swing.JPanel pnTabela;
    private javax.swing.JPanel pnTabelaFuncionarios;
    private javax.swing.JScrollPane spTabelaFuncionarios;
    private javax.swing.JTable tbFuncionarios;
    private javax.swing.JTextField txtBilheteIdentidadeFuncionario;
    private javax.swing.JTextField txtNomeFuncionario;
    private javax.swing.JTextField txtTelefoneFuncionario;
    // End of variables declaration//GEN-END:variables
}
