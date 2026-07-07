-- Insert sample users
INSERT INTO usuarios (cuit_cuil, nombre_completo, email, password, estado)
VALUES
('20-12345678-9', 'Alice Admin', 'alice.admin@example.com', '$2a$10$MhXqsSRwKmE2QxqO2TEUfeze56dP2nBoaAFC6IdtrYcIRniISmSpG', TRUE),
('23-98765432-1', 'Bob Solic', 'bob.solic@example.com', '$2a$10$MhXqsSRwKmE2QxqO2TEUfeze56dP2nBoaAFC6IdtrYcIRniISmSpG', TRUE),
('27-55555555-5', 'Charlie Tech', 'charlie.tech@example.com', '$2a$10$MhXqsSRwKmE2QxqO2TEUfeze56dP2nBoaAFC6IdtrYcIRniISmSpG', TRUE);

-- Assign roles to users
-- Alice is an admin
INSERT INTO users_roles (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u, roles r
WHERE u.cuit_cuil = '20-12345678-9' AND r.role = 'ROLE_ADMIN';

-- Bob is a solicitante
INSERT INTO users_roles (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u, roles r
WHERE u.cuit_cuil = '23-98765432-1' AND r.role = 'ROLE_SOLICITANTE';

-- Charlie is a tecnico
INSERT INTO users_roles (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u, roles r
WHERE u.cuit_cuil = '27-55555555-5' AND r.role = 'ROLE_TECNICO';
