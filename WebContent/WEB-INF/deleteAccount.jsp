<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
<title>Supprimer le compte</title>
</head>
<body>
	<div style="background-image:url('inc/styles/Wallpaper.jpeg');background-size: 1920px 1080px;"> 
	  	<br>
	  	<c:import url="menu.jsp"></c:import>
	  	<br>
	  	<br>
	</div>
	
	<form method="post" action="deleteAccount">
		<section class="page-section">
		    <div class="container">
		      <div class="row justify-content-center">
		        <div class="col-lg-8 text-center">
		          <h2 class="mt-0"><font color="#f4623a">Supprimer le compte</font></h2>
		          <hr class="divider my-4">
		          <p class="text-muted mb-5">Etes-vous bien sûr de vouloir supprimer le compte ?</p>
		          		                
		          <label for="nom">Mot de passe <span class="requis">*</span></label>
		          <input type="password" id="ancienMotdepasse" name="ancienMotdepasse" value="" size="20" maxlength="20" />
		          <p class="erreur">${form.erreurs['ancienMotdepasse']}</p>
		                
		          <input class="btn btn-primary btn-xl js-scroll-trigger" type="submit" value="Oui"/>
		          <a class="btn btn-secondary btn-xl js-scroll-trigger" href="accueil">Non</a>
		        </div>
		      </div>
		    </div>
		  </section>
	  </form>
</body>
</html>