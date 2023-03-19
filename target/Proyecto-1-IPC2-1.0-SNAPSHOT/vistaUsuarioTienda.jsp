<%-- 
    Document   : vistaUsuarioTienda
    Created on : 15 mar. 2023, 0:17:27
    Author     : rudy-reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Tienda</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        
    </head>
    <body>

        <jsp:include page="/WEB-INF/barraCerrarSesion.jsp"/>

        <div class="row my-3 " >
            
            
            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Pedidos</h5>
                        <p class="card-text">Puede realizar pedidos de productos a la bodega central</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesTienda?accion=pedido" class="btn btn-primary">Realizar Pedido</a>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Incidencias</h5>
                        <p class="card-text">Puede realizar incidencias de algún envio que realizó la bodega central</p>
                        <a href="#" class="btn btn-primary">Realizar Incidencia</a>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Devoluciones</h5>
                        <p class="card-text">Puede realizar devoluciones de los productos de algún envio </p>
                        <a href="#" class="btn btn-primary">Realizar Devolucion</a>
                    </div>
                </div>
            </div>
            
        </div>
        <div class="row my-3 " >
            
            
            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Verificar Envios</h5>
                        <p class="card-text">Permite ver los envios de productos enviados por la bodega central</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesTienda?accion=envios" class="btn btn-primary">Ver Envio</a>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Productos</h5>
                        <p class="card-text">Puede ver todos los productos que la tienda tiene en stock</p>
                        <a href="#" class="btn btn-primary">Ver productos</a>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Reportes</h5>
                        <p class="card-text">Permite visualizar todos los reportes generados por el usuario</p>
                        <a href="#" class="btn btn-primary">Realizar Devolucion</a>
                    </div>
                </div>
            </div>
            
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
