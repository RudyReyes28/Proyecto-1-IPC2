<%-- 
    Document   : index
    Created on : 13 mar. 2023, 18:47:23
    Author     : rudy-reyes
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ingresar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script type="text/javascript" src="js/ObtenerArchivo.js"></script>
    </head>
    <body>

        <form name="subirArchivo" method="post" enctype="multipart/form-data">
            <div class="container my-3">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        Cargar Datos
                    </button>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Subir el documento jsp</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <label>Subir el archivo:</label>
                            <input type="file" onchange="cargarArchivo(this)" name="archivo">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <input type="submit" name="proceso" class="btn btn-primary" value="Cargar datos"/>
                            <input type="hidden" name="nombre" value=""/>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <iframe name="null" style="display:none;"></iframe>

        <form action="ServletLoginUsuario?accion=verificar" method="post">
            <div class="container my-5">
                <div class="row justify-content-center  p-5">
                    <div class="text-center mb-8 col-xl-4 border border-primary rounded" >
                        <h2>Ingresar usuario</h2> 

                        <div class="mb-3 my-4">
                            <label for="ingresarUsuario" class="form-label">Usuario</label>
                            <input type="text" name="usuario" class="form-control" id="ingresarUsuario">

                        </div>
                        <div class="mb-3">
                            <label for="ingresarContraseña" class="form-label">Contraseña</label>
                            <input type="password" name="contraseña" class="form-control" id="exampleInputPassword1">
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <c:if test="${!empty(error)}">
            <div class="alert alert-warning alert-dismissible fade show container justify-content-center " role="alert">
                <strong>${error}</strong> Compruebe si el usuario y la contraseña sean correctos.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </body>
</html>
