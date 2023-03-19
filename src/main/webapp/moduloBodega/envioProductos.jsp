<%-- 
    Document   : envioProductos
    Created on : 18 mar. 2023, 15:47:59
    Author     : rudy-reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enviar productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>

        <jsp:include page="/WEB-INF/vistaEnvio/cabecero.jsp"/>
        <jsp:include page="/WEB-INF/vistaEnvio/botonesNavegacion.jsp"/>
        <jsp:include page="/WEB-INF/vistaEnvio/listadoProductos.jsp"/>

        <div class="container">
            <div class="col-md-3 justify-content-md-end">
                <a href="${pageContext.request.contextPath}/ServletControladorEnvios?accion=enviar" class="btn btn-danger btn-block">
                    Enviar
                </a>
            </div>
        </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</body>
</html>
