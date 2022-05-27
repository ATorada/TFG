package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ángel Torada
 */
public class ConexionBBDD {

    /**
     * Método que se encarga de devolver una conexión con la base de datos
     *
     * @return Devuele la conexión con la base de datos
     */
    public Connection conexion() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://tai.db.elephantsql.com/anpnlxep",
                    "anpnlxep", "vvlNvnmyeMEthAuBjgrHfzgSqF6wl9go");
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }

        return conexion;
    }

}
