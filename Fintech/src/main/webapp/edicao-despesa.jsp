<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de Despesas</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Edição de Despesas</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="despesa" method="post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" value="${despesa.codigo}" name="codigo">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nomeCliente" id="id-nome" class="form-control" value="${despesa.nomeCliente}">
		</div>
		<div class="form-group">
			<label for="id-contas">Contas</label>
			<input type="text" name="contas" id="id-contas" class="form-control" value="${despesa.contas}">
		</div>
		<div class="form-group">
			<label for="id-produtos">Produtos</label>
			<input type="text" name="produtos" id="id-produtos" class="form-control" value="${despesa.produtos}">
		</div>
		<div class="form-group">
			<label for="id-lazer">Lazer</label>
			<input type="text" name="lazer" id="id-lazer" class="form-control" value="${despesa.lazer}">
		</div>
		<div class="form-group">
			<label for="id-dataDespesa">Data de Cadastramento</label>
			<input type="text" name="dataDespesa" id="id-dataDespesa" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-total">Valor total</label>
			<input type="text" name="total" id="id-extra" class="form-control" value="${despesa.total}">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>