package br.com.crud_sqlite.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andrei.vupt on 12/11/2017.
 */

public class UsuarioGitHub implements Serializable {

    private int id;
    private String login;
    private String name;
    //private List<UsuarioGitHub> list;

    public UsuarioGitHub() {
    }

    /*
    public List<UsuarioGitHub> getList() {
        return list;
    }

    public void setList(List<UsuarioGitHub> list) {
        this.list = list;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
