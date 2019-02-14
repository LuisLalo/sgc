-- MySQL Script generated by MySQL Workbench
-- Wed May  2 08:19:43 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bd_sgc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd_sgc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_sgc` DEFAULT CHARACTER SET utf8 ;
USE `bd_sgc` ;

-- -----------------------------------------------------
-- Table `bd_sgc`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`departamento` (
  `id_departamento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  `ruta` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `nomenclatura` VARCHAR(5) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id_departamento`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`puesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`puesto` (
  `id_puesto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_puesto`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`rol` (
  `id_rol` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_rol`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`usuario` (
  `num_empleado` INT(11) NOT NULL,
  `nombres` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `apellidos` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `correo` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `contrasena` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `id_puesto` INT(11) NOT NULL,
  `id_departamento` INT(11) NOT NULL,
  `id_rol` INT(11) NOT NULL,
  `estatus` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`num_empleado`),
  UNIQUE INDEX `num_empleado_UNIQUE` (`num_empleado` ASC),
  INDEX `fk_usuarios_puesto_idx` (`id_puesto` ASC),
  INDEX `fk_usuarios_rol1_idx` (`id_rol` ASC),
  INDEX `fk_Usuario_Departamento1_idx` (`id_departamento` ASC),
  CONSTRAINT `fk_Usuario_Departamento1`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `bd_sgc`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_puesto`
    FOREIGN KEY (`id_puesto`)
    REFERENCES `bd_sgc`.`puesto` (`id_puesto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_rol1`
    FOREIGN KEY (`id_rol`)
    REFERENCES `bd_sgc`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`actividad_accion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`actividad_accion` (
  `id_actividad` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_estimada` DATETIME NOT NULL,
  `fecha_real` DATETIME NOT NULL,
  `porcentaje_avance` INT(11) NOT NULL,
  `descripcion` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `responsable` INT(11) NOT NULL,
  `accion_proyecto` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_actividad`),
  INDEX `fk_actividades_acciones_usuarios1_idx` (`responsable` ASC),
  CONSTRAINT `fk_actividades_acciones_usuarios1`
    FOREIGN KEY (`responsable`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`causa_raiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`causa_raiz` (
  `id_causa_raiz` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_causa_raiz`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`estatus_accion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`estatus_accion` (
  `id_estatus` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_estatus`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`proviene_de`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`proviene_de` (
  `id_proviene_de` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_proviene_de`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`tipo_auditoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`tipo_auditoria` (
  `id_tipo_auditoria` INT(11) NOT NULL,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_auditoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`accion_correctiva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`accion_correctiva` (
  `id_accion` INT(11) NOT NULL AUTO_INCREMENT,
  `proviene_de` INT(11) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `numero_auditoria` INT(11) NOT NULL,
  `tipo_auditoria` INT(11) NOT NULL,
  `observacion_norma` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `departamento` INT(11) NOT NULL,
  `responsable_solventar` INT(11) NOT NULL,
  `responsable_verificar` INT(11) NOT NULL,
  `causa_raiz` INT(11) NOT NULL,
  `descripcion_causa_raiz` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `accion_correctiva` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fecha_aplicacion` DATETIME NOT NULL,
  `id_estatus_accion` INT(11) NOT NULL,
  `resultado` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `numero_accion` INT(11) NOT NULL,
  PRIMARY KEY (`id_accion`),
  INDEX `fk_acciones_correctivas_estatus_acciones1_idx` (`id_estatus_accion` ASC),
  INDEX `fk_acciones_correctivas_usuarios1_idx` (`responsable_solventar` ASC),
  INDEX `fk_acciones_correctivas_usuarios2_idx` (`responsable_verificar` ASC),
  INDEX `fk_acciones_correctivas_proviene_de1_idx` (`proviene_de` ASC),
  INDEX `fk_acciones_correctivas_causa_raiz1_idx` (`causa_raiz` ASC),
  INDEX `fk_acciones_correctivas_departamentos1_idx` (`departamento` ASC),
  INDEX `fk_acciones_correctivas_tipo_auditoria1_idx` (`tipo_auditoria` ASC),
  INDEX `fk_acciones_correctivas_actividades_acciones1_idx` (`numero_accion` ASC),
  CONSTRAINT `fk_acciones_correctivas_actividades_acciones1`
    FOREIGN KEY (`numero_accion`)
    REFERENCES `bd_sgc`.`actividad_accion` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_causa_raiz1`
    FOREIGN KEY (`causa_raiz`)
    REFERENCES `bd_sgc`.`causa_raiz` (`id_causa_raiz`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_departamentos1`
    FOREIGN KEY (`departamento`)
    REFERENCES `bd_sgc`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_estatus_acciones1`
    FOREIGN KEY (`id_estatus_accion`)
    REFERENCES `bd_sgc`.`estatus_accion` (`id_estatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_proviene_de1`
    FOREIGN KEY (`proviene_de`)
    REFERENCES `bd_sgc`.`proviene_de` (`id_proviene_de`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_tipo_auditoria1`
    FOREIGN KEY (`tipo_auditoria`)
    REFERENCES `bd_sgc`.`tipo_auditoria` (`id_tipo_auditoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_usuarios1`
    FOREIGN KEY (`responsable_solventar`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acciones_correctivas_usuarios2`
    FOREIGN KEY (`responsable_verificar`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`clasificador_actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`clasificador_actividad` (
  `id_clasificador_actividad` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `color` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_clasificador_actividad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`actividad` (
  `id_actividad` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_inicio` DATE NOT NULL,
  `fecha_final` DATE NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `hora_final` TIME NOT NULL,
  `descripcion` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `id_usuario` INT(11) NOT NULL,
  `id_clasificador_actividad` INT(11) NOT NULL,
  `lugar` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `estatus` INT(11) NOT NULL,
  PRIMARY KEY (`id_actividad`),
  INDEX `fk_actividades_clasificador_actividades1_idx` (`id_clasificador_actividad` ASC),
  CONSTRAINT `fk_actividades_clasificador_actividades1`
    FOREIGN KEY (`id_clasificador_actividad`)
    REFERENCES `bd_sgc`.`clasificador_actividad` (`id_clasificador_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`formulario_reunion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`formulario_reunion` (
  `id_reunion` INT(11) NOT NULL AUTO_INCREMENT,
  `asunto` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fecha_reunion` DATETIME NOT NULL,
  `objetivo` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_reunion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`acuerdo_reunion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`acuerdo_reunion` (
  `id_acuerdo` INT(11) NOT NULL AUTO_INCREMENT,
  `id_reunion` INT(11) NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fecha_vencimiento` DATETIME NOT NULL,
  `id_usuario_responsable` INT(11) NOT NULL,
  PRIMARY KEY (`id_acuerdo`),
  INDEX `fk_acuerdos_reuniones_formulario_reuniones1_idx` (`id_reunion` ASC),
  INDEX `fk_acuerdos_reuniones_usuarios1_idx` (`id_usuario_responsable` ASC),
  CONSTRAINT `fk_acuerdos_reuniones_formulario_reuniones1`
    FOREIGN KEY (`id_reunion`)
    REFERENCES `bd_sgc`.`formulario_reunion` (`id_reunion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acuerdos_reuniones_usuarios1`
    FOREIGN KEY (`id_usuario_responsable`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`acuerdo_respuesta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`acuerdo_respuesta` (
  `id_respuesta` INT(11) NOT NULL AUTO_INCREMENT,
  `id_acuerdo` INT(11) NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fecha_respuesta` DATETIME NOT NULL,
  `id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id_respuesta`),
  INDEX `fk_acuerdos_respuestas_acuerdos_reuniones1_idx` (`id_acuerdo` ASC),
  INDEX `fk_acuerdos_respuestas_usuarios1_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_acuerdos_respuestas_acuerdos_reuniones1`
    FOREIGN KEY (`id_acuerdo`)
    REFERENCES `bd_sgc`.`acuerdo_reunion` (`id_acuerdo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acuerdos_respuestas_usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`clasificador_documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`clasificador_documento` (
  `id_clasificador_documento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_clasificador_documento`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`tipo_archivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`tipo_archivo` (
  `id_tipo_archivo` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `ruta` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_tipo_archivo`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`tipo_documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`tipo_documento` (
  `id_tipo_documento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `ruta` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  `nomenclatura` VARCHAR(5) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`documento` (
  `id_documento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(75) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `ruta` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `id_tipo_archivo` INT(11) NOT NULL,
  `id_tipo_documento` INT(11) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `estatus` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `id_clasificador_documento` INT(11) NOT NULL,
  `orden_documento` INT(11) NOT NULL,
  `id_departamento` INT(11) NOT NULL,
  PRIMARY KEY (`id_documento`),
  UNIQUE INDEX `id_documentos_UNIQUE` (`id_documento` ASC),
  INDEX `fk_documentos_tipo_documento1_idx` (`id_tipo_documento` ASC),
  INDEX `fk_documentos_tipo_archivo1_idx` (`id_tipo_archivo` ASC),
  INDEX `fk_documentos_clasificador_documentos1_idx` (`id_clasificador_documento` ASC),
  INDEX `fk_documentos_departamentos1_idx` (`id_departamento` ASC),
  CONSTRAINT `fk_documentos_clasificador_documentos1`
    FOREIGN KEY (`id_clasificador_documento`)
    REFERENCES `bd_sgc`.`clasificador_documento` (`id_clasificador_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_documentos_departamentos1`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `bd_sgc`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_documentos_tipo_archivo1`
    FOREIGN KEY (`id_tipo_archivo`)
    REFERENCES `bd_sgc`.`tipo_archivo` (`id_tipo_archivo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_documentos_tipo_documento1`
    FOREIGN KEY (`id_tipo_documento`)
    REFERENCES `bd_sgc`.`tipo_documento` (`id_tipo_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`documento_formulario_reunion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`documento_formulario_reunion` (
  `id_documento` INT(11) NOT NULL AUTO_INCREMENT,
  `id_reunion` INT(11) NOT NULL,
  PRIMARY KEY (`id_documento`, `id_reunion`),
  INDEX `fk_formulario_reuniones_has_documentos_documentos1_idx` (`id_documento` ASC),
  INDEX `fk_formulario_reuniones_has_documentos_formulario_reuniones_idx` (`id_reunion` ASC),
  CONSTRAINT `fk_formulario_reuniones_has_documentos_documentos1`
    FOREIGN KEY (`id_documento`)
    REFERENCES `bd_sgc`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_formulario_reuniones_has_documentos_formulario_reuniones1`
    FOREIGN KEY (`id_reunion`)
    REFERENCES `bd_sgc`.`formulario_reunion` (`id_reunion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`frecuencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`frecuencia` (
  `id_frecuencia` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id_frecuencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`indicador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`indicador` (
  `id_indicador` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `objetivo` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `meta` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `id_frecuencia` INT(11) NOT NULL,
  `tipo_grafica` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `informacion_adicional` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `id_departamento` INT(11) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `estatus` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_indicador`),
  INDEX `fk_indicadores_frecuencia1_idx` (`id_frecuencia` ASC),
  INDEX `fk_indicadores_departamentos1_idx` (`id_departamento` ASC),
  CONSTRAINT `fk_indicadores_departamentos1`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `bd_sgc`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_indicadores_frecuencia1`
    FOREIGN KEY (`id_frecuencia`)
    REFERENCES `bd_sgc`.`frecuencia` (`id_frecuencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`expresion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`expresion` (
  `id_expresion` INT(11) NOT NULL AUTO_INCREMENT,
  `id_indicador` INT(11) NOT NULL,
  `expresion` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `valor_minimo` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `valor_maximo` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `color_rgb` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `simbolo_expresion` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_expresion`),
  INDEX `fk_expresiones_indicadores1_idx` (`id_indicador` ASC),
  CONSTRAINT `fk_expresiones_indicadores1`
    FOREIGN KEY (`id_indicador`)
    REFERENCES `bd_sgc`.`indicador` (`id_indicador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`recurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`recurso` (
  `id_recurso` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `costo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_recurso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`tecnica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`tecnica` (
  `id_tecnica` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_tecnica`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`tipo_proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`tipo_proyecto` (
  `id_tipo_proyecto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`id_tipo_proyecto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`proyecto_mejora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`proyecto_mejora` (
  `id_proyecto` INT(11) NOT NULL AUTO_INCREMENT,
  `numero_proyecto` INT(11) NOT NULL,
  `nombre` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `area_aplicacion` INT(11) NOT NULL,
  `proviene_de` INT(11) NOT NULL,
  `tipo_proyecto` INT(11) NOT NULL,
  `situacion_actual` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `situacion_deseada` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `area_responsable` INT(11) NOT NULL,
  `responsable` INT(11) NOT NULL,
  `id_tecnica` INT(11) NOT NULL,
  `id_recurso` INT(11) NOT NULL,
  PRIMARY KEY (`id_proyecto`),
  INDEX `fk_proyectos_mejora_departamentos1_idx` (`area_aplicacion` ASC),
  INDEX `fk_proyectos_mejora_departamentos2_idx` (`area_responsable` ASC),
  INDEX `fk_proyectos_mejora_tecnicas1_idx` (`id_tecnica` ASC),
  INDEX `fk_proyectos_mejora_recursos1_idx` (`id_recurso` ASC),
  INDEX `fk_proyectos_mejora_usuarios1_idx` (`responsable` ASC),
  INDEX `fk_proyectos_mejora_proviene_de1_idx` (`proviene_de` ASC),
  INDEX `fk_proyectos_mejora_tipo_proyecto1_idx` (`tipo_proyecto` ASC),
  INDEX `fk_proyectos_mejora_actividades_acciones1_idx` (`numero_proyecto` ASC),
  CONSTRAINT `fk_proyectos_mejora_actividades_acciones1`
    FOREIGN KEY (`numero_proyecto`)
    REFERENCES `bd_sgc`.`actividad_accion` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_departamentos1`
    FOREIGN KEY (`area_aplicacion`)
    REFERENCES `bd_sgc`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_departamentos2`
    FOREIGN KEY (`area_responsable`)
    REFERENCES `bd_sgc`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_proviene_de1`
    FOREIGN KEY (`proviene_de`)
    REFERENCES `bd_sgc`.`proviene_de` (`id_proviene_de`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_recursos1`
    FOREIGN KEY (`id_recurso`)
    REFERENCES `bd_sgc`.`recurso` (`id_recurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_tecnicas1`
    FOREIGN KEY (`id_tecnica`)
    REFERENCES `bd_sgc`.`tecnica` (`id_tecnica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_tipo_proyecto1`
    FOREIGN KEY (`tipo_proyecto`)
    REFERENCES `bd_sgc`.`tipo_proyecto` (`id_tipo_proyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_mejora_usuarios1`
    FOREIGN KEY (`responsable`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`resultado_indicador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`resultado_indicador` (
  `id_resultado` INT(11) NOT NULL AUTO_INCREMENT,
  `id_indicador` INT(11) NOT NULL,
  `frecuencia` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `valor` INT(11) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `anio` DATETIME NOT NULL,
  PRIMARY KEY (`id_resultado`),
  INDEX `fk_resultados_indicadores_indicadores1_idx` (`id_indicador` ASC),
  CONSTRAINT `fk_resultados_indicadores_indicadores1`
    FOREIGN KEY (`id_indicador`)
    REFERENCES `bd_sgc`.`indicador` (`id_indicador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`usuario_actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`usuario_actividad` (
  `num_empleado` INT(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` INT(11) NOT NULL,
  PRIMARY KEY (`num_empleado`, `id_actividad`),
  INDEX `fk_usuarios_has_actividades_actividades1_idx` (`id_actividad` ASC),
  INDEX `fk_usuarios_has_actividades_usuarios1_idx` (`num_empleado` ASC),
  CONSTRAINT `fk_usuarios_has_actividades_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `bd_sgc`.`actividad` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_actividades_usuarios1`
    FOREIGN KEY (`num_empleado`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`usuario_documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`usuario_documento` (
  `num_empleado` INT(11) NOT NULL AUTO_INCREMENT,
  `id_documento` INT(11) NOT NULL,
  PRIMARY KEY (`num_empleado`, `id_documento`),
  INDEX `fk_usuarios_has_documentos_documentos1_idx` (`id_documento` ASC),
  INDEX `fk_usuarios_has_documentos_usuarios1_idx` (`num_empleado` ASC),
  CONSTRAINT `fk_usuarios_has_documentos_documentos1`
    FOREIGN KEY (`id_documento`)
    REFERENCES `bd_sgc`.`documento` (`id_documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_documentos_usuarios1`
    FOREIGN KEY (`num_empleado`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `bd_sgc`.`usuario_formulario_reunion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sgc`.`usuario_formulario_reunion` (
  `num_empleado` INT(11) NOT NULL AUTO_INCREMENT,
  `id_reunion` INT(11) NOT NULL,
  PRIMARY KEY (`num_empleado`, `id_reunion`),
  INDEX `fk_usuarios_has_formulario_reuniones_formulario_reuniones1_idx` (`id_reunion` ASC),
  INDEX `fk_usuarios_has_formulario_reuniones_usuarios1_idx` (`num_empleado` ASC),
  CONSTRAINT `fk_usuarios_has_formulario_reuniones_formulario_reuniones1`
    FOREIGN KEY (`id_reunion`)
    REFERENCES `bd_sgc`.`formulario_reunion` (`id_reunion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_formulario_reuniones_usuarios1`
    FOREIGN KEY (`num_empleado`)
    REFERENCES `bd_sgc`.`usuario` (`num_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
