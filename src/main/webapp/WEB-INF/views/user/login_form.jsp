<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "../layout/header.jsp" %>
<div class="container">
	<!-- loginProc를 만들지 않음 왜냐하면 스프링 시큐리티가 가로채서 진행을 함! -->
	<form action="/auth/loginProc" method="post">
	  <div class="form-group">
	    <label for="username">User Name:</label>
	    <input type="text" class="form-control" placeholder="Enter username" id="username" name ="username" />
	  </div>
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" placeholder="Enter password" id="password"name ="password" />
	  </div>
	  <div class="form-group form-check">
	    <label class="form-check-label"> 
	    <input class="form-check-input" type="checkbox" /> Remember me </label>
	  </div>
	  <button id ="btn-login"type="submit" class="btn btn-primary">Log in</button>
	</form>
	
</div>
<br/>
<!--  
<script src="/js/user.js"></script>
 -->

<%@ include file= "../layout/footer.jsp"%>
