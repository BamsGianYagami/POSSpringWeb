<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
      <title>Confirm-Checkout</title>
      <link href="../bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="../bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="nav-bar.jsp"/>
        <div class="container-fluid row vh-100">
            <div class="d-flex flex-column container-fluid col my-2 py-sm-2 px-sm-2 vh-100">
                <div class="d-flex flex-column vh-100 overflow-scroll">
                    <table class="table table-striped">
                        <thead class="table-primary">
                            <tr>
                                <th>item Id</th>
                                <th>Nama Barang</th>
                                <th>Harga Satuan</th>
                                <th>Banyaknya</th>
                                <th>Harga <i class="fa-solid fa-xmark"></i> banyaknya</th>
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
                <div class="container-fluid mt-auto mb-3 border border-1 my-2 py-sm-2 px-sm-2">
                    <b class="d-flex justify-content-end fs-4">Total Harga ${grandTotal}</b>
                    <div class="d-flex justify-content-center fs-4">
                        <a class="btn btn-primary btn-lg px-5" href="/checkout" role="button"><i class="fa-solid fa-circle-arrow-left"></i> Kembali</i></a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>