<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
<title>Historique</title>
</head>
<body>
	<div style="background-image:url('inc/styles/Wallpaper.jpeg');background-size: 1920px 1080px;"> 
		<br>
	  	<c:import url="/WEB-INF/menu.jsp"></c:import>
	  	<br>
	  	<br>
	</div>
	
	<br>
	<div class="text-center">
		<h2 class="mt-0">Historique</h2>
	    <hr class="divider my-4">
    </div>
    <br>
	
	<c:choose>
		<c:when test="${empty historiques}">
			<p><b>Vous n'avez pas encore fait de partie</b></p>
		</c:when>
		
		<c:otherwise>
		
			<table>
				<tr>
					<th>Joueur </th>
					<th>Mode de jeu</th>
					<th>Nombre de joueur</th>
					<th onclick="sortTable(0)">Score</th>
					<th onclick="sortTable(1)">Map</th>
				</tr>
				<c:forEach items="${historiques}" var="historique">
					<tr>
						<td><p>${historique.usernameJoueur}  (${historique.emailJoueur})  </p></td>
						<td><p>${historique.modeJeu} </p></td>
						<td><p>${historique.nbJoueur} </p></td>
						<td><p>${historique.score} </p></td>
						<td><p>${historique.mapName} </p></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>	
</body>
</html>