<%-- 
    Document   : crearUsuarioBodega
    Created on : 20 mar. 2023, 23:37:44
    Author     : rudy-reyes
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1></i>Creacion Usuario Bodega</h1>
                    </div>
                </div>
            </div>
        </header>

        <form action="${pageContext.request.contextPath}/ServletCreacionUsuarios?accion=uBodega"
              method="POST">

            <div class="row container">

                <div class="form-floating mb-3" style="border: 2px solid cornflowerblue">
                    <input name="codigo" type="number" class="form-control" id="floatingInput" placeholder="codigo">
                    <label for="floatingInput">Codigo</label>
                </div>

                <div class="form-floating mb-3" style="border: 2px solid cornflowerblue">
                    <input name="nombre" type="text" class="form-control" id="floatingInput" placeholder="nombre">
                    <label for="floatingInput">Nombre</label>
                </div>

                <!-- Aqui van las tiendas -->
                <div class="form-floating mb-3" style="border: 2px solid cornflowerblue">
                    <select multiple name="tienda" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                        <c:forEach var="tiendas" items="${tiendas}">
                            <option value="${tiendas}">${tiendas}</option>
                        </c:forEach>
                    </select>
                    <label for="floatingSelect">Seleccione las tiendas</label>
                </div>

                <div class="form-floating mb-3" style="border: 2px solid cornflowerblue">
                    <input name="usuario" type="text" class="form-control" id="floatingInput" placeholder="usuario">
                    <label for="floatingInput">Nombre de Usuario</label>
                </div>
                <div class="form-floating mb-3" style="border: 2px solid cornflowerblue">
                    <input name="contrasenia" type="password" class="form-control" id="floatingPassword" placeholder="Contraseña">
                    <label for="floatingPassword">Contraseña</label>
                </div>
                <div class="form-floating mb-3" style="border: 2px solid cornflowerblue">
                    <input name="correo" type="email" class="form-control" id="floatingInput" placeholder="usuario@gmail.com">
                    <label for="floatingInput">Correo</label>
                </div>


                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Crear Usuario</button>
                </div>   
            </div>
        </form>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>


    </body>
</html>
