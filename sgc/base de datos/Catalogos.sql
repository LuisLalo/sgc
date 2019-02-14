-- -----------------------------------------------------
-- Querys para llenado de catálogos del SGC
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `bd_sgc`.`Rol`
-- -----------------------------------------------------
insert into `bd_sgc`.`Rol` values (1,'Usuario', 'Usuario General');
insert into `bd_sgc`.`Rol` values (2,'Administrador de Area', 'Administrador de Area');
insert into `bd_sgc`.`Rol` values (3,'Administrador del SGC', 'Administrador del SGC');
insert into `bd_sgc`.`Rol` values (4,'Auditor Interno', 'Auditor Interno');
insert into `bd_sgc`.`Rol` values (5,'Auditor Externo', 'Auditor Externo');
insert into `bd_sgc`.`Rol` values (6,'Invitado', 'Invitado');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Puesto`
-- -----------------------------------------------------
insert into `bd_sgc`.`Puesto` values(1,'Auxiliar', 'Auxiliar de Departamento');
insert into `bd_sgc`.`Puesto` values(2,'Analista', 'Analista de Departamento');
insert into `bd_sgc`.`Puesto` values(3,'Jefe de Departamento', 'Jefe de Departamento');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Departamento`
-- -----------------------------------------------------
insert into `bd_sgc`.`Departamento` values(1,'Gestión de Calidad', 'Gestión de Calidad','gestion_calidad','GC');
insert into `bd_sgc`.`Departamento` values(2,'Apoyo Informático', 'Departamento de Apoyo Informático','apoyo_informático','AP');
insert into `bd_sgc`.`Departamento` values(3,'Auditoría Interna', 'Departamento de Auditoía Interna','auditoria_interna','AI');
insert into `bd_sgc`.`Departamento` values(4,'Contabilidad', 'Departamento de Contabilidad','contabilidad','CT');
insert into `bd_sgc`.`Departamento` values(5,'Control Patrimonial', 'Departamento de Control Patrimonial','control_patrimonial','CP');
insert into `bd_sgc`.`Departamento` values(6,'Finanzas', 'Departamento de Finanzas','finanzas','FI');
insert into `bd_sgc`.`Departamento` values(7,'Presupuestos', 'Departamento de Presupuestos','presupuestos','PP');
insert into `bd_sgc`.`Departamento` values(8,'Tesorería - Campus Ensenada', 'Tesorería - Campus Ensenada','tesoreria_ensenada');
insert into `bd_sgc`.`Departamento` values(9,'Tesorería - Campus Mexicali', 'Tesorería - Campus Mexicali','tesoreria_mexicali');
insert into `bd_sgc`.`Departamento` values(10,'Tesorería - Campus Tijuana', 'Tesorería - Campus Tijuana','tesoreria_tijuana');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Tipo_Documento`
-- -----------------------------------------------------
insert into `bd_sgc`.`Tipo_Documento` values(1, 'Manual de Gestión SGC','Manual de Gestión SGC','N1');
insert into `bd_sgc`.`Tipo_Documento` values(2, 'Procedimiento','Procedimiento','procedimiento','N2');


-- -----------------------------------------------------
-- Table `bd_sgc`.`Tipo_Archivo`
-- -----------------------------------------------------
insert into `bd_sgc`.`Tipo_Archivo` values(1, 'Documento de Worid','Documento de Word');
insert into `bd_sgc`.`Tipo_Archivo` values(2, 'Presentacion PowerPoint','Presentacion PowerPoint');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Clasificador_Documento`
-- -----------------------------------------------------
insert into `bd_sgc`.`Clasificador_Documento` values(1, 'Minuta de Reunión Directiva','Minuta de Reunión Directiva');
insert into `bd_sgc`.`Clasificador_Documento` values(2, 'Seguimiento Reunión Directiva','Seguimiento Reunión Directiva');
insert into `bd_sgc`.`Clasificador_Documento` values(3, 'Resultado de Auditoría Interna','Resultado de Auditoría Interna');
insert into `bd_sgc`.`Clasificador_Documento` values(4, 'Acciones Correctivas de Auditoría Interna','Acciones Correctivas de Auditoría Interna');
insert into `bd_sgc`.`Clasificador_Documento` values(5, 'Resultado de Auditoría Externa','Resultado de Auditoría Externa');
insert into `bd_sgc`.`Clasificador_Documento` values(6, 'Acciones Correctivas de Auditoría Externa','Acciones Correctivas de Auditoría Externa');
insert into `bd_sgc`.`Clasificador_Documento` values(7, 'Resultado de la Encuesta del Clima Laboral','Resultado de la Encuesta del Clima Laboral');
insert into `bd_sgc`.`Clasificador_Documento` values(8, 'Acciones Correctivas Encuesta del Clima Laboral','Acciones Correctivas Encuesta del Clima Laboral');
insert into `bd_sgc`.`Clasificador_Documento` values(9, 'Minuta de Reunión de Trabajo','Minuta de Reunión de Trabajo');
insert into `bd_sgc`.`Clasificador_Documento` values(10, 'Seguimiento Reunión de Trabajo','Seguimiento Reunión de Trabajo');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Estatus_Accion`
-- -----------------------------------------------------
insert into `bd_sgc`.`Estatus_Accion` values(1, 'Primera Acción','Primera Acción');
insert into `bd_sgc`.`Estatus_Accion` values(2, 'Segunda Acción','Segunda Acción');
insert into `bd_sgc`.`Estatus_Accion` values(3, 'Tercera Acción','Tercera Acción');
insert into `bd_sgc`.`Estatus_Accion` values(4, 'Cuarta Acción','Cuarta Acción');
insert into `bd_sgc`.`Estatus_Accion` values(5, 'Quinta Acción','Quinta Acción');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Proviene_de`
-- -----------------------------------------------------
insert into `bd_sgc`.`Proviene_de` values(1, 'Arriba','Arriba');
insert into `bd_sgc`.`Proviene_de` values(2, 'Abajo','Abajo');
insert into `bd_sgc`.`Proviene_de` values(3, 'Izquierda','Izquierda');
insert into `bd_sgc`.`Proviene_de` values(4, 'Derecha','Derecha');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Causa_Raiz`
-- -----------------------------------------------------
insert into `bd_sgc`.`Causa_Raiz` values(1, 'Raiz Uno','Raiz Uno');
insert into `bd_sgc`.`Causa_Raiz` values(2, 'Raiz Dos','Raiz Dos');
insert into `bd_sgc`.`Causa_Raiz` values(3, 'Raiz Tres','Raiz Tres');
insert into `bd_sgc`.`Causa_Raiz` values(4, 'Raiz Cuarto','Raiz Cuarto');
insert into `bd_sgc`.`Causa_Raiz` values(5, 'Raiz Cinco','Raiz Cinco');

-- -----------------------------------------------------
-- Table `bd_sgc`.`Tipo_Auditoria`
-- -----------------------------------------------------
insert into `bd_sgc`.`Tipo_Auditoria` values(1, 'Auditoría Interna','Auditoría Interna');
insert into `bd_sgc`.`Tipo_Auditoria` values(2, 'Auditoría Externa','Auditoría Externa');