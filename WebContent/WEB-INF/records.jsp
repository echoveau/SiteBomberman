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
	
	<h1>${sessionScope.utilisateur.userName}</h1>
	
	
	<c:choose>
		<c:when test="${empty historiques}">
			<p><b>Vous n'avez pas encore fait de partie</b></p>
		</c:when>
		
		
		<c:otherwise>
			<c:forEach items="${historiques}" var="historique">
 				<p>${historique.usernameJoueur1}     SALUT    ${historique.usernameJoueur2}  </p>
			</c:forEach>
		</c:otherwise>
	</c:choose>	
</body>
</html>