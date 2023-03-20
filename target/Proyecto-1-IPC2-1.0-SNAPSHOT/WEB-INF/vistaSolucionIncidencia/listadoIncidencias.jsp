
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Incidencias</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>ID incidencia</th>
                                <th>Usuario Tienda</th>
                                <th>Fecha</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de productos -->
                            <c:forEach var="incidencia" items="${listaIncidencia}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${incidencia.idIncidencia}</td>
                                    <td>${incidencia.codigoUsuario}</td>
                                    <td>${incidencia.fechaIncidencia}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletSolucionarIncidencias?accion=mostrar&idIncidencia=${incidencia.idIncidencia}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Ver Incidencia
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