package br.com.fintech.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fintch.dao.CadastroReceitaDAO;
import br.com.fintech.bean.Receita;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;

public class ReceitaDAOTeste {
public static void main(String[] args) {

	CadastroReceitaDAO dao = DAOFactory.getCadastroReceitaDAO();
	
	
			Receita receita= new Receita("Scott",10,1,0,Calendar.getInstance(),100,0);
			try {
				dao.cadastrar(receita);
				System.out.println("Receita cadastrada.");
			} catch (DBException e) {
				e.printStackTrace();
			}

			try {
				dao.remover(0);
				System.out.println("Produto removido.");
			} catch (DBException e) {
				e.printStackTrace();
			}	
				
 }
}
