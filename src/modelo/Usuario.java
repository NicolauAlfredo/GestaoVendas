/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author user
 */
public class Usuario {

    private Integer idUsuario;
    private Funcionario funcionario;
    private String senhaUsuario;
    private String loginUsuario;
    private String perfilUsuario;

    public Usuario() {
        this.idUsuario = 0;
        this.funcionario = new Funcionario();
        this.senhaUsuario = "";
        this.loginUsuario = "";
        this.perfilUsuario = "";
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
        this.funcionario = new Funcionario();
        this.senhaUsuario = "";
        this.loginUsuario = "";
        this.perfilUsuario = "";
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    @Override
    public String toString() {
        return getFuncionario().getNomeFuncionario();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;
            if (usuario.getIdUsuario() == this.getIdUsuario()) {
                return true;
            }
        }
        return false;
    }
}
