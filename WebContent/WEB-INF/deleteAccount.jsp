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
	
	<section class="page-section">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-8 text-center">
	          <h2 class="mt-0"><font color="#f4623a">Supprimer le compte</font></h2>
	          <hr class="divider my-4">
	          <p class="text-muted mb-5">Etes-vous bien sûr de vouloir supprimer le compte ?</p>
	          <a class="btn btn-primary btn-xl js-scroll-trigger" href="deleteAccount">Oui</a>
	          <a class="btn btn-secondary btn-xl js-scroll-trigger" href="accueil">Non</a>
	        </div>
	      </div>
	    </div>
	  </section>
</body>
</html>