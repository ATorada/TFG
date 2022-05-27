package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ángel Torada
 */
public class DatosBBDD {

    ConexionBBDD c;

    /**
     * Constructor que inicializa un objeto de la clase conexión
     */
    public DatosBBDD() {
        this.c = new ConexionBBDD();
    }

    /**
     * Método que se encarga de registrar un usuario
     *
     * @param usuario Usuario a registrar
     * @param contra Contraseña del usuario
     * @throws SQLException Se lanzará en caso de que el usuario ya exista
     *
     */
    public void registrarUsuario(String usuario, String contra) throws SQLException {
        Connection cnConnection = c.conexion();
        PreparedStatement psRegistro = cnConnection.prepareStatement("INSERT INTO public.usuario(usuario, contra) VALUES (?, ?);");
        psRegistro.setString(1, usuario);
        psRegistro.setString(2, contra);
        psRegistro.executeUpdate();
        cnConnection.close();

    }

    /**
     * Método que se encarga de crear la sesión un usuario
     *
     * @param usuario Usuario a consultar
     * @return Devuelve la fecha del último inicio de sesión del usuario
     */
    public Date ultimoInicioUsuario(String usuario) {

        try {
            Connection cnConnection = c.conexion();
            PreparedStatement psUltimoInicio = cnConnection.prepareStatement("SELECT ultimo_inicio FROM usuario WHERE usuario = ?;");
            psUltimoInicio.setString(1, usuario);
            ResultSet rs = psUltimoInicio.executeQuery();
            rs.next();
            Date resultado = rs.getDate("ultimo_inicio");

            PreparedStatement psActualizarUltimoInicio = cnConnection.prepareStatement("UPDATE public.usuario"
                    + "	SET ultimo_inicio=?"
                    + "	WHERE usuario = ?;");
            psActualizarUltimoInicio.setDate(1, Date.valueOf(LocalDate.now()));
            psActualizarUltimoInicio.setString(2, usuario);
            psActualizarUltimoInicio.executeUpdate();

            cnConnection.close();
            return resultado;
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     * Método que se encarga de crear la sesión un usuario
     *
     * @param usuario Usuario que inicia sesión
     * @param contra Contraseña del usuario
     * @return Devuelve true en caso de que exista en la base de datos
     */
    public boolean loginUsuario(String usuario, String contra) {

        try {
            Connection cnConnection = c.conexion();
            PreparedStatement psLogin = cnConnection.prepareStatement("SELECT count(*) FROM usuario WHERE usuario = ? AND contra = ?;");
            psLogin.setString(1, usuario);
            psLogin.setString(2, contra);
            ResultSet rs = psLogin.executeQuery();
            rs.next();
            boolean resultado = rs.getInt(1) == 1;
            cnConnection.close();
            return resultado;
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * Método que se encarga de obtener el último inicio de sesión de un usuario
     *
     * @param usuario Usuario del que se quiere obtener la compra grande
     * @return Devuelve la información de la compra grande el usuario
     */
    public ArrayList obtenerCompraGrandeUsuario(String usuario) {

        try {
            Connection cnConnection = c.conexion();
            PreparedStatement psCompraGrande = cnConnection.prepareStatement("SELECT nombre,periodo,cantidad FROM compra_grande WHERE usuario = ?;");
            psCompraGrande.setString(1, usuario);

            ResultSet rsCompraGrande = psCompraGrande.executeQuery();
            ArrayList compraGrande = new ArrayList();
            while (rsCompraGrande.next()) {
                compraGrande.add(rsCompraGrande.getString("nombre")); //Valor 0 = Nombre
                compraGrande.add(rsCompraGrande.getDate("periodo")); //Valor 1 = Periodo
                compraGrande.add(rsCompraGrande.getDouble("cantidad")); //Valor 2 = Cantidad
            }
            cnConnection.close();
            return compraGrande;

        } catch (SQLException e) {

            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return null;
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

        try {
            Connection cnConnection = c.conexion();
            PreparedStatement psIdUnidadFamiliar = cnConnection.prepareStatement("SELECT id_unidad_familiar FROM usuario WHERE usuario = ?;");
            psIdUnidadFamiliar.setString(1, usuario);

            ResultSet rsIdUnidadFamiliar = psIdUnidadFamiliar.executeQuery();
            rsIdUnidadFamiliar.next();
            cnConnection.close();
            return rsIdUnidadFamiliar.getInt("id_unidad_familiar");
        } catch (SQLException e) {
            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return 0;
    }

    /**
     * Método que se encarga de obtener todos los ingresos y gastos de los
     * usuarios de una unidad familiar
     *
     * @param usuario Usuario de la unidad familiar de dónde queremos obtener
     * los datos
     * @param periodo Periodo de los datos a buscar
     * @return Devuelve los ingresos y gastos de los usuarios de la Unidad
     * Familiar
     */
    public HashMap<String, ArrayList> obtenerUnidadFamiliar(String usuario, Date periodo) {

        try {
            HashMap<String, ArrayList> map = new HashMap<>();
            Connection cnConnection = c.conexion();

            int id_unidad_familiar = obtenerIdUnidadFamiliar(usuario);
            if (id_unidad_familiar != 0) {
                PreparedStatement psUsuariosUnidadFamiliar = cnConnection.prepareStatement("SELECT usuario FROM usuario WHERE id_unidad_familiar = ?;");
                psUsuariosUnidadFamiliar.setInt(1, id_unidad_familiar);

                ResultSet rsUsuariosUnidadFamiliar = psUsuariosUnidadFamiliar.executeQuery();

                while (rsUsuariosUnidadFamiliar.next()) {
                    ArrayList finanzas = new ArrayList();
                    PreparedStatement psGastos = cnConnection.prepareStatement("SELECT cantidad FROM finanzas WHERE usuario = ? AND categoria IS NOT NULL AND periodo = ? AND computa_unidad_familiar = true;");
                    psGastos.setString(1, rsUsuariosUnidadFamiliar.getString("usuario"));
                    psGastos.setDate(2, periodo);

                    ResultSet rsGastos = psGastos.executeQuery();

                    double totalGastos = 0;
                    while (rsGastos.next()) {
                        totalGastos += rsGastos.getDouble("cantidad");
                    }

                    PreparedStatement psIngresos = cnConnection.prepareStatement("SELECT cantidad FROM finanzas WHERE usuario = ? AND categoria IS NULL AND periodo = ? AND computa_unidad_familiar = true;");
                    psIngresos.setString(1, rsUsuariosUnidadFamiliar.getString("usuario"));
                    psIngresos.setDate(2, periodo);

                    ResultSet rsIngresos = psIngresos.executeQuery();

                    double totalIngresos = 0;
                    while (rsIngresos.next()) {
                        totalIngresos += rsIngresos.getDouble("cantidad");
                    }

                    finanzas.add(totalGastos); //Valor 0 = Gastos
                    finanzas.add(totalIngresos); //Valor 1 = Ingresos

                    map.put(rsUsuariosUnidadFamiliar.getString("usuario"), finanzas);
                }
            }
            cnConnection.close();
            return map;

        } catch (SQLException e) {

            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return null;
    }

    /**
     * Método que se encarga de obtener los ingresos de un usuario
     *
     * @param usuario Usuario del que se quieren obtener los ingresos
     * @param periodo Periodo del que se quieren obtener los ingresos
     * @return Ingresos del usuario
     */
    public HashMap<String, ArrayList> obtenerIngresosUsuario(String usuario, Date periodo) {

        try {
            HashMap<String, ArrayList> map = new HashMap<>();

            Connection cnConnection = c.conexion();
            PreparedStatement psIngresos = cnConnection.prepareStatement("SELECT concepto,cantidad,constante,computa_unidad_familiar FROM finanzas WHERE usuario = ? AND categoria IS NULL AND periodo = ?;");
            psIngresos.setString(1, usuario);
            psIngresos.setDate(2, periodo);

            ResultSet rsIngresos = psIngresos.executeQuery();

            while (rsIngresos.next()) {
                ArrayList atributos = new ArrayList();
                String concepto = rsIngresos.getString("concepto");
                atributos.add(rsIngresos.getDouble("cantidad")); //Valor 0 = Cantidad
                atributos.add(rsIngresos.getBoolean("constante")); //Valor 1 = Constante
                atributos.add(rsIngresos.getBoolean("computa_unidad_familiar")); //Valor 2 = Computa Unidad Familiar
                map.put(concepto, atributos);
            }
            cnConnection.close();
            return map;

        } catch (SQLException e) {

            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return null;
    }

    /**
     * Método que se encarga de insertar un ingreso o un gasto de un usuario
     *
     * @param usuario Usuario del que se quieren hacer el ingreso o gasto
     * @param concepto Concepto del ingreso/gasto
     * @param periodo Periodo del que se va a hacer el ingreso/gasto
     * @param cantidad Cantidad del ingreso/gasto
     * @param constante Determina si es un ingreso/gasto constante
     * @param esIngreso Determina si es un ingreso
     * @param computaUnidadFamiliar Determina si computa en la Unidad Familiar
     * @param categoria Categoría del gasto
     */
    public void insertarGastoIngreso(String usuario, String concepto, Date periodo, double cantidad, boolean constante, boolean esIngreso, boolean computaUnidadFamiliar, String categoria) throws SQLException {
        try (Connection cnConnection = c.conexion()) {
            PreparedStatement psGastoIngreso = cnConnection.prepareStatement("INSERT INTO public.finanzas("
                    + "usuario, concepto, periodo, cantidad, constante, es_ingreso, computa_unidad_familiar, categoria)"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            psGastoIngreso.setString(1, usuario);
            psGastoIngreso.setString(2, concepto);
            psGastoIngreso.setDate(3, periodo);
            psGastoIngreso.setDouble(4, cantidad);
            psGastoIngreso.setBoolean(5, constante);
            psGastoIngreso.setBoolean(6, esIngreso);
            psGastoIngreso.setBoolean(7, computaUnidadFamiliar);
            psGastoIngreso.setString(8, categoria);
            psGastoIngreso.executeUpdate();
            cnConnection.close();
        }
    }

    /**
     * Método que se encarga de borrar un gasto o un ingreso de un usuario
     *
     * @param usuario Usuario del que se va a borrar un ingreso/gasto
     * @param concepto Concepto del gasto a borrar
     * @param periodo Periodo del gasto a borrar
     * @throws SQLException En caso de que no exista o no se pueda borrar
     * lanzará
     */
    public void deleteGastoIngreso(String usuario, String concepto, Date periodo) throws SQLException {
        try (Connection cnConnection = c.conexion()) {
            PreparedStatement psDeleteGastoIngreso = cnConnection.prepareStatement("DELETE FROM public.finanzas"
                    + "	WHERE usuario = ? AND periodo = ? AND concepto = ?;");
            psDeleteGastoIngreso.setString(1, usuario);
            psDeleteGastoIngreso.setDate(2, periodo);
            psDeleteGastoIngreso.setString(3, concepto);
            psDeleteGastoIngreso.executeUpdate();
            cnConnection.close();
        }
    }

    /**
     * Método que se encarga de obtener los gastos de usuario
     *
     * @param usuario Usuario del que se van a obtener los gastos
     * @param periodo Periodo de los gastos que se quieren obtener
     * @return Gastos del usuario
     */
    public HashMap<String, ArrayList> obtenerGastosUsuario(String usuario, Date periodo) {

        try {
            HashMap<String, ArrayList> map = new HashMap<>();

            Connection cnConnection = c.conexion();
            PreparedStatement psGastos = cnConnection.prepareStatement("SELECT concepto,cantidad,categoria,constante,computa_unidad_familiar FROM finanzas WHERE usuario = ? AND categoria IS NOT NULL AND periodo = ?;");
            psGastos.setString(1, usuario);
            psGastos.setDate(2, periodo);

            ResultSet rsGastos = psGastos.executeQuery();

            while (rsGastos.next()) {
                ArrayList atributos = new ArrayList();
                String concepto = rsGastos.getString("concepto");
                atributos.add(rsGastos.getDouble("cantidad")); //Valor 0 = Cantidad
                atributos.add(rsGastos.getString("categoria")); //Valor 1 = Categoria
                atributos.add(rsGastos.getBoolean("constante")); //Valor 2 = Constante
                atributos.add(rsGastos.getBoolean("computa_unidad_familiar")); //Valor 3 = Computa Unidad Familiar
                map.put(concepto, atributos);
            }
            cnConnection.close();
            return map;

        } catch (SQLException e) {
            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return null;
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
        try (Connection cnConnection = c.conexion()) {
            PreparedStatement psGastoIngreso = cnConnection.prepareStatement("INSERT INTO public.compra_grande("
                    + "	usuario, nombre, periodo, cantidad)"
                    + "	VALUES (?, ?, ?, ?);");
            psGastoIngreso.setString(1, usuario);
            psGastoIngreso.setString(2, nombre);
            psGastoIngreso.setDate(3, periodo);
            psGastoIngreso.setDouble(4, cantidad);
            psGastoIngreso.executeUpdate();
            cnConnection.close();
        }
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
        try (Connection cnConnection = c.conexion()) {
            PreparedStatement psGastoIngreso = cnConnection.prepareStatement("UPDATE public.compra_grande"
                    + "	SET nombre=?, periodo=?, cantidad=?"
                    + "	WHERE usuario = ?;");
            psGastoIngreso.setString(1, nombre);
            psGastoIngreso.setDate(2, periodo);
            psGastoIngreso.setDouble(3, cantidad);
            psGastoIngreso.setString(4, usuario);
            psGastoIngreso.executeUpdate();
            cnConnection.close();
        }
    }

    /**
     * Método que se encarga de actualizar la unidad familiar de un usuario
     *
     * @param usuario Usuario del que se va a actualizar la unidad familiar
     * @param id_unidad_familiar Nuevo ID de la unidad familiar para el usuario
     * @param cnConnection Conexión que se pasará en caso de que sea el último
     * usuario para realizar una transacción
     * @throws SQLException En caso de que no se pueda actualizar se lanzará
     */
    public void actualizarUnidadFamiliar(String usuario, int id_unidad_familiar, Connection cnConnection) throws SQLException {
        boolean bandera = true;
        if (cnConnection == null) {
            cnConnection = c.conexion();
            bandera = false;
        }
        try {
            if (id_unidad_familiar == 0) {
                PreparedStatement psUnidadFamiliar = cnConnection.prepareStatement("UPDATE public.usuario"
                        + "	SET id_unidad_familiar = NULL"
                        + "	WHERE usuario = ?;");
                psUnidadFamiliar.setString(1, usuario);

                psUnidadFamiliar.executeUpdate();
            } else {
                PreparedStatement psUnidadFamiliar = cnConnection.prepareStatement("UPDATE public.usuario"
                        + "	SET id_unidad_familiar = ?"
                        + "	WHERE usuario = ?;");
                psUnidadFamiliar.setInt(1, id_unidad_familiar);
                psUnidadFamiliar.setString(2, usuario);
                psUnidadFamiliar.executeUpdate();
            }
            if (!bandera) {
                cnConnection.close();
            }
        } catch (Exception e) {

        }

    }

    /**
     * Método que se encarga de borrar una compra grande de un usuario
     *
     * @param usuario Usuario del que se va a eliminar la compra grande
     * @throws SQLException Se lanzará en caso de que no se pueda borrar la
     * compra grande
     */
    public void deleteCompraGrande(String usuario) throws SQLException {
        try (Connection cnConnection = c.conexion()) {
            PreparedStatement psDeleteGastoIngreso = cnConnection.prepareStatement("DELETE FROM public.compra_grande"
                    + "	WHERE usuario = ?;");
            psDeleteGastoIngreso.setString(1, usuario);
            psDeleteGastoIngreso.executeUpdate();
            cnConnection.close();
        }
    }

    /**
     * Método que se encarga de crear una unidad familiar
     *
     * @param usuario Usuario que crea la unidad familiar
     * @throws SQLException Se lanzará en caso de que no se pueda crear la
     * unidad familiar
     */
    public void crearUnidadFamiliar(String usuario) throws SQLException {
        try (Connection cnConnection = c.conexion()) {
            cnConnection.setAutoCommit(false);
            PreparedStatement psCrearUnidadFamiliar = cnConnection.prepareStatement("INSERT INTO public.unidad_familiar default values RETURNING id_unidad_familiar;");
            ResultSet rsCrearUnidadFamiliar = psCrearUnidadFamiliar.executeQuery();
            rsCrearUnidadFamiliar.next();
            int id = rsCrearUnidadFamiliar.getInt("id_unidad_familiar");
            actualizarUnidadFamiliar(usuario, id, cnConnection);
            cnConnection.commit();
            cnConnection.close();
        }
    }

    /**
     * Método que se encarga de actualizar el gasto de tipo ahorro de un usuario
     *
     * @param usuario Usuario del que se va a actualizar el ahorro
     * @param periodo Periodo del ahorro que va a actualizarse
     * @param cantidad Cantidad del nuevo ahorro
     * @throws SQLException Se lanzará en caso de que no se pueda actualizar el
     * gasto
     */
    public void actualizarAhorro(String usuario, Date periodo, double cantidad) throws SQLException {
        try (Connection cnConnection = c.conexion()) {
            PreparedStatement psActualizarAhorro = cnConnection.prepareStatement("UPDATE public.finanzas"
                    + "	SET cantidad=?"
                    + "	WHERE usuario = ? AND periodo = ? AND concepto = 'ahorro';");
            psActualizarAhorro.setDouble(1, cantidad);
            psActualizarAhorro.setString(2, usuario);
            psActualizarAhorro.setDate(3, periodo);
            psActualizarAhorro.executeUpdate();
            cnConnection.close();
        }
    }

    /**
     * Método que se encarga de obtener todos los ingresos de un usuario
     *
     * @param usuario Usuario del que se van a obtener todos los ingresos
     * @return Ingresos del usuario
     */
    public HashMap<String, ArrayList> obtenerTodosLosIngresosUsuario(String usuario) {

        try {
            HashMap<String, ArrayList> map = new HashMap<>();

            Connection cnConnection = c.conexion();
            PreparedStatement psIngresos = cnConnection.prepareStatement("SELECT concepto,cantidad,periodo FROM finanzas WHERE usuario = ? AND categoria IS NULL;");
            psIngresos.setString(1, usuario);

            ResultSet rsIngresos = psIngresos.executeQuery();

            while (rsIngresos.next()) {
                ArrayList atributos = new ArrayList();
                atributos.add(rsIngresos.getString("concepto")); //Valor 0 = Concepto
                atributos.add(rsIngresos.getDouble("cantidad")); //Valor 1 = Cantidad
                atributos.add(rsIngresos.getDate("periodo")); //Valor 2 = Constante
                map.put(rsIngresos.getString("concepto").concat(rsIngresos.getDate("periodo").toString()), atributos);
            }
            cnConnection.close();
            return map;

        } catch (SQLException e) {

            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return null;
    }

    /**
     * Método que se encarga de obtener todos los gastos de un usuario
     *
     * @param usuario Usuario del que se van a obtener todos los gastos
     * @return Gastos del usuario
     */
    public HashMap<String, ArrayList> obtenerTodosLosGastosUsuario(String usuario) {

        try {
            HashMap<String, ArrayList> map = new HashMap<>();

            Connection cnConnection = c.conexion();
            PreparedStatement psGastos = cnConnection.prepareStatement("SELECT concepto,cantidad,periodo FROM finanzas WHERE usuario = ? AND categoria IS NOT NULL;");
            psGastos.setString(1, usuario);

            ResultSet rsGastos = psGastos.executeQuery();

            while (rsGastos.next()) {
                ArrayList atributos = new ArrayList();
                atributos.add(rsGastos.getString("concepto")); //Valor 0 = Concepto
                atributos.add(rsGastos.getDouble("cantidad")); //Valor 1 = Cantidad
                atributos.add(rsGastos.getDate("periodo")); //Valor 2 = Constante
                map.put(rsGastos.getString("concepto").concat(rsGastos.getDate("periodo").toString()), atributos);
            }
            cnConnection.close();
            return map;

        } catch (SQLException e) {

            System.out.println("Algo ha salido mal con la conexión a la base de datos.");
        }
        return null;
    }

}
