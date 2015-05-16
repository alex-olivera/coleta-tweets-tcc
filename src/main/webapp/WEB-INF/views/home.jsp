<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta charset="utf-8">
<title>Home</title>

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
		<li><a href="nova-busca">Iniciar Nova Busca</a></li>
	</ol>
	
</body>
</html>
