<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
      <title>Login</title>
      <link href="${contextPath}/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/fontawesome-free-6.5.1-web/css/brands.min.css" rel="stylesheet">
      <script src="${contextPath}/fontawesome-free-6.5.1-web/js/all.js"></script>
    </head>
    <body>
      <section class="vh-100" style="background: url(images/MAESTRO-LOGO.jpg);">
        <div class="container-fluid h-custom">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
              <img src="${contextPath}/images/MAESTRO-inside-1.jpg" class="img-fluid" alt="pic1">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1 bg-dark text-white">
              <h1>MAESTRO</h1>
              <form:form action="${contextPath}/" method="post" modelAttribute="login">
                <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                  <p class="lead fw-normal mb-0 me-3">Sign in</p>
                </div>
      
      
                <!-- username input -->
                <div class="form-outline mb-4">
                  <input type="text" id="username" class="form-control form-control-lg"
                    placeholder="Enter Username" name="username" />
                  <label class="form-label" for="username">Username</label>
                </div>
      
                <!-- password input -->
                <div class="form-outline mb-3">
                  <input type="password" id="password" class="form-control form-control-lg"
                    placeholder="Enter password" name="password"/>
                  <label class="form-label" for="password">Password</label>
                </div>
      
                <div class="text-center text-lg-start mt-4 pt-2">
                  <button type="submit" class="btn btn-primary btn-lg"
                    style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                  <c:if test="${loginFailed}">
                    <p class="small fw-bold mt-2 pt-1 mb-0 link-danger">Username atau password salah!</p>
                  </c:if>
                  <c:if test="${logout}">
                    <p class="small fw-bold mt-2 pt-1 mb-0 link-danger">anda telah logout!</p>
                  </c:if>
                </div>
      
              </form:form>
            </div>
          </div>
        </div>
        <div
          class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
          <!-- Copyright -->
          <div class="text-white mb-3 mb-md-0">
            dibuat pada Maret 2024 untuk tugas Kuliah Kerja Praktek
          </div>
          <!-- Copyright -->
      
          <!-- Right -->
          <div>
            <a href="#!" class="text-white me-4">
              <i class="fab fa-facebook-f"></i>
            </a>
            <a href="#!" class="text-white me-4">
              <i class="fab fa-twitter"></i>
            </a>
            <a href="#!" class="text-white me-4">
              <i class="fab fa-google"></i>
            </a>
            <a href="#!" class="text-white">
              <i class="fab fa-linkedin-in"></i>
            </a>
          </div>
          <!-- Right -->
        </div>
      </section>
    </body>
</html>