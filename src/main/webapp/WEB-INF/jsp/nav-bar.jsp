<nav class="navbar navbar-expand-lg navbar-light bg-dark text-white" style="background-color: #76abfaf6;">
  <b class="navbar-brand text-warning">MAESTRO</b>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav me-auto">
      <li class="nav-item active">
        <a class="nav-link text-white" href="/dashboard">Home Dashboard</a>
      </li>
      <!-- <li class="nav-item">
        <a class="nav-link" href="/main/addStock">Add Stock</a>
      </li> -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          User Management
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/users">view Users</a>
          <a class="dropdown-item" href="/addUser">add User</a>
          <!-- <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a> -->
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Stock Management
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/stocks">view Stock</a>
          <a class="dropdown-item" href="/addStock">add Stock</a>
          <!-- <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a> -->
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link text-white" href="/checkout">Checkout</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-white" href="/transaction">Transaction History</a>
      </li>
      <!-- <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li> -->
    </ul>
    <c:if test="${pageContext.request.userPrincipal.name != null}"></c:if>
      <form class="align-self-end"
        id="logoutForm" method="POST" action="${contextPath}/logout"> <!-- new atribute to make this form logout POST-->
        <div class="row me-sm-2">
          
          <!-- <input class="col-auto me-sm-2" type="search" placeholder="Search" aria-label="Search">
          <button class="col-auto btn btn-primary me-sm-2" type="submit">Search</button> -->
          
          <p class="col-auto my-md-0">Masuk sebagai <b>${pageContext.request.userPrincipal.name}</b></p>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button type="submit" class="col-auto btn btn-sm btn-primary my-md-0">logout</button>

        </div>
      </form>
    </c:if>
  </div>
  
  <!-- <p class="my-2 my-md-0 me-md-2">Masuk sebagai <b>${pageContext.request.userPrincipal.name}</b></p>
  <c:if test="${pageContext.request.userPrincipal.name != null}">
      <form class="my-2 my-md-0 me-md-2" id="logoutForm" method="POST" action="${contextPath}/logout">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button type="submit" class="btn btn-primary">logout</button>
      </form>
  </c:if> -->

  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
</nav>