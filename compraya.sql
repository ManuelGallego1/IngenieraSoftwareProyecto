DROP DATABASE IF EXISTS compraya;

-- Creación de la base de datos para el proyecto Compraya
CREATE DATABASE compraya;

USE compraya;

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    rol ENUM('admin', 'cliente') DEFAULT 'cliente',
    celular VARCHAR(15) NOT NULL
);

-- Tabla de Categorías
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla de Productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    categoria_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Inventario
CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada INT NOT NULL,
    salida INT NOT NULL,
    referencia_compra VARCHAR(255),
    producto_id INT NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Carrito de Compras
CREATE TABLE carritos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Productos en Carrito de Compras
CREATE TABLE productos_en_carrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carrito_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    descuento DECIMAL(10, 2),
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (carrito_id) REFERENCES carritos(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE
);

-- Tabla de Facturas
CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    fecha DATE NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    total_impuestos DECIMAL(10, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado ENUM('Pendiente', 'Pagada', 'Cancelada') NOT NULL,
    id_cliente INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Detalle de Factura
CREATE TABLE detalle_factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    descuento DECIMAL(10, 2),
    id_producto INT NOT NULL,
    id_factura INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_factura) REFERENCES facturas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Puntos
CREATE TABLE puntos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    cantidad INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Puntos Redimidos
CREATE TABLE puntos_redimidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_puntos INT NOT NULL,
    fecha_redencion DATE NOT NULL,
    detalle_factura_id INT NOT NULL UNIQUE,
    id_puntos INT NOT NULL,
    FOREIGN KEY (detalle_factura_id) REFERENCES detalle_factura(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_puntos) REFERENCES puntos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Puntos Ganados
CREATE TABLE puntos_ganados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_puntos INT NOT NULL,
    fecha_ganancia DATE NOT NULL,
    motivo VARCHAR(255),
    referencia VARCHAR(255),
    id_puntos INT NOT NULL,
    FOREIGN KEY (id_puntos) REFERENCES puntos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Métodos de Pago
CREATE TABLE metodos_pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    metodo VARCHAR(255) UNIQUE NOT NULL
);

-- Tabla de Informes
CREATE TABLE informes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_informe VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    datos_json JSON NOT NULL
);

INSERT INTO usuarios (nombre, contrasena, identificacion, email, rol, celular)
VALUES ('Juan Pérez', 'password123', '123456789', 'juan.perez@example.com', 'cliente', '3001234567');

INSERT INTO categorias (nombre)
VALUES ('Electrónica'), ('Hogar');

INSERT INTO productos (nombre, descripcion, precio, imagen, categoria_id)
VALUES 
('Laptop', 'Laptop de alto rendimiento', 1500.00, 'laptop.jpg', 1),
('Aspiradora', 'Aspiradora silenciosa', 200.00, 'aspiradora.jpg', 2);

INSERT INTO inventario (entrada, salida, referencia_compra, producto_id)
VALUES 
(10, 0, 'Compra inicial', 1), -- 10 unidades de Laptop
(20, 0, 'Compra inicial', 2); -- 20 unidades de Aspiradora

INSERT INTO carritos (usuario_id)
VALUES (1);

INSERT INTO productos_en_carrito (carrito_id, producto_id, cantidad, descuento, total)
VALUES 
(1, 1, 1, 0, 1500.00), -- 1 Laptop sin descuento
(1, 2, 2, 0, 400.00); -- 2 Aspiradoras sin descuento

INSERT INTO metodos_pago (metodo)
VALUES ('Tarjeta de Crédito');

INSERT INTO facturas (codigo, fecha, subtotal, total_impuestos, total, estado, id_cliente, id_metodo_pago)
VALUES ('FAC-001', CURDATE(), 1900.00, 304.00, 2204.00, 'Pagada', 1, 1);

INSERT INTO detalle_factura (cantidad, valor_total, descuento, id_producto, id_factura)
VALUES 
(1, 1500.00, 0, 1, 1), -- Laptop
(2, 400.00, 0, 2, 1); -- Aspiradora

INSERT INTO inventario (entrada, salida, referencia_compra, producto_id)
VALUES 
(0, 1, 'Venta factura FAC-001', 1), -- 1 Laptop vendida
(0, 2, 'Venta factura FAC-001', 2); -- 2 Aspiradoras vendidas

-- Actualizar la tabla Puntos
INSERT INTO puntos (usuario_id, cantidad)
VALUES (1, 100) 
ON DUPLICATE KEY UPDATE cantidad = cantidad + 100;

-- Registrar el detalle en PuntosGanados
INSERT INTO puntos_ganados (cantidad_puntos, fecha_ganancia, motivo, referencia, id_puntos)
VALUES (100, CURDATE(), 'Compra factura FAC-001', 'FAC-001', 1);

-- Redimir puntos en la tabla Puntos
UPDATE puntos SET cantidad = cantidad - 50 WHERE usuario_id = 1;

-- Registrar el detalle en PuntosRedimidos
INSERT INTO puntos_redimidos (cantidad_puntos, fecha_redencion, detalle_factura_id, id_puntos)
VALUES (50, CURDATE(), 1, 1);

INSERT INTO informes (tipo_informe, fecha, datos_json)
VALUES ('Reporte de ventas', CURDATE(), 
'{
    "factura": "FAC-001",
    "subtotal": 1900.00,
    "impuestos": 304.00,
    "total": 2204.00,
    "cliente": "Juan Pérez"
}');

-- Consultas organizadas
-- Consulta de saldo actual de puntos
SELECT u.nombre AS usuario, p.cantidad AS saldo_actual
FROM puntos p
JOIN usuarios u ON p.usuario_id = u.id
WHERE u.id = 1;

-- Consulta de stock actual de productos
SELECT p.nombre AS producto, 
       SUM(i.entrada) - SUM(i.salida) AS stock_actual
FROM inventario i
JOIN productos p ON i.producto_id = p.id
GROUP BY i.producto_id;

-- Consulta de puntos ganados
SELECT pg.cantidad_puntos, pg.fecha_ganancia, pg.motivo
FROM puntos_ganados pg
WHERE pg.id_puntos = 1;

-- Consulta de puntos redimidos
SELECT pr.cantidad_puntos, pr.fecha_redencion
FROM puntos_redimidos pr
WHERE pr.id_puntos = 1;
