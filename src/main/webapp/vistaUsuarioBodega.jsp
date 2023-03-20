<%-- 
    Document   : vistaUsuarioBodega
    Created on : 15 mar. 2023, 0:18:07
    Author     : rudy-reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Bodega</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>

        <jsp:include page="/WEB-INF/barraCerrarSesion.jsp"/>

        <div class="row my-3 " >


            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Envios</h5>
                        <p class="card-text">Puede realizar envios de los productos pedidos por los usuarios de tienda</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesBodega?accion=envio" class="btn btn-primary">Realizar Envio</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Incidencias</h5>
                        <p class="card-text">Puede solucionar las incidencias enviadas por los usuarios de tienda</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesBodega?accion=incidencias" class="btn btn-primary">Solucionar Incidencia</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Devoluciones</h5>
                        <p class="card-text">Permite aceptar o rechazar las devoluciones enviadas por los usuarios de tienda</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesBodega?accion=devoluciones" class="btn btn-primary">Aceptar/Rechazar Devolucion</a>
                    </div>
                </div>
            </div>


        </div>
        <div class="row my-3 " >




            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Catalogo</h5>
                        <p class="card-text">Puede ver todos los productos del catalogo de la bodega central</p>
                        <a href="#" class="btn btn-primary">Ver Catalogo</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Reportes</h5>
                        <p class="card-text">Puede ver todos los reportes generados por los usuarios</p>
                        <a href="#" class="btn btn-primary">Ver Reportes</a>
                    </div>
                </div>
            </div>

        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>