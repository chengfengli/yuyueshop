/*提示*/
$.fn.tip = function(text) {
	if (!text) {
		text = "";
	}
	var input_width = this.width();
	var left = this.offset().left + 10;
	var top = this.offset().top + 26;
	$("body")
			.append(
					'<div class="tips" style="background: #A7D1F7;position: absolute;left:'
							+ left
							+ 'px;top:'
							+ top
							+ 'px">'
							+ '<div style="border-right:6px solid transparent;border-top:0 solid transparent;border-left:6px solid transparent;border-bottom:10px solid #A7D1F7;position: absolute;top:-10px;left:13px;"></div>'
							+ '<div style="padding:3px 5px;font-size: 12px;min-width:14px;min-height: 16px;">'
							+ text + '</div>' + '</div>');
	this.click(function() {
		$(".tips").remove();
	});
}

/* 非空验证 */
function check() {
	$(".tips").remove();
	var account = $("#account").val();
	var password = $("#password").val();
	var code = $("#code").val();
	if (account == "") {
		$("#account").tip("用户名不能为空!");
		$("#account").focus();
		return false;
	}
	if (password == "") {
		$("#password").tip("密码不能为空!");
		$("#password").focus();
		return false;
	}
	if (code == "") {
		$("#code").tip("请输入验证码!");
		$("#code").focus();
		return false;
	}
	return true;
}

$(function() {
	/*alert('浏览器UA='+$.NV('UA')+    
		    '\n\n浏览器名称='+$.NV('name')+    
		    '\n\n浏览器版本='+parseInt($.NV('version'))+    
		    '\n\n浏览器外壳='+$.NV('shell'));*/
	
	/* 显示其他账号 */
	$("#downmenu_ico").click(function() {
		$("#account-list").toggle();
	});

	/* 双击显示其他账号 */
	$("#account").dblclick(function() {
		$("#downmenu_ico").click();
	});

	/* 回车键登录 */
	$(document).keypress(function(e) {
		if (e.keyCode == 13) {
			$("#loginbtn").click();
		}
	})

	/* 登录 */
	$("#loginbtn").click(function() {
		var account = $("#account").val();
		var password = $("#password").val();
		var code = $("#code").val();
		var remember = 0;
		if ($("#remember").is(":checked")) {
			remember = 1;
		}
		if (check()) {
			$.ajax({
				url : path + "/user/login",
				type : "post",
				dataType : "json",
				data : {
					userAcount : account,
					userPassword : password,
					code : code,
					rememberPassword : remember
				},
				success : function(result) {
					if (result.isSuccess) {
						location.href = path + "/index.jsp";
					} else {
						if (result.errorType == "codeError") {
							$("#code").tip(result.strMessage);
						} else {
							$("#account,#password").tip(result.strMessage);
						}
						$("#code_content").click();
					}
				},
				error : function(result) {
					alert("系统错误!");
				}
			})
		}
	});

	/* 刷新验证码 */
	$("#code_content").click(
		function() {
			$.ajax({
				url : path + "/user/recode",
				type : "post",
				dataType : "json",
				success : function(result) {
					$("#code_content").css(
							"background",
							"url(" + path + "/img/" + result.strMessage
									+ ")")
				},
				error : function(result) {
					alert("系统错误!");
				}
			});
		}
	);
	
	/*自动加载在本机上登录过的并记住密码的账号*/
	/*$.ajax({
		url:path + "/user/loadAccount",
		type:"post",
		dataType:"json",
		success:function(result){
			$("#password").attr("type","password");
			if(result.length>0){
				$("#account").val(result[0].userAcount);
				$.ajax({
					url:path + "/user/loadPwd",
					type:"post",
					dataType:"json",
					data:{userAccount:result[0].userAcount},
					success:function(result){
						$("#password").val(result.strMessage);
					}
				})
			}
			for(var i = 0;i<result.length;i++){
				$("#account-list").append("<li>"+result[i].userAcount+"</li>");
			}
			 切换其他账号 
			$("#account-list li").on("click",function() {
				var other_account = $(this).text();
				$("#account").val(other_account);
				$("#account-list").toggle();
				$.ajax({
					url:path + "/user/loadPwd",
					type:"post",
					dataType:"json",
					data:{userAccount:other_account},
					success:function(result){
						$("#password").val(result.strMessage);
					}
				})
			});
		}
	});*/
});
