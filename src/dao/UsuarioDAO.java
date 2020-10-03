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
import modelo.Usuario;
import util.Conexao;

/**
 *
 * @author user
 */
public class UsuarioDAO implements GenericoDAO<Usuario> {

    @Override
    public void guardar(Usuario usuario) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO usuario (id_funcionario, senha_usuario, login_usuario, perfil_usuario) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, usuario.getFuncionario().getIdFuncionario());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setString(3, usuario.getLoginUsuario());
            ps.setString(4, usuario.getPerfilUsuario());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS do usuário. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE usuario SET id_funcionario = ?, senha_usuario  = ?, login_usuario = ?, perfil_usuario = ? WHERE id_usuario = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, usuario.getFuncionario().getIdFuncionario());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setString(3, usuario.getLoginUsuario());
            ps.setString(4, usuario.getPerfilUsuario());
            ps.setInt(5, usuario.getIdUsuario());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS do usuario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Usuario usuario) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, usuario.getIdUsuario());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS do usuario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Usuario usuario = new Usuario();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        try {
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setFuncionario(funcionarioDAO.buscarPorId(rs.getInt("id_funcionario")));
                usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                usuario.setLoginUsuario(rs.getString("login_usuario"));
                usuario.setPerfilUsuario(rs.getString("perfil_usuario"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS do usuario por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return usuario;
    }

    @Override
    public ArrayList<Usuario> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaClientes = new ArrayList();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        try {
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setFuncionario(funcionarioDAO.buscarPorId(rs.getInt("id_funcionario")));
                usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                usuario.setLoginUsuario(rs.getString("login_usuario"));
                usuario.setPerfilUsuario(rs.getString("perfil_usuario"));
                listaClientes.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS do usuario. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaClientes;
    }

}
