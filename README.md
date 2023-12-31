# Ejercicio técnico

## Descripción
Aplicación Spring Boot que provee un endpoint REST para consultar el precio aplicable a un producto en una fecha determinada. Utiliza una base de datos H2 con la tabla PRICES que contiene los precios y tarifas aplicables por producto, cadena y rango de fechas.

El endpoint acepta los parámetros: fecha, productoId y brandId. Devuelve: productoId, brandId, tarifa aplicable, rango de fechas y precio.

Se utiliza OpenAPI para documentar la API REST. La especificación OpenAPI está disponible en la URL:

```
http://localhost:8080/api/v1/v3/api-docs
```
Y Flyway para el versionado de la base de datos.

## API Endpoint

```
GET /product/{product_id}/price?date_time={date_time}&brand_id={brand_id}
```

### Parámetros:
- date_time: fecha de aplicación (YYYY-MM-DD hh:mm:ss)
- product_id: id del producto
- brand_id: id de la cadena

### Respuesta:
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceListId": 1,
  "dateRange": {
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T22:59:59"
  },
  "price": 35.5
}
```

## Test de endpoint

Se realizan los siguientes tests al endpoint:

### Test 1
- Fecha: 2020-06-14 10:00
- Producto: 35455
- Marca: 1
- **Criterio de aceptación**:
    - Tarifa aplicable debe ser "1"
    - Precio debe ser 35.50

### Test 2
- Fecha: 2020-06-14 16:00
- Producto: 35455
- Marca: 1
- **Criterio de aceptación:**
    - Tarifa aplicable debe ser "2"
    - Precio debe ser 25.45

### Test 3
- Fecha: 2020-06-14 21:00
- Producto: 35455
- Marca: 1
- **Criterio de aceptación:**
    - Tarifa aplicable debe ser "1"
    - Precio debe ser 35.50

### Test 4
- Fecha: 2020-06-15 10:00
- Producto: 35455
- Marca: 1
- **Criterio de aceptación:**
    - Tarifa aplicable debe ser "3"
    - Precio debe ser 30.50

### Test 5:
- Fecha: 2020-06-16 21:00
- Producto: 35455
- Marca: 1
- **Criterio de aceptación:**
    - Tarifa aplicable debe ser "4"
    - Precio debe ser 38.95



