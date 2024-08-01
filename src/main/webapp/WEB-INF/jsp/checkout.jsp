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
        <div class="container-fluid row vh-100">

            <div class="d-flex flex-column container-fluid col-6 my-2 py-sm-2 px-sm-2 vh-100">
                <div class="d-flex pd-2 row row-cols bd-highlight vh-100 overflow-scroll">
                    
                    <c:forEach items="${stocks}" var="stock">
                        <div class="col mx-1 my-1">
                            <div class="card" style="width: 18rem;">
                                <img src="..." class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">${stock.itemName}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">item id: ${stock.itemId}</h6>
                                    <p class="card-text">price: ${stock.itemPrice}</p>

                                    <!-- penjelasan:
                                     modelAttribute adalah tag tambahan dari JSP, dibutuhkan deklarasi model attribute sebelum load halaman! -->
                                    <form:form action="/addtoCart/${stock.itemId}" method="post" modelAttribute="cart" class="input-group input-group-sm mb-3">
                                        <c:if test="${stock.qty==0}">
                                            <input type="submit" disabled="disabled" class="btn btn-secondary mb-3 disabled" value="habis"/>
                                        </c:if>

                                        <c:if test="${stock.qty>0}">
                                            <form:label class="form-label mb-3" path="qty">qty (${stock.qty}): </form:label>
                                            <form:input path="qty" class="form-control mb-3" type="number" value="1" min="1" max="${stock.qty}"/>
                                            <!--penjelasan:
                                                Dengan prefix form dari JSP, ada tambahan tag path yang secara otomatis akan mapping data
                                                sebagai request parameter, dengan bantuan form:form secara otomatis akan ditambahkan input
                                                type hidden berupa _csrf, namun bisa digabung dengan html sub tag biasa dari <form> sesuai kebutuhan,
                                                contohnya pada button submit yang tidak perlu mapping data -->
                                            <input type="submit" class="btn btn-primary mb-3" value="add to cart"/>
                                        </c:if>
                                    </form:form>
                                    
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
            
            <div class="d-flex flex-column container-fluid col-4 border border-info border-2 my-2 py-sm-2 px-sm-2 vh-100">
                <div class="d-flex flex-column vh-100 overflow-scroll">
                    <div class="container">
                        <h4>list Cart</h4>
                    </div>
                    <div class="list-group">

                        <c:forEach items="${carts}" var="cart">
                            <div class="list-group-item list-group-item-action">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${cart.itemName}</h5>
                                    <small>item ID: ${cart.itemId}</small>
                                </div>
                                <p class="mb-1">Price: ${cart.itemPrice}</p>
                                <form:form action="/removeFromCart/${cart.itemId}" method="post" modelAttribute="cart" class="input-group input-group-sm mb-3">
                                    <form:label class="form-label mb-3" path="qty">qty (${cart.qty}): </form:label>
                                    <form:input class="form-control-sm mb-3" path="qty" type="number" value="${cart.qty}" min="1" max="${cart.qty}"/>
                                    <input type="submit" class="btn btn-primary mb-3" value="remove"/>
                                </form:form>
                            </div>
                        </c:forEach>

                    </div>
                </div>

                <!-- old style -->
                <!-- <div class="container-fluid mt-auto mb-3 border border-1 my-2 py-sm-2 px-sm-2">
                    <b class="d-flex justify-content-end fs-4">Total Harga ${grandTotal}</b>
                    <a href="/confirmCheckout" class="btn btn-primary mb-3" value="confirm checkout">
                </div> -->

                <!-- new style -->
                <div class="d-flex justify-content-between bd-highlight mb-3">
                    <div class="p-2 bd-highlight mb-3">
                        <b class="d-flex justify-content-start">Total Harga ${grandTotal}</b>
                    </div>
                    <div class="p-2 bd-highlight mb-3">
                        <a class="d-flex justify-content-end btn btn-primary btn-sm" href="/confirmCheckout" role="button">confirm checkout</a>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>