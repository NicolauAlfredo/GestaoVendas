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
import modelo.Cliente;
import util.Conexao;

/**
 *
 * @author user
 */
public class ClienteDAO implements GenericoDAO<Cliente> {

    @Override
    public void guardar(Cliente cliente) {
        Conexao conexao = new Conexao();
        try {
            String sql = "INSERT INTO cliente (nome_cliente, nif_cliente, endereco_cliente, telefone_cliente) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getNifCliente());
            ps.setString(3, cliente.getEnderecoCliente());
            ps.setString(4, cliente.getTelefoneCliente());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao GUARDAR DADOS do cliente. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void actualizar(Cliente cliente) {
        Conexao conexao = new Conexao();
        try {
            String sql = "UPDATE cliente SET nome_cliente = ?, nif_cliente = ?, endereco_cliente = ?, telefone_cliente = ? WHERE id_cliente = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getNifCliente());
            ps.setString(3, cliente.getEnderecoCliente());
            ps.setString(4, cliente.getTelefoneCliente());
            ps.setInt(5, cliente.getIdCliente());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("\"Erro ao ACTUALIZAR DADOS do cliente. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public void apagar(Cliente cliente) {
        Conexao conexao = new Conexao();
        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao APAGAR DADOS do cliente. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        Conexao conexao = new Conexao();
        Cliente cliente = new Cliente();

        try {
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setNifCliente(rs.getString("nif_cliente"));
                cliente.setEnderecoCliente(rs.getString("endereco_cliente"));
                cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao LER DADOS do cliente por ID. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return cliente;
    }

    @Override
    public ArrayList<Cliente> BuscarTodos() {
        Conexao conexao = new Conexao();
        ArrayList listaClientes = new ArrayList();
        try {
            String sql = "SELECT * FROM cliente ORDER BY nome_cliente";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setNifCliente(rs.getString("nif_cliente"));
                cliente.setEnderecoCliente(rs.getString("endereco_cliente"));
                cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao LER TODOS OS DADOS do cliente. \n" + e.getLocalizedMessage());
        } finally {
            conexao.desconectar();
        }
        return listaClientes;
    }

}
