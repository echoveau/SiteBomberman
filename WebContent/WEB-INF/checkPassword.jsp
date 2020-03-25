<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
<title>Verification du mot de passe</title>
</head>
<body>
	<section class="page-section" id="changeAccount">
		<div class="container">
		      <div class="text-center">
		
				<form method="post" action="changeAccount">
		            <fieldset>
		                <h2 class="mt-0">Verification du mot de passe</h2>
		                <hr class="divider my-4">
		                <p class="text-muted mb-5">Vous pouvez vous connecter via ce formulaire.</p>

		
		                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
		                <input type="password" id="motdepasse" name="lastCheck" value="" size="20" maxlength="20" />
		                <p class="erreur">${form.erreurs['motdepasse']}</p>
		              
		                
		                <input class="btn btn-primary btn-xl js-scroll-trigger" type="submit" value="Connexion" class="sansLabel" />
		              
		                
		                <p class="${empty form.erreurs ? 'succes' : 'd-block'}">${form.resultat}</p>
		                
		            </fieldset>
		      </form>
			</div>
		</div>
	</section>

</body>
</html>