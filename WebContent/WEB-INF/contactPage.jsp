<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
<title>Insert title here</title>
</head>
<body>
	<div style="background-image:url('inc/styles/Wallpaper.jpeg');"> 
	  	<c:import url="menu.jsp"></c:import>
	</div>
	
	<section class="page-section" id="contact">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-8 text-center">
	          <h2 class="mt-0">Contact</h2>
	          <hr class="divider my-4">
	          <p class="text-muted mb-5">Besoin d'informations ? Des soucis avec l'application ? Envoyez nous un email, et nous reviendrons vers vous aussi vite que possible !</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-lg-4 ml-auto text-center mb-5 mb-lg-0">
	          <img width="70" height="70" src="inc/styles/pictogrammeMail.png"/>
	          <a class="d-block" href="mailto:contact@yourwebsite.com">kbalavoine@etud.univ-angers.fr</a>
	        </div>
	        <div class="col-lg-4 mr-auto text-center">
	          <img width="70" height="70" src="inc/styles/pictogrammeMail.png"/>
	          <a class="d-block" href="mailto:contact@yourwebsite.com">echoveau@etud.univ-angers.fr</a>
	        </div>
	      </div>
	    </div>
	  </section>

</body>
</html>