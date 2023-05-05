package br.com.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintch.dao.CadastroReceitaDAO;
import br.com.fintech.bean.Receita;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CadastroReceitaDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getCadastroReceitaDAO();
	}
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request,response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "Receita removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}


	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nomeCliente = request.getParameter("nomeCliente");
			double salario = Double.parseDouble(request.getParameter("salario"));
			double renda = Double.parseDouble(request.getParameter("renda"));
			double extra = Double.parseDouble(request.getParameter("extra"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataReceita = Calendar.getInstance();
			dataReceita.setTime(format.parse(request.getParameter("dataReceita")));
			double total = Double.parseDouble(request.getParameter("total"));
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			Receita receita = new Receita(nomeCliente,salario,renda,extra,dataReceita,total,codigo);
			dao.atualizar(receita);

			request.setAttribute("msg", "Receita atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request,response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nomeCliente = request.getParameter("nomeCliente");
			double salario = Double.parseDouble(request.getParameter("salario"));
			double renda = Double.parseDouble(request.getParameter("renda"));
			double extra = Double.parseDouble(request.getParameter("extra"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataReceita = Calendar.getInstance();
			dataReceita.setTime(format.parse(request.getParameter("dataReceita")));
			double total = Double.parseDouble(request.getParameter("total"));

			Receita receita = new Receita(nomeCliente,salario,renda,extra,dataReceita,total,0);
			dao.cadastrar(receita);

			request.setAttribute("msg", "Receita cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		abrirFormCadastroReceita(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao-receita":
			abrirFormEdicaoReceita(request, response);
			break;
		case "abrir-form-cadastro-receita":
			abrirFormCadastroReceita(request, response);
			break;
		}
		
	}

	private void abrirFormCadastroReceita(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getRequestDispatcher("cadastro-receita.jsp").forward(request, response);
	}

	private void abrirFormEdicaoReceita(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Receita receita = dao.buscar(id);
		request.setAttribute("receita", receita);
		request.getRequestDispatcher("edicao-receita.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Receita> lista = dao.listar();
		request.setAttribute("receita", lista);
		request.getRequestDispatcher("consulta-receita.jsp").forward(request, response);
	}

}