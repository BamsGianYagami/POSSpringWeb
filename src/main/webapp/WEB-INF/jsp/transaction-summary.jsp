<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
      <title>Transaction History</title>
      <link href="${contextPath}/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="${contextPath}/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="nav-bar.jsp"/>
        <div class="container-fluid row">
            <div class="container-fluid col my-2 py-sm-2 px-sm-2">
                <table class="table table-striped">
                    <thead class="table-primary">
                        <tr>
                            <th>Transaction id</th>
                            <th>username</th>
                            <th>Transaction time</th>
                            <th>Total Purchasing</th>
                            <th>action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${summaries}" var="summary">
                            <tr>
                                <td>${summary.id}</td>
                                <td>${summary.username}</td>
                                <td>${summary.transactionTime}</td>
                                <td>${summary.grandTotal}</td>
                                <td>
                                    <a href="/transaction/${summary.id}" role="button" class="btn btn-primary">
                                        view detail
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>