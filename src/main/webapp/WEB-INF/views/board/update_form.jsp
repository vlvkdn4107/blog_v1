<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet"> 
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

    <div class="container">

        <form action="">

            <div class="form-group">
            	<input type= "hidden" id="id" value ="${board.id}">
                <label for="title">Title</label>
                <input type="text" value ="${board.title}" class="form-control" placeholder="Enter title" name="title" id="title">
            </div>

            <div class="form-group">
                <label for="content">content</label>
                <textarea class="form-control summernote" rows="5" id="content" name ="content">${board.content}"</textarea>
            </div>
        </form>
        <button type="button" id="btn-update" class="btn btn-primary">수정하기</button>
    </div>
     <br/>
     <br/>
    
     <script type="text/javascript">
        $(".summernote").summernote({
            placeholder: '내용을 입력해주세요',
            tabsize: 5, 
            height: 300
        });
    </script>
   
    <script src="/js/board.js"></script>
 <%@ include file="../layout/footer.jsp" %>

