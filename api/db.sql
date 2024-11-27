-- PROYECTO COMPRAYA
-- UNIVERSIDAD AUTONOMA DE MANIZALES
-- 2024

-- Creación de la base de datos
CREATE DATABASE compraya;
USE compraya;

-- Tabla Usuarios
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    celular VARCHAR(20) NOT NULL
);

-- Tabla Categorías
CREATE TABLE Categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla Productos
CREATE TABLE Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categorias(id)
);

-- Tabla Inventario
CREATE TABLE Inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada INT NOT NULL,
    salida INT NOT NULL,
    referencia_compra VARCHAR(100) NOT NULL,
    producto_id INT,
    FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

-- Tabla Carrito de Compras
CREATE TABLE CarritoCompras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT,
    cantidad INT NOT NULL,
    descuento DECIMAL(10, 2),
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

-- Tabla Puntos Redimidos
CREATE TABLE PuntosRedimidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_puntos INT NOT NULL,
    fecha_redencion DATE NOT NULL,
    detalle_factura_id INT
);

-- Tabla Puntos Ganados
CREATE TABLE PuntosGanados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_puntos INT NOT NULL,
    fecha_ganancia DATE NOT NULL,
    motivo VARCHAR(255),
    referencia VARCHAR(100)
);

-- Tabla Factura
CREATE TABLE Factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    total_impuestos DECIMAL(10, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    id_cliente INT,
    id_metodo_pago INT
);

-- Tabla Detalle Factura
CREATE TABLE DetalleFactura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    descuento DECIMAL(10, 2),
    id_producto INT,
    id_factura INT,
    FOREIGN KEY (id_producto) REFERENCES Productos(id),
    FOREIGN KEY (id_factura) REFERENCES Factura(id)
);

-- Tabla Métodos de Pago
CREATE TABLE MetodosPago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    metodo VARCHAR(50) NOT NULL
);

-- Tabla Informes
CREATE TABLE Informes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_informe VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    datos_json JSON NOT NULL
);