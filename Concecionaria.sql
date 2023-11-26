CREATE TABLE public.carro (
	id int NOT NULL,
	nome varchar (20) NOT NULL,
	chassi int NOT NULL,
	marca varchar (20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.acessorio (
	id int NOT NULL,
	produto varchar (20) NOT NULL,
	preco double precision NOT NULL,
	carro varchar (20) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE public.pneu (
	id int NOT NULL,
	preco double precision NOT NULL,	
	nome varchar (20) NOT NULL,
	descricao varchar (50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.usuario (
	usuario VARCHAR (25) PRIMARY KEY,
    senha INT NOT NULL
);