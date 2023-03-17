<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div  class="col-md-3">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#agregarProductoModal">
                        Agregar Producto
                    </button>
            </div>
        </div>
    </div>
</section>

<!--<div class="modal fade" id="agregarProductoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Producto</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControladorPedido?accion=insertar"
                  method="POST" class="was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">ID Pedido</label>
                        <input type="number" class="form-control" name="idPedido" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Codigo Producto</label>
                        <input type="number" class="form-control" name="codigoProducto" required>
                    </div>
                    <div class="form-group">
                        <label for="telefono">Cantidad</label>
                        <input type="number" class="form-control" name="cantidad" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar Pedido</button>
                </div>    
            </form>
        </div>
    </div>
</div>-->