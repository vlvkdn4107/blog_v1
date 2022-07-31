<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "../layout/header.jsp" %>

<div class="container">
	<form action="/auth/joinProc" method="post"><!--  form 으로 던진이유??  -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> <!--name은 키값 value는 값   -->
	  <div class="form-group">
	    <label for="username">User Name:</label>
	    <input type="text" class="form-control" placeholder="Enter username" id="username" name = "username" />
	  </div>
	  
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" placeholder="Enter password" id="password" name = "password" />
	  </div>
	  
	  <div class="form-group">
	    <label for="email">Email address:</label>
	    <input type="email" class="form-control" placeholder="Enter email" id="email" name = "email"/>
	  </div>
	  
	  <button id = "btn-save" type="submit" class="btn btn-primary">Join</button> <!-- form으로 던지기 위해서는  type 을 submit으로 해야한다 -->
	  
	</form>
</div>
<br/>

<!--
<script src="/js/user.js"></script>
-->
<%@ include file= "../layout/footer.jsp"%>