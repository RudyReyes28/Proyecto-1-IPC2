<%-- 
    Document   : aceptarDevolucion
    Created on : 20 mar. 2023, 0:30:54
    Author     : rudy-reyes
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devoluciones</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>

        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1></i>Aceptar o Rechazar Devoluciones</h1>
                    </div>
                </div>
            </div>
        </header>
        <form action="${pageContext.request.contextPath}/ServletAceptarDevoluciones?accion=devolucion"
              method="POST">

            <div class="row ">
                <div class="form-group">

                    <label for="codigoP">Tiendas</label>
                    <select name="tienda" class="form-select" aria-label="Default select example">
                        <c:forEach var="tienda" items="${tiendas}">
                            <option value="${tienda}">${tienda}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Ver Devolucion</button>
                </div>   
            </div>
        </form>


        <jsp:include page="/WEB-INF/vistaAceptarDevolucion/listadoDevolucion.jsp"/>     

        <div class="col-md-3 justify-content-md-end">
            <a href="${pageContext.request.contextPath}/vistaUsuarioBodega.jsp" class="btn btn-danger btn-block">
                Regresar
            </a>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
