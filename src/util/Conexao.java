/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Conexao {

    public Conexao() {
    }

    Connection con = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/sistema_venda?user=root&password=nicolau14-06-1999");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Não foi possivel encontrar o Driver!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Não foi possivel conectar ao banco");
        }
        return con;
    }

    public void desconectar() {
        try {
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Não foi possivel desconectar ao banco");
        }
    }

    public static void main(String[] args) {
        new Conexao();
    }

}
