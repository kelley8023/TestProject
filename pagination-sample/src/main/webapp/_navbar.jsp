<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">分页示例</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item ${pageContext.request.requestURI.endsWith('list.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/list?page=1"/>">简单分页</a>
                </li>
                <li class=" nav-item ${pageContext.request.requestURI.endsWith('pageableList.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/pageableList?page=1"/>">数据库分页</a>
                </li>
                <li class=" nav-item ${pageContext.request.requestURI.endsWith('page.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/page?page=1"/>">分页</a>
                </li>
            </ul>
        </div>
    </div>
</nav>