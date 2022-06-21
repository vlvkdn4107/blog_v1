"use strict"    // 전체 스크립트를 strict(엄격모드) 모드로 설정함.
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
		console.log(data);
	}
	
}

index.init();