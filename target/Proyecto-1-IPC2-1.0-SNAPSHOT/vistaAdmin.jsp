<%-- 
    Document   : vistaAdmin
    Created on : 15 mar. 2023, 0:16:43
    Author     : rudy-reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="/WEB-INF/barraCerrarSesion.jsp"/>

        <div class="row my-3 " >


            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Crear Usuario de Tienda</h5>
                        <p class="card-text">Puede crear un usuario de tienda para que gestione una tienda</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesAdmin?accion=crearT" class="btn btn-primary">Crear Usuario</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Crear Usuario de Bodega</h5>
                        <p class="card-text">Puede crear un usuario para que gestione la bodega central</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesAdmin?accion=crearB" class="btn btn-primary">Crear Usuario</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Crear Usuario Supervisor</h5>
                        <p class="card-text">Puede crear un usuario para que supervise las tiendas supervisadas</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesAdmin?accion=crearS" class="btn btn-primary">Crear Usuario</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row my-3 " >
            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Reportes</h5>
                        <p class="card-text">Puede mirar los reportes de las tiendas</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesAdmin?accion=reportes" class="btn btn-primary">Ver reportes</a>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
