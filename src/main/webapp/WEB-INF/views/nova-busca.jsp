<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta charset="utf-8">
<title>Nova Busca</title>

</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Coleta Tweets</a>
			</div>
		</div>
	</nav>

	<ol class="breadcrumb">
		<li><a href="index.jsp">Home</a></li>
		<li class="active">Iniciar Nova Busca</li>
	</ol>

	<form:form id="novaBuscaForm" role="form" commandName="coleta"
		servletRelativeAction="buscar" method="POST"
		cssClass="form-horizontal">
		<div class="form-group" style="width: 100%">
			<label for="descricao" class="col-sm-2 control-label">
				Descrição da busca:</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="descricao"
					id="descricao" placeholder="Descrição da busca" />
			</div>
		</div>
		<div class="form-group" style="width: 100%">
			<label for="busca" class="col-sm-2 control-label"> Buscar
				por:</label>
			<div class="col-sm-3">
				<input type="text" name="busca" class="form-control" id="busca"
					placeholder="Buscar por" />
			</div>
		</div>

		<div class="form-group" style="width: 100%">
			<label for="tempo_periodo" class="col-sm-2 control-label">
				Periodo de tempo para buscas: </label>
			<div class="col-sm-2">
				<select name="tempo_periodo" id="tempo_periodo" class="form-control">
					<option value="minuto">Minuto</option>
					<option value="dia">Dia</option>
					<option value="hora">Hora</option>
				</select>
			</div>
			<div class="col-sm-1">
				<input type="text" class="form-control" name="tempo_hora"
					id="tempo_hora" placeholder="Tempo" />
			</div>
		</div>

		<div class="form-group" style="width: 100%">
			<label for="quantidade" class="col-sm-2 control-label"> Qtd.
				de tweets por busca: </label>
			<div class="col-sm-2">
				<input type="text" name="quantidade" id="quantidade"
					class="form-control" placeholder="Qtd. tweets" />
			</div>
		</div>

		<div class="form-group" style="width: 100%">
			<label for="num_buscas" class="col-sm-2 control-label">
				Requisições da busca: </label>
			<div class="col-sm-2">
				<input type="text" class="form-control" name="num_buscas"
					id="num_buscas" placeholder="Requisições" />
			</div>
		</div>

		<div class="form-group" style="width: 100%">
			<div class="col-sm-offset-2 col-sm-2">
				<input type="submit" value="Procurar" class="btn btn-primary" />
			</div>
		</div>
		
	</form:form>



</body>
</html>
