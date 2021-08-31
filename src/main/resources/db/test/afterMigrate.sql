truncate table pessoa restart identity cascade; 
truncate table endereco restart identity cascade; 

insert into pessoa(nome, email, sexo, cpf, data_nascimento, celular) values
('João Andrade', 'andradepinheironeto24@hotmail.com', 'MASCULINO', '84061958003', '2001-03-24', '81998555369'),
('mark zuckerberg', 'mark@gmail.com', 'MASCULINO', '69913947006', '1984-05-14', '11332221133');

insert into endereco(rua, bairro, cidade, estado, numero, pessoa_id) values
('Rua Vinte e um de novembro', 'Genibáu', 'Fortaleza', 'CE', '330', 1),
('Rua Vitorino Carmilo', 'Barra Funda', 'São Paulo', 'SP', '320', 2);