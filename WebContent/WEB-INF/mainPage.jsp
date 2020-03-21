<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Bomberman</title>
	<link type="text/css" rel="stylesheet" href="inc/styles/style.css" />
</head>
<body>
	<div style="background-image:url('inc/styles/Wallpaper.jpeg');background-size: 1920px 1080px;"> 
	  
	  	<c:import url="menu.jsp"></c:import>

		<header class="masthead">
		  <div class="container h-100">
		      <div class="row h-100 align-items-center justify-content-center text-center">
		        <div class="col-lg-10 align-self-end">
		          <h1 class="text-uppercase text-white font-weight-bold">Télécharger le jeu bomberman</h1>
		          <hr class="divider my-4">
		        </div>
		        <div class="col-lg-8 align-self-baseline">
		          <p class="text-white-75 font-weight-light mb-5">Nous avons réalisé ce projet dans le cadre de notre cursus scolaire. L'objectif était de développer un jeu Bomberman en Java en utilisant le concept de Design Pattern </p>
		          <a class="btn btn-primary btn-xl js-scroll-trigger" href="about">Télécharger</a>
		        </div>
		      </div>
			</div>
		</header>
	</div>
</body>
</html>