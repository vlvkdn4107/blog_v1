// "use strict"    // 전체 스크립트를 strict(엄격모드) 모드로 설정함.
// index = 오브젝트 {안에는 변수}
// let = 함수 밖에서 쓰면 멤버변수 함수 안에쓰면 지역변수



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
		// console.log(data);
		
		// AJAX 호출
		$.ajax({
			// 서버측에 회원가입 요청
			type: "POST",
			url: "/blog/api/user",
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
		    location.href = "/blog";
		}).fail(function(xhr){
			// 통신 실패시 
			console.log(xhr);
			alert("회원가입이 실패 되었습니다.");
		});
		
	}
	
}

index.init();