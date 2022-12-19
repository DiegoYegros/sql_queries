--TOP CLIENTES CON MAS FACTURAS
-- SELECT cliente.id cliente_id, cliente.nombre, cliente.apellido, cliente.nro_cedula, cliente.telefono, 
-- COUNT(cliente.id) cantidad_facturas 
-- FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id
-- GROUP BY (cliente.id) 
-- ORDER BY (cantidad_facturas) DESC;

--Top clientes que más gastaron
-- SELECT cliente.nombre, SUM(ROUND(producto.precio*factura_detalle.cantidad)) gasto
-- FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id
-- INNER JOIN factura_detalle ON factura.id = factura_detalle.factura_id
-- INNER JOIN producto ON producto.id= factura_detalle.producto_id
-- GROUP BY (cliente.nombre) 
-- ORDER BY (gasto) DESC;

--Top monedas más utilizadas
-- SELECT moneda.nombre, count(moneda.nombre) cantidad_de_facturas 
-- FROM moneda INNER JOIN factura ON moneda.id=factura.moneda_id
-- GROUP BY (moneda.nombre)
-- ORDER BY (cantidad_de_facturas) DESC;

--Top proveedor de productos
-- SELECT proveedor.nombre, COUNT(proveedor.nombre) cantidad_de_productos FROM proveedor 
-- INNER JOIN producto ON proveedor.id = producto.proveedor_id 
-- GROUP BY(proveedor.nombre)
-- ORDER BY (cantidad_de_productos) DESC;


--Productos mas vendidos
-- SELECT producto.nombre, ROUND(SUM(factura_detalle.cantidad)) cantidad FROM producto
-- INNER JOIN factura_detalle ON factura_detalle.producto_id = producto.id 
-- GROUP BY (producto.nombre)
-- ORDER BY (cantidad) DESC;

--Productos menos vendidos
-- SELECT producto.nombre, ROUND(SUM(factura_detalle.cantidad)) cantidad FROM producto
-- INNER JOIN factura_detalle ON factura_detalle.producto_id = producto.id 
-- GROUP BY (producto.nombre)
-- ORDER BY (cantidad);

--Consulta que muestre fecha de emisión de factura, nombre y apellido del cliente, 
-- nombres de productos de esa factura, cantidades compradas, nombre de tipo de factura de una factura específica
-- SELECT f.id, f.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre nombre_producto, fd.cantidad, ft.nombre FROM factura f 
-- INNER JOIN factura_detalle fd ON fd.factura_id = f.id
-- INNER JOIN cliente ON cliente.id = f.cliente_id 
-- INNER JOIN factura_tipo ft ON ft.id = f.factura_tipo_id
-- INNER JOIN producto ON producto.id = fd.producto_id
-- ORDER BY f.id;

--Montos de facturas ordenadas según totales
-- SELECT factura.id, ROUND(SUM(factura_detalle.cantidad*producto.precio)) totales FROM factura 
-- JOIN factura_detalle ON factura.id = factura_id 
-- JOIN producto ON factura_detalle.producto_id = producto.id
-- GROUP BY (factura.id)
-- ORDER BY (totales) DESC;

--Mostrar el iva 10% de los montos totales de facturas (suponer que todos los productos tienen IVA 10%)
-- SELECT factura.id, ROUND(SUM(factura_detalle.cantidad*producto.precio)*.11) totales FROM factura 
-- JOIN factura_detalle ON factura.id = factura_id 
-- JOIN producto ON factura_detalle.producto_id = producto.id
-- GROUP BY (factura.id)
-- ORDER BY (totales) DESC;



