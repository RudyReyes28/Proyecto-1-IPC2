<!-- <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/ServletLoginUsuario?accion=cerrar">ToDo Web</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div> -->
<nav class="navbar bg-primary" data-bs-theme="dark">
    <div class="container">
        <h3>Bienvenido ${usuario.nombre}</h3>
            
        <nav class="navbar">
            <div class="container-fluid">
                <div class="justify-content-end">
                    <a class="navbar-brand btn btn-outline-success" href="${pageContext.request.contextPath}/ServletLoginUsuario?accion=cerrar">Cerrar Sesion</a>
                </div>
            </div>
        </nav>
    </div>
</nav>
