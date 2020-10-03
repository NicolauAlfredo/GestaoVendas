/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ItemVenda;
import modelo.Venda;
import util.Conexao;
import util.Situacao;

/**
 *
 * @author user
 */
public class VendaDAO implements GenericoDAO<Venda> {

    @Override
    public void guardar(Venda venda) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO venda (id_cliente, data_venda, valor_total_venda, situacao_venda) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, venda.getCliente().getIdCliente());
            ps.setDate(2, new Date(venda.getDataVenda().getTime()));
            ps.setDouble(3, venda.getValorTotalVenda());
            ps.setInt(4, venda.getSituacao().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idVenda = rs.getInt(1);

            for (ItemVenda iv : venda.getItens()) {
                sql = "INSERT INTO item_venda (id_produto, id_venda, quantidade_item_venda, valor_unitario_item_venda) VALUES (?, ?, ?, ?)";
                ps = conexao.conectar().prepareStatement(sql);
                ps.setInt(1, iv.getProduto().getIdProduto());
                ps.setInt(2, idVenda);
                ps.setInt(3, iv.getQuantidadeItemVenda());
                ps.setDouble(4, iv.getValorUnitarioItemVenda());
                ps.execute();

                if (venda.getSituacao() == Situacao.FINALIZADA) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.saidaEstoque(conexao, iv.getProduto().getIdProduto(), iv.getQuantidadeItemVenda());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS da venda. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Venda venda) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE venda SET id_cliente = ?, data_venda = ?, valor_total_venda = ?, situacao_venda = ? WHERE id_venda = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, venda.getCliente().getIdCliente());
            ps.setDate(2, new Date(venda.getDataVenda().getTime()));
            ps.setDouble(3, venda.getValorTotalVenda());
            ps.setInt(4, venda.getSituacao().getId());
            ps.setInt(5, venda.getIdVenda());
            ps.execute();

            for (ItemVenda iv : venda.getItensRemover()) {
                sql = "DELETE FROM item_venda WHERE id_item_venda = ?";
                ps = conexao.conectar().prepareStatement(sql);
                ps.setInt(1, iv.getIdItemVenda());
                ps.execute();
            }

            for (ItemVenda iv : venda.getItens()) {
                if (iv.getIdItemVenda() == 0) {
                    sql = "INSERT INTO item_venda (id_produto, id_venda, quantidade_item_venda, valor_unitario_item_venda) VALUES (?, ?, ?, ?)";
                    ps = conexao.conectar().prepareStatement(sql);
                    ps.setInt(1, iv.getProduto().getIdProduto());
                    ps.setInt(2, iv.getVenda().getIdVenda());
                    ps.setInt(3, iv.getQuantidadeItemVenda());
                    ps.setDouble(4, iv.getValorUnitarioItemVenda());
                    ps.execute();
                } else {
                    sql = "UPDATE item_venda SET id_produto = ?, id_venda = ?, quantidade_item_venda = ?, valor_unitario_item_venda = ? WHERE id_item_venda = ?";
                    ps = conexao.conectar().prepareStatement(sql);
                    ps.setInt(1, iv.getProduto().getIdProduto());
                    ps.setInt(2, iv.getVenda().getIdVenda());
                    ps.setInt(3, iv.getQuantidadeItemVenda());
                    ps.setDouble(4, iv.getValorUnitarioItemVenda());
                    ps.setInt(5, iv.getIdItemVenda());
                    ps.execute();
                }

                if (venda.getSituacao() == Situacao.FINALIZADA) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.saidaEstoque(conexao, iv.getProduto().getIdProduto(), iv.getQuantidadeItemVenda());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ACTUALIZAR DADOS da venda. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Venda venda) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE venda SET id_cliente = ?, data_venda = ?, valor_total_venda = ?, situacao_venda = ? WHERE id_venda = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, venda.getCliente().getIdCliente());
            ps.setDate(2, new Date(venda.getDataVenda().getTime()));
            ps.setDouble(3, venda.getValorTotalVenda());
            ps.setInt(4, Situacao.CANCELADA.getId());
            ps.setInt(5, venda.getIdVenda());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS da venda. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Venda buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Venda venda = new Venda();
        try {
            String sql = "SELECT * FROM venda ORDER BY data_venda DESC";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setCliente(clienteDAO.buscarPorId(rs.getInt("id_cliente")));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setSituacao(rs.getInt("situacao_venda"));

                String sqlItem = "SELECT * FROM item_venda WHERE id_venda = ?";
                PreparedStatement psItem = conexao.conectar().prepareStatement(sqlItem);
                psItem.setInt(1, venda.getIdVenda());
                ResultSet rsItem = psItem.executeQuery();

                while (rsItem.next()) {
                    ItemVenda iv = new ItemVenda();
                    iv.setIdItemVenda(rsItem.getInt("id_item_venda"));
                    iv.setProduto(produtoDAO.buscarPorId(rsItem.getInt("id_produto")));
                    iv.setVenda(venda);
                    iv.setQuantidadeItemVenda(rsItem.getInt("quantidade_item_venda"));
                    iv.setValorUnitarioItemVenda(rsItem.getDouble("valor_unitario_item_venda"));
                    venda.addItem(iv);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS da venda por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return venda;
    }

    @Override
    public ArrayList<Venda> BuscarTodos() {
        Conexao conexao = new Conexao();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList listaVendas = new ArrayList();
        try {

            String sql = "SELECT * FROM venda ORDER BY data_venda DESC";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setCliente(clienteDAO.buscarPorId(rs.getInt("id_cliente")));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setSituacao(rs.getInt("situacao_venda"));

                String sqlItem = "SELECT * FROM item_venda WHERE id_venda = ?";
                PreparedStatement psItem = conexao.conectar().prepareStatement(sqlItem);
                psItem.setInt(1, venda.getIdVenda());
                ResultSet rsItem = psItem.executeQuery();

                while (rsItem.next()) {
                    ItemVenda iv = new ItemVenda();
                    iv.setIdItemVenda(rsItem.getInt("id_item_venda"));
                    iv.setProduto(produtoDAO.buscarPorId(rsItem.getInt("id_produto")));
                    iv.setVenda(venda);
                    iv.setQuantidadeItemVenda(rsItem.getInt("quantidade_item_venda"));
                    iv.setValorUnitarioItemVenda(rsItem.getDouble("valor_unitario_item_venda"));
                    venda.addItem(iv);
                }

                listaVendas.add(venda);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS da venda. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaVendas;
    }

    public List<Venda> buscarPorIntervaloDatas(java.sql.Date dataInicio, java.sql.Date dataFim) {
        List<Venda> vendas = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            String sql = "SELECT * FROM venda WHERE data_venda BETWEEN ? AND ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setDate(1, dataInicio);
            ps.setDate(2, dataFim);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                ClienteDAO clienteDAO = new ClienteDAO();

                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setCliente(clienteDAO.buscarPorId(rs.getInt("id_cliente")));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setSituacao(rs.getInt("situacao_venda"));
                vendas.add(venda);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo entre : " + dataInicio + "e " + dataFim);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LER DADOS da venda: " + ex.getLocalizedMessage());
        } finally {
            Conexao conexao = new Conexao();
            conexao.desconectar();
        }
        return vendas;
    }
}
