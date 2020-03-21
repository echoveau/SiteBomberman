<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>

		<!-- BANDEAU -->
		<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
		    <div class="container">
		      <a class="navbar-brand js-scroll-trigger" href="<c:out value="accueil" />">Bomberman</a>
		      <button class="navbar-toggler navbar-toggler-right collapsed" type="button" onclick="expand()">
		        <span class="navbar-toggler-icon"></span>
		      </button>
		      <div class="collapse navbar-collapsed" id="navbarResponsive">
		        <ul class="navbar-nav ml-auto my-2 my-lg-0">
		     
				        <c:choose>
							<c:when test="${empty sessionScope.utilisateur}">
								<li class="nav-item">
									<a class="nav-link" href="<c:out value="subscribe" />">S'inscrire</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item">
									<ul id="menu-deroulant">
										<li>
											<a class="nav-link" href="#">${sessionScope.utilisateur.userName}</a>
											<ul style="background:#696969;">
												<li class="nav-item" id="marge">
													<a class="nav-link" href="<c:out value="viewAccount" />">Consulter le compte</a>
												</li>
				         						<li class="nav-item" id="marge">
													<a class="nav-link" href="<c:out value="" />">Modifier le compte</a>
												</li>
												<li class="nav-item" id="marge">
													<a class="nav-link" href="<c:out value="deleteAccount" />">Supprimer le compte</a>
												</li>
											</ul>
										</li>
									</ul>
								</li>			
								
								
								<li class="nav-item">
				            		<a class="nav-link" href="<c:out value="records" />">Historique</a>
				         		</li>			
							</c:otherwise>
						</c:choose>
						

		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="about" />">Classement</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="contact" />">Contact</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="about" />">A propos</a>
		          </li>
		          
		          		<c:choose>
							<c:when test="${empty sessionScope.utilisateur}">
								<li class="nav-item">
									<a class="nav-link" href="<c:out value="connexion" />">Connexion</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item">
									<a class="nav-link" href="<c:out value="LogOut" />">Déconnexion</a>
								</li>
							</c:otherwise>
						</c:choose>

		        </ul>
		      </div>
		    </div>
		  </nav>
		  
		  
		  
		  
		<!-- ALGO -->
		<!-- Vérifié le redimansionnement de la fenêtre + fonction expand() --> 
		<script>
			// Fonction exécutée au redimensionnement
			function redimensionnement() {
			  var result = document.getElementById('result');
			  if("matchMedia" in window) { // Détection
			    if(window.matchMedia("(min-width:992px)").matches) {
			    	document.getElementById("navbarResponsive").className = "collapse navbar-collapse show";
			    } else {
			    	document.getElementById("navbarResponsive").className = "collapse navbar-collapsed";
			    }
			  }
			}
			redimensionnement();
			// On lie l'événement resize à la fonction
			window.addEventListener('resize', redimensionnement, false);

			// Fonction exécutée lors de l'appui sur le bouton
			function expand() {
				if(document.getElementById("navbarResponsive").className == "collapse navbar-collapse show")
					document.getElementById("navbarResponsive").className = "collapse navbar-collapsed";
				else
					document.getElementById("navbarResponsive").className = "collapse navbar-collapse show";
			}
		</script>
</body>
</html>