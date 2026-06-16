INSERT INTO public.usuarios(id, estado, password, cuit_cuil, nombre_completo,email) VALUES (1, true, '$2a$10$MhXqsSRwKmE2QxqO2TEUfeze56dP2nBoaAFC6IdtrYcIRniISmSpG', 'admin','admin','email@email.com');
INSERT INTO public.usuarios(id, estado, password, cuit_cuil,nombre_completo,email) VALUES (2, true, '$2a$10$MhXqsSRwKmE2QxqO2TEUfeze56dP2nBoaAFC6IdtrYcIRniISmSpG', 'user','user','email@email.com');
INSERT INTO public.roles(id, role,estado) VALUES (1, 'ROLE_ADMIN',true);
INSERT INTO public.roles(id, role,estado) VALUES (2, 'ROLE_USER',true);
INSERT INTO public.users_roles(usuario_id, rol_id) VALUES (1,1);
INSERT INTO public.users_roles(usuario_id, rol_id) VALUES (2,2);