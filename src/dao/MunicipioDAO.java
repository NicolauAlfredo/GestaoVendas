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
import java.util.List;
import modelo.Municipio;
import util.Conexao;

/**
 *
 * @author user
 */
public class MunicipioDAO implements GenericoDAO<Municipio> {

    @Override
    public void guardar(Municipio municipio) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO municipio (nome_municipio, id_provincia) VALUES(?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvincia().getIdProvincia());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS do municipio. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Municipio municipio) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE municipio SET nome_municipio = ?, id_provincia = ? WHERE id_municipio = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvincia().getIdProvincia());
            ps.setInt(3, municipio.getIdMunicipio());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao ACTUALIZAR DADOS do municipio. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Municipio municipio) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM municipio WHERE id_municipio = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, municipio.getIdMunicipio());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS do municipio. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Municipio buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Municipio municipio = new Municipio();
        ProvinciaDAO provinciaDAO = new ProvinciaDAO();
        try {
            String sql = "SELECT * FROM municipio WHERE id_municipio = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                municipio.setIdMunicipio(rs.getInt("id_municipio"));
                municipio.setNomeMunicipio(rs.getString("nome_municipio"));
                municipio.setProvincia(provinciaDAO.buscarPorId(rs.getInt("id_provincia")));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS do municipio por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return municipio;
    }

    @Override
    public ArrayList<Municipio> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaProvincias = new ArrayList();
        ProvinciaDAO provinciaDAO = new ProvinciaDAO();

        try {
            String sql = "SELECT * FROM municipio ORDER BY nome_municipio";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(rs.getInt("id_municipio"));
                municipio.setNomeMunicipio(rs.getString("nome_municipio"));
                municipio.setProvincia(provinciaDAO.buscarPorId(rs.getInt("id_provincia")));
                listaProvincias.add(municipio);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS do municipio. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaProvincias;
    }

    public List<Municipio> buscarPorProvincia(int id) {
        Conexao conexao = new Conexao();
        ProvinciaDAO provinciaDAO = new ProvinciaDAO();
        List<Municipio> municipios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE m.id_provincia = ? order by m.nome_municipio";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
                municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
                municipio.setProvincia(provinciaDAO.buscarPorId(rs.getInt("p.id_provincia")));
                municipios.add(municipio);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao LER DADOS do municipio por província. \n" + ex.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return municipios;
    }
}
