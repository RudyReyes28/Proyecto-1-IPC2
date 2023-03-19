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
                            <c:forEach var="producto" items="${listado}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${producto.codigo}</td>
                                    <td>${producto.nombre}</td>
                                    <td>${producto.costoU}</td>
                                    <td>${producto.cantidad}</td>
                                    <td>${producto.costoTotal}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorEnvios?accion=editar&idEnvio=${idEnvio}&codigoP=${producto.codigo}"
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
<jsp:include page="/WEB-INF/vistaEnvio/agregarProducto.jsp"/>
     