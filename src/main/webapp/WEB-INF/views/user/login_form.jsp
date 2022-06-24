<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "../layout/header.jsp" %>
<div class="container">
	<form>
	  <div class="form-group">
	    <label for="username">User Name:</label>
	    <input type="text" class="form-control" placeholder="Enter username" id="username" />
	  </div>
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" placeholder="Enter password" id="password" />
	  </div>
	  <div class="form-group form-check">
	    <label class="form-check-label"> 
	    <input class="form-check-input" type="checkbox" /> Remember me </label>
	  </div>
	</form>
	<button id ="btn-login"type="button" class="btn btn-primary">Log in</button>
</div>
<br/>
<script src="/js/user.js"></script>
<%@ include file= "../layout/footer.jsp"%>
