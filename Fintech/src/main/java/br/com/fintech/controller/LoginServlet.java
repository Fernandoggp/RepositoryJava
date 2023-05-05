package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintch.dao.UsuarioDAO;
import br.com.fintech.bean.Usuario;
import br.com.fintech.factory.DAOFactory;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
	
	public LoginServlet() {
        dao = DAOFactory.getUsuarioDAO();
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(email, senha);
		
		if (dao.validarUsuario(usuario)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
			String mensagem = "Um login foi realizado";
			request.getRequestDispatcher("home2.jsp").forward(request, response);
		}else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}