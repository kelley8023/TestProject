<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>数据库分页</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container" role="main">

    <c:set var="totalPages" value="${requestScope.totalPages}"/>
    <c:set var="page" value="${requestScope.page}"/>
    <c:set var="currentPageUsers" value="${requestScope.users}"/>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <p>总页数:${totalPages},当前页:${page}</p>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8 table-responsive">
            <table class="table table-hover table-striped table-bordered table-sm">
                <thead>
                <tr>
                    <td>用户编号</td>
                    <td>姓名</td>
                    <td>密码</td>
                    <td>生日</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${currentPageUsers}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.password}</td>
                        <td>${user.birthday}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row justify-content-center">
        <div>
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="<c:url value="/pageableList?page=1"/>">首页</a></li>
                    <li class="page-item"><a class="page-link"
                                             href="<c:url value="/pageableList?page=${page-1>1?page-1:1}"/>">&laquo;</a>
                    </li>
                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page?'active':''}"/>
                        <li class="page-item ${active}">
                            <a class="page-link"
                               href="<c:url value="/pageableList?page=${loop.index}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="<c:url value="/pageableList?page=${page+1<totalPages?page+1:totalPages}"/>">&raquo;</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link"
                           href="<c:url value="/pageableList?page=${totalPages}"/>">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>

</div>

<%@include file="_footer.jsp" %>
</body>
</html>
