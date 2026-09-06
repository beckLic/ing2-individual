INSERT INTO usuarios (id, username, password, nombre) VALUES
                                                          ('usr-001', 'admin', 'admin123', 'Administrador Principal'),
                                                          ('usr-002', 'vendedor', 'vend123', 'Juan Perez');

INSERT INTO proveedores (cuit, razon_social) VALUES
                                                 (30111111112, 'Tech Solutions S.A.'),
                                                 (30222222221, 'Global Software Ltd.');

INSERT INTO productos (id, nombre, marca, eliminado) VALUES
                                                         ('prod-001', 'Windows 11 Pro', 'Microsoft', false),
                                                         ('prod-002', 'Teclado Mecánico', 'Logitech', false);

INSERT INTO software (id, clave_licencia, version, vigencia_licencia_meses, enlace_descarga) VALUES
    ('prod-001', 'WIN-11-XXXX', '11.0.2', 12, 'http://descargas.microsoft.com/win11');

INSERT INTO hardware (id, numero_serie, garantia_meses) VALUES
    ('prod-002', 'LOG-K-999', 24);

INSERT INTO hardware_especificaciones (hardware_id, especificacion) VALUES
                                                                        ('prod-002', 'Switches Blue'),
                                                                        ('prod-002', 'Retroiluminación RGB');