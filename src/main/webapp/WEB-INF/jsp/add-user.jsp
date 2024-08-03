<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
      <title>Add User</title>
      <link href="${contextPath}/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="${contextPath}/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
      <jsp:include page="nav-bar.jsp"/>
      <div class="container-fluid row">
        <div class="container-fluid col border border-info border-2 my-2 py-sm-2 px-sm-2">
        <form:form action="" method="post" modelAttribute="user">
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="id">ID: </form:label> <form:input type="text" path="id" class="form-control" disabled="true"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="username">Username: </form:label> <form:input type="text" path="username" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label"  path="name">Nama User: </form:label> <form:input type="text" path="name" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="email">Email: </form:label> <form:input path="email" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="password">Password: </form:label> <form:input path="password" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <form:label class="form-label" path="roles">Roles: </form:label> <form:input path="roles" class="form-control"/>
          </div>
          <div class="mb-3 border border-1 my-2 py-sm-2 px-sm-2">
            <input type="submit" value="submit" class="btn btn-primary"/>
          </div>
        </form:form>
      </div>
    </div>
    </body>
</html>