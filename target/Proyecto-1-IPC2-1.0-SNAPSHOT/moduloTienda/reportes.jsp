<%-- 
    Document   : reportes
    Created on : 21 mar. 2023, 10:16:07
    Author     : rudy-reyes
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1></i>Reportes del usuario de tienda</h1>
                    </div>
                </div>
            </div>
        </header>

        <div class="row my-3 " >


            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Reporte 1</h5>
                        <p class="card-text">Reporte de productos de tienda con existencias menores a x cantidad</p>
                        <a href="${pageContext.request.contextPath}/ServletTiendaReportes?accion=reporte1" class="btn btn-primary">Ver reporte 1</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Reporte 2</h5>
                        <p class="card-text">Reporte de pedidos realizados en un intervalo de tiempo por estado</p>
                        <a href="${pageContext.request.contextPath}/ServletTiendaReportes?accion=reporte2" class="btn btn-primary">Ver reporte 2</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Reporte 3</h5>
                        <p class="card-text"> Reporte de envíos recibidos en un intervalo de tiempo (indicando la cantidad de incidencias y
                            devoluciones activas)</p>
                        <a href="${pageContext.request.contextPath}/ServletTiendaReportes?accion=reporte3" class="btn btn-primary">Ver reporte 3</a>
                    </div>
                </div>
            </div>

        </div>
        
        <div class="col-md-3 justify-content-md-end">
            <a href="${pageContext.request.contextPath}/vistaUsuarioTienda.jsp" class="btn btn-danger btn-block">
                Regresar
            </a>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
