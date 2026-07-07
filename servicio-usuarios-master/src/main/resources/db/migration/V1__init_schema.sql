-- Create table for users
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    cuit_cuil VARCHAR(20) UNIQUE NOT NULL,
    nombre_completo VARCHAR(200) NOT NULL,
    email VARCHAR(254) NOT NULL,
    password VARCHAR(100),
    estado BOOLEAN
);

-- Create table for roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role VARCHAR(30) UNIQUE NOT NULL
);

-- Join table for many-to-many relationship
CREATE TABLE users_roles (
    usuario_id INT NOT NULL,
    rol_id INT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE
);
