<%-- 
    Document   : incidencia
    Created on : 19 mar. 2023, 10:55:43
    Author     : rudy-reyes
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Incidencia</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="/WEB-INF/vistaIncidencia/cabecero.jsp"/>
        <form action="${pageContext.request.contextPath}/ServletCrearIncidencias?accion=crearIncidencia"
              method="POST">

            <div class="row ">
                <div class="form-group">

                    <label for="codigoP">Envios</label>
                    <select name="idEnvio" class="form-select" aria-label="Default select example">
                        <c:forEach var="listadoE" items="${listadoEnvio}">
                            <option value="${listadoE}">${listadoE}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Crear Incidencia</button>
                </div>   
            </div>
        </form>
                        
        <jsp:include page="/WEB-INF/vistaIncidencia/listadoProductos.jsp"/>     
        
        <div class="col-md-3 justify-content-md-end">
            <a href="${pageContext.request.contextPath}/vistaUsuarioTienda.jsp" class="btn btn-danger btn-block">
                Regresar
            </a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
