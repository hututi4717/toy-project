<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 이런 공통헤더에 이렇게 라이브러리를 추가하면, 다른 jsp에서 굳이 또 추가해 줄 필요가 없어진다.  -->

<%-- <% String contextPath = request.getContextPath(); %>   <!-- 자바 서블릿에 접근해 컨택스트패스를 가져와서 그를 기반으로 동적으로경로가 바뀌게끔 했음.  --> --%>
<!-- el태그로 갈아치우면서 필요없게됨(바로 아래 줄 코드가 대체) -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<meta charset="UTF-8">
<link rel = "stylesheet" href = "${contextPath}/resources/css/all.css">
<script type = "text/javascript" src = "${contextPath}/resources/js/webUtil.js"></script>
