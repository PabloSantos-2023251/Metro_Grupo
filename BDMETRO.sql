drop database if exists proyecto_metro_in5cm;
create database proyecto_metro_in5cm;
use proyecto_metro_in5cm;

create table personal (
    id_personal int primary key,
    nombre varchar(100),
    cargo varchar(50)
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

DELIMITER ;