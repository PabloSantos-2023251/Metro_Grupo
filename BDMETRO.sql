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
    anos_experiencia int not null
);

create table impacto_trafico (
    id_impacto int primary key auto_increment,
    zona varchar(100),
    reduccion_trafico_porcentaje decimal(5,2)
);

create table horarios (
    id_horario int primary key auto_increment auto_increment,
    hora_salida time,
    hora_llegada time,
    id_tren int
);

create table mantenimiento (
    id_mantenimiento int primary key auto_increment,
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

-- Líneas
CALL sp_insert_linea('Línea 1', 'Azul', 15.5);
CALL sp_insert_linea('Línea 2', 'Rojo', 12.0);
CALL sp_insert_linea('Línea 3', 'Verde', 18.2);
CALL sp_insert_linea('Línea 4', 'Amarillo', 10.5);
CALL sp_insert_linea('Línea 5', 'Naranja', 20.0);
CALL sp_insert_linea('Línea 6', 'Morado', 14.3);
CALL sp_insert_linea('Línea 12', 'Dorado', 25.1);
CALL sp_insert_linea('Línea A', 'Celeste', 9.8);
CALL sp_insert_linea('Línea B', 'Gris', 11.2);
CALL sp_insert_linea('Línea C', 'Rosa', 13.4);

-- Trenes
CALL sp_crear_tren(1, 'Bombardier Movia', 1200, 'Operativo');
CALL sp_crear_tren(2, 'Alstom Metropolis', 1500, 'Operativo');
CALL sp_crear_tren(3, 'CAF S-6000', 1100, 'Mantenimiento');
CALL sp_crear_tren(4, 'Siemens Inspiro', 1300, 'Operativo');
CALL sp_crear_tren(5, 'Mitsubishi Corp', 1000, 'Operativo');
CALL sp_crear_tren(6, 'Bombardier Movia', 1200, 'Fuera de servicio');
CALL sp_crear_tren(7, 'Alstom Metropolis', 1500, 'Operativo');
CALL sp_crear_tren(8, 'CAF S-6000', 1100, 'Operativo');
CALL sp_crear_tren(9, 'Siemens Inspiro', 1300, 'Mantenimiento');
CALL sp_crear_tren(10, 'CRRC Nanjing', 1600, 'Operativo');

-- Estaciones (Referencian a Líneas)
CALL sp_insert_estacion('Estación Central', 'Zona 1', 1);
CALL sp_insert_estacion('El Trebol', 'Zona 11', 1);
CALL sp_insert_estacion('Plaza España', 'Zona 9', 2);
CALL sp_insert_estacion('Los Próceres', 'Zona 10', 2);
CALL sp_insert_estacion('San Juan', 'Zona 7', 3);
CALL sp_insert_estacion('Roosevelt', 'Zona 11', 3);
CALL sp_insert_estacion('Cayalá', 'Zona 16', 4);
CALL sp_insert_estacion('Vista Hermosa', 'Zona 15', 5);
CALL sp_insert_estacion('San Cristóbal', 'Zona 8 Mixco', 6);
CALL sp_insert_estacion('El Obelisco', 'Zona 13', 1);

-- Personal
CALL sp_crear_personal(101, 'Carlos García', 'Supervisor');
CALL sp_crear_personal(102, 'Ana Martínez', 'Mantenimiento');
CALL sp_crear_personal(103, 'Luis Pérez', 'Seguridad');
CALL sp_crear_personal(104, 'Marta López', 'Atención al Cliente');
CALL sp_crear_personal(105, 'Jorge Estrada', 'Técnico Eléctrico');
CALL sp_crear_personal(106, 'Sofía Reyes', 'Limpieza');
CALL sp_crear_personal(107, 'Roberto Gómez', 'Administrativo');
CALL sp_crear_personal(108, 'Elena Torres', 'Seguridad');
CALL sp_crear_personal(109, 'Miguel Méndez', 'Mantenimiento');
CALL sp_crear_personal(110, 'Lucía Fernández', 'Taquillera');

-- Conductores
CALL sp_crear_conductor(1, 'Mario Estrada', 'LIC-A-554', 10);
CALL sp_crear_conductor(2, 'Josefa Ruiz', 'LIC-A-992', 5);
CALL sp_crear_conductor(3, 'Pedro Castillo', 'LIC-A-112', 12);
CALL sp_crear_conductor(4, 'Julia Méndez', 'LIC-A-883', 3);
CALL sp_crear_conductor(5, 'Esteban Quito', 'LIC-A-441', 8);
CALL sp_crear_conductor(6, 'Claudia Ramos', 'LIC-A-229', 15);
CALL sp_crear_conductor(7, 'Fernando Gil', 'LIC-A-770', 6);
CALL sp_crear_conductor(8, 'Sandra Paniagua', 'LIC-A-661', 9);
CALL sp_crear_conductor(9, 'Ricardo Arjona', 'LIC-A-002', 20);
CALL sp_crear_conductor(10, 'Karla Oliva', 'LIC-A-338', 4);

-- Pasajeros
CALL sp_crear_pasajero(1, 'Juan Pueblo', 'Frecuente');
CALL sp_crear_pasajero(2, 'Maria Choc', 'Estudiante');
CALL sp_crear_pasajero(3, 'Estuardo Diaz', 'Adulto Mayor');
CALL sp_crear_pasajero(4, 'Kevin Gonzales', 'General');
CALL sp_crear_pasajero(5, 'Andrea Morales', 'Frecuente');
CALL sp_crear_pasajero(6, 'Ramiro Perez', 'Discapacitado');
CALL sp_crear_pasajero(7, 'Brenda Lima', 'General');
CALL sp_crear_pasajero(8, 'Hector Valle', 'Estudiante');
CALL sp_crear_pasajero(9, 'Silvia Luna', 'General');
CALL sp_crear_pasajero(10, 'Victor Jara', 'Adulto Mayor');

-- Impacto Tráfico
CALL sp_crear_impacto(1, 'Zona 1 Centro', 25.50);
CALL sp_crear_impacto(2, 'Zona 10 Viva', 15.20);
CALL sp_crear_impacto(3, 'Zona 4 Gran Centro', 30.00);
CALL sp_crear_impacto(4, 'Calzada Roosevelt', 45.10);
CALL sp_crear_impacto(5, 'Avenida Las Americas', 10.50);
CALL sp_crear_impacto(6, 'Calzada San Juan', 35.80);
CALL sp_crear_impacto(7, 'Boulevard Liberacion', 50.00);
CALL sp_crear_impacto(8, 'Ruta al Atlantico', 20.30);
CALL sp_crear_impacto(9, 'Avenida Petapa', 28.90);
CALL sp_crear_impacto(10, 'Anillo Periferico', 40.00);

-- Horarios (Referencian a Trenes)
CALL sp_crear_horario(1, '06:00:00', '06:45:00', 1);
CALL sp_crear_horario(2, '07:00:00', '07:45:00', 2);
CALL sp_crear_horario(3, '08:00:00', '08:45:00', 4);
CALL sp_crear_horario(4, '09:00:00', '09:45:00', 5);
CALL sp_crear_horario(5, '10:00:00', '10:45:00', 7);
CALL sp_crear_horario(6, '11:00:00', '11:45:00', 8);
CALL sp_crear_horario(7, '12:00:00', '12:45:00', 10);
CALL sp_crear_horario(8, '13:00:00', '13:45:00', 1);
CALL sp_crear_horario(9, '14:00:00', '14:45:00', 2);
CALL sp_crear_horario(10, '15:00:00', '15:45:00', 4);

-- Mantenimiento (Referencian a Trenes)
CALL sp_crear_mantenimiento(1, '2024-01-15', 'Cambio de frenos', 3);
CALL sp_crear_mantenimiento(2, '2024-01-20', 'Limpieza profunda', 6);
CALL sp_crear_mantenimiento(3, '2024-02-05', 'Revisión de pantógrafo', 9);
CALL sp_crear_mantenimiento(4, '2024-02-10', 'Actualización de software', 1);
CALL sp_crear_mantenimiento(5, '2024-02-15', 'Cambio de luminarias', 2);
CALL sp_crear_mantenimiento(6, '2024-03-01', 'Reparación de aire acondicionado', 4);
CALL sp_crear_mantenimiento(7, '2024-03-05', 'Engrase de ejes', 5);
CALL sp_crear_mantenimiento(8, '2024-03-10', 'Revisión de motores', 7);
CALL sp_crear_mantenimiento(9, '2024-03-15', 'Pintura exterior', 8);
CALL sp_crear_mantenimiento(10, '2024-03-20', 'Chequeo de puertas', 10);

-- Boletos (Referencian a Pasajeros)
CALL crear_boleto(5.00, '2024-05-01', 1);
CALL crear_boleto(2.50, '2024-05-01', 2);
CALL crear_boleto(0.00, '2024-05-01', 3);
CALL crear_boleto(5.00, '2024-05-01', 4);
CALL crear_boleto(4.00, '2024-05-02', 5);
CALL crear_boleto(0.00, '2024-05-02', 6);
CALL crear_boleto(5.00, '2024-05-02', 7);
CALL crear_boleto(2.50, '2024-05-02', 8);
CALL crear_boleto(5.00, '2024-05-03', 9);
CALL crear_boleto(0.00, '2024-05-03', 10);
