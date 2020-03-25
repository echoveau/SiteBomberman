<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consulter le compte</title>
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
</head>
<body>	
	<div style="background-image:url('inc/styles/Wallpaper.jpeg');background-size: 1920px 1080px;"> 
	  	<br>
	  	<c:import url="menu.jsp"></c:import>
	  	<br>
	  	<br>
	</div>
	<section class="page-section" id="contact">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-8 text-center">
	          <h2 class="mt-0">Consulter le compte</h2>
	          <hr class="divider my-4">
	          <p class="text-muted mb-5">Voici les informations connues sur votre compte. Si vous voulez modifier ou supprimer votre compte, rendez vous dans l'onglet portant votre nom d'utilisateur</p>
	        </div>
	      </div>
		    <div class="col-lg-8" style="margin : auto;">
		      	<p> Adresse mail :  <a href="">${sessionScope.utilisateur.email}</a></p>
		      	<p> Nom d'utilisateur : ${sessionScope.utilisateur.userName}</p>
	      	</div>
	    </div>
	  </section>
</body>
</html>