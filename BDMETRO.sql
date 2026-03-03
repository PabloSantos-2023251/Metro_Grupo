drop database if exists proyecto_metro_in5cm;
create database proyecto_metro_in5cm;
use proyecto_metro_in5cm;

create table personal (
    id_personal int primary key auto_increment,
    nombre varchar(100),
    cargo varchar(50)
);

create table impacto_trafico (
    id_impacto int primary key auto_increment,
    zona varchar(100),
    reduccion_trafico_porcentaje decimal(5,2)
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
DELIMITER ;