-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2024 a las 06:31:59
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_controlhorarouni_g1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_asignaturas`
--

CREATE TABLE `tbl_asignaturas` (
  `idAsignatura` int(10) NOT NULL,
  `nombreAsignatura` varchar(20) NOT NULL,
  `estado` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbl_asignaturas`
--

INSERT INTO `tbl_asignaturas` (`idAsignatura`, `nombreAsignatura`, `estado`) VALUES
(1, 'INGLES', 'Activo'),
(2, 'MATEMATICAS', 'Activo'),
(3, 'MINISTERIO', 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_catedraticos`
--

CREATE TABLE `tbl_catedraticos` (
  `idCatedratico` int(10) NOT NULL,
  `nombreCatedratico` varchar(30) NOT NULL,
  `direccionCatedratico` varchar(30) NOT NULL,
  `telefonoCatedratico` varchar(18) NOT NULL,
  `estado` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbl_catedraticos`
--

INSERT INTO `tbl_catedraticos` (`idCatedratico`, `nombreCatedratico`, `direccionCatedratico`, `telefonoCatedratico`, `estado`) VALUES
(1, 'Rosario', 'Lempira', '(504)    -  -  ', 'Activo'),
(2, 'Caleb', 'bence', '(504)    -  -  ', 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_horario`
--

CREATE TABLE `tbl_horario` (
  `idHorario` int(11) NOT NULL,
  `dia_semana` varchar(20) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `estado` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbl_horario`
--

INSERT INTO `tbl_horario` (`idHorario`, `dia_semana`, `hora_inicio`, `hora_fin`, `estado`) VALUES
(1, 'LUNES', '07:00:00', '07:45:00', 'Activo'),
(2, 'LUNES', '07:45:00', '08:30:00', 'Activo'),
(3, 'LUNES', '08:30:00', '09:15:00', 'Activo'),
(4, 'LUNES', '09:15:00', '10:00:00', 'Activo'),
(5, 'LUNES', '10:30:00', '11:15:00', 'Activo'),
(6, 'LUNES', '11:15:00', '11:59:00', 'Activo'),
(7, 'MARTES', '07:00:00', '07:45:00', 'Activo'),
(8, 'MARTES', '07:45:00', '08:30:00', 'Activo'),
(9, 'MARTES', '08:30:00', '09:15:00', 'Activo'),
(10, 'MARTES', '09:15:00', '10:30:00', 'Activo'),
(11, 'MARTES', '10:30:00', '11:15:00', 'Activo'),
(12, 'MARTES', '11:15:00', '12:00:00', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_imparte`
--

CREATE TABLE `tbl_imparte` (
  `idImparte` int(10) NOT NULL,
  `idCatedratico` int(10) NOT NULL,
  `idAsignatura` int(10) NOT NULL,
  `idHorario` int(11) NOT NULL,
  `estado` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbl_imparte`
--

INSERT INTO `tbl_imparte` (`idImparte`, `idCatedratico`, `idAsignatura`, `idHorario`, `estado`) VALUES
(4, 1, 1, 1, 'Activo'),
(5, 1, 1, 7, 'Activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_asignaturas`
--
ALTER TABLE `tbl_asignaturas`
  ADD PRIMARY KEY (`idAsignatura`);

--
-- Indices de la tabla `tbl_catedraticos`
--
ALTER TABLE `tbl_catedraticos`
  ADD PRIMARY KEY (`idCatedratico`);

--
-- Indices de la tabla `tbl_horario`
--
ALTER TABLE `tbl_horario`
  ADD PRIMARY KEY (`idHorario`);

--
-- Indices de la tabla `tbl_imparte`
--
ALTER TABLE `tbl_imparte`
  ADD PRIMARY KEY (`idImparte`),
  ADD KEY `idCatedratico` (`idCatedratico`),
  ADD KEY `idAsignatura` (`idAsignatura`),
  ADD KEY `idHorario` (`idHorario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_asignaturas`
--
ALTER TABLE `tbl_asignaturas`
  MODIFY `idAsignatura` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tbl_catedraticos`
--
ALTER TABLE `tbl_catedraticos`
  MODIFY `idCatedratico` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tbl_horario`
--
ALTER TABLE `tbl_horario`
  MODIFY `idHorario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tbl_imparte`
--
ALTER TABLE `tbl_imparte`
  MODIFY `idImparte` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbl_imparte`
--
ALTER TABLE `tbl_imparte`
  ADD CONSTRAINT `tbl_imparte_ibfk_1` FOREIGN KEY (`idCatedratico`) REFERENCES `tbl_catedraticos` (`idCatedratico`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_imparte_ibfk_2` FOREIGN KEY (`idAsignatura`) REFERENCES `tbl_asignaturas` (`idAsignatura`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_imparte_ibfk_3` FOREIGN KEY (`idHorario`) REFERENCES `tbl_horario` (`idHorario`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
