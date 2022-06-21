// index = 오브젝트 {안에는 변수}
let index = {
	init: function(){
		$("#btn-save").bind("click", () =>{
			alert("btn-save 버튼이 눌러 졌습니다.");
			this.save();
		});
	},
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);
		$.ajax({
		type: "POST",
		url: "/blog/api/user",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType:"json"
	}).done(function(data, textStatus, xhr){
		// 통신 성공시
		console.log(xhr);
		console.log(textStatus);
		console.log(data);
		alert("회원가입 성공");
		location.href = "/blog";
	}).fail(function(xhr){
		consolo.log(xhr);
		alert("회원가입 실패!")
	});
	}
	
}

index.init();