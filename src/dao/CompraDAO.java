/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Compra;
import modelo.ItemCompra;
import util.Conexao;
import util.Situacao;

/**
 *
 * @author user
 */
public class CompraDAO implements GenericoDAO<Compra> {

    @Override
    public void guardar(Compra compra) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO compra (id_fornecedor, data_compra, valor_total_compra, situação_compra) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, compra.getFornecedor().getIdFornecedor());
            ps.setDate(2, new Date(compra.getDataCompra().getTime()));
            ps.setDouble(3, compra.getValorTotalCompra());
            ps.setInt(4, compra.getSituacaoCompra().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idCompra = rs.getInt(1);

            for (ItemCompra itemCompra : compra.getItens()) {
                sql = "INSERT INTO item_compra (id_produto, id_compra, quantidade_item_compra, valor_unitario_item_compra) VALUES (?, ?, ?, ?)";
                ps = conexao.conectar().prepareStatement(sql);
                ps.setInt(1, itemCompra.getProduto().getIdProduto());
                ps.setInt(2, idCompra);
                ps.setInt(3, itemCompra.getQuantidadeItemCompra());
                ps.setDouble(4, itemCompra.getValorUnitarioItemCompra());
                ps.execute();

                if (compra.getSituacaoCompra() == Situacao.FINALIZADA) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.entradaEstoque(conexao, itemCompra.getProduto().getIdProduto(), itemCompra.getQuantidadeItemCompra());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS da compra. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Compra compra) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE compra SET id_fornecedor = ?, data_compra = ?, valor_total_compra = ?, situação_compra = ? WHERE id_compra = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, compra.getFornecedor().getIdFornecedor());
            ps.setDate(2, new Date(compra.getDataCompra().getTime()));
            ps.setDouble(3, compra.getValorTotalCompra());
            ps.setInt(4, compra.getSituacaoCompra().getId());
            ps.setInt(5, compra.getIdCompra());
            ps.execute();

            for (ItemCompra itemCompra : compra.getItensRemover()) {
                sql = "DELETE FROM item_compra WHERE id_item_compra = ?";
                ps = conexao.conectar().prepareStatement(sql);
                ps.setInt(1, itemCompra.getIdItemCompra());
                ps.execute();
            }

            for (ItemCompra itemCompra : compra.getItens()) {
                if (itemCompra.getIdItemCompra() == 0) {
                    sql = "INSERT INTO item_compra (id_produto, id_compra, quantidade_item_compra, valor_unitario_item_compra) VALUES (?, ?, ?, ?)";
                    ps = conexao.conectar().prepareStatement(sql);
                    ps.setInt(1, itemCompra.getProduto().getIdProduto());
                    ps.setInt(2, itemCompra.getCompra().getIdCompra());
                    ps.setInt(3, itemCompra.getQuantidadeItemCompra());
                    ps.setDouble(4, itemCompra.getValorUnitarioItemCompra());
                    ps.execute();
                } else {
                    sql = "UPDATE item_compra SET id_produto = ?, id_compra = ?, quantidade_item_compra = ?, valor_unitario_item_compra = ? WHERE id_item_compra = ?";
                    ps = conexao.conectar().prepareStatement(sql);
                    ps.setInt(1, itemCompra.getProduto().getIdProduto());
                    ps.setInt(2, itemCompra.getCompra().getIdCompra());
                    ps.setInt(3, itemCompra.getQuantidadeItemCompra());
                    ps.setDouble(4, itemCompra.getValorUnitarioItemCompra());
                    ps.setInt(5, itemCompra.getIdItemCompra());
                    ps.execute();
                }

                if (compra.getSituacaoCompra() == Situacao.FINALIZADA) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.entradaEstoque(conexao, itemCompra.getProduto().getIdProduto(), itemCompra.getQuantidadeItemCompra());
                }
            }
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS da compra. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Compra compra) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE compra SET id_fornecedor = ?, data_compra = ?, valor_total_compra = ?, situação_compra = ? WHERE id_compra = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, compra.getFornecedor().getIdFornecedor());
            ps.setDate(2, new Date(compra.getDataCompra().getTime()));
            ps.setDouble(3, compra.getValorTotalCompra());
            ps.setInt(4, Situacao.CANCELADA.getId());
            ps.setInt(5, compra.getIdCompra());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS da compra. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Compra buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Compra compra = new Compra();
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            String sql = "SELECT * FROM compra ORDER BY data_compra DESC";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setFornecedor(fornecedorDAO.buscarPorId(rs.getInt("id_fornecedor")));
                compra.setDataCompra(rs.getDate("data_compra"));
                compra.setSituacao(rs.getInt("situação_compra"));

                String sqlItem = "SELECT * FROM item_compra WHERE id_item_compra = ?";
                PreparedStatement psItem = conexao.conectar().prepareStatement(sqlItem);
                psItem.setInt(1, compra.getIdCompra());
                ResultSet rsItem = psItem.executeQuery();

                while (rsItem.next()) {
                    ItemCompra itemCompra = new ItemCompra();
                    itemCompra.setIdItemCompra(rsItem.getInt("id_item_compra"));
                    itemCompra.setProduto(produtoDAO.buscarPorId(rsItem.getInt("id_produto")));
                    itemCompra.setCompra(compra);
                    itemCompra.setQuantidadeItemCompra(rsItem.getInt("quantidade_item_compra"));
                    itemCompra.setValorUnitarioItemCompra(rsItem.getDouble("valor_unitario_item_compra"));
                    compra.addItem(itemCompra);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS da compra por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return compra;
    }

    @Override
    public ArrayList<Compra> BuscarTodos() {
        ArrayList listaCompras = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            String sql = "SELECT * FROM compra ORDER BY data_compra DESC";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setFornecedor(fornecedorDAO.buscarPorId(rs.getInt("id_fornecedor")));
                compra.setDataCompra(rs.getDate("data_compra"));
                compra.setSituacao(rs.getInt("situação_compra"));

                String sqlItem = "SELECT * FROM item_compra WHERE id_item_compra = ?";
                PreparedStatement psItem = conexao.conectar().prepareStatement(sqlItem);
                psItem.setInt(1, compra.getIdCompra());
                ResultSet rsItem = psItem.executeQuery();

                while (rsItem.next()) {
                    ItemCompra itemCompra = new ItemCompra();
                    itemCompra.setIdItemCompra(rsItem.getInt("id_item_compra"));
                    itemCompra.setProduto(produtoDAO.buscarPorId(rsItem.getInt("id_produto")));
                    itemCompra.setCompra(compra);
                    itemCompra.setQuantidadeItemCompra(rsItem.getInt("quantidade_item_compra"));
                    itemCompra.setValorUnitarioItemCompra(rsItem.getDouble("valor_unitario_item_compra"));
                    compra.addItem(itemCompra);
                }

                listaCompras.add(compra);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS da compra. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaCompras;
    }

}
