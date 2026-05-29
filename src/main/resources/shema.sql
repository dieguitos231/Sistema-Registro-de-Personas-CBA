CREATE TABLE usuario
(
    n_documento        INT UNIQUE PRIMARY KEY,
    correo_electronico VARCHAR(100) UNIQUE NOT NULL,
    password           VARCHAR(255)        NOT NULL,
    rol                VARCHAR(100) NOT NULL
)
CREATE TABLE funcionario
(
    n_documento INT PRIMARY KEY ,
    cargo VARCHAR(100),
    FOREIGN KEY (n_documento) REFERENCES usuario(n_documento)
)
CREATE TABLE aprendiz
(
    n_documento INT PRIMARY KEY ,
    ficha INT,
    FOREIGN KEY (n_documento) REFERENCES usuario(n_documento)
)
CREATE TABLE detalle_usuario
(
    tipo_documento VARCHAR (10),
    n_documento INT PRIMARY KEY,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    fecha_creacion TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (n_documento) REFERENCES usuario(n_documento)
)
CREATE TABLE tarjeta_usuario
(
    n_documento INT PRIMARY KEY ,
    codigo_tarjeta VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha_emision DATE DEFAULT  CURRENT_DATE NOT NULL,
    fecha_expiracion DATE DEFAULT  CURRENT_DATE NOT NULL,
    FOREIGN KEY (n_documento) REFERENCES usuario(n_documento)
)
CREATE TABLE historial_ingreso
(
    id_historial SERIAL PRIMARY KEY ,
    fecha_acceso TIMESTAMP DEFAULT  CURRENT_TIMESTAMP NOT NULL,
    n_documento INT,
    FOREIGN KEY (n_documento) REFERENCES tarjeta_usuario(n_documento)
    ON DELETE CASCADE
)

CREATE TABLE reporte
(
    id_reporte SERIAL PRIMARY KEY ,
    asunto VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha_solicitud TIMESTAMPTZ  DEFAULT  CURRENT_TIMESTAMP NOT NULL,
    fecha_soporte TIMESTAMPTZ DEFAULT  CURRENT_TIMESTAMP NOT NULL,
    n_documento INT ,
    FOREIGN KEY  (n_documento) REFERENCES usuario(n_documento)
)