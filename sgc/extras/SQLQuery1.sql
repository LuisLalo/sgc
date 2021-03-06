USE [master]
GO
/****** Object:  Database [Sistema_de_Gestion_de_Calidad]    Script Date: 4/14/2018 12:37:28 AM ******/
CREATE DATABASE [Sistema_de_Gestion_de_Calidad]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Sistema_de_Gestion_de_Calidad', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\Sistema_de_Gestion_de_Calidad.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Sistema_de_Gestion_de_Calidad_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\Sistema_de_Gestion_de_Calidad_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Sistema_de_Gestion_de_Calidad].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET ARITHABORT OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET  MULTI_USER 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET QUERY_STORE = OFF
GO
USE [Sistema_de_Gestion_de_Calidad]
GO
ALTER DATABASE SCOPED CONFIGURATION SET IDENTITY_CACHE = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [Sistema_de_Gestion_de_Calidad]
GO
/****** Object:  Table [dbo].[Acciones_Correctivas]    Script Date: 4/14/2018 12:37:29 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Acciones_Correctivas](
	[id_accion] [int] NOT NULL,
	[proviene_de] [varchar](50) NOT NULL,
	[fecha] [datetime] NOT NULL,
	[no.auditoria] [int] NOT NULL,
	[tipo_auditoria] [varchar](50) NOT NULL,
	[observacion_norma] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[departamento] [varchar](50) NOT NULL,
	[responsable_solventar] [varchar](50) NOT NULL,
	[responsable_verificar] [varchar](50) NOT NULL,
	[causa_raiz] [varchar](50) NOT NULL,
	[descripcion_causa_raiz] [varchar](50) NOT NULL,
	[accion_correctiva] [varchar](50) NOT NULL,
	[fecha_aplicacion] [datetime] NOT NULL,
	[estatus] [varchar](50) NOT NULL,
	[resultado] [varchar](50) NOT NULL,
	[no.accion] [int] NOT NULL,
 CONSTRAINT [PK_Acciones_Correctivas] PRIMARY KEY CLUSTERED 
(
	[id_accion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Actividades]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Actividades](
	[id_actividad] [int] NOT NULL,
	[fecha_inicio] [datetime] NOT NULL,
	[fecha_final] [datetime] NOT NULL,
	[hora_inicio] [time](7) NOT NULL,
	[hora_final] [time](7) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[id_usuario] [int] NOT NULL,
	[clasificador_id] [int] NOT NULL,
	[lugar] [varchar](50) NOT NULL,
	[fecha_creacion] [datetime] NOT NULL,
	[estatus] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Acts] PRIMARY KEY CLUSTERED 
(
	[id_actividad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Actividades_Acciones]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Actividades_Acciones](
	[id_actividad] [int] NOT NULL,
	[fecha_estimada] [datetime] NOT NULL,
	[fecha_real] [datetime] NOT NULL,
	[porcentaje_avance] [int] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[id_proyecto] [int] NOT NULL,
	[responsable] [varchar](50) NOT NULL,
	[tipo_proyecto] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Actividades_Acciones] PRIMARY KEY CLUSTERED 
(
	[id_actividad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Acuerdos_Respuestas]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Acuerdos_Respuestas](
	[id_acuerdo] [int] NOT NULL,
	[id_respuesta] [int] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[fecha_respuesta] [datetime] NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Acuerdos_Respuestas] PRIMARY KEY CLUSTERED 
(
	[id_respuesta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Acuerdos_Reuniones]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Acuerdos_Reuniones](
	[id_acuerdo] [int] NOT NULL,
	[id_reunion] [int] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[fecha_vencimiento] [datetime] NOT NULL,
	[id_usuario_responsable] [int] NOT NULL,
 CONSTRAINT [PK_Acuerdos_Reuniones] PRIMARY KEY CLUSTERED 
(
	[id_acuerdo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clasificador_acts]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clasificador_acts](
	[clasificador_act_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[color] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Clasificador_act] PRIMARY KEY CLUSTERED 
(
	[clasificador_act_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clasificador_Docs]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clasificador_Docs](
	[clasificador_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Clasificador_Docs] PRIMARY KEY CLUSTERED 
(
	[clasificador_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clasificadores]    Script Date: 4/14/2018 12:37:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clasificadores](
	[id_clasificador] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Clasificadores] PRIMARY KEY CLUSTERED 
(
	[id_clasificador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Departamentos]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Departamentos](
	[departamento_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Departamento] PRIMARY KEY CLUSTERED 
(
	[departamento_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Documentos]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Documentos](
	[id_documento] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[ruta] [varchar](50) NOT NULL,
	[tipo_archivo_id] [int] NOT NULL,
	[tipo_documento_id] [int] NOT NULL,
	[fecha_creacion] [datetime] NOT NULL,
	[estatus] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[clasificador_id] [int] NOT NULL,
	[orden_documento] [varchar](50) NOT NULL,
	[departamento_id] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Documentos] PRIMARY KEY CLUSTERED 
(
	[id_documento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Estatus_Acciones]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estatus_Acciones](
	[estatus_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Estatus_Acciones] PRIMARY KEY CLUSTERED 
(
	[estatus_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Expresiones]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Expresiones](
	[id_expresion] [int] NOT NULL,
	[id_indicador] [int] NOT NULL,
	[expresion] [varchar](50) NOT NULL,
	[informacion_adicional] [varchar](50) NOT NULL,
	[departamento] [varchar](50) NOT NULL,
	[fecha_creacion] [datetime] NOT NULL,
	[estatus] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Expresiones] PRIMARY KEY CLUSTERED 
(
	[id_expresion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Formularios_Reuniones]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Formularios_Reuniones](
	[id_reuniones] [int] NOT NULL,
	[asunto] [varchar](50) NOT NULL,
	[fecha_reunion] [datetime] NOT NULL,
	[objetivo] [varchar](50) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Frecuencia]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Frecuencia](
	[frecuencia_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Frecuencia] PRIMARY KEY CLUSTERED 
(
	[frecuencia_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Indicadores]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Indicadores](
	[id_indicador] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[objetivo] [varchar](50) NOT NULL,
	[meta] [varchar](50) NOT NULL,
	[frecuencia] [varchar](50) NOT NULL,
	[tipo_grafica] [varchar](50) NOT NULL,
	[expresion] [varchar](50) NOT NULL,
	[informacion_adicional] [varchar](50) NOT NULL,
	[departamento] [varchar](50) NOT NULL,
	[fecha_creacion] [datetime] NOT NULL,
	[estatus] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Indicadores] PRIMARY KEY CLUSTERED 
(
	[id_indicador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proyectos_Mejora]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proyectos_Mejora](
	[id_proyecto] [int] NOT NULL,
	[no.proyecto] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[area_aplicacion] [varchar](50) NOT NULL,
	[proviene_de] [varchar](50) NOT NULL,
	[tipo_proyecto] [varchar](50) NOT NULL,
	[situacion_actual] [varchar](50) NOT NULL,
	[situacion_deseada] [varchar](50) NOT NULL,
	[fecha_creacion] [datetime] NOT NULL,
	[area_responsable] [varchar](50) NOT NULL,
	[responsable] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Proyectos_Mejora] PRIMARY KEY CLUSTERED 
(
	[id_proyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Puesto]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Puesto](
	[puesto_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Puesto] PRIMARY KEY CLUSTERED 
(
	[puesto_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recursos]    Script Date: 4/14/2018 12:37:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recursos](
	[id_recurso] [int] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[costo] [decimal](18, 0) NOT NULL,
 CONSTRAINT [PK_Recursos] PRIMARY KEY CLUSTERED 
(
	[id_recurso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resultados_Indicadores]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resultados_Indicadores](
	[id_resultado] [int] NOT NULL,
	[id_indicador] [int] NOT NULL,
	[frecuencia] [varchar](50) NOT NULL,
	[valor] [int] NOT NULL,
	[fecha_creacion] [datetime] NOT NULL,
	[año] [datetime] NOT NULL,
 CONSTRAINT [PK_Resultados_Indicadores] PRIMARY KEY CLUSTERED 
(
	[id_resultado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rol]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rol](
	[rol_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Rol] PRIMARY KEY CLUSTERED 
(
	[rol_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tecnicas]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tecnicas](
	[id_tecnicas] [int] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Tecnicas] PRIMARY KEY CLUSTERED 
(
	[id_tecnicas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tipo_Archivo]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tipo_Archivo](
	[tipo_archivo_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[ruta] [varchar](50) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tipo_Documento]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tipo_Documento](
	[tipo_documento_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Tipo_Documento_1] PRIMARY KEY CLUSTERED 
(
	[tipo_documento_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tipo_Proyecto]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tipo_Proyecto](
	[tipo_proyecto_id] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Tipo_Proyecto] PRIMARY KEY CLUSTERED 
(
	[tipo_proyecto_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[no.empleado] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellidos] [varchar](50) NOT NULL,
	[rol_id] [int] NOT NULL,
	[correo] [varchar](50) NOT NULL,
	[contraseña] [varchar](50) NOT NULL,
	[puesto_id] [int] NOT NULL,
	[estatus] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[no.empleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios_Actividades]    Script Date: 4/14/2018 12:37:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios_Actividades](
	[no.empleado] [int] NOT NULL,
	[id_actividad] [int] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Actividades_Clasificador_acts] FOREIGN KEY([clasificador_id])
REFERENCES [dbo].[Clasificador_acts] ([clasificador_act_id])
GO
ALTER TABLE [dbo].[Actividades] CHECK CONSTRAINT [FK_Actividades_Clasificador_acts]
GO
ALTER TABLE [dbo].[Documentos]  WITH CHECK ADD  CONSTRAINT [FK_Documentos_Clasificador_Docs] FOREIGN KEY([clasificador_id])
REFERENCES [dbo].[Clasificador_Docs] ([clasificador_id])
GO
ALTER TABLE [dbo].[Documentos] CHECK CONSTRAINT [FK_Documentos_Clasificador_Docs]
GO
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Rol] FOREIGN KEY([rol_id])
REFERENCES [dbo].[Rol] ([rol_id])
GO
ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_Rol]
GO
ALTER TABLE [dbo].[Usuarios_Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Actividades_Actividades] FOREIGN KEY([id_actividad])
REFERENCES [dbo].[Actividades] ([id_actividad])
GO
ALTER TABLE [dbo].[Usuarios_Actividades] CHECK CONSTRAINT [FK_Usuarios_Actividades_Actividades]
GO
ALTER TABLE [dbo].[Usuarios_Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Actividades_Usuarios] FOREIGN KEY([no.empleado])
REFERENCES [dbo].[Usuarios] ([no.empleado])
GO
ALTER TABLE [dbo].[Usuarios_Actividades] CHECK CONSTRAINT [FK_Usuarios_Actividades_Usuarios]
GO
USE [master]
GO
ALTER DATABASE [Sistema_de_Gestion_de_Calidad] SET  READ_WRITE 
GO
