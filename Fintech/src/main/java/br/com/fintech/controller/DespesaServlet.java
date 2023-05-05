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

import br.com.fintch.dao.CadastroDespesaDAO;
import br.com.fintch.dao.CadastroReceitaDAO;
import br.com.fintech.bean.Despesa;
import br.com.fintech.bean.Receita;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CadastroDespesaDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getCadastroDespesaDAO();
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
			request.setAttribute("msg", "Despesa removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}


	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nomeCliente");
			double contas = Double.parseDouble(request.getParameter("contas"));
			double produtos = Double.parseDouble(request.getParameter("produtos"));
			double lazer = Double.parseDouble(request.getParameter("lazer"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataDespesa= Calendar.getInstance();
			dataDespesa.setTime(format.parse(request.getParameter("dataDespesa")));
			double total = Double.parseDouble(request.getParameter("total"));
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			Despesa despesa = new Despesa(nome,contas,produtos,lazer,dataDespesa,total,codigo);
			dao.atualizar(despesa);

			request.setAttribute("msg", "Despesa atualizada!");
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
			double contas = Double.parseDouble(request.getParameter("contas"));
			double produtos = Double.parseDouble(request.getParameter("produtos"));
			double lazer = Double.parseDouble(request.getParameter("lazer"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataDespesa= Calendar.getInstance();
			dataDespesa.setTime(format.parse(request.getParameter("dataDespesa")));
			double total = Double.parseDouble(request.getParameter("total"));

			Despesa despesa = new Despesa(nomeCliente, contas,produtos,lazer,dataDespesa,total,0);  
			dao.cadastrar(despesa);

			request.setAttribute("msg", "Despesa cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		abrirFormCadastroDespesa(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao-despesa":
			abrirFormEdicaoDespesa(request, response);
			break;
		case "abrir-form-cadastro-despesa":
			abrirFormCadastroDespesa(request, response);
			break;
		}
		
	}

	private void abrirFormCadastroDespesa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("cadastro-despesa.jsp").forward(request, response);
	}

	private void abrirFormEdicaoDespesa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Despesa despesa = dao.buscar(id);
		request.setAttribute("despesa", despesa);
		request.getRequestDispatcher("edicao-despesa.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Despesa> lista = dao.listar();
		request.setAttribute("despesa", lista);
		request.getRequestDispatcher("consulta-despesa.jsp").forward(request, response);
	}

}