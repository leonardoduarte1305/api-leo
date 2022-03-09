insert into gestor (nome, email, senha) values ('leonardo', 'leo@leo.com', '$2a$10$lB03/m9IKDjBubawgnJ/bucuiq0JYz1.ue6S0KT7pb6zQSagv.Xea');
insert into gestor (nome, email, senha) values ('admin', 'admin@admin.com', '$2a$10$6GQwJl6/UzkVP8XTA4tLieubclp5g/z30yx69vZAKkOYRcqOVHv8u');
	
insert into perfil (id_perfil, nome_perfil) values (1, 'ROLE_MODERADOR');
insert into perfil (id_perfil, nome_perfil) values (2, 'ROLE_GERENTE');
insert into perfil (id_perfil, nome_perfil) values (3, 'ROLE_COLABORADOR');
	
insert into gestor_perfis (gestor_id_gestor, perfis_id_perfil) values (2, 2);
insert into gestor_perfis (gestor_id_gestor, perfis_id_perfil) values (1, 3);

insert into setores (descricao) values ('DevOps');
insert into setores (descricao) values ('Marketing');
insert into setores (descricao) values ('Financeiro');
insert into setores (descricao) values ('Logistica');
insert into setores (descricao) values ('RH');
insert into setores (descricao) values ('Dev');
insert into setores (descricao) values ('Teste para Exclusao');
    
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
     ('074.390.439-01', '2020-05-01', '1989-04-13', 'leonardoduarte130@gmail.com', 'Zenira G Duarte da Silva', 6, '48 99131-1949');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('074.390.439-02', '2020-05-01', '1988-06-13', 'leonardoduarte13@gmail.com', 'Leopoldo G Duarte da Silva', 5, '48 99131-1950');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values 
	 ('074.390.439-03', '2020-05-01', '1979-04-13', 'leonardoduarte1@gmail.com', 'Leonel G Duarte da Silva', 4, '48 99131-1951');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('074.390.439-04', '2020-05-01', '1969-01-13', 'leonardoduarte@gmail.com', 'Leocádio G Duarte da Silva', 3, '48 99131-1952');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('074.390.439-05', '2020-05-01', '1959-02-13', 'leonardoduart@gmail.com', 'Rafael G Duarte da Silva', 2, '48 99131-1953');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('174.390.439-06', '2020-05-01', '1969-03-13', 'leonardoduar@gmail.com', 'Leopoldina G Duarte da Silva', 1, '48 99131-1954');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('274.390.439-07', '2020-05-01', '1979-05-13', 'leonardodua@gmail.com', 'Leoncio G Duarte da Silva', 6, '48 99131-1955');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('374.390.439-08', '2020-05-01', '1989-04-13', 'leonardodu@gmail.com', 'Leomar G Duarte da Silva', 5, '48 99131-1956');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('474.390.439-09', '2020-05-01', '1999-08-13', 'leonardod@gmail.com', 'Amanda G Duarte da Silva', 4, '48 99131-1957');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('574.390.439-10', '2020-05-01', '2000-09-13', 'leonardo@gmail.com', 'Leonidas G Duarte da Silva', 3, '48 99131-1958');
insert into colaborador (cpf, dt_Cadastro, dt_Nascimento, email, nome, idsetor, telefone) values
	 ('577.390.439-10', '2020-05-01', '2000-09-13', 'amanada@gmail.com', 'Leonidas Gonçalves', 3, '48 11131-1958');