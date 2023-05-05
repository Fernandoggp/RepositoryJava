package br.com.fintech.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fintch.dao.CadastroDespesaDAO;
import br.com.fintech.bean.Despesa;
import br.com.fintech.bean.Receita;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;

public class DespesaDAOTeste {
public static void main(String[] args) {
		
		CadastroDespesaDAO dao = DAOFactory.getCadastroDespesaDAO();
		
		
		
		Despesa despesa= new Despesa("Scott",10,1,7,Calendar.getInstance(),100,0);
		try {
			dao.cadastrar(despesa);
			System.out.println("Despesa cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		dao.buscar(1);
		

				
 }
}
