<%--
  Created by IntelliJ IDEA.
  User: a42
  Date: 11/19/23
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap demo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<div class="container-fluid">
  <div class="row">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Dropdown
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" aria-disabled="true">Disabled</a>
            </li>
          </ul>
          <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
  </div>

  <div class="row content">
    <div class="col">
      <div class="card" style="width: 18rem;">
        <img src="..." class="card-img-top" alt="...">
        <div class="card-body">
          <form action="/todo/modify" method="post">
          <div class="input-group mb-3">
            <span class="input-group-text">TNO</span>
            <input type="text" name="tno" class="form-control"
                   value=<c:out value="${dto.tno}"></c:out> readonly>
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text">Title</span>
            <input type="text" name="title" class="form-control"
                   value=<c:out value="${dto.title}"></c:out> >
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text">DueDate</span>
            <input type="date" name="dueDate" class="form-control"
                   value=<c:out value="${dto.dueDate}"></c:out> >
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text">Writer</span>
            <input type="text" name="writer" class="form-control"
                   value=<c:out value="${dto.writer}"></c:out> readonly>
          </div>

          <div class="form-check">
            <label class="form-check-label">
              Finished &nbsp;
            </label>
            <input class="form-check-input" type="checkbox" name="finished" ${dto.finished ? "checked" : ""} disabled >
          </div>
          <div class="my-4">
            <div class="float-end">
              <button type="button" class="btn btn-danger">Remove</button>
              <button type="button" class="btn btn-primary">Modify</button>
              <button type="button" class="btn btn-secondary">List</button>
            </div>
          </div>
          </form>
          <script>
            const formObj = document.querySelector("form")

            document.querySelector(".btn-danger").addEventListener("click", function (e){
              e.preventDefault();
              e.stopPropagation();

              // Create a new hidden input for tno
              var hiddenInput = document.createElement("input");
              hiddenInput.type = "hidden";
              hiddenInput.name = "tno";
              hiddenInput.value = ${dto.tno}; // Ensure this is the correct way to get the tno value

              // Append the hidden input to the form
              formObj.appendChild(hiddenInput);

              formObj.action = "/todo/remove";
              formObj.method = "post";
              formObj.submit();
            }, false)

            document.querySelector(".btn-primary").addEventListener("click", function (e) {
              self.location = "/todo/modify?tno=" + ${dto.tno}
              e.preventDefault();
              e.stopPropagation();

              formObj.action ="/todo/modify"
              formObj.method ="post"

              formObj.submit()
            }, false)

            document.querySelector(".btn-secondary").addEventListener("click", function (e){
              self.location = "/todo/list"
            }, false)
          </script>
      </div>
    </div>
  </div>
  </div>
  <script>
    const serverValidResult = {}
    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
  </script>
</div>
</body>
</html>