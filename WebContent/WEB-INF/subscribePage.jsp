<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
<title>Inscription</title>
</head>
<body>
	<div style="background-image:url('inc/styles/Wallpaper.jpeg');background-size: 1920px 1080px;"> 
	  	<br>
	  	<c:import url="menu.jsp"></c:import>
	  	<br>
	  	<br>
	</div>
	<section class="page-section" id="inscription">
		<div class="container">
		      <div class="text-center">
		
				<form method="post" action="subscribe">
		            <fieldset>
		                <h2 class="mt-0">Inscription</h2>
		                <hr class="divider my-4">
		                <p class="text-muted mb-5">Vous pouvez vous inscrire via ce formulaire.</p>
						
						<label for="email">Adresse email <span class="requis">*</span></label>
			            <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
			            <p class="erreur">${form.erreurs['email']}</p>
		
		                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
		                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
		                <p class="erreur">${form.erreurs['motdepasse']}</p>

		
		                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
		                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
		                <p class="erreur">${form.erreurs['confirmation']}</p>

		                <label for="nom">Nom d'utilisateur</label>
		                <input type="text" id="nom" name="nom" value="" size="20" maxlength="20" />
		                <p class="erreur">${form.erreurs['nom']}</p>
		                
		                
		                <input class="btn btn-primary btn-xl js-scroll-trigger" type="submit" value="Inscription" class="sansLabel" />
		              
		                
		                <p class="${empty form.erreurs ? 'succes' : 'd-block'}">${form.resultat}</p>
		                
		            </fieldset>
		      </form>
			</div>
		</div>
	</section>

</body>
</html>