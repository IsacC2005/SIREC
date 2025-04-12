-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2024 a las 15:28:24
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nucleocomunal`
--
CREATE DATABASE IF NOT EXISTS `nucleocomunal` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `nucleocomunal`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casa`
--

CREATE TABLE `casa` (
  `id` int(11) NOT NULL,
  `nCasa` varchar(25) DEFAULT NULL,
  `id_estdCasa` int(11) NOT NULL,
  `id_direccion` int(11) NOT NULL,
  `rModuloCLP` tinyint(1) NOT NULL,
  `sAgua` tinyint(1) NOT NULL,
  `sAguasN` tinyint(1) NOT NULL,
  `sLuz` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `casa`
--

INSERT INTO `casa` (`id`, `nCasa`, `id_estdCasa`, `id_direccion`, `rModuloCLP`, `sAgua`, `sAguasN`, `sLuz`) VALUES
(201, NULL, 1, 22, 1, 1, 1, 1),
(202, '', 1, 22, 0, 1, 1, 1),
(203, '', 1, 22, 0, 1, 1, 1),
(204, '', 1, 22, 0, 1, 1, 1),
(205, '', 1, 22, 0, 1, 1, 1),
(206, '', 1, 22, 0, 1, 1, 1),
(207, '', 1, 22, 0, 1, 1, 1),
(208, '', 1, 22, 0, 0, 1, 1),
(209, '', 1, 22, 0, 1, 1, 1),
(210, '', 1, 22, 0, 1, 1, 1),
(211, '', 1, 22, 0, 1, 1, 1),
(212, '', 1, 22, 0, 1, 1, 0),
(213, '', 1, 22, 0, 1, 1, 1),
(214, '', 1, 22, 0, 1, 1, 1),
(215, '', 1, 32, 0, 0, 0, 0),
(216, 'kiikko', 1, 23, 0, 1, 1, 1),
(217, 'kiikko', 1, 26, 0, 1, 1, 1),
(218, 'kiikko', 1, 26, 0, 1, 1, 1),
(219, 'kiikko', 1, 30, 0, 1, 1, 1),
(220, '', 2, 32, 0, 1, 1, 1),
(221, '', 3, 32, 0, 1, 1, 1),
(222, '3lv1t0', 2, 32, 0, 0, 0, 0),
(223, 'P4p3L', 1, 32, 0, 0, 0, 0),
(224, 'lk3.d,', 1, 31, 0, 0, 0, 0),
(225, '233kl', 1, 32, 0, 0, 1, 1),
(226, '', 1, 32, 0, 1, 1, 1),
(227, '', 3, 31, 1, 1, 1, 1),
(228, '', 1, 31, 1, 1, 1, 1),
(229, '', 1, 31, 1, 1, 1, 1),
(230, '', 1, 30, 1, 1, 1, 1),
(231, '', 2, 30, 1, 1, 1, 1),
(232, '', 2, 30, 1, 1, 1, 1),
(233, '', 3, 30, 1, 1, 1, 1),
(234, '', 1, 30, 1, 1, 1, 1),
(235, '', 1, 29, 1, 1, 1, 1),
(236, '', 1, 29, 1, 1, 1, 1),
(237, '', 1, 29, 0, 1, 1, 1),
(238, '', 2, 26, 0, 1, 0, 0),
(239, '', 1, 26, 1, 1, 1, 1),
(240, '', 1, 24, 0, 1, 1, 0),
(241, '', 1, 22, 1, 1, 1, 1),
(242, '', 1, 24, 1, 1, 1, 1),
(243, 'jlkasdii', 1, 29, 1, 1, 1, 1),
(244, 'lj', 3, 29, 1, 1, 1, 1),
(245, 'lkk', 1, 28, 0, 1, 1, 1),
(246, 'ddfasf', 1, 27, 1, 1, 1, 1),
(247, 'chuknrk', 2, 26, 0, 0, 0, 0),
(248, 'llklk', 2, 25, 0, 1, 0, 0),
(249, '', 1, 22, 1, 1, 0, 0),
(250, 'll', 1, 22, 0, 1, 1, 0),
(251, '', 1, 22, 0, 0, 1, 1),
(252, '', 1, 22, 0, 0, 0, 0),
(253, 'kkl', 2, 22, 0, 1, 0, 0),
(254, '', 1, 22, 0, 0, 0, 0),
(255, '', 1, 23, 1, 1, 1, 1),
(256, '', 1, 23, 1, 1, 1, 1),
(257, '', 1, 25, 1, 1, 1, 1),
(258, '', 1, 28, 1, 1, 1, 1),
(259, '', 1, 27, 1, 1, 1, 1),
(260, '', 1, 29, 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controldelogin`
--

CREATE TABLE `controldelogin` (
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `controldelogin`
--

INSERT INTO `controldelogin` (`estado`) VALUES
(0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `demografia`
--

CREATE TABLE `demografia` (
  `id` int(11) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `id_lider` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `demografia`
--

INSERT INTO `demografia` (`id`, `calle`, `id_lider`) VALUES
(22, 'Principal Omar Visquel', 521),
(23, 'Omar Visquel, parte Media', 648),
(24, 'Omar Visquel parte Baja', 648),
(25, 'Transversal I', 649),
(26, 'Transversal II', 581),
(27, 'Transversal III', 657),
(28, 'Transversal IV', 654),
(29, 'Calle #2', 659),
(30, 'Callejon Bolivariano', 582),
(31, 'Calle la manga', 579),
(32, 'Parque Ferial', 573);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `discapacidad`
--

CREATE TABLE `discapacidad` (
  `id` int(2) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `discapacidad` varchar(32) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `discapacidad`
--

INSERT INTO `discapacidad` (`id`, `id_tipo`, `discapacidad`, `descripcion`) VALUES
(1, 1, 'Limitaciones en la movilidad', 'Dificultad para caminar o utilizar ciertas partes del cuerpo \r\ndebido a condiciones musculoesqueléticas o neuromusculares.'),
(2, 1, 'Parálisis', 'Afectación de una o más extremidades debido a lesiones \r\nen la médula espinal o enfermedades neuromusculares.'),
(3, 1, 'Amputación', 'Pérdida de una extremidad o parte de ella.'),
(4, 1, 'Espasticidad', 'Rigidez muscular que dificulta el movimiento.'),
(13, 2, 'Visión parcial', 'Limitaciones en la visión.'),
(14, 2, 'Ceguera total', 'Ausencia completa de visión.'),
(15, 2, 'Dificultad auditiva', 'Pérdida parcial de la audición.'),
(16, 2, 'Sordera total', 'Incapacidad para escuchar sonidos.'),
(17, 2, 'Discapacidad olfativa', 'Pérdida del sentido del olfato.'),
(18, 2, 'Discapacidad del gusto', 'Pérdida del sentido del gusto.'),
(19, 3, 'Retraso mental', 'Funcionamiento intelectual inferior al promedio.'),
(20, 3, 'Dificultades en el aprendizaje', 'Problemas en el desarrollo del lenguaje y habilidades sociales.'),
(21, 3, 'Trastornos del espectro autista', 'Dificultades en la comunicación y comportamiento.'),
(22, 3, 'Esquizofrenia', 'Alteraciones en el pensamiento y la percepción.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estdcasa`
--

CREATE TABLE `estdcasa` (
  `id` int(11) NOT NULL,
  `estdCasa` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estdcasa`
--

INSERT INTO `estdcasa` (`id`, `estdCasa`) VALUES
(1, 'Propia'),
(2, 'Alquilada'),
(3, 'Prestada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lider_calle`
--

CREATE TABLE `lider_calle` (
  `id_persona` int(11) NOT NULL,
  `id_calle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mgacademico`
--

CREATE TABLE `mgacademico` (
  `id` int(11) NOT NULL,
  `mgAcademico` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mgacademico`
--

INSERT INTO `mgacademico` (`id`, `mgAcademico`) VALUES
(1, 'Educación Inicial'),
(2, 'Educación Basica'),
(3, 'Educación Media'),
(4, 'Educación Superior');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nacionalidad`
--

CREATE TABLE `nacionalidad` (
  `id` int(11) NOT NULL,
  `nacionalidad` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `nacionalidad`
--

INSERT INTO `nacionalidad` (`id`, `nacionalidad`) VALUES
(2, 'Venezolano'),
(3, 'Extranjero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `pNombre` varchar(15) NOT NULL,
  `sNombre` varchar(15) NOT NULL,
  `pApellido` varchar(15) NOT NULL,
  `sApellido` varchar(15) NOT NULL,
  `cedula` int(9) DEFAULT NULL,
  `id_sexo` int(11) NOT NULL,
  `fechaN` date NOT NULL,
  `id_mgAcademico` int(11) NOT NULL,
  `id_nacionalidad` int(11) NOT NULL,
  `telefono` double DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `id_rolFamiliar` int(11) NOT NULL,
  `idCasa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `pNombre`, `sNombre`, `pApellido`, `sApellido`, `cedula`, `id_sexo`, `fechaN`, `id_mgAcademico`, `id_nacionalidad`, `telefono`, `correo`, `id_rolFamiliar`, `idCasa`) VALUES
(518, 'Saturnina', 'Saturnina', 'Baron', 'Baron', 5071354, 6, '1957-07-04', 3, 2, 0, '2', 1, 201),
(519, 'Pedro', 'Pedro', 'Ramirez', 'Ramirez', 5071611, 5, '1973-08-24', 2, 2, 0, '2', 2, 201),
(520, 'Junior', 'Junior', 'Ramirez', 'Ramirez', 27862703, 5, '1969-06-15', 3, 2, 0, '2', 3, 201),
(521, 'Carmen', 'Carmen', 'Arias', 'Arias', 12511919, 6, '1973-08-24', 2, 2, 0, '0', 3, 202),
(523, 'Ana', 'Ana', 'Ramirez', 'Ramirez', 32167535, 6, '2006-08-10', 3, 2, 0, 'Correo', 3, 202),
(524, 'Alexandra', 'Alexandra', 'Ramirez', 'Ramirez', 0, 6, '2008-03-10', 2, 2, 0, 'Correo', 3, 202),
(529, 'Jose', 'Jose', 'Pinto', 'Pinto', 0, 5, '2015-11-10', 1, 2, 0, 'Correo', 1, 202),
(536, 'Richard', 'Richard', 'Ferrer', 'Ferrer', 10497800, 5, '1969-06-15', 3, 2, 0, 'Correo', 1, 203),
(541, 'Modesta', 'Modesta', 'Cario', 'Cario', 5143073, 6, '1949-02-25', 3, 2, 0, 'Correo', 1, 206),
(542, 'Jesus', 'Jesus', 'Cario', 'Cario', 627743, 5, '1976-02-19', 3, 2, 0, 'Correo', 3, 206),
(561, 'ana', 'ana', 'sotomayor', 'sotomayor', 14295233, 6, '1979-10-10', 2, 2, 0, 'Correo', 1, 213),
(562, 'ruth', 'ruth', 'tomas', 'tomas', 33466590, 6, '2008-01-08', 1, 2, 0, 'Correo', 3, 213),
(563, 'luisana', 'luisana', 'alagal', 'alagal', 0, 6, '2016-02-03', 1, 2, 0, 'Correo', 3, 213),
(564, 'yolianni', 'yolianni', 'tomas', 'tomas', 28518874, 6, '2001-11-25', 4, 2, 0, 'Correo', 1, 214),
(565, 'Alexander', 'alxander', 'tomas', 'tomas', 0, 5, '2015-02-08', 1, 2, 0, 'Correo', 3, 214),
(567, 'Ezequiel', 'Isacc', 'Ardiles', 'Ardiles', 31625450, 5, '2005-10-28', 3, 2, 22, 'Correo', 3, 201),
(568, 'Rosmeydi', 'Rosmeydi', 'Suarez', 'Suarez', 23393447, 6, '1996-11-12', 4, 2, 9, 'Correo', 1, 215),
(570, 'Danyerlis', 'Danyerlis', 'Teneria', 'Teneria', 0, 6, '2014-12-02', 2, 2, 0, 'Correo', 3, 215),
(571, 'Davianyer', 'Davianyer', 'Teneria', 'Teneria', 0, 5, '2017-01-31', 1, 2, 0, 'Correo', 3, 215),
(572, 'Danyer', 'Danyer', 'Teneria', 'Teneria', 0, 5, '2012-11-02', 2, 2, 0, 'Correo', 3, 215),
(573, 'Nataly', 'Nataly', 'Zamora', 'Zamor', 23966075, 6, '1993-01-20', 4, 2, 0, 'Correo', 1, 216),
(574, 'Carlos', 'Carlos', 'Navas', 'Navas', 16193093, 5, '1985-07-12', 4, 2, 0, 'Correo', 2, 216),
(575, 'Javier', 'Javier', 'Navas', 'Navas', 0, 5, '2013-05-31', 2, 2, 0, 'Correo', 3, 216),
(576, 'Beronica', 'Beronica', 'Navas', 'Navas', 0, 6, '2015-11-03', 1, 2, 0, 'Correo', 3, 216),
(577, 'Charlene', 'Charlene', 'Navas', 'Navas', 0, 6, '2017-02-09', 1, 2, 0, 'Correo', 3, 216),
(578, 'Davianyerlis', 'Davianyerlis', 'Teneria', 'Teneria', 0, 6, '2019-04-09', 1, 2, 0, 'Correo', 3, 215),
(579, 'Angie', 'Angie', 'Caldera', 'Caldera', 27408896, 6, '1998-06-03', 4, 2, 0, 'Correo', 1, 217),
(580, 'Asley', 'Asley', 'Caldera', 'Caldera', 0, 6, '2019-10-29', 1, 2, 0, 'Correo', 3, 217),
(581, 'Hilda', 'Hilda', 'Carrasquel', 'Carrasquel', 10496065, 6, '1967-08-13', 4, 2, 0, 'Correo', 1, 218),
(582, 'Karla', 'Karla', 'Tomas', 'Tomas', 34670835, 6, '2012-10-25', 2, 2, 0, 'Correo', 1, 219),
(583, 'Lyan', 'Lyan', 'Tomas', 'Tomas', 0, 5, '2021-03-21', 1, 2, 0, 'Correo', 3, 219),
(584, 'Juana', 'Juana', 'Marquez', 'Marquez', 20389432, 6, '1990-10-11', 3, 2, 0, 'Correo', 1, 220),
(585, 'Pedro', 'Pedro', 'Romero', 'Romero', 17082554, 5, '1982-03-23', 2, 2, 0, 'Correo', 2, 220),
(586, 'Mary', 'Mary', 'Romero', 'Romer', 0, 6, '2007-02-13', 2, 2, 0, 'Correo', 3, 220),
(587, 'Katerin', 'Katerin', 'Romero', 'Romero', 0, 6, '2018-06-24', 1, 2, 0, 'Correo', 3, 220),
(588, 'Katerin', 'Katerin', 'Romero', 'Romero', 0, 6, '2018-06-24', 1, 2, 0, 'Correo', 3, 221),
(589, 'Danni', 'Danni', 'Teneria', 'Teneria', 18596761, 5, '1987-04-09', 4, 2, 0, 'Correo', 1, 221),
(590, 'Rufino', 'Rufino', 'Duque', 'Duque', 7198098, 5, '1955-11-16', 4, 2, 0, 'Correo', 1, 222),
(591, 'Pabel', 'Pabel', 'Corbada', 'Corbada', 14517740, 5, '1957-03-01', 3, 2, 0, 'Correo', 1, 223),
(592, 'Nayibeth', 'Nayibeth', 'Iguaro', 'Iguaro', 27045451, 6, '1995-11-05', 4, 2, 0, 'Correo', 1, 224),
(593, 'Felix', 'Felix', 'Benavente', 'Benavente', 5707211, 6, '1954-11-11', 3, 2, 9, 'Correo', 1, 225),
(594, 'Argenis', 'Argenis', 'Isturiz', 'Isturiz', 23101488, 5, '1993-03-23', 4, 2, 9889, 'Correo', 1, 226),
(595, 'Yesica', 'Yesica', 'Soto', 'Soto', 26393848, 6, '1997-09-06', 4, 2, 9999, 'Correo', 1, 227),
(596, 'Rusmely', 'Rusmely', 'Alayon', 'Alayon', 25663532, 6, '1994-02-15', 4, 2, 0, 'Correo', 1, 228),
(597, 'Jesus', 'Jesus', 'Martinez', 'Martinez', 0, 5, '2015-01-20', 2, 2, 0, 'Correo', 3, 228),
(598, 'Iransuy', 'Iransuy', 'Mota', 'Mota', 20756179, 6, '1989-11-10', 4, 2, 0, 'Correo', 1, 229),
(599, 'Irene', 'Irene', 'Ramirez', 'Remirez', 8418095, 6, '1955-05-05', 4, 2, 0, 'Correo', 1, 230),
(600, 'Henry', 'Henry', 'Laya', 'Laya', 13144226, 5, '1976-07-21', 3, 2, 0, 'Correo', 1, 231),
(601, 'Ana', 'Ana', 'Amarima', 'Amarima', 17371449, 6, '1985-10-07', 4, 2, 0, 'Correo', 1, 232),
(602, 'Freddy', 'Freddy', 'Bandres', 'Bandres', 29899388, 5, '2004-12-23', 3, 2, 9887, 'Correo', 1, 233),
(603, 'Betty', 'Betty', 'Tovar', 'Tovar', 17371995, 6, '1984-09-09', 4, 2, 0, 'Correo', 1, 234),
(604, 'Elioza', 'Elioza', 'Belisario', 'Belisario', 0, 6, '2012-12-28', 1, 2, 0, 'Correo', 3, 234),
(605, 'Eliel', 'Eliel', 'Belisario', 'Belisario', 0, 5, '2015-12-09', 1, 2, 0, 'Correo', 2, 234),
(606, 'Carmen', 'Carmen', 'Loreto', 'Loreto', 10499174, 6, '1970-08-09', 4, 2, 0, 'Correo', 1, 235),
(607, 'Juan', 'Juan', 'Alvarado', 'Alvarado', 958443, 5, '1965-10-28', 4, 2, 0, 'Correo', 2, 235),
(608, 'Rosmary', '', 'Pimentel', 'Pimentel', 20714922, 6, '1991-11-26', 4, 2, 0, 'Correo', 1, 236),
(609, 'Carlos', '', 'Mendoza', 'Mendoza', 20714922, 5, '1992-08-27', 4, 2, 0, 'Correo', 2, 236),
(610, 'Angel', '', 'Mendoza', 'Mendoza', 0, 5, '2012-04-19', 1, 2, 0, '0', 3, 236),
(611, 'Jesus', '', 'Matey', 'Matey', 26830990, 5, '1999-08-19', 3, 2, 0, 'Correo', 1, 237),
(612, 'Wuilmary', '', 'Tesara', 'Tesara', 28563156, 6, '2021-05-22', 3, 2, 0, 'Correo', 1, 238),
(613, 'Alexander', '', 'Isturiz', 'Isturiz', 27862419, 5, '1997-04-13', 3, 2, 0, 'Correo', 2, 238),
(614, 'Wuilianny', '', 'Isturiz', 'Isturiz', 0, 6, '2021-05-14', 1, 2, 0, 'Correo', 3, 238),
(615, 'Juana', '', 'Bolvar', 'Bolvar', 8789597, 6, '1994-05-22', 4, 2, 0, 'Correo', 1, 239),
(616, 'Pedro', '', 'Bolvar', 'Bolvar', 11367003, 6, '1967-06-01', 4, 2, 909, 'Correo', 2, 239),
(617, 'Alexandra', '', 'Salazar', 'Salazar', 25910792, 6, '1994-02-06', 4, 2, 0, 'Correo', 1, 240),
(618, 'Kleiyer', '', 'Marin', 'Marin', 0, 5, '2014-08-21', 2, 2, 0, 'Correo', 3, 240),
(619, 'Lkk', '', 'Ss', 'Ss', 23232, 5, '2020-01-07', 3, 2, 2, 'Correo', 1, 241),
(620, 'Dfasdf', '', 'Dfasdf', 'Adsf', 343434, 6, '2022-04-27', 2, 2, 33, 'Correo', 2, 241),
(621, 'Berta', '', 'De-quintana', 'De-quintana', 10496767, 6, '1971-08-23', 4, 2, 0, 'Correo', 1, 242),
(622, 'Paulino', '', 'Quintana', 'Quintana', 7293584, 5, '1953-05-13', 4, 2, 0, 'Correo', 2, 242),
(623, 'Jose', '', 'Pinate', 'Pinate', 8420327, 5, '1959-01-09', 3, 2, 0, 'Correo', 1, 243),
(624, 'Albert', '', 'Pinate', 'Pinate', 19961101, 5, '1992-04-29', 4, 2, 0, 'Correo', 2, 243),
(625, 'Yesica', '', 'Palma', 'Palma', 19638399, 6, '1989-10-06', 4, 2, 0, 'Correo', 3, 244),
(626, 'Yohancel', '', 'Punchilppy', 'Punchilppy', 0, 5, '2011-12-18', 2, 2, 0, 'Correo', 3, 244),
(627, 'Adelaida', '', 'Baron', 'Baron', 8418338, 6, '1963-06-10', 3, 2, 0, 'Correo', 1, 245),
(628, 'Yoander', '', 'Baron', 'Baron', 31944343, 5, '2005-06-20', 3, 2, 0, 'Correo', 3, 245),
(629, 'Maria', '', 'Olivares', 'Olivares', 2211889, 6, '2035-11-12', 2, 2, 0, 'Correo', 1, 246),
(630, 'Jose', '', 'Lopez', 'Lopez', 6691627, 5, '1959-09-11', 4, 2, 0, 'Correo', 3, 246),
(631, 'Chuck', '', 'Norrys', 'Norrys', 10202202, 5, '1960-10-22', 4, 2, 911, 'Correo', 1, 247),
(632, 'El', '', 'Padrino', 'Padrino', 202930, 5, '2040-04-09', 4, 2, 0, 'Correo', 1, 248),
(633, 'Jhon', '', 'Wik', 'Wik', 92001, 5, '2000-01-04', 3, 2, 9, 'Correo', 3, 248),
(634, 'Asdflkjsadfasfd', '', 'Asdfasdf', 'Adsfasdf', 333, 5, '2020-01-22', 2, 2, 12, 'Correo', 1, 249),
(635, 'Ijljslfjasjdf', '', 'Asdfas', 'Fd', 2323, 5, '2000-01-22', 3, 2, 0, 'Correo', 3, 249),
(636, 'Dasdf', '', 'Afds', 'Adsf', 2, 5, '2020-04-22', 2, 2, 0, 'Correo', 1, 250),
(637, 'Rahek', 'Rahek', 'Bombay', 'Bombay', 232323231, 5, '2000-10-22', 3, 2, 1, 'Correo', 2, 250),
(638, 'Asdfasdfasdf', '', 'Asdfasdf', 'Fdasfd', 333333, 5, '2041-10-22', 4, 2, 2323, 'Correo', 1, 251),
(639, 'Afsasdf', '', 'Asdfasdf', 'Asdfdsf', 333333, 5, '1946-05-15', 4, 2, 2, 'Correo', 2, 251),
(640, 'Asdfasdf', '', 'Hoadf', 'Asdfh', 232423, 5, '1980-01-22', 2, 2, 34, 'Correo', 1, 252),
(641, 'Jdlj;s;fad', '', 'Asfdlasf', 'Agfgadf', 323, 6, '1981-02-22', 4, 2, 22, 'Correo', 2, 252),
(642, 'Aksdfas', '', 'Asdf', 'Asdf', 33434, 5, '1961-01-03', 3, 2, 9090, 'Correo', 1, 253),
(643, 'Asfasfdad', '', 'Asdfasfdasf', 'ADfsdf', 343, 6, '2020-01-07', 3, 2, 9898, 'Correo', 2, 253),
(644, 'Carlos', '', 'Vive', 'Vive', 121, 5, '1980-01-22', 4, 2, 0, 'Correo', 1, 254),
(645, 'Carla', 'Karla', 'Mata', 'Mata', 12012, 6, '2001-12-21', 3, 3, 4166661, 'Correo', 2, 254),
(646, 'Olalola', '', 'Lalaosl', 'Lsjdlfjidf', 3434343, 5, '2000-10-22', 3, 2, 9, 'Correo', 2, 254),
(647, 'Yusmeli', 'Yusmeli', 'Sifonte', 'Sifonte', 21231797, 6, '1990-09-27', 3, 2, 0, 'Correo', 1, 255),
(648, 'Ana', 'Ana', 'Tomas', 'Tomas', 27862345, 6, '2000-12-21', 3, 2, 0, 'Correo', 1, 256),
(649, 'Odalis', 'Odalis', 'Munos', 'Munos', 23966189, 6, '1990-07-02', 4, 2, 0, 'Correo', 1, 257),
(650, 'Richard', 'Richard', 'Apontes', 'Apontes', 17371879, 5, '1985-01-13', 3, 2, 0, 'Correo', 2, 257),
(651, 'Cristian', 'Cristian', 'Aponte', 'Aponte', 33588997, 5, '2007-09-23', 2, 2, 0, 'Correo', 3, 257),
(652, 'Crisdaly', 'Crisdaly', 'Aponte', 'Aponte', 33635615, 6, '2009-05-06', 2, 2, 0, 'Correo', 3, 257),
(653, 'Anderson', 'Anderson', 'Aponte', 'Aponte', 0, 5, '2013-02-09', 1, 2, 0, 'Correo', 3, 257),
(654, 'Eliana', 'Eliana', 'Marin', 'Marin', 20398706, 6, '1988-01-10', 4, 2, 0, 'Correo', 1, 258),
(655, 'Endry', 'Endry', 'Rodriges', 'Rodriges', 0, 5, '2006-10-30', 3, 2, 0, 'Correo', 3, 258),
(656, 'Franklin', 'Franklin', 'Rodrigez', 'Rodrigez', 0, 5, '2014-01-09', 1, 2, 0, 'Correo', 3, 258),
(657, 'Maria', 'Maria', 'Pinate', 'Pinate', 19275343, 6, '1982-09-16', 4, 2, 0, 'Correo', 1, 259),
(658, 'Hector', 'Hector', 'Gonzalez', 'Gonzalez', 23563527, 5, '1988-09-02', 3, 2, 0, 'Correo', 2, 259),
(659, 'Yesenia', 'Yesenia', 'Alvarado', 'Alvarado', 23563459, 6, '1995-03-04', 4, 2, 0, 'Correo', 1, 260),
(660, 'Valentina', 'Valentina', 'Alvarado', 'Alvarado', 33328318, 6, '2010-04-28', 2, 2, 0, 'Correo', 3, 260),
(661, 'Isabela', 'Isabela', 'Arevalo', 'Arevalo', 0, 6, '2017-09-02', 1, 2, 0, 'Correo', 3, 260);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `p_discapacidad`
--

CREATE TABLE `p_discapacidad` (
  `idPersona` int(11) NOT NULL,
  `idDiscpacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `p_discapacidad`
--

INSERT INTO `p_discapacidad` (`idPersona`, `idDiscpacidad`) VALUES
(586, 15),
(589, 20),
(590, 13),
(590, 15),
(591, 13),
(591, 15),
(593, 17),
(593, 18),
(593, 3),
(593, 15),
(594, 17),
(594, 18),
(594, 3),
(594, 15),
(597, 17),
(597, 3),
(597, 4),
(598, 17),
(598, 3),
(598, 4),
(601, 13),
(601, 14),
(602, 13),
(602, 14),
(604, 1),
(604, 2),
(605, 1),
(605, 2),
(606, 1),
(606, 13),
(606, 15),
(607, 22),
(609, 3),
(610, 1),
(610, 2),
(610, 4),
(610, 13),
(610, 14),
(610, 15),
(610, 16),
(610, 17),
(610, 18),
(610, 19),
(610, 20),
(610, 21),
(611, 1),
(611, 2),
(611, 4),
(611, 13),
(611, 14),
(611, 15),
(611, 16),
(611, 17),
(611, 18),
(611, 19),
(611, 20),
(611, 21),
(613, 19),
(613, 13),
(614, 16),
(614, 22),
(616, 19),
(616, 22),
(616, 13),
(618, 18),
(618, 13),
(619, 14),
(620, 2),
(620, 4),
(620, 13),
(621, 2),
(621, 4),
(621, 14),
(622, 3),
(622, 13),
(623, 13),
(623, 15),
(624, 17),
(624, 18),
(625, 1),
(625, 2),
(625, 3),
(625, 4),
(625, 13),
(625, 14),
(625, 17),
(625, 19),
(625, 20),
(625, 21),
(625, 22),
(626, 15),
(626, 16),
(626, 18),
(628, 18),
(628, 13),
(629, 1),
(629, 13),
(629, 15),
(630, 17),
(630, 18),
(631, 1),
(631, 2),
(631, 3),
(631, 4),
(631, 13),
(631, 14),
(631, 15),
(631, 16),
(631, 17),
(631, 18),
(631, 19),
(631, 20),
(631, 21),
(631, 22),
(633, 1),
(633, 2),
(633, 3),
(633, 4),
(633, 13),
(633, 14),
(633, 15),
(633, 16),
(633, 17),
(633, 18),
(633, 19),
(633, 20),
(633, 21),
(633, 22),
(634, 1),
(634, 2),
(634, 3),
(634, 4),
(634, 13),
(635, 16),
(635, 17),
(635, 18),
(635, 19),
(635, 14),
(635, 15),
(636, 1),
(636, 4),
(636, 13),
(639, 1),
(639, 2),
(639, 3),
(639, 4),
(639, 13),
(639, 14),
(639, 15),
(639, 16),
(639, 17),
(639, 18),
(639, 19),
(639, 20),
(639, 21),
(639, 22),
(641, 22),
(643, 1),
(644, 22),
(645, 22),
(645, 17),
(645, 15),
(647, 15),
(648, 13),
(650, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rolfamiliar`
--

CREATE TABLE `rolfamiliar` (
  `id` int(11) NOT NULL,
  `rolFamiliar` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rolfamiliar`
--

INSERT INTO `rolfamiliar` (`id`, `rolFamiliar`) VALUES
(1, 'Jefe de Familia'),
(2, 'Esposo(a)'),
(3, 'Hijo(a)');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sexo`
--

CREATE TABLE `sexo` (
  `id` int(11) NOT NULL,
  `sexo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sexo`
--

INSERT INTO `sexo` (`id`, `sexo`) VALUES
(5, 'Hombre'),
(6, 'Mujer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_discapacidad`
--

CREATE TABLE `tipo_discapacidad` (
  `id` int(11) NOT NULL,
  `tipo` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_discapacidad`
--

INSERT INTO `tipo_discapacidad` (`id`, `tipo`) VALUES
(1, 'física o motora'),
(2, 'sensorial'),
(3, 'intelectual o cognitiva');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `user` varchar(25) NOT NULL,
  `psw` varchar(25) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `cemilla` varchar(11) NOT NULL,
  `pregunta` varchar(50) DEFAULT NULL,
  `respuesta` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`user`, `psw`, `tipo`, `cemilla`, `pregunta`, `respuesta`) VALUES
('campos', 'campos', 'Administrador', '60 60 60 80', NULL, NULL),
('Carmen', 'Nuevo', 'Basico', '40 40 40 40', NULL, NULL),
('isacc', 'isacc', 'Administrador', '95 60 20 10', NULL, NULL),
('Root', 'Toor', 'Basico', '80 30 50 50', 'Cual es la velocidad de la luz', 'No se xd'),
('user', 'root', 'admin', '0', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `casa`
--
ALTER TABLE `casa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_estdCasa` (`id_estdCasa`),
  ADD KEY `id_direccion` (`id_direccion`);

--
-- Indices de la tabla `controldelogin`
--
ALTER TABLE `controldelogin`
  ADD PRIMARY KEY (`estado`);

--
-- Indices de la tabla `demografia`
--
ALTER TABLE `demografia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `demografia_ibfk_1` (`id_lider`);

--
-- Indices de la tabla `discapacidad`
--
ALTER TABLE `discapacidad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipo` (`id_tipo`);

--
-- Indices de la tabla `estdcasa`
--
ALTER TABLE `estdcasa`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `lider_calle`
--
ALTER TABLE `lider_calle`
  ADD UNIQUE KEY `id_calle` (`id_calle`),
  ADD UNIQUE KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `mgacademico`
--
ALTER TABLE `mgacademico`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `nacionalidad`
--
ALTER TABLE `nacionalidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `persona_ibfk_1` (`id_mgAcademico`),
  ADD KEY `persona_ibfk_2` (`id_nacionalidad`),
  ADD KEY `persona_ibfk_3` (`id_sexo`),
  ADD KEY `persona_ibfk_4` (`id_rolFamiliar`),
  ADD KEY `idCasa` (`idCasa`);

--
-- Indices de la tabla `p_discapacidad`
--
ALTER TABLE `p_discapacidad`
  ADD KEY `idDiscpacidad` (`idDiscpacidad`),
  ADD KEY `idPersona` (`idPersona`);

--
-- Indices de la tabla `rolfamiliar`
--
ALTER TABLE `rolfamiliar`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sexo`
--
ALTER TABLE `sexo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_discapacidad`
--
ALTER TABLE `tipo_discapacidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `casa`
--
ALTER TABLE `casa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=261;

--
-- AUTO_INCREMENT de la tabla `demografia`
--
ALTER TABLE `demografia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `discapacidad`
--
ALTER TABLE `discapacidad`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `estdcasa`
--
ALTER TABLE `estdcasa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `nacionalidad`
--
ALTER TABLE `nacionalidad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=662;

--
-- AUTO_INCREMENT de la tabla `rolfamiliar`
--
ALTER TABLE `rolfamiliar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sexo`
--
ALTER TABLE `sexo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tipo_discapacidad`
--
ALTER TABLE `tipo_discapacidad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `casa`
--
ALTER TABLE `casa`
  ADD CONSTRAINT `casa_ibfk_1` FOREIGN KEY (`id_estdCasa`) REFERENCES `estdcasa` (`id`),
  ADD CONSTRAINT `casa_ibfk_2` FOREIGN KEY (`id_direccion`) REFERENCES `demografia` (`id`);

--
-- Filtros para la tabla `demografia`
--
ALTER TABLE `demografia`
  ADD CONSTRAINT `demografia_ibfk_1` FOREIGN KEY (`id_lider`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `discapacidad`
--
ALTER TABLE `discapacidad`
  ADD CONSTRAINT `discapacidad_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_discapacidad` (`id`);

--
-- Filtros para la tabla `lider_calle`
--
ALTER TABLE `lider_calle`
  ADD CONSTRAINT `lider_calle_ibfk_1` FOREIGN KEY (`id_calle`) REFERENCES `demografia` (`id`),
  ADD CONSTRAINT `lider_calle_ibfk_2` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`id_mgAcademico`) REFERENCES `mgacademico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persona_ibfk_2` FOREIGN KEY (`id_nacionalidad`) REFERENCES `nacionalidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persona_ibfk_3` FOREIGN KEY (`id_sexo`) REFERENCES `sexo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persona_ibfk_4` FOREIGN KEY (`id_rolFamiliar`) REFERENCES `rolfamiliar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persona_ibfk_5` FOREIGN KEY (`idCasa`) REFERENCES `casa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `p_discapacidad`
--
ALTER TABLE `p_discapacidad`
  ADD CONSTRAINT `p_discapacidad_ibfk_1` FOREIGN KEY (`idDiscpacidad`) REFERENCES `discapacidad` (`id`),
  ADD CONSTRAINT `p_discapacidad_ibfk_2` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
