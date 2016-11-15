package sm1617ej2;

/**Created on 13/11/2016.
 * @author Alejandro Romo Rivero.
 * Se crea la interfaz: Como es una interfaz es un conjunto abstracto de forma que cuando implementemos en una clase la interfaz se nos
 * exigira crear estos métodos e inicializarlos, sacando los valores que se les pasan a los métodos y luego realizando cada una de las
 * tareas para la que están pensado los métodos. Y finalmente tras el proceso que se debe realizar en cada método se deberá devolver
 * por medio del return el valor o cadena que debe devolver cada método.
 */
public interface interfaz {
    //Método Modificar al cual le paso el nombreproducto, proveedor, identificador, valor actual y valor mínimo para que el usurio pueda
    //modificar lo que desee y luego sea guardado y por ello me devuelve una cadena con la modificación realizada que se guardará.
    public String Modificar(String nombreproducto,String proveedor,int identificador,int valoractual,int valorminimo);

    //Método Eliminar al cual le paso el nombreproducto, proveedor, identificador, valor actual y valor mínimo para que el usurio elimine lo que
    //ha seleccionado y será un método Boolean ya que debemos confirmar que se ha eliminado sin error el producto seleccionado por el
    //usuario.
    public Boolean Eliminar(String nombreproducto,String proveedor,int identificador,int valoractual,int valorminimo);

    //Método Introducir al cual le paso el nombreproducto, proveedor, identificador, valor actual y valor mínimo que quiere añadir el usuario a su
    //control del stock. Me devolverá una cadena con lo introducido por parte del usuario para guardarlo.
    public String Introducir(String nombreproducto,String proveedor,int identificador,int valoractual,int valorminimo);

}//Cierro la interfaz.
