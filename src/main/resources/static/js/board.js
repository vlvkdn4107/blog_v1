let index ={
	init: function(){
		$("#btn-save").bind("click",() =>{
			this.save();
		});
		$("#btn-delete").bind("click",() =>{
			this.deleteById();
		});
	},
	save: function(){
		// 데이터 가져오기.
		let data = {
			title: $("#title").val(),
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
		.fali(function(error){
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
		
	}
}

index.init();