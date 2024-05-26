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
        <div class="container-fluid col-4 border border-info border-2 my-2 py-sm-2 px-sm-2">
        <form:form action="" method="post" modelAttribute="inputItem">
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="itemId">Item ID: </form:label> <form:input type="text" path="itemId" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label"  path="itemName">Nama Barang: </form:label> <form:input type="text" path="itemName" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="itemPrice">Harga Satuan: </form:label> <form:input path="itemPrice" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="qty">Banyaknya: </form:label> <form:input path="qty" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="totalPrice">Harga x banyaknya: </form:label> <form:input path="totalPrice" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <input type="submit" value="submit" class="btn btn-primary"/>
          </div>
        </form:form>
      </div>
    </div>
    </body>
</html>