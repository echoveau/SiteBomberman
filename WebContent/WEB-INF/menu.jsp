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
		      <a class="navbar-brand js-scroll-trigger" href="<c:out value="acceuil" />">Bomberman</a>
		      <button class="navbar-toggler navbar-toggler-right collapsed" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="true" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		      </button>
		      <div class="collapse navbar-collapse show" id="navbarResponsive">
		        <ul class="navbar-nav ml-auto my-2 my-lg-0">
		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="subscribe" />">S'inscrire</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="about" />">Classement</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="contact" />">Contact</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="<c:out value="about" />">A propos</a>
		          </li>
		        </ul>
		      </div>
		    </div>
		  </nav>
</body>
</html>