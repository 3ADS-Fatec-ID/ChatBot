USE BOT
GO

create table Endereco (
id int IDENTITY Primary Key,
endereco varchar (500) not null, 
cidade varchar (250) not null, 
estado varchar (2) not null, 
criado_em datetime not null,
editado_em datetime not null)

create table Universidade (
id int IDENTITY Primary Key,
nomeUniversidade varchar(250) not null,
descricaoUniversidade varchar (500) not null,
criado_em datetime not null,
editado_em datetime not null,
id_endereco int not null REFERENCES Endereco)

create table Curso (
id int IDENTITY Primary Key,
siglaCurso varchar(20) not null,
nomeCurso varchar (500) not null,
criado_em datetime not null,
editado_em datetime not null)

create table Curso_Universidade (
id int IDENTITY Primary Key,
id_curso int not null REFERENCES Curso, 
id_universidade int not null REFERENCES Universidade)

create table Usuario (
id int IDENTITY Primary Key,
nomeUsuario varchar (500) not null,
id_telegram bigint UNIQUE not null,
termoAceite bit not null,
id_curso_universidade int not null REFERENCES Curso_Universidade,
criado_em datetime not null,
editado_em datetime not null)

create table Progresso (
id int IDENTITY Primary Key,
nomeProgresso varchar (50) not null)

create table Pesquisa (
id int IDENTITY Primary Key,
corpo varchar (500) not null,
criado_em datetime not null,
editado_em datetime not null,
id_Progresso int not null REFERENCES Progresso,
id_Usuario int not null REFERENCES Usuario)

create table Palavra_Chave (
id int IDENTITY Primary Key,
nomePalavraChave varchar (250) not null,
criado_em datetime not null,
editado_em datetime not null)  

create table Pesquisa_Historico (
id int IDENTITY Primary Key,
criado_em datetime not null,
id_Palavra_Chave int not null REFERENCES Palavra_Chave,
id_Pesquisa int not null REFERENCES Pesquisa)


create table Acervo (
id int IDENTITY Primary Key,
autor varchar (250) not null,
tema varchar (250) not null,
orientador varchar (250) not null,
criado_em datetime not null,
editado_em datetime not null)

create table Duvida (
id int IDENTITY Primary Key,
nomeDuvida varchar (250) not null,
descricaoDuvida varchar (max) not null,
criado_em datetime not null,
editado_em datetime not null)

create table Pesquisavel (
id int IDENTITY Primary Key,
id_Acervo int REFERENCES Acervo,
id_Duvida int REFERENCES Duvida)

create table Tipo_Mensagem_Dominio (
id int IDENTITY Primary Key,
tipo varchar (50) not null)

create table Mensagem_Dominio (
id int IDENTITY Primary Key,
id_Progresso int not null REFERENCES Progresso,
corpoMensagemDominio varchar (500) not null,
criado_em datetime not null,
editado_em datetime not null,
id_Tipo_Mensagem_Dominio int not null REFERENCES Tipo_Mensagem_Dominio)

create table Palavra_Chave_Pesquisa (
id int IDENTITY Primary Key,
criado_em datetime not null,
id_Palavra_Chave int not null REFERENCES Palavra_Chave,
id_curso int not null REFERENCES Curso, 
id_Pesquisavel int not null REFERENCES Pesquisavel)

