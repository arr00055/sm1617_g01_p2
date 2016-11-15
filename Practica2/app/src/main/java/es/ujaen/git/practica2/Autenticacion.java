package es.ujaen.git.practica2;

/**La clase Autenticacion será la encargada de guardar los datos del usuario.
 * la clase es declarada como public siendo una pieza de código visible en todos los niveles.
 */
public class Autenticacion{
    //Atributos de la clase Autenticacion.
    public static final int SERVICE_PORT=6000;
    //Con el modificador protected solo las clases que se encuentren en el mismo paquete pueden ver y acceder a estos atributos.
    protected String mUser="";
    protected String mPass="";
    protected String mIP="";
    protected int    mPort=SERVICE_PORT;

    /**
     *Constructor de la clase Autenticacion.
     * @param user variable donde se guarda el nombre o nick del usuario.
     * @param pass variable donde se guarda la contraseña del usuario.
     * @param ip   variable donde se guarda la dirección Ip del usuario.
     * @param port variable donde se guarda el puerto del usuario.
     */
    public Autenticacion(String user,String pass,String ip, int port){

        mUser=user;
        mPass=pass;
        mIP=ip;
        mPort=port;

    }//Fin del constructor de la clase Autenticacion.
 /**
   *  Método getUser.
   *  @return mUser.
   */
    public String getUser(){
        return mUser;
    }
    /**
     *  Método setUser.
     */
    public void setUser(String user){
        mUser=user;
    }
    /**
     *  Método getPass.
     *  @return mPass.
     */
    public String getPass(){
        return mPass;
    }
    /**
     *  Método setPass.
     */
    public void setPass(String pass){
        mPass=pass;
    }
    /**
     *  Método getIP.
     *  @return mIP.
     */
    public String getIP(){
        return mIP;
    }
    /**
     *  Método setIP.
     */
    public void setIP(String ip){
        mIP=ip;
    }
    /**
     *  Método getPort.
     *  @return mPort.
     */
    public int getPort(){
        return mPort;
    }
    /**
     *  Método setPort.
     */
    public void setPort(int port){
        mPort=port;
    }

}//Fin de la clase Autenticacion.
