<%-- 
    Document   : reporte1
    Created on : 21 mar. 2023, 10:50:27
    Author     : rudy-reyes
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte 1</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>

        <form action="${pageContext.request.contextPath}/ServletTiendaReportes?accion=verReporte1"
              method="POST">


            <div class="row ">
                <div class="form-group">
                    <label for="cantidad">Existencias</label>
                    <input type="number" class="form-control" name="existencias" required>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Productos</button>
                </div>   
            </div>

        </form>

        <section id="clientes">
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <div class="card">
                            <div class="card-header">
                                <h4>Listado de Productos</h4>
                            </div>
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>#</th>
                                        <th>Codigo Producto</th>
                                        <th>Existencias</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de productos -->
                                    <c:forEach var="producto" items="${listado}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${producto.codigo}</td>
                                            <td>${producto.existencias}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>

                </div>
        </section>

        <div class="col-md-3 justify-content-md-end">
            <a href="${pageContext.request.contextPath}/ServletTiendaReportes?accion=regresar" class="btn btn-danger btn-block">
                Regresar
            </a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
