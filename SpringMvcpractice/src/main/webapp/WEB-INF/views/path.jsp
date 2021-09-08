<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>���� ���� ��� �������� ${page } ������ �Դϴ�.</h1>
	
	<!-- jstl�� Ȱ���ؼ� pagr�� 100�̸��̸� �ϴܿ� h2�±׸� �̿��ؼ�
		"�ʹݺ��Դϴ�", 100�̻� 200�̸��̸� "�߹ݺ��Դϴ�", 200�̻��̸� "�Ĺݺ��Դϴ�"
		��� ������ �߰��� ������ּ���. -->
	<c:choose>
		<c:when test="${page < 100 }"><h2>�ʹݺ��Դϴ�.</h2></c:when>
		<c:when test="${page >= 100 && page < 200 }"><h2>�߹ݺ��Դϴ�.</h2></c:when>
		<c:when test="${page >= 200 }"><h2>�Ĺݺ��Դϴ�.</h2></c:when>
	</c:choose>
</body>
</html>