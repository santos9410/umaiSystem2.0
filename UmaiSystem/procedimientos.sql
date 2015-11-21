
USE umaiDB
/*
Procedimiento que obtiene el ultimo id ingresado
*/
DELIMITER //
CREATE PROCEDURE ultima_receta()
BEGIN
   SELECT * FROM receta ORDER BY idRec DESC LIMIT 1;
END //
DELIMITER ;



/*
Procedimiento que obtiene Todas las recetas
*/
DELIMITER //
CREATE PROCEDURE todas_receta()
BEGIN
   SELECT * FROM receta;
END //
DELIMITER ;

/*
Procedimiento que inserta recetas
*/
DELIMITER //
CREATE PROCEDURE update_receta(idRec int,nombreRec varchar(30),tipoRec varchar(30),tamañoPorcion varchar(30),numeroPorcion varchar(30),tiempoPreparacion varchar(30),difulcutad varchar(30),margenError varchar(30),porcientoPerdida varchar(30))
BEGIN
   INSERT INTO `umaiDB`.`receta`
(`idRec`,
`nombreRec`,
`tipoRec`,
`tamañoPorcion`,
`numeroPorcion`,
`tiempoPreparacion`,
`difucultad`,
`margenError`,
`porcientoPerdida`)
VALUES
(idRec,
nombreRec,
tipoRec,
tamañoPorcion,
numeroPorcion,
tiempoPreparacion,
difucultad,
margenError,
porcientoPerdida);

END //
DELIMITER ;

/*
Procedimiento que inserta recetas
*/
DELIMITER //
CREATE PROCEDURE insertar_receta(idRec int,nombreRec varchar(30),tipoRec varchar(30),tamañoPorcion varchar(30),numeroPorcion varchar(30),tiempoPreparacion varchar(30),difulcutad varchar(30),margenError varchar(30),porcientoPerdida varchar(30))
BEGIN
   UPDATE `umaiDB`.`receta`
SET
`nombreRec` = nombreRec,
`tipoRec` = tipoRec,
`tamañoPorcion` = tamañoPorcion,
`numeroPorcion` = numeroPorcion,
`tiempoPreparacion` = tiempoPreparacion,
`difucultad` = difucultad,
`margenError` = margenError,
`porcientoPerdida` = porcientoPerdida
WHERE `idRec` = idRec;

END //
DELIMITER ;


/*
Procedimiento inserta detalle receta
*/
DELIMITER //
CREATE PROCEDURE inserta_detalleReceta(idRec int,idProd int,cantidadProd int)
BEGIN
   INSERT INTO `umaiDB`.`detalleReceta`
(`idRec`,
`idProd`,
`cantidadProd`)
VALUES
(idRec,
idProd,
cantidadProd);
END //
DELIMITER ;

/*
Procedimiento obtiene el detalle de una receta por id
*/
DELIMITER //
CREATE PROCEDURE obtiene_detalleReceta(idRe int)
BEGIN
	select * from detalleReceta where idRec = idRe;
END //
DELIMITER ;


