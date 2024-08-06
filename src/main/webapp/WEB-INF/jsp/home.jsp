<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
      <title>Home</title>
      <link href="${contextPath}/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="${contextPath}/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
      <jsp:include page="nav-bar.jsp"/>
      <section class="vh-100" style="background: url(images/MAESTRO-LOGO.jpg);">
        <div class="container-fluid h-custom">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
              <img src="${contextPath}/images/MAESTRO-outlet.jpg" class="img-fluid" alt="outlet">
            </div>
            <div class="col-md-9 col-lg-6 col-xl-5">
              <img src="${contextPath}/images/MAESTRO-inside-1.jpg" class="img-fluid" alt="inside">
            </div>
          </div>
        </div>
      </section>
    </body>
</html>