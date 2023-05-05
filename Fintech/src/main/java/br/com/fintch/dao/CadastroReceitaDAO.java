package br.com.fintch.dao;

import java.util.List;

import br.com.fintech.bean.Receita;
import br.com.fintech.exception.DBException;



public interface CadastroReceitaDAO {
	void cadastrar(Receita receita) throws DBException;
	void atualizar(Receita receita) throws DBException;
	void remover(int codigo) throws DBException;
	Receita buscar(int id);
	List<Receita> listar();
}
