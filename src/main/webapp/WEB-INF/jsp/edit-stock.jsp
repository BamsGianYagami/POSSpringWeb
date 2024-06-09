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
        <div class="container-fluid col border border-info border-2 my-2 py-sm-2 px-sm-2">
        <form:form action="" method="post" modelAttribute="stock">
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
            <form:label class="form-label" path="qty">Banyaknya Stock: </form:label> <form:input path="qty" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="unitCount">Satuan Unit (kg, pcs dan sebagainya): </form:label> <form:input path="unitCount" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <input type="submit" value="submit" class="btn btn-primary"/>
          </div>
        </form:form>
      </div>
    </div>
    </body>
</html>