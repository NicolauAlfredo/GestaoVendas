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
import modelo.Unidade;
import util.Conexao;

/**
 *
 * @author user
 */
public class UnidadeDAO implements GenericoDAO<Unidade> {

    @Override
    public void guardar(Unidade unidade) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO unidade (nome_unidade) VALUES(?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, unidade.getNomeUnidade());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS da unidade. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Unidade unidade) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE unidade SET nome_unidade = ? WHERE id_unidade = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, unidade.getNomeUnidade());
            ps.setInt(2, unidade.getIdUnidade());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS da unidade. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Unidade unidade) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM unidade WHERE id_unidade = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, unidade.getIdUnidade());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS da unidade. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Unidade buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Unidade unidade = new Unidade();

        try {
            String sql = "SELECT * FROM unidade WHERE id_unidade = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                unidade.setIdUnidade(rs.getInt("id_unidade"));
                unidade.setNomeUnidade(rs.getString("nome_unidade"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS da unidade por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return unidade;
    }

    @Override
    public ArrayList<Unidade> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaUnidades = new ArrayList();
        try {
            String sql = "SELECT * FROM unidade ORDER BY nome_unidade";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Unidade unidade = new Unidade();
                unidade.setIdUnidade(rs.getInt("id_unidade"));
                unidade.setNomeUnidade(rs.getString("nome_unidade"));
                listaUnidades.add(unidade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS da unidade. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaUnidades;
    }

}
