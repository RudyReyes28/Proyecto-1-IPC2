
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Devoluciones</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>ID Devolucion</th>
                                <th>Usuario Tienda</th>
                                <th>Fecha</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de productos -->
                            <c:forEach var="devolucion" items="${listaDevolucion}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${devolucion.idDevolucion}</td>
                                    <td>${devolucion.codigoUsuario}</td>
                                    <td>${devolucion.fechaDevolucion}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletAceptarDevoluciones?accion=mostrar&idDevolucion=${devolucion.idDevolucion}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Ver Devolucion
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