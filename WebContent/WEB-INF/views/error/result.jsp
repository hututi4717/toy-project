<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "/WEB-INF/views/include/head.jsp" %>
</head>
<body>
	<!-- 여기서의 스크립트 코드는, response바디에 이 스크립트 코드를 써서 브라우저에 보내겟다.. 라고 생각하면 된다.
	스크립트와 jstl 은 서로 동작 시점이 다르다.  -->
	
	<script type="text/javascript">
		
	<%-- 안내창 출력 --%>
	<c:if test="${not empty msg}">
		alert("${msg}");	
	</c:if>
	
	<%-- 뒤로가기 --%>
	<c:if test="${not empty back}">
		history.back();	
	</c:if>
	
	<%-- 페이지 이동 --%>
	<c:if test="${not empty url}">
		location.href='${url}';
	</c:if>
	
	</script>
</body>
</html>