package br.com.fintch.dao;

import br.com.fintech.bean.Usuario;

public interface UsuarioDAO {

	boolean validarUsuario(Usuario usuario);
	
}