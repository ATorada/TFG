package Controlador;

import Modelo.DatosBBDD;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
*
*
* Desarrollada por Ángel Torada
*
*

/**
 *
 * @autor Ángel Torada
 * @version 1.0
 */
public class Controlador {

    private final DatosBBDD bbdd;

    /**
     * Constructor del controlador
     */
    public Controlador() {
        bbdd = new DatosBBDD();
    }

    /**
     * Método que se encarga de crear la sesión un usuario
     *
     * @param usuario Usuario que inicia sesión
     * @param contra Contraseña del usuario
     * @return Devuelve true en caso de que exista en la base de datos
     */
    public boolean iniciarSesion(String usuario, String contra) {
        try {
            return bbdd.loginUsuario(usuario, cifrarContra(contra));
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Método que se encarga de registrar un usuario
     *
     * @param usuario Usuario a registrar
     * @param contra Contraseña del usuario
     * @throws SQLException Se lanzará en caso de que el usuario ya exista
     */
    public void registrarUsuario(String usuario, String contra) throws SQLException {
        try {
            bbdd.registrarUsuario(usuario, cifrarContra(contra));
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que se encarga de crear la sesión un usuario
     *
     * @param usuario Usuario a consultar
     * @return Devuelve la fecha del último inicio de sesión del usuario
     */
    public Date ultimoInicioUsuario(String usuario) {
        return bbdd.ultimoInicioUsuario(usuario);
    }

    /**
     * Método que se encarga de obtener los ingresos de un usuario
     *
     * @param usuario Usuario del que se quieren obtener los ingresos
     * @param fecha Fecha de los ingresos a obtener
     * @return Ingresos del usuario
     */
    public HashMap<String, ArrayList> obtenerIngresosUsuario(String usuario, Date fecha) {
        if (fecha != null) {
            return bbdd.obtenerIngresosUsuario(usuario, formatearPeriodo(fecha));
        } else {
            return bbdd.obtenerIngresosUsuario(usuario, obtenerPeriodoActual());
        }

    }

    /**
     * Método que se encarga de devolver los gastos de un usuario
     *
     * @param usuario Usuario del que queremos obtener los gastos
     * @param fecha Periodo de los gastos
     * @return
     */
    public HashMap<String, ArrayList> obtenerGastosUsuario(String usuario, Date fecha) {
        if (fecha != null) {
            return bbdd.obtenerGastosUsuario(usuario, formatearPeriodo(fecha));
        } else {
            return bbdd.obtenerGastosUsuario(usuario, obtenerPeriodoActual());
        }

    }

    /**
     * Método que se encarga de insertar un ingreso o un gasto de un usuario
     *
     * @param usuario Usuario del que se quieren hacer el ingreso o gasto
     * @param concepto Concepto del ingreso/gasto
     * @param cantidad Cantidad del ingreso/gasto
     * @param constante Determina si es un ingreso/gasto constante
     * @param esIngreso Determina si es un ingreso
     * @param computaUnidadFamiliar Determina si computa en la Unidad Familiar
     * @param categoria Categoría del gasto
     * @throws SQLException Se devolverá en caso de que no se pueda insertar
     */
    public void insertarGastoIngreso(String usuario, String concepto, double cantidad, boolean constante, boolean esIngreso, boolean computaUnidadFamiliar, String categoria) throws SQLException {
        bbdd.insertarGastoIngreso(usuario, concepto, obtenerPeriodoActual(), cantidad, constante, esIngreso, computaUnidadFamiliar, categoria);
    }

    /**
     * Método que se encarga de borrar un gasto o un ingreso de un usuario
     *
     * @param usuario Usuario del que se va a borrar un ingreso/gasto
     * @param concepto Concepto del gasto a borrar
     * @throws SQLException En caso de que no exista o no se pueda borrar
     * lanzará
     */
    public void deleteGastoIngreso(String usuario, String concepto) throws SQLException {
        bbdd.deleteGastoIngreso(usuario, concepto, obtenerPeriodoActual());
    }

    /**
     * Método que se encarga de obtener una compra grande un usuario
     *
     * @param usuario Usuario del que se quiere obtener la compra grande
     * @return Devuelve la información de la compra grande el usuario
     */
    public ArrayList obtenerCompraGrandeUsuario(String usuario) {
        return bbdd.obtenerCompraGrandeUsuario(usuario);
    }

    /**
     * Método que se encarga de insertar una compra grande a un usuario
     *
     * @param usuario Usuario al cual se le va a insertar la compra grande
     * @param nombre Nombre de la compra grande
     * @param periodo Periodo final de la compra grande
     * @param cantidad Cantidad del precio de la compra grande
     * @throws SQLException En caso de no poder crearla se lanzará
     */
    public void insertarCompraGrande(String usuario, String nombre, Date periodo, double cantidad) throws SQLException {
        bbdd.insertarCompraGrande(usuario, nombre, formatearPeriodo(periodo), cantidad);
    }

    /**
     * Método que se encarga de actualizar una compra grande
     *
     * @param usuario Usuario del que se actualizará la compra grande
     * @param nombre Nuevo nombre de la compra grande
     * @param periodo Nuevo periodo de la compra grande
     * @param cantidad Nueva cantidad de la compra grande
     * @throws SQLException En caso de que no se pueda actualizar se lanzará
     */
    public void actualizarCompraGrande(String usuario, String nombre, Date periodo, double cantidad) throws SQLException {
        bbdd.actualizarCompraGrande(usuario, nombre, formatearPeriodo(periodo), cantidad);
    }

    /**
     * Método que se encarga de borrar una compra grande de un usuario
     *
     * @param usuario Usuario del que se va a eliminar la compra grande
     * @throws SQLException Se lanzará en caso de que no se pueda borrar la
     * compra grande
     */
    public void deleteCompraGrande(String usuario) throws SQLException {
        bbdd.deleteCompraGrande(usuario);
    }

    /**
     * Método que se encarga de obtener todos los ingresos y gastos de los
     * usuarios de una unidad familiar
     *
     * @param usuario Usuario de la unidad familiar de dónde queremos obtener
     * los datos
     * @return Devuelve los ingresos y gastos de los usuarios de la Unidad
     * Familiar
     */
    public HashMap<String, ArrayList> obtenerUnidadFamiliar(String usuario) {
        return bbdd.obtenerUnidadFamiliar(usuario, obtenerPeriodoActual());
    }

    /**
     * Método que se encarga de actualizar la unidad familiar de un usuario
     *
     * @param usuario Usuario del que se va a actualizar la unidad familiar
     * @param id Nuevo ID de la unidad familiar para el usuario usuario para
     * realizar una transacción
     * @throws SQLException En caso de que no se pueda actualizar se lanzará
     */
    public void actualizarUnidadFamiliar(String usuario, int id) throws SQLException {
        bbdd.actualizarUnidadFamiliar(usuario, id, null);
    }

    /**
     * Método que se encarga de crear una unidad familiar
     *
     * @param usuario Usuario que crea la unidad familiar
     * @throws SQLException Se lanzará en caso de que no se pueda crear la
     * unidad familiar
     */
    public void crearUnidadFamiliar(String usuario) throws SQLException {
        bbdd.crearUnidadFamiliar(usuario);
    }

    /**
     * Método que se encarga de obtener el ID de la Unidad Familiar de un
     * usuario
     *
     * @param usuario Usuario del que se quiere obtener el ID de la unidad
     * familiar
     * @return Devuelve el ID de la Unidad Familiar del usuario
     */
    public int obtenerIdUnidadFamiliar(String usuario) {
        return bbdd.obtenerIdUnidadFamiliar(usuario);
    }

    /**
     * Método que se encarga de actualizar el gasto de tipo ahorro de un usuario
     *
     * @param usuario Usuario del que se va a actualizar el ahorro
     * @param cantidad Cantidad del nuevo ahorro
     * @throws SQLException Se lanzará en caso de que no se pueda actualizar el
     * gasto
     */
    public void actualizarAhorro(String usuario, double cantidad) throws SQLException {
        bbdd.actualizarAhorro(usuario, obtenerPeriodoActual(), cantidad);
    }

    /**
     * Método que se encarga de obtener todos los ingresos de un usuario
     *
     * @param usuario Usuario del que se van a obtener todos los ingresos
     * @return Ingresos del usuario
     */
    public HashMap<String, ArrayList> obtenerTodosLosIngresosUsuario(String usuario) {
        return bbdd.obtenerTodosLosIngresosUsuario(usuario);
    }

    /**
     * Método que se encarga de obtener todos los gastos de un usuario
     *
     * @param usuario Usuario del que se van a obtener todos los gastos
     * @return Gastos del usuario
     */
    public HashMap<String, ArrayList> obtenerTodosLosGastosUsuario(String usuario) {
        return bbdd.obtenerTodosLosGastosUsuario(usuario);
    }

    /**
     * Método que se encarga de devolver el periodo actual
     *
     * @return Devuelve la fecha del periodo actual
     */
    public Date obtenerPeriodoActual() {
        LocalDate fechaActual = LocalDate.now();
        String periodo = fechaActual.getYear() + "-" + fechaActual.getMonthValue() + "-1";
        return Date.valueOf(periodo);
    }

    //Método que se encarga de formatear una fecha a su periodo
    private Date formatearPeriodo(Date date) {
        LocalDate fecha = date.toLocalDate();
        String periodo = fecha.getYear() + "-" + fecha.getMonthValue() + "-1";
        return Date.valueOf(periodo);
    }

    //Método que se encarga de cifrar la contraseña
    private String cifrarContra(String contra) throws NoSuchAlgorithmException, NoSuchProviderException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(contra.getBytes());

        byte[] bytes = md.digest(contra.getBytes());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return sb.toString();

    }

}
