let index ={
	init: function(){
		$("#btn-save").bind("click",() =>{
			this.save();
		});
		$("#btn-delete").bind("click",() =>{
			this.deleteById();
		});
		$("#btn-update").bind("click",() =>{
			this.update();
		});
		$("#btn-reply-save").bind("click",() =>{
			this.replySave();
		});
		
		
		$("#btn-reply-update").bind("click",() =>{
			this.replyUpdate();
		});
		
	
		
	},
	save: function(){
		// 데이터 가져오기.
		let data = {
			title: xSSCheck($("#title").val(), 1),
			content: $("#content").val()
		}
		console.log(data);
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
			
		})
		.done(function (data, textStatus, xhr){
			if(data.status){
				alert("글쓰기 완료!!");	
				location.href = ("/"); 
			}
			
		})
		.fail(function(error){
			alert("글쓰기 실패!");
		});
	},
	deleteById: function(){
		let id = $("#board-id").text(); // .text 컨텐츠안에있는 text 놈을 들고온다.
			$.ajax({
				type: "DELETE",
				url: "/api/board/" + id
			})
			.done(function(data){
				if(data.status){
				alert("글 삭제 성공!");
				location.href = "/";	
				}
				
			})
			.fail(function(){
				alert("글 삭제 실패!");
			});
		
	},
	update: function(){
		let boardId = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			async: false // 비동기식으로 처리 할꺼야! 라는 타입
		})
		.done(function(data){
			if(data.status){
				alert("글수정 완료!");
				location.href = "/";
			}
		})
		.fail(function(error){
			alert("글수정 실패!!");
		});
	},
	
	
	// 댓글 등록
	replySave: function(){
		// 데이터 가져오기 (boardId: 해당 게시글의 아이디 입니다.)
			let data = {
			boardId: $("#board-id").text(),
			content: $("#reply-content").val()
		}
		console.log(data);
		// ``(백틱)  (자바 스크립트 변수를 문자열 안에 넣어서 사용할수 있다.)
		$.ajax({ // 새로고침없이 사용할려고 ajax를 쓴다.
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
			
		})
		.done(function (response){
			if(response.status){
				addReplyElement(response.data); // 리스폰스에있는 data
			}else{
				
			}
			
			
			/*
			if(response.status){
				alert("댓글 쓰기 완료!!");	
				location.href = (`/board/${data.boardId}`); 
			}
			*/	
		})
		.fail(function(error){
			alert("댓글 쓰기 실패!");
		});
	},// end of replySave
	
	
	replyDelete: function(boardId, replyId){
		/*
		console.log("boardId :" + boardId);
		console.log("replyId :" + replyId);
		*/
			$.ajax({
				type: "DELETE",
				url: `/api/board/${boardId}/reply/${replyId}`,
				dataType: "json"
			})
			.done(function(response){
				console.log(response + " : response");
				alert("댓글 삭제 성공!");
				location.href=`/board/${boardId}`; // 비동기식으로 도전 해보기!
			})
			.fail(function(error){
				console.log(error);
				alert("댓글 삭제 실패!");
			})
			
			
	}
	
}
// == (문자열,int 상관없이 같으면 ok) === (타입까지 다 맞아야 ok)
function addReplyElement(reply){
	
	let principalId = $("#principal--id").val();
	
	let childElement = ` <li class="list-group-item d-flex justify-content-between" id="reply--${reply.id}">
	      <div>${reply.content}</div>
	      <div class="d-flex">
	        <div class = "m-2">작성자 : ${reply.user.username}&nbsp;&nbsp;</div> 
	        <c:if test="${reply.user.id == principalId}">
	        	<button class="badge badge-danger m-2" onclick = "index.replyDelete(${reply.board.id}, ${reply.id});">삭제</button>
	        </c:if>
	        
	      </div>
	    </li>`;
		
	  	
	  $("#reply--box").prepend(childElement);	  
	  $("#reply-content").val("");
	  console.log(reply.user.id == principalId);
	  
}
function xSSCheck(str, level) {
    if (level == undefined || level == 0) {
        str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g,"");
    } else if (level != undefined && level == 1) {
        str = str.replace(/\</g, "&lt;");
        str = str.replace(/\>/g, "&gt;");
    }
    return str;
}



index.init();