CREATE TABLE public.user_auth (
	user_auth_id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	login varchar(32) NOT NULL,
	pass varchar(32) NOT NULL,
	CONSTRAINT user_auth_login_key UNIQUE (login),
	CONSTRAINT user_auth_pkey PRIMARY KEY (user_auth_id)
);

CREATE TABLE public.type_transaction (
	type_transaction_id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	type_transaction_label varchar(32) NULL,
	CONSTRAINT type_transaction_pkey PRIMARY KEY (type_transaction_id)
);

CREATE TABLE public.user_transaction (
	transaction_id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	user_auth_id int4 NOT NULL,
	amount float4 DEFAULT 0.0 NOT NULL,
	transaction_date date NOT NULL,
	type_transaction_id int4 NOT NULL,
	CONSTRAINT user_transaction_pk PRIMARY KEY (transaction_id)
);

INSERT INTO public.user_auth (login, pass) VALUES ('admin', '1234')