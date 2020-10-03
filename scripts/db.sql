SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sistema_venda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema_venda` DEFAULT CHARACTER SET utf8 ;
USE `sistema_venda` ;

-- -----------------------------------------------------
-- Table `sistema_venda`.`unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`unidade` (
  `id_unidade` INT NOT NULL AUTO_INCREMENT,
  `nome_unidade` VARCHAR(45) NULL,
  PRIMARY KEY (`id_unidade`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `codigo_barra_produto` VARCHAR(200) NOT NULL,
  `nome_produto` VARCHAR(200) NOT NULL,
  `preco_compra_produto` DECIMAL(10,2) NOT NULL,
  `preco_venda_produto` DECIMAL(10,2) NOT NULL,
  `quantidade_estoque_produto` INT NOT NULL,
  `data_validade_produto` VARCHAR(10) NULL,
  `id_categoria` INT NOT NULL,
  `id_unidade` INT NOT NULL,
  PRIMARY KEY (`id_produto`),
  INDEX `fk_produto_unidade1_idx` (`id_unidade` ASC),
  INDEX `fk_produto_categoria1_idx` (`id_categoria` ASC),
  CONSTRAINT `fk_produto_unidade1`
    FOREIGN KEY (`id_unidade`)
    REFERENCES `sistema_venda`.`unidade` (`id_unidade`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_produto_categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `sistema_venda`.`categoria` (`id_categoria`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sistema_venda`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`provincia` (
  `id_provincia` INT NOT NULL AUTO_INCREMENT,
  `nome_provincia` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id_provincia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`municipio` (
  `id_municipio` INT NOT NULL AUTO_INCREMENT,
  `nome_municipio` VARCHAR(200) NOT NULL,
  `id_provincia` INT NOT NULL,
  PRIMARY KEY (`id_municipio`),
  INDEX `fk_municipio_provincia1_idx` (`id_provincia` ASC),
  CONSTRAINT `fk_municipio_provincia1`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `sistema_venda`.`provincia` (`id_provincia`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`funcionario` (
  `id_funcionario` INT NOT NULL AUTO_INCREMENT,
  `nome_funcionario` VARCHAR(200) NOT NULL,
  `bi_funcionario` VARCHAR(200) NULL,
  `telefone_funcionario` VARCHAR(200) NULL,
  `id_municipio` INT NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  INDEX `fk_funcionario_municipio1_idx` (`id_municipio` ASC),
  CONSTRAINT `fk_funcionario_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `sistema_venda`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `id_funcionario` INT NOT NULL,
  `senha_usuario` VARCHAR(200) NOT NULL,
  `login_usuario` VARCHAR(200) NOT NULL,
  `perfil_usuario` VARCHAR(200) NOT NULL,
  INDEX `fk_usuario_funcionario1_idx` (`id_funcionario` ASC),
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `fk_usuario_funcionario1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `sistema_venda`.`funcionario` (`id_funcionario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sistema_venda`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nome_cliente` VARCHAR(200) NOT NULL,
  `nif_cliente` VARCHAR(200) NULL,
  `endereco_cliente` VARCHAR(200) NOT NULL,
  `telefone_cliente` VARCHAR(200) NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`fornecedor` (
  `id_fornecedor` INT NOT NULL AUTO_INCREMENT,
  `nome_fornecedor` VARCHAR(200) NOT NULL,
  `nif_fornecedor` VARCHAR(200) NULL,
  `endereco_fornecedor` VARCHAR(200) NOT NULL,
  `telefone_fornecedor` VARCHAR(200) NULL,
  PRIMARY KEY (`id_fornecedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`compra` (
  `id_compra` INT NOT NULL AUTO_INCREMENT,
  `id_fornecedor` INT NOT NULL,
  `data_compra` DATE NOT NULL,
  `valor_total_compra` DECIMAL(10,2) NOT NULL,
  `situação_compra` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_compra_fornecedor1_idx` (`id_fornecedor` ASC),
  CONSTRAINT `fk_compra_fornecedor1`
    FOREIGN KEY (`id_fornecedor`)
    REFERENCES `sistema_venda`.`fornecedor` (`id_fornecedor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` INT NOT NULL,
  `data_venda` DATE NOT NULL,
  `valor_total_venda` DECIMAL(10,2) NOT NULL,
  `situacao_venda` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_venda`),
  INDEX `fk_venda_cliente1_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_venda_cliente1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `sistema_venda`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`item_compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`item_compra` (
  `id_item_compra` INT NOT NULL AUTO_INCREMENT,
  `id_produto` INT NOT NULL,
  `id_compra` INT NOT NULL,
  `quantidade_item_compra` INT NOT NULL,
  `valor_unitario_item_compra` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_item_compra`),
  INDEX `fk_item_compra_produto1_idx` (`id_produto` ASC),
  INDEX `fk_item_compra_compra1_idx` (`id_compra` ASC),
  CONSTRAINT `fk_item_compra_produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `sistema_venda`.`produto` (`id_produto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_item_compra_compra1`
    FOREIGN KEY (`id_compra`)
    REFERENCES `sistema_venda`.`compra` (`id_compra`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema_venda`.`item_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema_venda`.`item_venda` (
  `id_item_venda` INT NOT NULL AUTO_INCREMENT,
  `id_produto` INT NOT NULL,
  `id_venda` INT NOT NULL,
  `quantidade_item_venda` INT NULL,
  `valor_unitario_item_venda` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id_item_venda`),
  INDEX `fk_item_venda_produto1_idx` (`id_produto` ASC),
  INDEX `fk_item_venda_venda1_idx` (`id_venda` ASC),
  CONSTRAINT `fk_item_venda_produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `sistema_venda`.`produto` (`id_produto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_item_venda_venda1`
    FOREIGN KEY (`id_venda`)
    REFERENCES `sistema_venda`.`venda` (`id_venda`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
