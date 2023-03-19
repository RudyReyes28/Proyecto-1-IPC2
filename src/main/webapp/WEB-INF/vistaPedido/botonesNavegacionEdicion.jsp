<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a href="moduloTienda/pedido.jsp" class="btn btn-ligth btn-block">
                    Regresar al Pedido
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block">
                     Guardar Producto
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/ServletControladorPedido?accion=eliminar&idPedido=${productoP.idPedido}&codigoP=${productoP.codigo}"
                   class="btn btn-danger btn-block">
                     Eliminar Producto
                </a>
            </div>
        </div>
    </div>
</section>