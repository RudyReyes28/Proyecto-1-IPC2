<%-- 
    Document   : editarProducto
    Created on : 18 mar. 2023, 16:05:00
    Author     : rudy-reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    </head>
    <body>
        
        <jsp:include page="/WEB-INF/vistaEnvio/cabecero.jsp"/>

        <form action="${pageContext.request.contextPath}/ServletControladorEnvios?accion=modificar"
              method="POST" class="was-validated">

            <!--Botones de Navegacion -->
           <jsp:include page="/WEB-INF/vistaEnvio/botonesNavegacionEdicion.jsp"/>

            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Producto</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="idPedido">ID Envio</label>
                                        <input type="number" class="form-control" name="idEnvio" value="${productoP.idEnvio}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="codigoP">Codigo producto</label>
                                        <input type="number" class="form-control" name="codigoP" value="${productoP.codigo}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="costoU">Costo Unitario</label>
                                        <input type="number" class="form-control" name="costoU" value="${productoP.costoU}" step="any" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="cantidad">Cantidad</label>
                                        <input type="number" class="form-control" name="cantidad" value="${productoP.cantidad}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="costoT">Costo Total</label>
                                        <input type="number" class="form-control" name="costoT" value="${productoP.costoTotal}" step="any" readonly>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="nombreP">Nombre</label>
                                        <input type="text" class="form-control" name="nombreP" value="${productoP.nombre}" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section> 

        </form>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

    </body>
</html>
