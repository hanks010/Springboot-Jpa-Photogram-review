// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault();
	let data = $("#profileUpdate").serialize();
	console.log(data);

	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded;charset=utf-8", //보내는 데이터의 타입
		dataType: "json" // 서버에서 받을 타입
	}).done(res => {
		console.log("update 성공", res);
		//location.href =`/user/${userId}`;
	}).fail(error => {
		console.log("update 실패");
		console.log(error.responseJSON);
		if (error.responseJSON.data == null) { //없는 id로 업데이트 들어올 때
			alert(err.responseJSON.message);
		} else { // 공백 등 유효성 검사 실패할 때
			const message = JSON.stringify(error.responseJSON.data)
				.replace("{", "").replace("}", "").replace(':', " ").replace('name', '이름은').replace(/\"/gi, "");
			alert(message);
		}
	})

}