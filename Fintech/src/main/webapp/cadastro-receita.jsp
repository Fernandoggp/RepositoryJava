<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Receita</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Cadastro de Receita</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="receita" method="post">
		<input type="hidden" value="cadastrar" name="acao">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nomeCliente" id="id-nome" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-salario">Salário</label>
			<input type="text" name="salario" id="id-salario" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-renda">Renda passiva</label>
			<input type="text" name="renda" id="id-renda" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-extra">Renda extra</label>
			<input type="text" name="extra" id="id-extra" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-dataReceita">Data de cadastramento</label>
			<input type="text" name="dataReceita" id="dataReceita" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-total">Valor total</label>
			<input type="text" name="total" id="id-total" class="form-control">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>