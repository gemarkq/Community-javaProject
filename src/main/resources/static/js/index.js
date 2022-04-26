$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

	// 获取标题内容
	var title = $("#recipient-name").val();
	var content = $("#message-text").val();
	// send asyn request
	$.post(
		CONTEXT_PATH + "/discuss/add",
		{"title":title, "content":content},
		function (data) {
			data = $.parseJSON(data);
			// 在提示框当中显示返回消息
			$("#hintBody").text(data.msg);
			// 显示提示框，2s后自动隐藏提示框
			$("#hintModal").modal("show");
			setTimeout(function(){
				$("#hintModal").modal("hide");
				// refresh page
				if(data.code == 0) {
					window.location.reload();
				}
			}, 2000);
		}
	)


}