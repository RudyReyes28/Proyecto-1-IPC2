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
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de productos -->
                            <c:forEach var="pedido" items="${listadoP}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${pedido.codigo}</td>
                                    <td>${pedido.nombre}</td>
                                    <td>${pedido.costoU}</td>
                                    <td>${pedido.cantidad}</td>
                                    <td>${pedido.costoTotal}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Editar
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
<!-- Agregar producto MODAL -->
<jsp:include page="/WEB-INF/vistaPedido/agregarProducto.jsp"/>
     