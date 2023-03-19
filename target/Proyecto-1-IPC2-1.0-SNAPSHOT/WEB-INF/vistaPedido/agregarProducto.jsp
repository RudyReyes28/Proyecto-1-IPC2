<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="agregarProductoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Producto</h5> 
                <button class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControladorPedido?accion=insertar"
                  method="POST" class="was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="idPedio">ID Pedido</label>
                        <input type="number" class="form-control" name="idPedido" value="${idPedido}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="codigoP">Codigo Producto</label>
                        <select name="idProducto" class="form-select" aria-label="Default select example">
                            <c:forEach var="catalogo" items="${catalogo}">
                                <option value="${catalogo}">${catalogo}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="cantidad">Cantidad</label>
                        <input type="number" class="form-control" name="cantidad" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar Producto</button>
                </div>    
            </form>
        </div>
    </div>
</div>
