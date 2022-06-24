function saveBoard(){
	let title = document.querySelector("#title").value;
	let content = document.querySelector("#content").value;
	
	let board = {
		title : title,
		content : content
	}
	fetch("/boardSave",{
		method: "post",
		headers: {
			'content-type' : 'application/json; charset=utf-8'
		},
		body: JSON.stringify(board)
	})
	.then(res => res.text())
	.then(res =>{
		if(res == "ok"){
			alert("글쓰기 성공");
		}else{
			alert("글쓰기 실패!");
		}
	});
}