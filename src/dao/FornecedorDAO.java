/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Fornecedor;
import util.Conexao;

/**
 *
 * @author user
 */
public class FornecedorDAO implements GenericoDAO<Fornecedor> {

    @Override
    public void guardar(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO fornecedor (nome_fornecedor, nif_fornecedor, endereco_fornecedor, telefone_fornecedor) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, fornecedor.getNomeFornecedor());
            ps.setString(2, fornecedor.getNifFornecedor());
            ps.setString(3, fornecedor.getEnderecoFornecedor());
            ps.setString(4, fornecedor.getTelefoneFornecedor());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS do fornecedor. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE fornecedor SET nome_fornecedor = ?, nif_fornecedor = ?, endereco_fornecedor = ?, telefone_fornecedor = ? WHERE id_fornecedor = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, fornecedor.getNomeFornecedor());
            ps.setString(2, fornecedor.getNifFornecedor());
            ps.setString(3, fornecedor.getEnderecoFornecedor());
            ps.setString(4, fornecedor.getTelefoneFornecedor());
            ps.setInt(5, fornecedor.getIdFornecedor());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS do fornecedor. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, fornecedor.getIdFornecedor());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS do fornecedor. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Fornecedor buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Fornecedor fornecedor = new Fornecedor();

        try {
            String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
                fornecedor.setNomeFornecedor(rs.getString("nome_fornecedor"));
                fornecedor.setNifFornecedor(rs.getString("nif_fornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("endereco_fornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefone_fornecedor"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS do fornecedor por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return fornecedor;
    }

    @Override
    public ArrayList<Fornecedor> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaFornecedores = new ArrayList();
        try {
            String sql = "SELECT * FROM fornecedor ORDER BY nome_fornecedor";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
                fornecedor.setNomeFornecedor(rs.getString("nome_fornecedor"));
                fornecedor.setNifFornecedor(rs.getString("nif_fornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("endereco_fornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefone_fornecedor"));
                listaFornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS do fornecedor. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaFornecedores;
    }
}
