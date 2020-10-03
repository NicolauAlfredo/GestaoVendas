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
import modelo.Funcionario;
import util.Conexao;

/**
 *
 * @author user
 */
public class FuncionarioDAO implements GenericoDAO<Funcionario> {

    @Override
    public void guardar(Funcionario funcionario) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO funcionario (nome_funcionario, bi_funcionario, telefone_funcionario, id_municipio) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, funcionario.getNomeFuncionario());
            ps.setString(2, funcionario.getBiFuncionario());
            ps.setString(3, funcionario.getTelefoneFuncionario());
            ps.setInt(4, funcionario.getMunicipio().getIdMunicipio());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS do funcionario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Funcionario funcionario) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE funcionario SET nome_funcionario = ?, bi_funcionario  = ?, telefone_funcionario = ?, id_municipio = ? WHERE id_funcionario = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, funcionario.getNomeFuncionario());
            ps.setString(2, funcionario.getBiFuncionario());
            ps.setString(3, funcionario.getTelefoneFuncionario());
            ps.setInt(4, funcionario.getMunicipio().getIdMunicipio());
            ps.setInt(5, funcionario.getIdFuncionario());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS do funcionario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Funcionario funcionario) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, funcionario.getIdFuncionario());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS do funcionario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Funcionario buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Funcionario funcionario = new Funcionario();
        MunicipioDAO municipioDAO = new MunicipioDAO();

        try {
            String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
                funcionario.setBiFuncionario(rs.getString("bi_funcionario"));
                funcionario.setTelefoneFuncionario(rs.getString("telefone_funcionario"));
                funcionario.setMunicipio(municipioDAO.buscarPorId(rs.getInt("id_municipio")));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS do funcionario por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return funcionario;
    }

    @Override
    public ArrayList<Funcionario> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaClientes = new ArrayList();
        MunicipioDAO municipioDAO = new MunicipioDAO();
        
        try {
            String sql = "SELECT * FROM funcionario ORDER BY nome_funcionario";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
                funcionario.setBiFuncionario(rs.getString("bi_funcionario"));
                funcionario.setTelefoneFuncionario(rs.getString("telefone_funcionario"));
                funcionario.setMunicipio(municipioDAO.buscarPorId(rs.getInt("id_municipio")));
                listaClientes.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS do funcionario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaClientes;
    }

}
