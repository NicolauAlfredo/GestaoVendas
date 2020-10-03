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
import java.util.ArrayList;
import modelo.Produto;
import util.Conexao;

/**
 *
 * @author user
 */
public class ProdutoDAO implements GenericoDAO<Produto> {

    @Override
    public void guardar(Produto produto) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO produto (codigo_barra_produto, nome_produto, preco_compra_produto, preco_venda_produto, quantidade_estoque_produto, data_validade_produto, id_categoria, id_unidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, produto.getCodigoBarraProduto());
            ps.setString(2, produto.getNomeProduto());
            ps.setDouble(3, produto.getPrecoCompraProduto());
            ps.setDouble(4, produto.getPrecoVendaProduto());
            ps.setInt(5, produto.getQuantidadeEstoqueProduto());
            ps.setString(6, produto.getDataValidadeProduto());
            ps.setInt(7, produto.getCategoria().getIdCategoria());
            ps.setInt(8, produto.getUnidade().getIdUnidade());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS do produto. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Produto produto) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE produto SET codigo_barra_produto = ?, nome_produto = ?, preco_compra_produto = ?, preco_venda_produto = ?, quantidade_estoque_produto = ?, data_validade_produto = ?, unidade_produto = ?, id_categoria = ? WHERE id_produto = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, produto.getCodigoBarraProduto());
            ps.setString(2, produto.getNomeProduto());
            ps.setDouble(3, produto.getPrecoCompraProduto());
            ps.setDouble(4, produto.getPrecoVendaProduto());
            ps.setInt(5, produto.getQuantidadeEstoqueProduto()); 
            ps.setString(6, produto.getDataValidadeProduto());
            ps.setInt(7, produto.getCategoria().getIdCategoria());
            ps.setInt(8, produto.getUnidade().getIdUnidade());
            ps.setInt(9, produto.getIdProduto());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao ACTUALIZAR DADOS do produto. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public void entradaEstoque(Conexao conexao, int id, int quantidade) {
        try {
            String sql = "UPDATE produto SET quantidade_estoque_produto = quantidade_estoque_produto  + ? WHERE id_produto = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, quantidade);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao DAR ENTRADA AOS DADOS do produto no estoque. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public void saidaEstoque(Conexao conexao, int id, int quantidade) {
        try {
            String sql = "UPDATE produto SET quantidade_estoque_produto = quantidade_estoque_produto  - ? WHERE id_produto = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, quantidade);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao DAR SAÍDA AOS DADOS do produto no estoque. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Produto produto) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, produto.getIdProduto());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS do produto. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Produto buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Produto produto = new Produto();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        UnidadeDAO unidadeDAO = new UnidadeDAO();

        try {
            String sql = "SELECT * FROM produto WHERE id_produto = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setCodigoBarraProduto(rs.getString("codigo_barra_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setPrecoCompraProduto(rs.getDouble("preco_compra_produto"));
                produto.setPrecoVendaProduto(rs.getDouble("preco_venda_produto"));
                produto.setQuantidadeEstoqueProduto(rs.getInt("quantidade_estoque_produto"));
                produto.setDataValidadeProduto(rs.getString("data_validade_produto"));
                produto.setUnidade(unidadeDAO.buscarPorId(rs.getInt("id_unidade")));
                produto.setCategoria(categoriaDAO.buscarPorId(rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LISTAR DADOS do produto por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return produto;
    }

    @Override
    public ArrayList<Produto> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaProdutos = new ArrayList();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        UnidadeDAO unidadeDAO = new UnidadeDAO();

        try {
            String sql = "SELECT * FROM produto ORDER BY nome_produto";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setCodigoBarraProduto(rs.getString("codigo_barra_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setPrecoCompraProduto(rs.getDouble("preco_compra_produto"));
                produto.setPrecoVendaProduto(rs.getDouble("preco_venda_produto"));
                produto.setQuantidadeEstoqueProduto(rs.getInt("quantidade_estoque_produto"));
                produto.setDataValidadeProduto(rs.getString("data_validade_produto"));
                produto.setUnidade(unidadeDAO.buscarPorId(rs.getInt("id_unidade")));
                produto.setCategoria(categoriaDAO.buscarPorId(rs.getInt("id_categoria")));
                listaProdutos.add(produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LISTAR TODOS OS DADOS produtos. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaProdutos;
    }

}
