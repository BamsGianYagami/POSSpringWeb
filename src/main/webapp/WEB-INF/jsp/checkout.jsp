<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
      <title>Checkout</title>
      <link href="${contextPath}/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="${contextPath}/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
      <jsp:include page="nav-bar.jsp"/>
      <div class="container">
        <table class="table table-striped">
            <thead class="table-primary">
                <tr>
                    <th>item Id</th>
                    <th>Nama Barang</th>
                    <th>Harga Satuan</th>
                    <th>Banyaknya</th>
                    <th>Harga x banyaknya</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${checkout}" var="checkout">
                    <tr>
                        <td>${checkout.itemId}</td>
                        <td>${checkout.itemName}</td>
                        <td>${checkout.itemPrice}</td>
                        <td>${checkout.qty}</td>
                        <td>${checkout.itemPrice * checkout.qty}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      </div>
    </body>
</html>