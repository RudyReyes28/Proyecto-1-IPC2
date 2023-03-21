<%-- 
    Document   : vistaUsuarioSupervisor
    Created on : 15 mar. 2023, 0:18:53
    Author     : rudy-reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Supervisor</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="/WEB-INF/barraCerrarSesion.jsp"/>

        <div class="row my-3 " >


            <div class="col-sm-4 mb-3 mb-sm-0">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Editar Pedidos</h5>
                        <p class="card-text">Puede editar los pedidos enviados de los usuarios de las tiendas supervisadas</p>
                        <a href="#" class="btn btn-primary">Realizar Envio</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4 ">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Pedidos Pendientes</h5>
                        <p class="card-text">Puede aceptar o rechazar los pedidos enviados de los usuarios de las tiendas supervisadas</p>
                        <a href="${pageContext.request.contextPath}/ServletOpcionesSupervisor?accion=pedidos" class="btn btn-primary">Ver pedidos</a>
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
