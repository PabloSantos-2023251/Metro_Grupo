drop database if exists proyecto_metro_in5cm;
create database proyecto_metro_in5cm;
use proyecto_metro_in5cm;

create table lineas (
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
    id_personal int primary key auto_increment,
    nombre varchar(100),
    email varchar(150) not null unique,
    password varchar(255) not null,
    cargo varchar(50),
    rol enum('administrador', 'empleado') not null default 'empleado'
);

create table pasajeros(
    id_pasajero int primary key auto_increment,
    nombre_pasajero varchar(50),
    tipo_pasajero varchar(30),
    email varchar(150) not null unique,
    password varchar(255) not null,
    rol varchar(20) default 'cliente'
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
    id_horario int primary key auto_increment,
    hora_salida time,
    hora_llegada time,
    id_tren int,
    foreign key (id_tren) references trenes(id_tren) on delete cascade
);

create table mantenimiento (
    id_mantenimiento int primary key auto_increment,
    fecha date,
    descripcion text,
    id_tren int,
    foreign key (id_tren) references trenes(id_tren) on delete cascade
);

create table boletos(
    id_boleto int primary key auto_increment,
    precio decimal(10,2),
    fecha date,
    id_pasajero int,
    foreign key (id_pasajero) references pasajeros(id_pasajero) on delete cascade
);

create table estaciones (
    id_estacion int primary key auto_increment,
    nombre varchar(50) not null,
    zona varchar(30),
    id_linea int,
    foreign key (id_linea) references lineas(id_linea) on delete cascade
);

delimiter //

create procedure sp_crear_personal(in _nom varchar(100), in _email varchar(150), in _pass varchar(255), in _car varchar(50), in _rol varchar(20))
begin
    insert into personal (nombre, email, password, cargo, rol) values (_nom, _email, _pass, _car, _rol);
end //

create procedure sp_leer_personal(in _id int)
begin
    select * from personal where id_personal = _id;
end //

create procedure sp_actualizar_personal(in _id int, in _nom varchar(100), in _email varchar(150), in _pass varchar(255), in _car varchar(50), in _rol varchar(20))
begin
    update personal set nombre = _nom, email = _email, password = _pass, cargo = _car, rol = _rol where id_personal = _id;
end //

create procedure sp_eliminar_personal(in _id int)
begin
    delete from personal where id_personal = _id;
end //

create procedure sp_crear_pasajero(in _nombre varchar(100), in _tipo varchar(50), in _email varchar(150), in _pass varchar(255))
begin
    insert into pasajeros(nombre_pasajero, tipo_pasajero, email, password) values(_nombre, _tipo, _email, _pass);
end //

create procedure sp_leer_pasajero(in _id int)
begin
    select * from pasajeros where id_pasajero = _id;
end //

create procedure sp_actualizar_pasajero(in _id int, in _nombre varchar(100), in _tipo varchar(50), in _email varchar(150), in _pass varchar(255))
begin
    update pasajeros set nombre_pasajero = _nombre, tipo_pasajero = _tipo, email = _email, password = _pass where id_pasajero = _id;
end //

create procedure sp_eliminar_pasajero(in _id int)
begin
    delete from pasajeros where id_pasajero = _id;
end //

create procedure sp_crear_tren(in _modelo varchar(50), in _capacidad int, in _estado varchar(30))
begin
    insert into trenes(modelo, capacidad_pasajeros, estado) values(_modelo, _capacidad, _estado);
end //

create procedure sp_leer_tren(in _id int)
begin
    select * from trenes where id_tren = _id;
end //

create procedure sp_actualizar_tren(in _id int, in _modelo varchar(50), in _capacidad int, in _estado varchar(30))
begin
    update trenes set modelo = _modelo, capacidad_pasajeros = _capacidad, estado = _estado where id_tren = _id;
end //

create procedure sp_eliminar_tren(in _id int)
begin
    delete from trenes where id_tren = _id;
end //

create procedure sp_crear_conductor(in _nombre varchar(100), in _licencia varchar(50), in _exp int)
begin
    insert into conductores(nombre, licencia, anos_experiencia) values(_nombre, _licencia, _exp);
end //

create procedure sp_leer_conductor(in _id int)
begin
    select * from conductores where id_conductor = _id;
end //

create procedure sp_actualizar_conductor(in _id int, in _nombre varchar(100), in _licencia varchar(50), in _exp int)
begin
    update conductores set nombre = _nombre, licencia = _licencia, anos_experiencia = _exp where id_conductor = _id;
end //

create procedure sp_eliminar_conductor(in _id int)
begin
    delete from conductores where id_conductor = _id;
end //

create procedure sp_insert_linea(in p_nombre_linea varchar(50), in p_color varchar(30), in p_longitud_km decimal(5,2))
begin
    insert into lineas(nombre_linea, color, longitud_km) values (p_nombre_linea, p_color, p_longitud_km);
end //

create procedure sp_actualizar_linea(in p_id int, in p_nom varchar(50), in p_col varchar(30), in p_lon decimal(5,2))
begin
    update lineas set nombre_linea = p_nom, color = p_col, longitud_km = p_lon where id_linea = p_id;
end //

create procedure sp_eliminar_linea(in p_id int)
begin
    delete from lineas where id_linea = p_id;
end //

create procedure sp_insert_estacion(in p_nombre varchar(50), in p_zona varchar(30), in p_id_linea int)
begin
    insert into estaciones(nombre, zona, id_linea) values (p_nombre, p_zona, p_id_linea);
end //

create procedure sp_actualizar_estacion(in p_id int, in p_nom varchar(50), in p_zon varchar(30), in p_lin int)
begin
    update estaciones set nombre = p_nom, zona = p_zon, id_linea = p_lin where id_estacion = p_id;
end //

create procedure sp_eliminar_estacion(in p_id int)
begin
    delete from estaciones where id_estacion = p_id;
end //

create procedure crear_boleto(in p_precio decimal(10,2), in p_fecha date, in p_id_pasajero int)
begin
    insert into boletos(precio, fecha, id_pasajero) values(p_precio, p_fecha, p_id_pasajero);
end //

create procedure sp_actualizar_boleto(in p_id int, in p_pre decimal(10,2), in p_fec date, in p_pas int)
begin
    update boletos set precio = p_pre, fecha = p_fec, id_pasajero = p_pas where id_boleto = p_id;
end //

create procedure sp_eliminar_boleto(in p_id int)
begin
    delete from boletos where id_boleto = p_id;
end //

create procedure sp_crear_horario(in _salida time, in _llegada time, in _tren int)
begin
    insert into horarios (hora_salida, hora_llegada, id_tren) values (_salida, _llegada, _tren);
end //

create procedure sp_crear_mantenimiento(in _fec date, in _desc text, in _tren int)
begin
    insert into mantenimiento (fecha, descripcion, id_tren) values (_fec, _desc, _tren);
end //

create procedure sp_crear_impacto(in _zona varchar(100), in _red decimal(5,2))
begin
    insert into impacto_trafico (zona, reduccion_trafico_porcentaje) values (_zona, _red);
end //

delimiter ;

call sp_insert_linea('Linea 1', 'Azul', 15.5);
call sp_insert_linea('Linea 2', 'Rojo', 12.0);
call sp_insert_linea('Linea 3', 'Verde', 18.2);
call sp_insert_linea('Linea 4', 'Amarillo', 10.5);
call sp_insert_linea('Linea 5', 'Naranja', 20.0);
call sp_insert_linea('Linea 6', 'Morado', 14.3);
call sp_insert_linea('Linea 12', 'Dorado', 25.1);
call sp_insert_linea('Linea A', 'Celeste', 9.8);
call sp_insert_linea('Linea B', 'Gris', 11.2);
call sp_insert_linea('Linea C', 'Rosa', 13.4);

call sp_crear_tren('Bombardier Movia', 1200, 'Operativo');
call sp_crear_tren('Alstom Metropolis', 1500, 'Operativo');
call sp_crear_tren('CAF S-6000', 1100, 'Mantenimiento');
call sp_crear_tren('Siemens Inspiro', 1300, 'Operativo');
call sp_crear_tren('Mitsubishi Corp', 1000, 'Operativo');
call sp_crear_tren('Bombardier Movia', 1200, 'Fuera de Servicio');
call sp_crear_tren('Alstom Metropolis', 1500, 'Operativo');
call sp_crear_tren('CAF S-6000', 1100, 'Operativo');
call sp_crear_tren('Siemens Inspiro', 1300, 'Mantenimiento');
call sp_crear_tren('CRRC Nanjing', 1600, 'Operativo');

call sp_insert_estacion('Estacion Central', 'Zona 1', 1);
call sp_insert_estacion('El Trebol', 'Zona 11', 1);
call sp_insert_estacion('Plaza España', 'Zona 9', 2);
call sp_insert_estacion('Los Proceres', 'Zona 10', 2);
call sp_insert_estacion('San Juan', 'Zona 7', 3);
call sp_insert_estacion('Roosevelt', 'Zona 11', 3);
call sp_insert_estacion('Cayala', 'Zona 16', 4);
call sp_insert_estacion('Vista Hermosa', 'Zona 15', 5);
call sp_insert_estacion('San Cristobal', 'Zona 8 Mixco', 6);
call sp_insert_estacion('El Obelisco', 'Zona 13', 1);

call sp_crear_personal('Carlos Garcia', 'carlos@metro.com', 'admin123', 'Supervisor', 'administrador');
call sp_crear_personal('Ana Martinez', 'ana@metro.com', 'user123', 'Mantenimiento', 'empleado');
call sp_crear_personal('Luis Perez', 'luis@metro.com', '123', 'Seguridad', 'empleado');
call sp_crear_personal('Marta Lopez', 'marta@metro.com', '123', 'Atencion Cliente', 'empleado');
call sp_crear_personal('Jorge Estrada', 'jorge@metro.com', '123', 'Tecnico', 'empleado');
call sp_crear_personal('Sofia Reyes', 'sofia@metro.com', '123', 'Limpieza', 'empleado');
call sp_crear_personal('Roberto Gomez', 'roberto@metro.com', '123', 'Administrativo', 'empleado');
call sp_crear_personal('Elena Torres', 'elena@metro.com', '123', 'Seguridad', 'empleado');
call sp_crear_personal('Miguel Mendez', 'migue@metro.com', '123', 'Mantenimiento', 'empleado');
call sp_crear_personal('Lucia Fernandez', 'lucia@metro.com', '123', 'Taquillera', 'empleado');

call sp_crear_conductor('Mario Estrada', 'LIC-A-554', 10);
call sp_crear_conductor('Josefa Ruiz', 'LIC-A-992', 5);
call sp_crear_conductor('Pedro Castillo', 'LIC-A-112', 12);
call sp_crear_conductor('Julia Mendez', 'LIC-A-883', 3);
call sp_crear_conductor('Esteban Quito', 'LIC-A-441', 8);
call sp_crear_conductor('Claudia Ramos', 'LIC-A-229', 15);
call sp_crear_conductor('Fernando Gil', 'LIC-A-770', 6);
call sp_crear_conductor('Sandra Paniagua', 'LIC-A-661', 9);
call sp_crear_conductor('Ricardo Arjona', 'LIC-A-002', 20);
call sp_crear_conductor('Karla Oliva', 'LIC-A-338', 4);

call sp_crear_pasajero('Juan Pueblo', 'Frecuente', 'juan@gmail.com', 'pass123');
call sp_crear_pasajero('Maria Choc', 'Estudiante', 'maria@yahoo.com', 'pass123');
call sp_crear_pasajero('Estuardo Diaz', 'Adulto Mayor', 'estuardo@gmail.com', 'pass123');
call sp_crear_pasajero('Kevin Gonzales', 'General', 'kevin@outlook.com', 'pass123');
call sp_crear_pasajero('Andrea Morales', 'Frecuente', 'andrea@gmail.com', 'pass123');
call sp_crear_pasajero('Ramiro Perez', 'Discapacitado', 'ramiro@yahoo.com', 'pass123');
call sp_crear_pasajero('Brenda Lima', 'General', 'brenda@gmail.com', 'pass123');
call sp_crear_pasajero('Hector Valle', 'Estudiante', 'hector@outlook.com', 'pass123');
call sp_crear_pasajero('Silvia Luna', 'General', 'silvia@gmail.com', 'pass123');
call sp_crear_pasajero('Victor Jara', 'Adulto Mayor', 'victor@yahoo.com', 'pass123');

call sp_crear_impacto('Zona 1 Centro', 25.50);
call sp_crear_impacto('Zona 10 Viva', 15.20);
call sp_crear_impacto('Zona 4 Gran Centro', 30.00);
call sp_crear_impacto('Calzada Roosevelt', 45.10);
call sp_crear_impacto('Avenida Americas', 10.50);
call sp_crear_impacto('Calzada San Juan', 35.80);
call sp_crear_impacto('Boulevard Liberacion', 50.00);
call sp_crear_impacto('Ruta Atlantico', 20.30);
call sp_crear_impacto('Avenida Petapa', 28.90);
call sp_crear_impacto('Anillo Periferico', 40.00);

call sp_crear_horario('06:00:00', '06:45:00', 1);
call sp_crear_horario('07:00:00', '07:45:00', 2);
call sp_crear_horario('08:00:00', '08:45:00', 4);
call sp_crear_horario('09:00:00', '09:45:00', 5);
call sp_crear_horario('10:00:00', '10:45:00', 7);
call sp_crear_horario('11:00:00', '11:45:00', 8);
call sp_crear_horario('12:00:00', '12:45:00', 10);
call sp_crear_horario('13:00:00', '13:45:00', 1);
call sp_crear_horario('14:00:00', '14:45:00', 2);
call sp_crear_horario('15:00:00', '15:45:00', 4);

call sp_crear_mantenimiento('2024-01-15', 'Cambio frenos', 3);
call sp_crear_mantenimiento('2024-01-20', 'Limpieza profunda', 6);
call sp_crear_mantenimiento('2024-02-05', 'Revision pantografo', 9);
call sp_crear_mantenimiento('2024-02-10', 'Update software', 1);
call sp_crear_mantenimiento('2024-02-15', 'Cambio luces', 2);
call sp_crear_mantenimiento('2024-03-01', 'Reparacion AC', 4);
call sp_crear_mantenimiento('2024-03-05', 'Engrase ejes', 5);
call sp_crear_mantenimiento('2024-03-10', 'Revision motores', 7);
call sp_crear_mantenimiento('2024-03-15', 'Pintura exterior', 8);
call sp_crear_mantenimiento('2024-03-20', 'Chequeo puertas', 10);

call crear_boleto(5.00, '2024-05-01', 1);
call crear_boleto(2.50, '2024-05-01', 2);
call crear_boleto(0.00, '2024-05-01', 3);
call crear_boleto(5.00, '2024-05-01', 4);
call crear_boleto(4.00, '2024-05-02', 5);
call crear_boleto(0.00, '2024-05-02', 6);
call crear_boleto(5.00, '2024-05-02', 7);
call crear_boleto(2.50, '2024-05-02', 8);
call crear_boleto(5.00, '2024-05-03', 9);
call crear_boleto(0.00, '2024-05-03', 10);

select * from pasajeros;