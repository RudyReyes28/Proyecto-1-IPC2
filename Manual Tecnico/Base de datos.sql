
CREATE DATABASE tienda_conveniencia;
USE tienda_conveniencia;
CREATE TABLE `tienda_conveniencia`.`usuario_bodega` (
  `codigo_usuario` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo_usuario`));
  
CREATE TABLE `tienda_conveniencia`.`catalogo_bodega` (
  `codigo_producto` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `costo` DECIMAL(6,2) NOT NULL,
  `precio` DECIMAL(6,2) NOT NULL,
  `existencias` INT NOT NULL,
  PRIMARY KEY (`codigo_producto`));
  
CREATE TABLE `tienda_conveniencia`.`union_ub` (
  `idunion_ub` INT NOT NULL AUTO_INCREMENT,
  `codigo_usuario` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  PRIMARY KEY (`idunion_ub`),
  INDEX `fk_codigo_usuario_idx` (`codigo_usuario` ASC) VISIBLE,
  INDEX `fk_codigo_producto_idx` (`codigo_producto` ASC) VISIBLE,
  CONSTRAINT `fk_codigo_usuario`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `tienda_conveniencia`.`usuario_bodega` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_codigo_producto`
    FOREIGN KEY (`codigo_producto`)
    REFERENCES `tienda_conveniencia`.`catalogo_bodega` (`codigo_producto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`tienda` (
  `codigo` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `tipo_tienda` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`));    
 

CREATE TABLE `tienda_conveniencia`.`catalogo_tienda` (
  `idcatalogo` INT NOT NULL AUTO_INCREMENT,
  `codigo_tienda` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  `existencias` INT NOT NULL,
  PRIMARY KEY (`idcatalogo`),
  INDEX `catalogo_tienda_fk_idx` (`codigo_tienda` ASC) VISIBLE,
  INDEX `catalogo_tienda_bodega_fk_idx` (`codigo_producto` ASC) VISIBLE,
  CONSTRAINT `catalogo_tienda_fk`
    FOREIGN KEY (`codigo_tienda`)
    REFERENCES `tienda_conveniencia`.`tienda` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `catalogo_tienda_bodega_fk`
    FOREIGN KEY (`codigo_producto`)
    REFERENCES `tienda_conveniencia`.`catalogo_bodega` (`codigo_producto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`conexion_tienda_bodega` (
  `idConexion` INT NOT NULL AUTO_INCREMENT,
  `codigo_tienda` INT NOT NULL,
  `codigo_bodega` INT NOT NULL,
  PRIMARY KEY (`idConexion`),
  INDEX `conexion_tienda_fk_idx` (`codigo_tienda` ASC) VISIBLE,
  INDEX `conexion_bodega_fk_idx` (`codigo_bodega` ASC) VISIBLE,
  CONSTRAINT `conexion_tienda_fk`
    FOREIGN KEY (`codigo_tienda`)
    REFERENCES `tienda_conveniencia`.`tienda` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `conexion_bodega_fk`
    FOREIGN KEY (`codigo_bodega`)
    REFERENCES `tienda_conveniencia`.`usuario_bodega` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`usuario_tienda` (
  `codigo_usuario` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `codigo_tienda` INT NOT NULL,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo_usuario`),
  INDEX `usuario_tienda_1_idx` (`codigo_tienda` ASC) VISIBLE,
  CONSTRAINT `usuario_tienda_1`
    FOREIGN KEY (`codigo_tienda`)
    REFERENCES `tienda_conveniencia`.`tienda` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`pedido` (
  `idpedido` INT NOT NULL AUTO_INCREMENT,
  `codigo_tienda` INT NOT NULL,
  `codigo_usuario` INT NOT NULL,
  `fecha_pedido` DATE NOT NULL,
  `total_pedido` DECIMAL(6,2) NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpedido`),
  INDEX `pedido_tienda_fk_idx` (`codigo_tienda` ASC) VISIBLE,
  INDEX `pedido_usuario_fk_idx` (`codigo_usuario` ASC) VISIBLE,
  CONSTRAINT `pedido_tienda_fk`
    FOREIGN KEY (`codigo_tienda`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_tienda`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pedido_usuario_fk`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE); 
 
CREATE TABLE `tienda_conveniencia`.`productos_pedidos` (
  `idproductos_pedidos` INT NOT NULL AUTO_INCREMENT,
  `idpedido` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  `costo_unitario` DECIMAL(6,2) NOT NULL,
  `cantidad` INT NOT NULL,
  `costoTotal` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`idproductos_pedidos`),
  INDEX `productos_pedidos_fk_idx` (`idpedido` ASC) VISIBLE,
  CONSTRAINT `productos_pedidos_fk`
    FOREIGN KEY (`idpedido`)
    REFERENCES `tienda_conveniencia`.`pedido` (`idpedido`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`incidencia` (
  `idincidencia` INT NOT NULL AUTO_INCREMENT,
  `idenvio` INT NULL,
  `codigo_tienda` INT NOT NULL,
  `codigo_usuario` INT NOT NULL,
  `fecha_incidencia` DATE NOT NULL,
  `solucion` VARCHAR(200) NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idincidencia`),
  INDEX `incidencia_tienda_fk_idx` (`codigo_tienda` ASC) VISIBLE,
  INDEX `incidencia_usuario_fk_idx` (`codigo_usuario` ASC) VISIBLE,
  CONSTRAINT `incidencia_tienda_fk`
    FOREIGN KEY (`codigo_tienda`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_tienda`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `incidencia_usuario_fk`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE); 

CREATE TABLE `tienda_conveniencia`.`productos_incidencias` (
  `idproductos_incidencias` INT NOT NULL AUTO_INCREMENT,
  `idincidencia` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  `cantidad_afectada` INT NOT NULL,
  `motivo_incidencia` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idproductos_incidencias`),
  INDEX `productos_incidencias_fk_idx` (`idincidencia` ASC) VISIBLE,
  CONSTRAINT `productos_incidencias_fk`
    FOREIGN KEY (`idincidencia`)
    REFERENCES `tienda_conveniencia`.`incidencia` (`idincidencia`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`devolucion` (
  `iddevolucion` INT NOT NULL AUTO_INCREMENT,
  `idenvio` INT NULL,
  `codigo_tienda` INT NOT NULL,
  `codigo_usuario` INT NOT NULL,
  `fecha_devolucion` DATE NOT NULL,
  `total` DECIMAL(6,2) NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iddevolucion`),
  INDEX `devolucion_tienda_fk_idx` (`codigo_tienda` ASC) VISIBLE,
  INDEX `devolucion_usuario_fk_idx` (`codigo_usuario` ASC) VISIBLE,
  CONSTRAINT `devolucion_tienda_fk`
    FOREIGN KEY (`codigo_tienda`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_tienda`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `devolucion_usuario_fk`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`productos_devueltos` (
  `idproductos_devueltos` INT NOT NULL AUTO_INCREMENT,
  `iddevolucion` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  `costo_unitario` DECIMAL(6,2) NOT NULL,
  `cantidad` INT NOT NULL,
  `costo_total` DECIMAL(6,2) NOT NULL,
  `motivo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idproductos_devueltos`),
  INDEX `productos_devueltos_fk_idx` (`iddevolucion` ASC) VISIBLE,
  CONSTRAINT `productos_devueltos_fk`
    FOREIGN KEY (`iddevolucion`)
    REFERENCES `tienda_conveniencia`.`devolucion` (`iddevolucion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`envios` (
  `idenvios` INT NOT NULL AUTO_INCREMENT,
  `idpedido` INT NULL,
  `codigo_tienda` INT NOT NULL,
  `codigo_usuario` INT NOT NULL,
  `fecha_salida` DATE NOT NULL,
  `fecha_recibida` DATE NULL,
  `total_envio` DECIMAL(6,2) NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idenvios`),
  INDEX `envios_fk_idx` (`codigo_usuario` ASC) VISIBLE,
  CONSTRAINT `envios_fk`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `tienda_conveniencia`.`usuario_bodega` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`productos_enviados` (
  `idproductos_enviados` INT NOT NULL AUTO_INCREMENT,
  `idenvio` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  `costo_unitario` DECIMAL(6,2) NOT NULL,
  `cantidad` INT NOT NULL,
  `costo_total` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`idproductos_enviados`),
  INDEX `productos_enviados_fk_idx` (`idenvio` ASC) VISIBLE,
  CONSTRAINT `productos_enviados_fk`
    FOREIGN KEY (`idenvio`)
    REFERENCES `tienda_conveniencia`.`envios` (`idenvios`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`supervisor_tienda` (
  `codigo_supervisor` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo_supervisor`));

CREATE TABLE `tienda_conveniencia`.`supervisar_tiendas` (
  `idsupervisar_tiendas` INT NOT NULL AUTO_INCREMENT,
  `codigo_supervisor` INT NOT NULL,
  `codigo_usuario` INT NOT NULL,
  PRIMARY KEY (`idsupervisar_tiendas`),
  INDEX `supervisar_tiendas_fk_idx` (`codigo_usuario` ASC) VISIBLE,
  INDEX `supervisar_tiendas_1_idx` (`codigo_supervisor` ASC) VISIBLE,
  CONSTRAINT `supervisar_tienda_fk`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `supervisar_tiendas_1`
    FOREIGN KEY (`codigo_supervisor`)
    REFERENCES `tienda_conveniencia`.`supervisor_tienda` (`codigo_supervisor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`administrador` (
  `codigo_admin` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`codigo_admin`));

CREATE TABLE `tienda_conveniencia`.`admin_bodega` (
  `idadmin_bodega` INT NOT NULL AUTO_INCREMENT,
  `codigo_admin` INT NOT NULL,
  `codigo_bodega` INT NOT NULL,
  PRIMARY KEY (`idadmin_bodega`),
  INDEX `admin_fk_idx` (`codigo_admin` ASC) VISIBLE,
  INDEX `admin_bodega_fk_idx` (`codigo_bodega` ASC) VISIBLE,
  CONSTRAINT `admin_fk`
    FOREIGN KEY (`codigo_admin`)
    REFERENCES `tienda_conveniencia`.`administrador` (`codigo_admin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `admin_bodega_fk`
    FOREIGN KEY (`codigo_bodega`)
    REFERENCES `tienda_conveniencia`.`usuario_bodega` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`admin_tienda` (
  `idadmin_tienda` INT NOT NULL AUTO_INCREMENT,
  `codigo_admin` INT NOT NULL,
  `codigo_usuarioT` INT NOT NULL,
  PRIMARY KEY (`idadmin_tienda`),
  INDEX `admin_fk_idx` (`codigo_admin` ASC) VISIBLE,
  INDEX `admin_tienda_fk_idx` (`codigo_usuarioT` ASC) VISIBLE,
  CONSTRAINT `usuario_admin_fk`
    FOREIGN KEY (`codigo_admin`)
    REFERENCES `tienda_conveniencia`.`administrador` (`codigo_admin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `admin_tienda_fk`
    FOREIGN KEY (`codigo_usuarioT`)
    REFERENCES `tienda_conveniencia`.`usuario_tienda` (`codigo_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`admin_supervisor` (
  `idadmin_supervisor` INT NOT NULL AUTO_INCREMENT,
  `codigo_admin` INT NOT NULL,
  `codigo_supervisor` INT NOT NULL,
  PRIMARY KEY (`idadmin_supervisor`),
  INDEX `admin_usuario_fk_idx` (`codigo_admin` ASC) VISIBLE,
  INDEX `admin_supervisor_fk_idx` (`codigo_supervisor` ASC) VISIBLE,
  CONSTRAINT `admin_usuarioA_fk`
    FOREIGN KEY (`codigo_admin`)
    REFERENCES `tienda_conveniencia`.`administrador` (`codigo_admin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `admin_supervisor_fk`
    FOREIGN KEY (`codigo_supervisor`)
    REFERENCES `tienda_conveniencia`.`supervisor_tienda` (`codigo_supervisor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `tienda_conveniencia`.`productos_dañados` (
  `idproductos_dañados` INT NOT NULL AUTO_INCREMENT,
  `idDevolucion` INT NOT NULL,
  `codigo_producto` INT NOT NULL,
  `cantidad_afectada` INT NOT NULL,
  `costo_unitario` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`idproductos_dañados`),
  INDEX `fk_productos_dañados_idx` (`idDevolucion` ASC) VISIBLE,
  CONSTRAINT `fk_productos_dañados`
    FOREIGN KEY (`idDevolucion`)
    REFERENCES `tienda_conveniencia`.`productos_devueltos` (`iddevolucion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

