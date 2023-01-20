DROP DATABASE IF EXISTS  cybersolutions;
-- -----------------------------------------------------
-- schema mydb
-- -----------------------------------------------------
create schema cybersolutions;

use cybersolutions;

-- -----------------------------------------------------
-- `MMMM TABLAS :V`
-- -----------------------------------------------------

-- -----------------------------------------------------
-- table `cybersolutions`.`tb_estado`
-- -----------------------------------------------------

create table tb_estado(
idestado		int not null primary key,
descripcion varchar(15)
);

-- -----------------------------------------------------
-- table `cybersolutions`.`tb_clientes`
-- -----------------------------------------------------
drop table if exists tb_clientes;

create table if not exists tb_clientes(
  id_cliente  char(5) not null,
  dni_cliente char(8) unique null,
  nom_cliente varchar(45) not null,
  ape_cliente varchar(45) not null,
  telef_cliente varchar(45) null,
  idestado	int not null default 1,
  primary key (id_cliente),
  unique(dni_cliente),
  unique(telef_cliente),
  foreign key (idestado) references tb_estado(idestado)
  );
  
-- -----------------------------------------------------
-- table `cybersolutions`.`tb_tipo`
-- -----------------------------------------------------

create table tb_tipo(
id_tipo	 int not null primary key,
des_tipo varchar(20)
);

-- -----------------------------------------------------
-- table `cybersolutions`.`tb_empleado`
-- -----------------------------------------------------
drop table if exists tb_empleado;

create table if not exists tb_empleado (
  id_empleado char(5) not null,
  dni_empleado char(8) not null,
  nom_empleado varchar(45) not null,
  ape_empleado varchar(45) not null,
  fec_ingreso_empleado date not null,
  usu_empleado varchar(45) not null,
  pass_empleado varchar(45) not null,
  telef_empleado varchar(45) null,
  id_tipo	int not null,
  primary key (id_empleado),
  unique(dni_empleado),
  unique (usu_empleado),
  unique(telef_empleado),
  foreign key (id_tipo) references tb_tipo(id_tipo));


-- -----------------------------------------------------
-- table `cybersolutions`.`tb_categorias`
-- -----------------------------------------------------
drop table if exists tb_categorias ;

create table if not exists tb_categorias (
  id_categoria int auto_increment,
  nom_cat varchar(20) not null,
  desc_cat varchar(80) not null,
  primary key (id_categoria),
  unique(nom_cat)
  );


-- -----------------------------------------------------
-- table `cybersolutions`.`tb_productos`
-- -----------------------------------------------------
drop table if exists tb_productos;

create table if not exists tb_productos(
  id_productos int auto_increment,
  nom_prod varchar(80) not null,
  prec_prod decimal(8,2) not null,
  stock_prod int not null,
  id_categoria int not null,
  primary key (id_productos),
  unique(nom_prod),
    foreign key (id_categoria)
    references tb_categorias(id_categoria));

-- -----------------------------------------------------
-- table `cybersolutions`.`tb_cab_boleta`
-- -----------------------------------------------------
drop table if exists tb_cab_boleta;

create table if not exists tb_cab_boleta(
num_bol     char(5) not null,
fch_bol    date,
id_cliente  char(5) not null,
id_empleado char(5) not null,
total_bol decimal(8,2),
primary key (num_bol),
  foreign key(id_cliente) 
  references tb_clientes(id_cliente),
   foreign key(id_empleado) 
  references tb_empleado(id_empleado));
  

  -- -----------------------------------------------------
-- table `cybersolutions`.`tb_det_boleta`
-- -----------------------------------------------------
drop table if exists tb_det_boleta;

create table if not exists tb_det_boleta(
num_bol     char(5) not null,
id_productos int auto_increment,
nom_prod	varchar(80) not null,
cantidad    int,
preciovta   decimal(8,2),
importe		decimal(8,2),
primary key (num_bol,id_productos),
foreign key (num_bol) references tb_cab_boleta(num_bol),
foreign key (id_productos) references tb_productos(id_productos));
  
select * from tb_cab_boleta;
select * from tb_det_boleta;
-- -----------------------------------------------------
-- `Insercciones`
-- -----------------------------------------------------
insert into tb_estado values (1, 'Activo');
insert into tb_estado values (2, 'Inactivo');
  
insert into tb_clientes values('CL001',72178636,'Diego','Gutarra','958254258',1);
insert into tb_clientes values('CL002',72277635,'Andres','Cervantes','945800988',1);
insert into tb_clientes values('CL003',72475634,'Santos','Lurita',null,1);
insert into tb_clientes values('CL004',72679633,'Daniel','Lazaro','943447957',2);
insert into tb_clientes values('CL005',73233432,'Julius','Guzman',null,2);

insert into tb_tipo values (1, 'Administrador');
insert into tb_tipo values (2, 'Cajero');

insert into tb_empleado values ('EM001','43464181','Alberto','Zapata Zaragosa','1997/11/18','julito','123456',null,1);
insert into tb_empleado values ('EM002','05949613','Jose Francisco','Valencia Garcia','1998/10/20','josesito','123456','964387874',1);
insert into tb_empleado values ('EM003','60333576','Alvaro','Pastor Cazorla','1996/09/18','alvarito','123456','966810432',2);
insert into tb_empleado values ('EM004','49964110','Noelia','Prada Rey','1995/05/08','noeliarey','123456','969462760',1);
insert into tb_empleado values ('EM005','34691326','Maria Pilar','Diaz Noche','1999/08/17','Filo5','123456',null,2);

insert into tb_categorias values (null,'Laptops','Gaming,Estudio');
insert into tb_categorias values (null,'Celulares','Lg, Motorola, etc');
insert into tb_categorias values (null,'Software','Licencias de antivirus y productos de microsoft');
insert into tb_categorias values (null,'Hardware','Componentes de PC y Laptops');
insert into tb_categorias values (null,'Enfermos','Deep weeb chicos');

insert into tb_productos values (null,'Predator Acer Helios 300',3700.00,50,1);
insert into tb_productos values (null,'iPhone 11 Pro Max',4500.00,20,2);
insert into tb_productos values (null,'Bitdefender Plus 2019',100.00,5,3);
insert into tb_productos values (null,'AMD Ryzen 9',2300.00,2,4);
insert into tb_productos values (null,'Papel Higenico Gamer',999999.00,1,5);

insert into tb_cab_boleta values('B0001','2020-07-15','CL001','EM005',14800.00);
insert into tb_cab_boleta values('B0002','2020-07-15','CL002','EM005',19300.00);
insert into tb_cab_boleta values('B0003','2020-07-15','CL003','EM005',19400.00);
insert into tb_cab_boleta values('B0004','2020-07-15','CL004','EM005',200.00);
insert into tb_cab_boleta values('B0005','2020-07-15','CL005','EM005',3900.00);

insert into tb_det_boleta values ('B0001', 1, 'Predator Acer Helios 300', 4, 3700.00, 14800.00);
insert into tb_det_boleta values ('B0002', 1, 'Predator Acer Helios 300', 4, 3700.00, 14800.00);
insert into tb_det_boleta values ('B0002', 2, 'iPhone 11 Pro Max', 1, 4500.00, 4500.00);
insert into tb_det_boleta values ('B0003', 1, 'Predator Acer Helios 300', 4, 3700.00, 14800.00);
insert into tb_det_boleta values ('B0003', 2, 'iPhone 11 Pro Max', 1, 4500.00, 4500.00);
insert into tb_det_boleta values ('B0003', 3, 'Bitdefender Plus 2019', 1, 100.00, 100.00);
insert into tb_det_boleta values ('B0004', 3, 'Bitdefender Plus 2019', 2, 100.00, 200.00);
insert into tb_det_boleta values ('B0005', 1, 'Predator Acer Helios 300', 1, 3700.00, 3700.00);
insert into tb_det_boleta values ('B0005', 3, 'Bitdefender Plus 2019', 2, 100.00, 200.00);

-- -----------------------------------------------------
-- `Procedimiento Almacenados`
-- -----------------------------------------------------

/*Listado Cliente*/

delimiter $$
create procedure usp_ListadoCliente()
begin
select cl.id_cliente,cl.dni_cliente,cl.nom_cliente,cl.ape_cliente,cl.telef_cliente,es.descripcion
from tb_clientes as cl join tb_estado as es
on cl.idestado = es.idestado;
end $$
delimiter ;


/*Consulta Cliente por Estado*/

delimiter $$
create procedure usp_consultaClientexEstado(idestado int)
begin
select cl.id_cliente, concat(cl.nom_cliente,' ',cl.ape_cliente) as 'Nombre completo', es.descripcion as 'Estado'
from tb_clientes as cl join tb_estado as es
on cl.idestado = es.idestado
where cl.idestado = idestado;
end $$
delimiter ;

/*Validar Usuario*/

  delimiter $$
create procedure usp_validaUsuario(usr char(45), pas char(45))
begin
select * from tb_empleado where usu_empleado = usr and pass_empleado = pas;
end $$
delimiter ;


/*Listar Empleado*/

delimiter $$
create procedure usp_ListadoEmpleado()
begin
select em.id_empleado, em.dni_empleado, em.nom_empleado, em.ape_empleado, em.fec_ingreso_empleado, em.telef_empleado, tp.des_tipo 
from tb_empleado as em join tb_tipo as tp
on em.id_tipo = tp.id_tipo;
end $$
delimiter ;


/*Listar Productos*/

delimiter $$
create procedure usp_ListadoProductos()
begin
select pr.id_productos,pr.nom_prod,pr.prec_prod,pr.stock_prod,ca.nom_cat
from tb_productos as pr join tb_categorias as ca
on pr.id_categoria = ca.id_categoria;
end $$
delimiter $$ ;


/*Listar Empleados por tipos */

delimiter $$
create procedure usp_ListaEmpleadoxTipo(idtipo int)
begin
select em.id_empleado, em.dni_empleado, concat(em.nom_empleado,' ',em.ape_empleado) , Timestampdiff(year,fec_ingreso_empleado,curdate()) as 'Años trabajando', t.des_tipo
from tb_tipo as t
join tb_empleado as em 
on t.id_tipo = em.id_tipo
where em.id_tipo = idtipo;
end $$
delimiter ;


/*Listar Cliente por Estado segun sus Cantidades de Compras*/


delimiter $$
create procedure usp_ListaClientexEstado(idestado int)
begin
select cl.id_cliente, cl.dni_cliente, concat(cl.nom_cliente,' ',cl.ape_cliente) as 'Nombre completo', cl.telef_cliente, es.descripcion as 'Estado', count(cb.num_bol) as 'Cant. Compras' 
from tb_estado as es
join tb_clientes as cl 
on es.idestado = cl.idestado
join tb_cab_boleta as cb
on cb.id_cliente = cl.id_cliente
where cl.idestado = idestado
group by cl.id_cliente, cl.dni_cliente, cl.nom_cliente, cl.ape_cliente, cl.telef_cliente, descripcion;
end $$
delimiter ;


/*Listar entre Fechas*/

delimiter $$
create procedure usp_ListaVentasFechas(fecha1 varchar(20), fecha2 varchar(20))
begin
select cb. num_bol, cb.fch_bol, concat(c.nom_cliente,' ',c.ape_cliente) as 'Nombre cliente', concat(em.nom_empleado,' ',em.ape_empleado) as 'Nombre empleado', cb.total_bol 
from tb_cab_boleta as cb
join tb_clientes as c
on cb.id_cliente = c.id_cliente
join tb_empleado as em
on cb.id_empleado = em.id_empleado
where cb.fch_bol between fecha1 and fecha2;
end $$
delimiter ;