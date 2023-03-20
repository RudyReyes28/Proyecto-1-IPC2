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

                    <form action="${pageContext.request.contextPath}/ServletCrearDevolucion?accion=enviar"
                          method="POST" class="was-validated">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Codigo Producto</th>
                                    <th>Cantidad Enviada</th>
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
                                        <td>
                                            <input type="hidden" name="codigoProducto" value="${producto.codigo}">
                                            ${producto.codigo}
                                        </td>
                                        <td>${producto.cantidad}</td>
                                        <td>
                                            <input type="number" name="cantidadAfectada" min="1" max="${producto.cantidad}" required>
                                        </td>
                                        <td>
                                            <select name="motivo" required>
                                                <option value="">Seleccione un motivo</option>
                                                <option value="PRODUCTO EQUIVOCADO">PRODUCTO EQUIVOCADO</option>
                                                <option value="PRODUCTO DAÑADO">PRODUCTO DAÑADO</option>
                                                <option value="PRODUCTO NO SOLICITADO">PRODUCTO NO SOLICITADO</option>
                                                <option value="SOBRANTE DE PRODUCTO">SOBRANTE DE PRODUCTO</option>
                                            </select>
                                        </td>

                                        </<td>
                                            <a href="${pageContext.request.contextPath}/ServletCrearDevolucion?accion=eliminar&codigoP=${producto.codigo}"
                                               class="btn btn-secondary">
                                                <i class="fas fa-angle-double-right"></i> Borrar
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="col-md-3 justify-content-md-end">
                            <button type="submit" class="btn btn-success btn-block">
                                Enviar Devolucion
                            </button>
                        </div>
                    </form>

                </div>
            </div>

        </div>
</section>
