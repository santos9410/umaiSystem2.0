	create database umaiDB;
	use umaiDB;
	##################################################3
	#Tabla usuarios 
	#Guardara los usuarios que utilizaran el sistema 
	##################################################

	create table usuarios(
		idUsu int auto_increment primary key,
		nombreUsu varchar(30) not null unique,
		pswUsu varchar(32) not null,
		calleUsu varchar(50),
		coloUsu varchar(50),
		ciudUsu varchar(50),
		codigoPostal varchar(20),
		telefonoUsu varchar(20),
		avatarUsu varchar(50),
		sueldoUsu int not null,
		puntosUsu int,			## Puntos de usuario para un uso proximo
		tipoPermiso int not null ,##Admin 0, Gerente 1, Mesero 2
		estadoContrato boolean##Activo, Baja
	);

	##Tabla que guarda el estado de las mesas, para en un futuro hacer una actualizacion 
	##e implementarlo en forma web 
	create table piso(
		idPiso int auto_increment primary key,
		idUsu int, #Aquien le pertenece la mesa ,
		mesa int not null, #Numero de mesa 
		estado boolean, #True Ocupado False: Libre
		foreign key(idUsu) references usuarios(idUsu) 
		
	);

	####3
	#TABLA para guardar Reservaciones
	create table reservaciones(
		idReser int auto_increment primary key,
		
		nombreReser varchar(20) not null,
		fechaReser  varchar(15) not null,
		horaReser   varchar(10),
		numPersonas int not null,
		anticipo int not null,
		comentarios varchar(50)
		
	);

	##Para el sistema de Clientes 
	create table clientes(
		idCli int auto_increment primary key,
		nombreCli varchar(20) not null,
		contrasenaCli varchar(20) not null,
		nombreUsuarioCli varchar(20) not null,
		telefonoCli  varchar(20),
		emailCli   varchar(50),
		avatarCli  varchar(30),
		puntosCli  int not null
		
	);




	create table ventas(
	idVenta int auto_increment primary key,
	idUsu int , 
	idCli int,
	descuento int, 
	foreign key(idUsu) references usuarios(idUsu),
	foreign key(idCli) references clientes(idCli) 

	);


	##Tabla que guarda los datos principales del proveedor de productos
	## 
	create table proveedor(
		idProv int auto_increment primary key,
		nombreProv varchar(30) not null,
        codigoProv varchar(20),
		ciudadProv varchar(50),
        coloniaProv varchar(50),
        calleProv varchar(30),
		telefonoProv  varchar(20),
		emailProv varchar(50),
        Contrato boolean##Activo, Baja
	);

	
    create table compras(
	idCompra int auto_increment,
	fechaCompra varchar(15) ,
	
	primary key(idCompra)
);

	create table detalle_compras(
    idDetalleCom int ,
    idCompra int,
    idProd int,
    CantidadProduc int,
    PrecioUnit int,
    total int,
    
	foreign key(idProd) references productos(idProd),
    foreign key(idCompra) references compras(idCompra),
	primary key(idCompra,idDetalleCom)
    );


#drop table detalle_compras;
#drop table compras;


	create table Productos(
		idProd int auto_increment primary key,
		idProv int,
		nombreProd  varchar(30) not null,
		descripcionProd varchar(150),
		unidadMedidadProd varchar(30),
		precioCompra int,
		ContratoProducto boolean , ##Activo, Baja
		foreign key (idProv) references proveedor(idProv)
	);
	#################################
	##Tabla que guardara cada receta#
	#################################
		create table receta(
			idRec int auto_increment primary key,
			nombreRec varchar(30) not null,
			tipoRec   varchar(20),
			tamañoPorcion  int, #Tamaño de la porcion en gramos
			numeroPorcion  int,#Numero de porciones que resultan
			tiempoPreparacion int,#Tiempo de preparacion en minutos
			dificultad varchar(10), ## que dificultada de preparacion Facil, Medio, Dificil
			procedimiento varchar(5000)#Que procedimiento se utilizara
			
		);
		##Guarde de la receta 
		##Esto es la lista de productos que intervienen en la preparacion de la receta
		create table detalleReceta(
			idDetalleRec int ,
			idRec int ,
			idProd int ,
			cantidadProd  int not null,
			foreign key (idProd) references Productos(idProd),
			foreign key(idRec) references receta(idRec) ,
			primary key(idRec,idDetalleRec)
		);


	##Creamos el usuario admin con todos los permisos

	insert into usuarios values(null,'admin',MD5('12345'),'conocida','conocida','conocida','conocida','conocida','conocida',0,0,0,true);
