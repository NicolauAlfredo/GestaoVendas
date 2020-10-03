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
import modelo.Categoria;
import util.Conexao;

/**
 *
 * @author user
 */
public class CategoriaDAO implements GenericoDAO<Categoria> {

    @Override
    public void guardar(Categoria categoria) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO categoria (nome_categoria) VALUES(?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNomeCategoria());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS da categória. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Categoria categoria) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE categoria SET nome_categoria = ? WHERE id_categoria = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNomeCategoria());
            ps.setInt(2, categoria.getIdCategoria());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS da categória. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Categoria categoria) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM categoria WHERE id_categoria = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, categoria.getIdCategoria());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS da categoria. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Categoria categoria = new Categoria();

        try {
            String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS da categoria por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return categoria;
    }

    @Override
    public ArrayList<Categoria> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaCategorias = new ArrayList();
        try {
            String sql = "SELECT * FROM categoria ORDER BY nome_categoria";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                listaCategorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS da categoria. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaCategorias;
    }

}
