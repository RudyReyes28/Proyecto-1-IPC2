<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT"/>

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
                                <th>Nombre</th>
                                <th>Costo Unitario</th>
                                <th>Cantidad</th>
                                <th> Costo Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de productos -->
                            <c:forEach var="producto" items="${listado}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${producto.codigo}</td>
                                    <td>${producto.nombre}</td>
                                    <td>${producto.costoU}</td>
                                    <td>${producto.cantidad}</td>
                                    <td>${producto.costoTotal}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="col-md-3 justify-content-md-end">
                        <a href="${pageContext.request.contextPath}/ServletRecibirEnvio?accion=aceptar" class="btn btn-danger btn-block">
                            Recibir
                        </a>
                    </div>
                </div>
            </div>

        </div>
</section>
