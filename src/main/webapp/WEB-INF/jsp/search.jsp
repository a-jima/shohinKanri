<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<style type="text/css">
table, th, td {
	border: 1px #000000 solid;
}
</style>
</head>
<body>
	<h1>商品検索</h1>
	<form action="search" method="post">
		<table>
			<tr>
				<th>商品名</th>
				<td><input type="text" name="name"></td>
				<th>商品分類</th>
				<td><select name="bunrui">
						<option value="衣服">衣服</option>
						<option value="事務用品">事務用品</option>
						<option value="キッチン用品">キッチン用品</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="検索">
	</form>
	
	
</body>
</html>