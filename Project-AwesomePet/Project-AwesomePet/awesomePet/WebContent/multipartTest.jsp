<%@ page
	language="java"
	contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	
	<body>
		<form action="uploadTest" method="POST" encType="multipart/form-data">
			<label>파일 업로드</label>
			<input type="file" name="myFile">
			<input type="hidden" name="hiddenValue" value="hello World">
			
			<input type="submit" value="전송">
		</form>
	</body>
</html>