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
      <div class="container-fluid row">
        <div class="container-fluid col my-2 py-sm-2 px-sm-2">
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
                  <c:forEach items="${stocks}" var="stocks">
                      <tr>
                          <td>${stocks.itemId}</td>
                          <td>${stocks.itemName}</td>
                          <td>${stocks.itemPrice}</td>
                          <td>${stocks.qty}</td>
                          <td>${stocks.itemPrice * checkout.qty}</td>
                      </tr>
                  </c:forEach>
              </tbody>
          </table>
        </div>
    </div>
    </body>
</html>