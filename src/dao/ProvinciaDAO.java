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
import modelo.Provincia;
import util.Conexao;

/**
 *
 * @author user
 */
public class ProvinciaDAO implements GenericoDAO<Provincia> {

    @Override
    public void guardar(Provincia provincia) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO provincia (nome_provincia) VALUES(?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, provincia.getNomeProvincia());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS da pronvíncia. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Provincia provincia) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE provincia SET nome_provincia = ? WHERE id_provincia = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, provincia.getNomeProvincia());
            ps.setInt(2, provincia.getIdProvincia());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao ACTUALIZAR DADOS da pronvíncia. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Provincia provincia) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM provincia WHERE id_provincia = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, provincia.getIdProvincia());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS da pronvíncia. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Provincia buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Provincia provincia = new Provincia();
        try {
            String sql = "SELECT * FROM provincia WHERE id_provincia = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                provincia.setIdProvincia(rs.getInt("id_provincia"));
                provincia.setNomeProvincia(rs.getString("nome_provincia"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS da província por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return provincia;
    }

    @Override
    public ArrayList<Provincia> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaProvincias = new ArrayList();

        try {
            String sql = "SELECT * FROM provincia ORDER BY nome_provincia";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                provincia.setIdProvincia(rs.getInt("id_provincia"));
                provincia.setNomeProvincia(rs.getString("nome_provincia"));
                listaProvincias.add(provincia);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS da província. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaProvincias;
    }
}
