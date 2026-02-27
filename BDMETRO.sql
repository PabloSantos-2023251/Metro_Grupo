drop database if exists proyecto_metro_in5cm;
create database proyecto_metro_in5cm;
use proyecto_metro_in5cm;

create table Lineas (
    id_linea int primary key auto_increment,
    nombre_linea varchar(50) not null,
    color varchar(30),
    longitud_km decimal(5,2)
);
create table trenes (
    id_tren int auto_increment primary key,
    modelo varchar(50) not null,
    capacidad_pasajeros int not null,
    estado varchar(30) not null
);

create table personal (
    id_personal int primary key,
    nombre varchar(100),
    cargo varchar(50)
);

create table Pasajeros(
    id_pasajero Int Primary Key Auto_increment,
    nombre_pasajero Varchar(50),
    tipo_pasajero Varchar(30)
);
create table conductores (
    id_conductor int auto_increment primary key,
    nombre varchar(100) not null,
    licencia varchar(50) not null,
    anios_experiencia int not null
);

create table impacto_trafico (
    id_impacto int primary key auto_increment,
    zona varchar(100),
    reduccion_trafico_porcentaje decimal(5,2)
);

create table horarios (
    id_horario int primary key,
    hora_salida time,
    hora_llegada time,
    id_tren int
);

create table mantenimiento (
    id_mantenimiento int primary key,
    fecha date,
    descripcion text,
    id_tren int
);

create table Boletos(
    id_boleto Int Primary Key Auto_increment,
    precio Decimal(10,2),
    fecha Date,
    id_pasajero int,
    Foreign Key (id_pasajero)
    References Pasajeros(id_pasajero)
);

create table Estaciones (
    id_estacion int primary key auto_increment,
    nombre varchar(50) not null,
    zona varchar(30),
    id_linea int,
    foreign key (id_linea) references Lineas(id_linea)
);

DELIMITER //

create procedure sp_crear_personal(in _id int, in _nom varchar(100), in _car varchar(50))
begin
    insert into personal (id_personal, nombre, cargo) values (_id, _nom, _car);
end //

create procedure sp_leer_personal(in _id int)
begin
    select * from personal where id_personal = _id;
end //

create procedure sp_actualizar_personal(in _id int, in _nom varchar(100), in _car varchar(50))
begin
    update personal set nombre = _nom, cargo = _car where id_personal = _id;
end //

create procedure sp_eliminar_personal(in _id int)
begin
    delete from personal where id_personal = _id;
end //

create procedure sp_crear_pasajero(in _id int, in _nombre varchar(100), in _tipo varchar(50))
begin
    insert into Pasajeros(id_pasajero, nombre_pasajero, tipo_pasajero) values(_id, _nombre, _tipo);
end //

create procedure sp_leer_pasajero(in _id int)
begin
    select * from Pasajeros where id_pasajero = _id;
end //

create procedure sp_actualizar_pasajero(in _id int, in _nombre varchar(100), in _tipo varchar(50))
begin
    update Pasajeros set nombre_pasajero = _nombre, tipo_pasajero = _tipo where id_pasajero = _id;
end //

create procedure eliminar_pasajero(in p_id_pasajero int)
begin
    delete from pasajeros where id_pasajero = p_id_pasajero;
end //

create procedure sp_crear_impacto(in _id int, in _zona varchar(100), in _red decimal(5,2))
begin
    insert into impacto_trafico (id_impacto, zona, reduccion_trafico_porcentaje) values (_id, _zona, _red);
end //

create procedure sp_leer_impacto(in _id int)
begin
    select * from impacto_trafico where id_impacto = _id;
end //

create procedure sp_crear_horario(in _id int, in _salida time, in _llegada time, in _tren int)
begin
    insert into horarios (id_horario, hora_salida, hora_llegada, id_tren) values (_id, _salida, _llegada, _tren);
end //

create procedure sp_leer_horario(in _id int)
begin
    select * from horarios where id_horario = _id;
end //

create procedure sp_actualizar_horario(in _id int, in _salida time, in _llegada time, in _tren int)
begin
    update horarios set hora_salida = _salida, hora_llegada = _llegada, id_tren = _tren where id_horario = _id;
end //

create procedure sp_eliminar_horario(in _id int)
begin
    delete from horarios where id_horario = _id;
end //

create procedure sp_crear_mantenimiento(in _id int, in _fec date, in _desc text, in _tren int)
begin
    insert into mantenimiento (id_mantenimiento, fecha, descripcion, id_tren) values (_id, _fec, _desc, _tren);
end //

create procedure sp_leer_mantenimiento(in _id int)
begin
    select * from mantenimiento where id_mantenimiento = _id;
end //

create procedure sp_actualizar_mantenimiento(in _id int, in _fec date, in _desc text, in _tren int)
begin
    update mantenimiento set fecha = _fec, descripcion = _desc, id_tren = _tren where id_mantenimiento = _id;
end //

create procedure sp_eliminar_mantenimiento(in _id int)
begin
    delete from mantenimiento where id_mantenimiento = _id;
end //


create procedure crear_boleto(in p_precio decimal(10,2), in p_fecha date, in p_id_pasajero int)
begin
	insert into boletos(precio, fecha, id_pasajero) values(p_precio, p_fecha, p_id_pasajero);
end //

create procedure leer_boletos()
begin
    select *
    from boletos 
    inner join pasajeros 
    on id_pasajero = p.id_pasajero;
end //

create procedure actualizar_boleto( in p_id_boleto int,in p_precio decimal(10,2), in p_fecha date, in p_id_pasajero int
)
begin
    update boletos
    set precio = p_precio, fecha = p_fecha,id_pasajero = p_id_pasajero
    where id_boleto = p_id_boleto;
end //

create procedure eliminar_boleto(in p_id_boleto int)
begin
    delete from boletos
    where id_boleto = p_id_boleto;
end //


DELIMITER ;



delimiter //

create procedure sp_crear_tren(
    in _id int,
    in _modelo varchar(50),
    in _capacidad int,
    in _estado varchar(30)
)
begin
    insert into trenes(id_tren, modelo, capacidad_pasajeros, estado)
    values(_id, _modelo, _capacidad, _estado);
end //

create procedure sp_leer_tren(in _id int)
begin
    select * from trenes where id_tren = _id;
end //

create procedure sp_actualizar_tren(
    in _id int,
    in _modelo varchar(50),
    in _capacidad int,
    in _estado varchar(30)
)
begin
    update trenes
    set modelo = _modelo,
        capacidad_pasajeros = _capacidad,
        estado = _estado
    where id_tren = _id;
end //

create procedure sp_eliminar_tren(in _id int)
begin
    delete from trenes where id_tren = _id;
end //

delimiter ;


delimiter //

create procedure sp_crear_conductor(
    in _id int,
    in _nombre varchar(100),
    in _licencia varchar(50),
    in _exp int
)
begin
    insert into conductores(id_conductor, nombre, licencia, anos_experiencia)
    values(_id, _nombre, _licencia, _exp);
end //

create procedure sp_leer_conductor(in _id int)
begin
    select * from conductores where id_conductor = _id;
end //

create procedure sp_actualizar_conductor(
    in _id int,
    in _nombre varchar(100),
    in _licencia varchar(50),
    in _exp int
)
begin
    update conductores
    set nombre = _nombre,
        licencia = _licencia,
        anios_experiencia = _exp
    where id_conductor = _id;
end //

create procedure sp_eliminar_conductor(in _id int)
begin
    delete from conductores where id_conductor = _id;
end //

delimiter ;

-- Procedimientos de almacenado es Lineas y estaciones 
-- Lineas
delimiter $$

create procedure sp_insert_linea(
    in p_nombre_linea varchar(50),
    in p_color varchar(30),
    in p_longitud_km decimal(5,2)
)
begin
    insert into lineas(nombre_linea, color, longitud_km)
    values (p_nombre_linea, p_color, p_longitud_km);
end $$

create procedure sp_listar_lineas()
begin
    select * from lineas;
end $$

create procedure sp_obtener_linea(
    in p_id_linea int
)
begin
    select *
    from lineas
    where id_linea = p_id_linea;
end $$

create procedure sp_actualizar_linea(
    in p_id_linea int,
    in p_nombre_linea varchar(50),
    in p_color varchar(30),
    in p_longitud_km decimal(5,2)
)
begin
    update lineas
    set nombre_linea = p_nombre_linea,
        color = p_color,
        longitud_km = p_longitud_km
    where id_linea = p_id_linea;
end $$

create procedure sp_eliminar_linea(
    in p_id_linea int
)
begin
    delete from lineas
    where id_linea = p_id_linea;
end $$

delimiter ;
-- Estaciones

delimiter $$

create procedure sp_insert_estacion(
    in p_nombre varchar(50),
    in p_zona varchar(30),
    in p_id_linea int
)
begin
    insert into estaciones(nombre, zona, id_linea)
    values (p_nombre, p_zona, p_id_linea);
end $$

create procedure sp_listar_estaciones()
begin
    select * from estaciones;
end $$

create procedure sp_obtener_estacion(
    in p_id_estacion int
)
begin
    select *
    from estaciones
    where id_estacion = p_id_estacion;
end $$

create procedure sp_actualizar_estacion(
    in p_id_estacion int,
    in p_nombre varchar(50),
    in p_zona varchar(30),
    in p_id_linea int
)
begin
    update estaciones
    set nombre = p_nombre,
        zona = p_zona,
        id_linea = p_id_linea
    where id_estacion = p_id_estacion;
end $$

create procedure sp_eliminar_estacion(
    in p_id_estacion int
)
begin
    delete from estaciones
    where id_estacion = p_id_estacion;
end $$

delimiter ;