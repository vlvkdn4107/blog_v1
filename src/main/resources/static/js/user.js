// "use strict"    // 전체 스크립트를 strict(엄격모드) 모드로 설정함.
// index = 오브젝트 {안에는 변수}
// let = 함수 밖에서 쓰면 멤버변수 함수 안에쓰면 지역변수



let index = {
	init: function(){
		$("#btn-save").bind("click", () =>{
			alert("btn-save 버튼이 눌러 졌습니다.");
			this.save();
		});
		// 전통적인 로그인 방식일때 사용한 부분
		//$("#btn-login").bind("click", () =>{
			//alert("btn-save 버튼이 눌러 졌습니다.");
			//this.login();
		//});
	
		$("#btn-update").bind("click",() =>{
			this.update();
		});
	},
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		
		// AJAX 호출
		$.ajax({
			// 서버측에 회원가입 요청
			type: "POST",
			url: "/auth/joinProc",
			// json 형식으로 만들어주는 함수
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답이 왔을 때 기본 데이터 타입이(Buffered 문자열)을 받아서 -> java script object 자동 변환
		}).done(function(data, textStatus, xhr){
			// 통신 성공시 
			console.log("xhr :" + xhr);
			console.log(xhr);
			console.log("textStatus :" + textStatus);
			console.log("data :" + data);
		    alert("회원가입이 완료 되었습니다.");
		    location.href = "/";
		}).fail(function(xhr){
			// 통신 실패시 
			console.log(xhr);
			alert("회원가입이 실패 되었습니다.");
		});
	},
//	login: function(){
//		let data = {
//			username: $("#username").val(),
//			password: $("#password").val()
//		}
		 //ajax 호출
//		$.ajax({
			// 회원 로그인 요청!
			//Get으로 할수는있지만 히스토리에 남기때문에 보안의 문제가 생긴다.
//			type: "POST",
//			url: "/api/user/login",
//			data: JSON.stringify(data),
//			contentType: "application/json; charset=utf-8",
//			dataType: "json" 
//		}).done(function(data, textStatus, xhr){
//			alert("로그인이 성공 되었습니다.")
//			location.href = "/"
//			console.log(data);
//		}).fail(function(error){
//			alert("로그인 실패 했습니다.")
//			console.log(error);
//		});  
		
//	},
	update: function(){
		
		let token=$("meta[name='_csrf']").attr("content"); // 메디태그 안에 name 속성에 -csrf .attr(속성값) 
		let header = $("meta[name='_csrf_header']").attr("content");
		
		let data = {
			username: $("#username").val(),
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);
		$.ajax({
			beforeSend: function(xhr){// 이 함수가 실행하기전에 먼저 불러진다
			console.log("xhr : " + xhr); // xhr = 자바스크립트 객체이다
			xhr.setRequestHeader(header, token);
			},	
			
			type: "PUT",
			url: "/user",
			data : JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
		.done(function(data){
			alert("수정 성공!");
			location.href = "/";
			console.log(data);
		})
		.fail(function(error){
			alert("수정 실패!!");
		});
	}
}

index.init();