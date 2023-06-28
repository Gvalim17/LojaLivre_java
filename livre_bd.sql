-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28/06/2023 às 01:39
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `livre_bd`
--
CREATE DATABASE IF NOT EXISTS `livre_bd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `livre_bd`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `cliente_cpf` bigint(11) NOT NULL,
  `cliente_nome` varchar(150) NOT NULL,
  `cliente_idade` int(11) NOT NULL,
  `cliente_email` varchar(150) NOT NULL,
  `cliente_telefone` int(11) NOT NULL,
  `cliente_endereco` varchar(150) NOT NULL,
  `cliente_cep` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`cliente_cpf`, `cliente_nome`, `cliente_idade`, `cliente_email`, `cliente_telefone`, `cliente_endereco`, `cliente_cep`) VALUES
(741258963, 'Guilherme Valim araujo', 24, 'GuilhermeValim@Guilherme Valim', 217412589, 'marlo da costa', 21974125);

-- --------------------------------------------------------

--
-- Estrutura para tabela `estoque`
--

CREATE TABLE `estoque` (
  `estoque_lote` int(11) NOT NULL,
  `estoque_codigo` int(11) NOT NULL,
  `estoque_nome` varchar(50) NOT NULL,
  `estoque_valor` double NOT NULL,
  `estoque_ativo` int(11) NOT NULL,
  `estoque_valor_total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `estoque`
--

INSERT INTO `estoque` (`estoque_lote`, `estoque_codigo`, `estoque_nome`, `estoque_valor`, `estoque_ativo`, `estoque_valor_total`) VALUES
(1, 1, 'Leite de po', 20, 181, 3620),
(1, 2, 'Leite de vaca', 69, 96, 6624),
(2, 1, 'Borracha', 69, 96, 6624);

--
-- Acionadores `estoque`
--
DELIMITER $$
CREATE TRIGGER `atualizar_valor_total` BEFORE UPDATE ON `estoque` FOR EACH ROW BEGIN
    IF NEW.estoque_ativo <> OLD.estoque_ativo THEN
        SET NEW.estoque_valor_total = NEW.estoque_valor * NEW.estoque_ativo;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `funcionario_codigo` int(11) NOT NULL,
  `funcionario_cpf` char(11) NOT NULL,
  `funcionario_nome` varchar(50) NOT NULL,
  `funcionario_salario` double NOT NULL,
  `funcionario_cargo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`funcionario_codigo`, `funcionario_cpf`, `funcionario_nome`, `funcionario_salario`, `funcionario_cargo`) VALUES
(1, '123456789', 'Guilherme Valim', 10000, 'Chefe'),
(2, '1234567891', 'Matheus Dudu', 45875, 'Chefe 2');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cliente_cpf`);

--
-- Índices de tabela `estoque`
--
ALTER TABLE `estoque`
  ADD PRIMARY KEY (`estoque_lote`,`estoque_codigo`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`funcionario_codigo`),
  ADD UNIQUE KEY `funcionario_cpf` (`funcionario_cpf`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
