<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录</title>
		<link rel="stylesheet" href="<%=context %>/css/login.css" />
	</head>
	<body>
		<div id="login_box">
			<h2 id="login_title">用&nbsp;户&nbsp;登&nbsp;录</h2>
			<form id="login_form">
				<table id="login_table">
					<tr>
						<td>账号</td>
						<td>
							<input type="text" id="account" name="account"  autocomplete="off"/>
							<div id="downmenu_ico"></div>
							<ul id="account-list"></ul>
						</td>
					</tr>
					<tr>
						<td>密码</td>
						<td>
							<input type="text" id="password" name="password"  autocomplete="off" onfocus="this.type='password'"/>
						</td>
					</tr>
					<%-- <tr>
						<td>验证码</td>
						<td>
							<input type="code" id="code" name="code" placeholder="点击验证码切换"/>
							<input type="button" id="code_content" style="background:url('<%=context %>/user/code');" />
						</td>
					</tr> --%>
					<tr>
						<td></td>
						<td>
							<input type="checkbox" id="remember" name="remember" />
							<label for="remember">记住密码</label>
							<input type="button" id="loginbtn" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
	<script type="text/javascript" src="<%=context %>/js/jquery-2.1.0.min.js" ></script>
	<script type="text/javascript" src="<%=context %>/js/login.js" ></script>
	<script type="text/javascript" src="<%=context %>/js/browser.js" ></script>
	<script>
		var path = "<%=context %>";
	</script>
</html>
