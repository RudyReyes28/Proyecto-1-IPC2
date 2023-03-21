
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Pedidos</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>ID Devolucion</th>
                                <th>Usuario Tienda</th>
                                <th>Fecha</th>
                                <th>Total</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de productos -->
                            <c:forEach var="pedido" items="${listaPedido}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${pedido.idPedido}</td>
                                    <td>${pedido.codigoUsuario}</td>
                                    <td>${pedido.fechaPedido}</td>
                                    <td>${pedido.totalPedido}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletAceptarPedidos?accion=mostrar&idPedido=${pedido.idPedido}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Ver Pedido
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

    </div>
</section>