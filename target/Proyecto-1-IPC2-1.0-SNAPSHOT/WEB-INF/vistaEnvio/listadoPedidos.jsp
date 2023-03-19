
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
                                <th>ID pedido</th>
                                <th>Usuario Tienda</th>
                                <th>Fecha</th>
                                <th> Costo Total</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de productos -->
                            <c:forEach var="pedido" items="${ListaPedidos}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${pedido.idPedido}</td>
                                    <td>${pedido.codigoUsuario}</td>
                                    <td>${pedido.fechaPedido}</td>
                                    <td>${pedido.totalPedido}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorEnvios?accion=mostrar&idPedido=${pedido.idPedido}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Ver pedido
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