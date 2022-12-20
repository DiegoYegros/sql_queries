import java.sql.*;
import java.util.Scanner;

public class App {
   public static Connection conectar(){
      Connection database = null;
      try{
         database = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bootcamp_market", "postgres", "A14dewqsoy98p");
         return database;
      }catch (Exception e){
         System.out.println("Ha ocurrido un error al conectarse");
         return null;
      }
   }
   public static void printResultSet (ResultSet rs) throws SQLException {
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();
      while (rs.next()){
         for (int i =1; i<= columnsNumber; i++){
            if (i>1) System.out.print(", ");
            String columnValue = rs.getString(i);
            System.out.println(rsmd.getColumnName(i) + " "+ columnValue);
         }
         System.out.println("");
      }
   }
   /*TOP CLIENTES CON MAS FACTURAS*/
   public static void problema1() throws SQLException {
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT cliente.id cliente_id, cliente.nombre, cliente.apellido, cliente.nro_cedula, cliente.telefono, \n" +
              "COUNT(cliente.id) cantidad_facturas \n" +
              "FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id\n" +
              "GROUP BY (cliente.id) \n" +
              "ORDER BY (cantidad_facturas) DESC;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Top clientes que más gastaron*/
   public static void problema2() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT cliente.nombre, SUM(ROUND(producto.precio*factura_detalle.cantidad)) gasto\n" +
              "FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id\n" +
              "INNER JOIN factura_detalle ON factura.id = factura_detalle.factura_id\n" +
              "INNER JOIN producto ON producto.id= factura_detalle.producto_id\n" +
              "GROUP BY (cliente.nombre) \n" +
              "ORDER BY (gasto) DESC;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

/*Top monedas más utilizadas*/
   public static void problema3() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT moneda.nombre, count(moneda.nombre) cantidad_de_facturas \n" +
              "FROM moneda INNER JOIN factura ON moneda.id=factura.moneda_id\n" +
              "GROUP BY (moneda.nombre)\n" +
              "ORDER BY (cantidad_de_facturas) DESC;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Top proveedor de productos*/
   public static void problema4() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT proveedor.nombre, COUNT(proveedor.nombre) cantidad_de_productos FROM proveedor \n" +
              "INNER JOIN producto ON proveedor.id = producto.proveedor_id \n" +
              "GROUP BY(proveedor.nombre)\n" +
              "ORDER BY (cantidad_de_productos) DESC;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Productos mas vendidos*/
   public static void problema5() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT producto.nombre, ROUND(SUM(factura_detalle.cantidad)) cantidad FROM producto\n" +
              "INNER JOIN factura_detalle ON factura_detalle.producto_id = producto.id \n" +
              "GROUP BY (producto.nombre)\n" +
              "ORDER BY (cantidad) DESC;\n";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Productos menos vendidos*/
   public static void problema6() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT producto.nombre, ROUND(SUM(factura_detalle.cantidad)) cantidad FROM producto\n" +
              "INNER JOIN factura_detalle ON factura_detalle.producto_id = producto.id \n" +
              "GROUP BY (producto.nombre)\n" +
              "ORDER BY (cantidad);";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Consulta que muestre fecha de emisión de factura, nombre y apellido del cliente,
   nombres de productos de esa factura, cantidades compradas, nombre de tipo de factura de una factura específica*/
   public static void problema7() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT f.id, f.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre nombre_producto, fd.cantidad, ft.nombre FROM factura f\n" +
              "   INNER JOIN factura_detalle fd ON fd.factura_id = f.id\n" +
              "   INNER JOIN cliente ON cliente.id = f.cliente_id\n" +
              "   INNER JOIN factura_tipo ft ON ft.id = f.factura_tipo_id\n" +
              "   INNER JOIN producto ON producto.id = fd.producto_id\n" +
              "   ORDER BY f.id;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Montos de facturas ordenadas según totales*/
   public static void problema8() throws SQLException{
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT factura.id, ROUND(SUM(factura_detalle.cantidad*producto.precio)) totales FROM factura \n" +
              "JOIN factura_detalle ON factura.id = factura_id \n" +
              "JOIN producto ON factura_detalle.producto_id = producto.id\n" +
              "GROUP BY (factura.id)\n" +
              "ORDER BY (totales) DESC;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
   }

   /*Mostrar el iva 10% de los montos totales de facturas (suponer que todos los productos tienen IVA 10%)*/
   public static void problema9() throws SQLException {
      Connection database = conectar();
      assert database != null;
      Statement stmt = database.createStatement();
      String selectSql = "SELECT factura.id, ROUND(SUM(factura_detalle.cantidad*producto.precio)*.11) totales FROM factura\n" +
              "   JOIN factura_detalle ON factura.id = factura_id\n" +
              "   JOIN producto ON factura_detalle.producto_id = producto.id\n" +
              "   GROUP BY (factura.id)\n" +
              "   ORDER BY (totales) DESC;";
      ResultSet rs = stmt.executeQuery(selectSql);
      App.printResultSet(rs);
      /*Mostrar el iva 10% de los montos totales de facturas (suponer que todos los productos tienen IVA 10%)*/
   }

   public static void main(String args[]) throws SQLException {
      System.out.println("Opened database successfully");
      Scanner objeto = new Scanner(System.in);
      boolean flag = true;
      String input;
      while (flag) {
         System.out.println("Ingrese numero de ejercicio que desea (1 al 9, 0 para salir)");
         input = objeto.nextLine();
         if (App.isInteger(input)){
            int numero = Integer.parseInt(input);
            if (numero == 0) flag = false;
            switch (numero) {
               case 1 -> App.problema1();
               case 2 -> App.problema2();
               case 3 -> App.problema3();
               case 4 -> App.problema4();
               case 5 -> App.problema5();
               case 6 -> App.problema6();
               case 7 -> App.problema7();
               case 8 -> App.problema8();
               case 9 -> App.problema9();
               default -> System.out.println("Entrada invalida");
            }
         }
         }
   }

   public static boolean isInteger(String numero){
      try{
         Integer.parseInt(numero);
         return true;

      }catch(Exception e){
         return false;
      }
   }
}