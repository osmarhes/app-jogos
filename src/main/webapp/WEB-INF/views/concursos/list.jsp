<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;
charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<table>
			<tr>
				<td>Concurso</td>
				<td>Data do Sorteio</td>
				<td>1ª dez.</td>
				<td>2ª dez.</td>
				<td>3ª dez.</td>
				<td>4ª dez.</td>
				<td>5ª dez</td>
				<td>6ª dez</td>
				<td>Ganhadores</td>
			</tr>
			<c:forEach var="concurso" items="${concursos}" >
				<tr>
					<td>${concurso.concurso}</td>
					<td>${concurso.dataSorteio}</td>
						<c:forEach items="${concurso.dezenas}" var="dezena">
							<td>${dezena}</td>
						</c:forEach>
					<td>${concurso.ganhadoresSena}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>