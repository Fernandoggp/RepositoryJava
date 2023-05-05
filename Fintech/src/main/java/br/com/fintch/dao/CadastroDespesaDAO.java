package br.com.fintch.dao;

import java.util.List;

import br.com.fintech.bean.Despesa;
import br.com.fintech.exception.DBException;

public interface CadastroDespesaDAO {
	void cadastrar(Despesa despesa) throws DBException;
	void atualizar(Despesa despesa) throws DBException;
	void remover(int codigo) throws DBException;
	Despesa buscar(int id);
	List<Despesa> listar();

}
