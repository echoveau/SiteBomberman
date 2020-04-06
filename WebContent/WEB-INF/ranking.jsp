<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
<title>Classement</title>
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
		<h2 class="mt-0">Classement</h2>
	    <hr class="divider my-4">
    </div>
    <br>
    
    <input id="bouton" type="button" onclick="change()" class="btn-xl" value="Trier par matchs" >
    <br>
    <br>
        
	<c:choose>
		<c:when test="${empty historiques}">
			<p><b>Vous n'avez pas encore fait de partie</b></p>
		</c:when>
		
		<c:otherwise>
		
			<div id="histo" class="collapse"">
				<table>
					<tr>
						<th>Date </th>
						<th>Joueur </th>
						<th>Mode de jeu</th>
						<th>Nombre de joueur</th>
						<th>Score</th>
						<th>Map</th>
					</tr>
					<c:forEach items="${historiques}" var="historique">
						<c:choose>
							<c:when test="${historique.emailJoueur.equals(sessionScope.utilisateur.email)}">
								<tr style="background-color: #dddddd">
									<td><p>${historique.datePartieFormate} </p></td>
									<td><p>${historique.usernameJoueur}  (${historique.emailJoueur})  </p></td>
									<td><p>${historique.modeJeu} </p></td>
									<td><p>${historique.nbJoueur} </p></td>
									<td><p>${historique.score} </p></td>
									<td><p>${historique.mapName} </p></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><p>${historique.datePartieFormate} </p></td>
									<td><p>${historique.usernameJoueur}  (${historique.emailJoueur})  </p></td>
									<td><p>${historique.modeJeu} </p></td>
									<td><p>${historique.nbJoueur} </p></td>
									<td><p>${historique.score} </p></td>
									<td><p>${historique.mapName} </p></td>
								</tr>
							</c:otherwise>
						
						</c:choose>
					</c:forEach>
				</table>
			</div>
			
			
			
			<div id="util">
				<table>
					<tr>
						<th>Joueur </th>
						<th>Nombre de parties gagnées</th>
						<th>Nombre de parties perdues</th>
						<th>Ratio</th>
					</tr>
					<c:forEach items="${utilisateurs}" var="utilisateur">
						<c:choose>
							<c:when test="${utilisateur.email.equals(sessionScope.utilisateur.email)}">
								<tr style="background-color: #dddddd">
									<td><p>${utilisateur.userName}  (${utilisateur.email})  </p></td>
									<td><p>${utilisateur.nbWonGames} </p></td>
									<td><p>${utilisateur.nbLostGames} </p></td>
									<td><p>${utilisateur.ratio}</p></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><p>${utilisateur.userName}  (${utilisateur.email})  </p></td>
									<td><p>${utilisateur.nbWonGames} </p></td>
									<td><p>${utilisateur.nbLostGames} </p></td>
									<td><p>${utilisateur.ratio}</p></td>
								</tr>
							</c:otherwise>
						
						</c:choose>

					</c:forEach>
				</table>
			</div>
			
		</c:otherwise>
	</c:choose>	
	
	<script>
	function change(){
		if(document.getElementById("histo").className == ""){
			document.getElementById("histo").className = "collapse";
			document.getElementById("util").className = "";	
			document.getElementById("bouton").value = "Trier par matchs";	
		}
		else{
			document.getElementById("histo").className = "";
			document.getElementById("util").className = "collapse";	
			document.getElementById("bouton").value = "Trier par joueurs";	
		}
	}
	</script>
</body>
</html>