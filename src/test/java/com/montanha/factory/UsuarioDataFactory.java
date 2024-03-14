package com.montanha.factory;

import com.montanha.pojo.Usuario;

public class UsuarioDataFactory {
    public static Usuario criarUsuarioAdministrador() {

        Usuario usuarioAdm = new Usuario();
        usuarioAdm.setEmail("admin@email.com");
        usuarioAdm.setSenha("654321");
        return usuarioAdm;
    }

    public static Usuario criarUsuarioVazio(){
        Usuario usuariovazio = new Usuario();
        usuariovazio.setEmail("");
        usuariovazio.setSenha("");
        return usuariovazio;
    }
}
