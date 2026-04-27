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
    id_personal int primary key,
    nombre varchar(100),
    email varchar(150) not null unique,
    password varchar(255) not null,
    cargo varchar(50),
    rol enum('administrador', 'empleado', 'cliente') not null default 'empleado'
);

create table pasajeros(
    id_pasajero int primary key auto_increment,
    nombre_pasajero varchar(50),
    tipo_pasajero varchar(30)
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
    foreign key (id_tren) references trenes(id_tren)
);

create table mantenimiento (
    id_mantenimiento int primary key auto_increment,
    fecha date,
    descripcion text,
    id_tren int,
    foreign key (id_tren) references trenes(id_tren)
);

create table boletos(
    id_boleto int primary key auto_increment,
    precio decimal(10,2),
    fecha date,
    id_pasajero int,
    foreign key (id_pasajero) references pasajeros(id_pasajero)
);

create table estaciones (
    id_estacion int primary key auto_increment,
    nombre varchar(50) not null,
    zona varchar(30),
    id_linea int,
    foreign key (id_linea) references lineas(id_linea)
);

delimiter //

create procedure sp_crear_personal(in _id int, in _nom varchar(100), in _email varchar(150), in _pass varchar(255), in _car varchar(50), in _rol varchar(20))
begin
    insert into personal (id_personal, nombre, email, password, cargo, rol) values (_id, _nom, _email, _pass, _car, _rol);
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

create procedure sp_crear_pasajero(in _id int, in _nombre varchar(100), in _tipo varchar(50))
begin
    insert into pasajeros(id_pasajero, nombre_pasajero, tipo_pasajero) values(_id, _nombre, _tipo);
end //

create procedure sp_leer_pasajero(in _id int)
begin
    select * from pasajeros where id_pasajero = _id;
end //

create procedure sp_actualizar_pasajero(in _id int, in _nombre varchar(100), in _tipo varchar(50))
begin
    update pasajeros set nombre_pasajero = _nombre, tipo_pasajero = _tipo where id_pasajero = _id;
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
    select * from boletos inner join pasajeros on boletos.id_pasajero = pasajeros.id_pasajero;
end //

create procedure actualizar_boleto(in p_id_boleto int, in p_precio decimal(10,2), in p_fecha date, in p_id_pasajero int)
begin
    update boletos set precio = p_precio, fecha = p_fecha, id_pasajero = p_id_pasajero where id_boleto = p_id_boleto;
end //

create procedure eliminar_boleto(in p_id_boleto int)
begin
    delete from boletos where id_boleto = p_id_boleto;
end //

create procedure sp_crear_tren(in _id int, in _modelo varchar(50), in _capacidad int, in _estado varchar(30))
begin
    insert into trenes(id_tren, modelo, capacidad_pasajeros, estado) values(_id, _modelo, _capacidad, _estado);
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

create procedure sp_crear_conductor(in _id int, in _nombre varchar(100), in _licencia varchar(50), in _exp int)
begin
    insert into conductores(id_conductor, nombre, licencia, anos_experiencia) values(_id, _nombre, _licencia, _exp);
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

create procedure sp_listar_lineas()
begin
    select * from lineas;
end //

create procedure sp_obtener_linea(in p_id_linea int)
begin
    select * from lineas where id_linea = p_id_linea;
end //

create procedure sp_actualizar_linea(in p_id_linea int, in p_nombre_linea varchar(50), in p_color varchar(30), in p_longitud_km decimal(5,2))
begin
    update lineas set nombre_linea = p_nombre_linea, color = p_color, longitud_km = p_longitud_km where id_linea = p_id_linea;
end //

create procedure sp_eliminar_linea(in p_id_linea int)
begin
    delete from lineas where id_linea = p_id_linea;
end //

create procedure sp_insert_estacion(in p_nombre varchar(50), in p_zona varchar(30), in p_id_linea int)
begin
    insert into estaciones(nombre, zona, id_linea) values (p_nombre, p_zona, p_id_linea);
end //

create procedure sp_listar_estaciones()
begin
    select * from estaciones;
end //

create procedure sp_obtener_estacion(in p_id_estacion int)
begin
    select * from estaciones where id_estacion = p_id_estacion;
end //

create procedure sp_actualizar_estacion(in p_id_estacion int, in p_nombre varchar(50), in p_zona varchar(30), in p_id_linea int)
begin
    update estaciones set nombre = p_nombre, zona = p_zona, id_linea = p_id_linea where id_estacion = p_id_estacion;
end //

create procedure sp_eliminar_estacion(in p_id_estacion int)
begin
    delete from estaciones where id_estacion = p_id_estacion;
end //

delimiter ;

call sp_insert_linea('linea 1', 'azul', 15.5);
call sp_insert_linea('linea 2', 'rojo', 12.0);
call sp_insert_linea('linea 3', 'verde', 18.2);
call sp_insert_linea('linea 4', 'amarillo', 10.5);
call sp_insert_linea('linea 5', 'naranja', 20.0);
call sp_insert_linea('linea 6', 'morado', 14.3);
call sp_insert_linea('linea 12', 'dorado', 25.1);
call sp_insert_linea('linea a', 'celeste', 9.8);
call sp_insert_linea('linea b', 'gris', 11.2);
call sp_insert_linea('linea c', 'rosa', 13.4);

call sp_crear_tren(1, 'bombardier movia', 1200, 'operativo');
call sp_crear_tren(2, 'alstom metropolis', 1500, 'operativo');
call sp_crear_tren(3, 'caf s-6000', 1100, 'mantenimiento');
call sp_crear_tren(4, 'siemens inspiro', 1300, 'operativo');
call sp_crear_tren(5, 'mitsubishi corp', 1000, 'operativo');
call sp_crear_tren(6, 'bombardier movia', 1200, 'fuera de servicio');
call sp_crear_tren(7, 'alstom metropolis', 1500, 'operativo');
call sp_crear_tren(8, 'caf s-6000', 1100, 'operativo');
call sp_crear_tren(9, 'siemens inspiro', 1300, 'mantenimiento');
call sp_crear_tren(10, 'crrc nanjing', 1600, 'operativo');

call sp_insert_estacion('estacion central', 'zona 1', 1);
call sp_insert_estacion('el trebol', 'zona 11', 1);
call sp_insert_estacion('plaza españa', 'zona 9', 2);
call sp_insert_estacion('los proceres', 'zona 10', 2);
call sp_insert_estacion('san juan', 'zona 7', 3);
call sp_insert_estacion('roosevelt', 'zona 11', 3);
call sp_insert_estacion('cayala', 'zona 16', 4);
call sp_insert_estacion('vista hermosa', 'zona 15', 5);
call sp_insert_estacion('san cristobal', 'zona 8 mixco', 6);
call sp_insert_estacion('el obelisco', 'zona 13', 1);

call sp_crear_personal(101, 'carlos garcia', 'carlos@metro.com', 'admin123', 'supervisor', 'administrador');
call sp_crear_personal(102, 'ana martinez', 'ana@metro.com', 'user123', 'mantenimiento', 'empleado');
call sp_crear_personal(103, 'luis perez', 'luis@metro.com', '123', 'seguridad', 'empleado');
call sp_crear_personal(104, 'marta lopez', 'marta@metro.com', '123', 'atencion al cliente', 'empleado');
call sp_crear_personal(105, 'jorge estrada', 'jorge@metro.com', '123', 'tecnico electrico', 'empleado');
call sp_crear_personal(106, 'sofia reyes', 'sofia@metro.com', '123', 'limpieza', 'empleado');
call sp_crear_personal(107, 'roberto gomez', 'roberto@metro.com', '123', 'administrativo', 'empleado');
call sp_crear_personal(108, 'elena torres', 'elena@metro.com', '123', 'seguridad', 'empleado');
call sp_crear_personal(109, 'miguel mendez', 'migue@metro.com', '123', 'mantenimiento', 'empleado');
call sp_crear_personal(110, 'lucia fernandez', 'lucia@metro.com', '123', 'taquillera', 'empleado');

call sp_crear_conductor(1, 'mario estrada', 'lic-a-554', 10);
call sp_crear_conductor(2, 'josefa ruiz', 'lic-a-992', 5);
call sp_crear_conductor(3, 'pedro castillo', 'lic-a-112', 12);
call sp_crear_conductor(4, 'julia mendez', 'lic-a-883', 3);
call sp_crear_conductor(5, 'esteban quito', 'lic-a-441', 8);
call sp_crear_conductor(6, 'claudia ramos', 'lic-a-229', 15);
call sp_crear_conductor(7, 'fernando gil', 'lic-a-770', 6);
call sp_crear_conductor(8, 'sandra paniagua', 'lic-a-661', 9);
call sp_crear_conductor(9, 'ricardo arjona', 'lic-a-002', 20);
call sp_crear_conductor(10, 'karla oliva', 'lic-a-338', 4);

call sp_crear_pasajero(1, 'juan pueblo', 'frecuente');
call sp_crear_pasajero(2, 'maria choc', 'estudiante');
call sp_crear_pasajero(3, 'estuardo diaz', 'adulto mayor');
call sp_crear_pasajero(4, 'kevin gonzales', 'general');
call sp_crear_pasajero(5, 'andrea morales', 'frecuente');
call sp_crear_pasajero(6, 'ramiro perez', 'discapacitado');
call sp_crear_pasajero(7, 'brenda lima', 'general');
call sp_crear_pasajero(8, 'hector valle', 'estudiante');
call sp_crear_pasajero(9, 'silvia luna', 'general');
call sp_crear_pasajero(10, 'victor jara', 'adulto mayor');

call sp_crear_impacto(1, 'zona 1 centro', 25.50);
call sp_crear_impacto(2, 'zona 10 viva', 15.20);
call sp_crear_impacto(3, 'zona 4 gran centro', 30.00);
call sp_crear_impacto(4, 'calzada roosevelt', 45.10);
call sp_crear_impacto(5, 'avenida las americas', 10.50);
call sp_crear_impacto(6, 'calzada san juan', 35.80);
call sp_crear_impacto(7, 'boulevard liberacion', 50.00);
call sp_crear_impacto(8, 'ruta al atlantico', 20.30);
call sp_crear_impacto(9, 'avenida petapa', 28.90);
call sp_crear_impacto(10, 'anillo periferico', 40.00);

call sp_crear_horario(1, '06:00:00', '06:45:00', 1);
call sp_crear_horario(2, '07:00:00', '07:45:00', 2);
call sp_crear_horario(3, '08:00:00', '08:45:00', 4);
call sp_crear_horario(4, '09:00:00', '09:45:00', 5);
call sp_crear_horario(5, '10:00:00', '10:45:00', 7);
call sp_crear_horario(6, '11:00:00', '11:45:00', 8);
call sp_crear_horario(7, '12:00:00', '12:45:00', 10);
call sp_crear_horario(8, '13:00:00', '13:45:00', 1);
call sp_crear_horario(9, '14:00:00', '14:45:00', 2);
call sp_crear_horario(10, '15:00:00', '15:45:00', 4);

call sp_crear_mantenimiento(1, '2024-01-15', 'cambio de frenos', 3);
call sp_crear_mantenimiento(2, '2024-01-20', 'limpieza profunda', 6);
call sp_crear_mantenimiento(3, '2024-02-05', 'revision de pantografo', 9);
call sp_crear_mantenimiento(4, '2024-02-10', 'actualizacion de software', 1);
call sp_crear_mantenimiento(5, '2024-02-15', 'cambio de luminarias', 2);
call sp_crear_mantenimiento(6, '2024-03-01', 'reparacion de aire acondicionado', 4);
call sp_crear_mantenimiento(7, '2024-03-05', 'engrase de ejes', 5);
call sp_crear_mantenimiento(8, '2024-03-10', 'revision de motores', 7);
call sp_crear_mantenimiento(9, '2024-03-15', 'pintura exterior', 8);
call sp_crear_mantenimiento(10, '2024-03-20', 'chequeo de puertas', 10);

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

select * from mantenimiento;
select * from horarios;