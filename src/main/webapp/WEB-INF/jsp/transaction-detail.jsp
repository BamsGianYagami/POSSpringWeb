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
        <div class="d-flex flex-column container-fluid col border border-info border-2 my-2 py-sm-2 px-sm-2 vh-100">
            <div class="d-flex flex-column vh-100 overflow-scroll">
                <div class="d-flex justify-content-center">
                    <h4>list Cart <i class="fa-solid fa-basket-shopping"></i></h4>
                </div>

                <table class="table table table-success table-striped">
                    <thead class="table-success">
                        <tr>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Item Price</th>
                            <th>Total Price</th>
                        </tr>
                    </thead>
                    <tbody class="table-light">
                        <c:forEach items="${transactionDetail}" var="detail">
                            <tr>
                                <td>${detail.itemId}</td>
                                <td>${detail.itemName}</td>
                                <td>${detail.qty}</td>
                                <td>${detail.itemPrice}</td>
                                <td>${stocks.totalPrice}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="d-flex justify-content-between bd-highlight mb-3">
                <div class="p-2 bd-highlight mb-3">
                    <b class="d-flex justify-content-start">Total Harga ${grandTotal}</b>
                </div>
                <div class="p-2 bd-highlight mb-3">
                    <a class="d-flex justify-content-end btn btn-success btn-sm" href="/transaction" role="button">Kembali</a>
                </div>
            </div>

        </div>
    </body>
</html>