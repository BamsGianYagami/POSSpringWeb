<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Checkout</title>
		<link href="${contextPath}/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
			<script src="${contextPath}/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
			<link href="${contextPath}/fontawesome-free-6.5.1-web/css/brands.min.css" rel="stylesheet">
				<script src="${contextPath}/fontawesome-free-6.5.1-web/js/all.js"></script>
			</head>
			<body>
				<jsp:include page="nav-bar.jsp"/>
				<div class="container-fluid row">
					<div class="container-fluid row my-2 py-sm-2 ps-sm-5 pe-sm-5">
						<a href="/addUser" role="button" class="btn btn-success btn-lg">
                            Add User 
							<i class="fa fa-plus-square" aria-hidden="true"></i>
						</a>
					</div>
					<div class="container-fluid col my-2 py-sm-2 px-sm-2">
						<table class="table table-striped">
							<thead class="table-primary">
								<tr>
									<th>ID</th>
									<th>username</th>
									<th>Nama User</th>
									<th>Email</th>
									<th>password</th>
									<th>roles</th>
									<th>action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>${user.id}</td>
										<td>${user.username}</td>
										<td>${user.name}</td>
										<td>${user.email}</td>
										<td>${user.password}</td>
										<td>${user.roles}</td>
										<td>
											<a href="/editUser/${user.id}" role="button" class="btn btn-outline-warning">
                                    edit
                                </a>
											<a href="/deleteUser/${user.id}" role="button" class="btn btn-outline-danger">
                                    delete
                                </a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</body>
		</html>