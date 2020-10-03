USE sistema_venda;

INSERT INTO `funcionario` (`nome_funcionario`, `bi_funcionario`, `telefone_funcionario`, `id_municipio`) VALUES 
 ('Administrador', '', '', 66);

INSERT INTO `usuario` (`id_funcionario`, `senha_usuario`, `login_usuario`, `perfil_usuario`) VALUES 
 (1, 'admin123666', 'Administrador', 'Administrador');