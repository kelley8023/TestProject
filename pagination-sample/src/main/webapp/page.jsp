<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>分页</title>
    <%@include file="_header.jsp" %>
</head>
<body>
<%@include file="_navbar.jsp" %>
<div class="container" role="main">

    <c:set var="users" value="${requestScope.users}"/>
    <c:set var="pagination" value="${requestScope.pagination}"/>
    <c:set var="page" value="${requestScope.page}"/>
    <c:set var="totalPages" value="${pagination.getPageCount()}"/>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form action="<c:url value="/page"/>" method="get">
                <p>用户总数:${pagination.getTotalCount()}，每页用户数:<input name="countPerPage" type="text"
                                                                   value="${pagination.getCountPerPage()}"/>
                    (<input type="submit"
                            value="修改"/>)，总页数:${pagination.getPageCount()}，当前页:${page}</p>
            </form>

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
                <c:forEach var="user" items="${users}">
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
                    <li class="page-item ${page==1?'disabled':''}"><a class="page-link"
                                                                      href="<c:url value="/page?page=${page-1>1?page-1:1}"/>">上一页</a>
                    </li>
                    <c:if test="${page!=1}">
                        <li class="page-item">
                            <a class="page-link" href="<c:url value="/page?page=1"/>">1</a>
                        </li>
                    </c:if>
                    <c:if test="${page>2}">
                        <li class="page-item disabled">
                            <a class="page-link">...</a>
                        </li>
                    </c:if>
                    <li class="page-item active">
                        <a class="page-link" href="<c:url value="/page?page=${page}"/>">${page}</a>
                    </li>
                    <c:if test="${totalPages-page>1}">
                        <li class="page-item disabled">
                            <a class="page-link">...</a>
                        </li>
                    </c:if>
                    <c:if test="${page!=totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="<c:url value="/page?page=${totalPages}"/>">${totalPages}</a>
                        </li>
                    </c:if>

                    <li class="page-item ${page==totalPages?'disabled':''}">
                        <a class="page-link" href="<c:url value="/page?page=${page+1<totalPages?page+1:totalPages}"/>">下一页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>

<%@include file="_footer.jsp" %>
</body>
</html>
