/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function cargarArchivo(elemento){
    var file = elemento.files[0];
    var objHidden = document.subirArchivo.nombre;
    
    objHidden.value = file.name;
    
    //document.subirArchivo.target = "null";
    document.subirArchivo.action = "ServletDescargarArchivo";
    document.subirArchivo.submit();
    alert("Archivo cargado");
}
