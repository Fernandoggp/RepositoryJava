package br.com.fintech.factory;

import br.com.fintch.dao.CadastroDespesaDAO;
import br.com.fintch.dao.CadastroReceitaDAO;
import br.com.fintch.dao.UsuarioDAO;
import br.com.fintech.impl.OracleCadastroDespesaDAO;
import br.com.fintech.impl.OracleCadastroReceitaDAO;
import br.com.fintech.impl.OracleUsuarioDAO;

public class DAOFactory {
	
	public static CadastroDespesaDAO getCadastroDespesaDAO() {
		return new OracleCadastroDespesaDAO();
	}
	
	public static CadastroReceitaDAO getCadastroReceitaDAO() {
		return new OracleCadastroReceitaDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

}
