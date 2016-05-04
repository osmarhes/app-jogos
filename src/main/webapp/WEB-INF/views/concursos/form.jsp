<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro Concurso</title>
</head>
<body>
	<spring:hasBindErrors name="bean">
		<ul>
		<c:forEach var="error" items="${errors.allErrors}">	
			<li>${error.code} - ${error.field}</li>
		</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<form action="/app-jogos/concursos" method="post">
		<input type="hidden" name="idTipoJogo" id="idTipoJogo" value="1" />
		<div>
			<label for="concurso">Numero Concurso</label> <input type="text"
				name="concurso" id="concurso" />
		</div>
		<div>
			<label for="dataSorteio">Data do sorteio</label> <input type="text"
				name="dataSorteio" id="dataSorteio" />
		</div>
		<div>
			<label for="arrecadacaoTotal">Valor</label> <input type="text"
				name="arrecadacaoTotal" id="arrecadacaoTotal" />
		</div>
		<div>
			<label for="ganhadoresSena">Número de Ganhadores</label> <input
				type="text" name="ganhadoresSena" id="ganhadoresSena" />
		</div>
		<div>
			<label for="cidade">Cidade</label> <input type="text" name="cidade"
				id="cidade" />
		</div>
		<div>
			<label for="estado">Estado</label> <input type="text" name="estado"
				id="estado" />
		</div>
		<div>
			<label for="dezenas[0]">Dezenas</label><br> <input type="text"
				name="dezenas[0]" id="dezenas[0]" /> <input type="text"
				name="dezenas[1]" id="dezenas[1]" /> <input type="text"
				name="dezenas[2]" id="dezenas[2]" /><br> <input type="text"
				name="dezenas[3]" id="dezenas[3]" /> <input type="text"
				name="dezenas[4]" id="dezenas[4]" /> <input type="text"
				name="dezenas[5]" id="dezenas[5]" />
		</div>
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form>

</body>
</html>