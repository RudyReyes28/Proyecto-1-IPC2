<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h1></i>Productos de la incidencia</h1>
                    </div>
                </div>
            </div>
        </header>
        <section id="clientes">
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <div class="card">
                            <div class="card-header">
                                <h4>Listado de Productos</h4>
                            </div>

                            <form action="${pageContext.request.contextPath}/ServletSolucionarIncidencias?accion=enviar"
                                  method="POST" class="was-validated">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>#</th>
                                            <th>Codigo Producto</th>
                                            <th>Cantidad Afectada</th>
                                            <th>Motivo</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de productos -->
                                        <c:forEach var="producto" items="${listado}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${producto.codigo}</td>
                                                <td>${producto.cantidad}</td>
                                                <td>${producto.motivo}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="form-floating">
                                    <textarea class="form-control" name="solucion" placeholder="Comentar solucion" id="floatingTextarea"></textarea>
                                    <label for="floatingTextarea">Escribir solucion</label>
                                </div>
                                <div class="col-md-3 justify-content-md-end">
                                    <button type="submit" class="btn btn-success btn-block">
                                        Enviar Incidencia
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
