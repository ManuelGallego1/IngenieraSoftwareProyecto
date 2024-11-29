DROP DATABASE IF EXISTS compraya;

-- Creación de la base de datos para el proyecto Compraya
CREATE DATABASE compraya;

USE compraya;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    celular VARCHAR(15) NOT NULL
);

-- Tabla de Categorías
CREATE TABLE Categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla de Productos
CREATE TABLE Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    categoria_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES Categorias(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Inventario
CREATE TABLE Inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada INT NOT NULL,
    salida INT NOT NULL,
    referencia_compra VARCHAR(255),
    producto_id INT NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES Productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Carrito de Compras
CREATE TABLE CarritoCompras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    descuento DECIMAL(10, 2),
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES Productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Facturas
CREATE TABLE Facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    fecha DATE NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    total_impuestos DECIMAL(10, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    id_cliente INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Detalle de Factura
CREATE TABLE DetalleFactura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    descuento DECIMAL(10, 2),
    id_producto INT NOT NULL,
    id_factura INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES Productos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_factura) REFERENCES Facturas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Puntos Redimidos
CREATE TABLE PuntosRedimidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_puntos INT NOT NULL,
    fecha_redencion DATE NOT NULL,
    detalle_factura_id INT NOT NULL,
    FOREIGN KEY (detalle_factura_id) REFERENCES DetalleFactura(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de Puntos Ganados
CREATE TABLE PuntosGanados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_puntos INT NOT NULL,
    fecha_ganancia DATE NOT NULL,
    motivo VARCHAR(255),
    referencia VARCHAR(255)
);

-- Tabla de Métodos de Pago
CREATE TABLE MetodosPago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    metodo VARCHAR(255) UNIQUE NOT NULL
);

-- Tabla de Informes
CREATE TABLE Informes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_informe VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    datos_json JSON NOT NULL
);

-- Paso 1: Crear un usuario
INSERT INTO Usuarios (nombre, contrasena, identificacion, email, celular)
VALUES ('Juan Pérez', 'password123', '123456789', 'juan.perez@example.com', '3001234567');

-- Paso 2: Crear dos productos
INSERT INTO Categorias (nombre) VALUES ('Electrónica'), ('Hogar');

INSERT INTO Productos (nombre, descripcion, precio, imagen, categoria_id)
VALUES ('Laptop', 'Laptop de alto rendimiento', 1500.00, 'laptop.jpg', 1),
       ('Aspiradora', 'Aspiradora silenciosa', 200.00, 'aspiradora.jpg', 2);

-- Paso 3: Registrar stock inicial en el inventario
INSERT INTO Inventario (entrada, salida, referencia_compra, producto_id)
VALUES (10, 0, 'Compra inicial', 1), -- 10 unidades de Laptop
       (20, 0, 'Compra inicial', 2); -- 20 unidades de Aspiradora

-- Paso 4: El usuario agrega productos al carrito
INSERT INTO CarritoCompras (producto_id, cantidad, descuento, total)
VALUES (1, 1, 0, 1500.00), -- 1 Laptop sin descuento
       (2, 2, 0, 400.00);  -- 2 Aspiradoras sin descuento

-- Paso 5: Crear una factura para el usuario
INSERT INTO Facturas (codigo, fecha, subtotal, total_impuestos, total, estado, id_cliente, id_metodo_pago)
VALUES ('FAC-001', CURDATE(), 1900.00, 304.00, 2204.00, 'Pagada', 1, 1);

-- Paso 6: Registrar el detalle de la factura
INSERT INTO DetalleFactura (cantidad, valor_total, descuento, id_producto, id_factura)
VALUES (1, 1500.00, 0, 1, 1), -- Detalle para la Laptop
       (2, 400.00, 0, 2, 1); -- Detalle para las Aspiradoras

-- Paso 7: Actualizar el inventario (registrar salidas)
INSERT INTO Inventario (entrada, salida, referencia_compra, producto_id)
VALUES (0, 1, 'Venta factura FAC-001', 1), -- Salida de 1 Laptop
       (0, 2, 'Venta factura FAC-001', 2); -- Salida de 2 Aspiradoras

-- Paso 8: Consultar el stock actualizado
SELECT p.nombre AS producto, 
       i.entrada, 
       i.salida, 
       (SUM(i.entrada) - SUM(i.salida)) AS stock_actual
FROM Inventario i
JOIN Productos p ON i.producto_id = p.id
GROUP BY i.producto_id;
