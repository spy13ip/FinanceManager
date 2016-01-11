CREATE TABLE currencies (
	_id integer primary key autoincrement,
	id_icon integer,
	code text not null,
	name text not null
);

CREATE TABLE purses (
	_id integer primary key autoincrement,
	id_icon integer,
	id_currency integer not null references currencies (_id),
	name text not null
);

CREATE TABLE category_types (
	_id integer primary key,
	name text not null
);

INSERT INTO category_types (_id, name) VALUES (1, 'Доходы');
INSERT INTO category_types (_id, name) VALUES (2, 'Расходы');
INSERT INTO category_types (_id, name) VALUES (3, 'Коррекция');
INSERT INTO category_types (_id, name) VALUES (4, 'Переводы');

CREATE TABLE categories (
	_id integer,
	id_parent integer references categories (_id),
	id_icon integer,
	id_type integer not null references category_types (_id),
	name text not null
);

CREATE TABLE transactions (
	_id integer primary key autoincrement,
	id_purse integer not null references purses (_id),
	modify_value real not null,
	balance_value real not null
);

CREATE TABLE modify_operations (
	_id integer primary key autoincrement,
	id_purse integer references purses (_id),
	value real not null,
	id_transaction integer not null references transactions (_id)
);

CREATE TABLE fix_operations (
	_id integer primary key autoincrement,
	id_purse integer references purses (_id),
	value real not null,
	id_transaction integer not null references transactions (_id)
);

CREATE TABLE move_operations (
	_id integer primary key autoincrement,
	id_source_purse integer not null references purses (_id),
	id_receiver_purse integer not null references purses (_id),
	value real not null,
	rate real not null,
	id_source_transaction integer not null references transactions (_id),
	id_receiver_transaction integer not null references transactions (_id)
);

CREATE TABLE operation_types (
	_id integer primary key,
	name text not null
);

INSERT INTO operation_types (_id, name) VALUES (1, 'Изменение');
INSERT INTO operation_types (_id, name) VALUES (2, 'Фиксация');
INSERT INTO operation_types (_id, name) VALUES (3, 'Перевод');

CREATE TABLE operations (
	_id integer primary key autoincrement,
	id_type integer not null references operation_types (_id),
	id_category integer not null references categories (_id),
	id_modify_operation integer references modify_operations (_id),
	id_fix_operation integer references fix_operations (_id),
	id_move_operation integer references move_operations (_id),
	description text
);
