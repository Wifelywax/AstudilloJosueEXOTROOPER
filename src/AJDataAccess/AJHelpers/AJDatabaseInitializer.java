//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package AJDataAccess.AJHelpers;


import AJInfrastructura.AJAppConfig;
import AJInfrastructura.AJAppException;
import java.sql.*;

public class AJDatabaseInitializer {

    private static final String DBPath = AJAppConfig.getDATABASE();
    public static void initializeDatabase() throws AJAppException {
        try (Connection conn = DriverManager.getConnection(DBPath)) {
            // SQLite está en auto-commit por defecto, no es necesario llamar a commit()

            Statement stmt = conn.createStatement();

            // Verificar si la tabla ya existe
            try (ResultSet rs = conn.getMetaData().getTables(null, null, "TipoExobot", null)) {
                if (rs.next()) {
                    System.out.println("✓ Base de datos ya existe, omitiendo inicialización");
                    return;
                }
            }

            System.out.println("✓ Inicializando base de datos...");

            // DDL - Crear tablas (solo si no existen)
            stmt.execute("CREATE TABLE IF NOT EXISTS TipoExobot (\n" +
                    "    IdTipoExobot    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    Nombre          VARCHAR(30) NOT NULL UNIQUE,\n" +
                    "    Estado          VARCHAR(1) NOT NULL DEFAULT 'A',\n" +
                    "    FechaCreacion   DATETIME DEFAULT (datetime('now','localtime')),\n" +
                    "    FechaModifica   DATETIME DEFAULT (datetime('now','localtime'))\n" +
                    ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Arma (\n" +
                    "    IdArma      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    Tipo        VARCHAR(30) NOT NULL,\n" +
                    "    Accion      VARCHAR(50) NOT NULL,\n" +
                    "    Entrenada   INTEGER DEFAULT 0,\n" +
                    "    Estado          VARCHAR(1) NOT NULL DEFAULT 'A',\n" +
                    "    FechaCreacion   DATETIME DEFAULT (datetime('now','localtime')),\n" +
                    "    FechaModifica   DATETIME DEFAULT (datetime('now','localtime'))\n" +
                    ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Exobot (\n" +
                    "    IdExobot        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    IdTipoExobot    INTEGER NOT NULL,\n" +
                    "    IdArmaDerecha   INTEGER,\n" +
                    "    IdArmaIzquierda INTEGER,\n" +
                    "    Serie           VARCHAR(30) NOT NULL UNIQUE,\n" +
                    "    Nombre          VARCHAR(30) NOT NULL,\n" +
                    "    Entreno         VARCHAR(2) DEFAULT 'NO',\n" +
                    "    NoAccion        INTEGER DEFAULT 0,\n" +
                    "    Estado          VARCHAR(1) NOT NULL DEFAULT 'A',\n" +
                    "    FechaCreacion   DATETIME DEFAULT (datetime('now','localtime')),\n" +
                    "    FechaModifica   DATETIME DEFAULT (datetime('now','localtime')),\n" +
                    "    FOREIGN KEY (IdTipoExobot)    REFERENCES TipoExobot(IdTipoExobot),\n" +
                    "    FOREIGN KEY (IdArmaDerecha)   REFERENCES Arma(IdArma),\n" +
                    "    FOREIGN KEY (IdArmaIzquierda) REFERENCES Arma(IdArma)\n" +
                    ")");

            // DML - Insertar datos iniciales solo si no existen
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM TipoExobot")) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    stmt.execute("INSERT INTO TipoExobot (Nombre) VALUES ('Infantería')");
                    stmt.execute("INSERT INTO TipoExobot (Nombre) VALUES ('Defensa')");
                    stmt.execute("INSERT INTO TipoExobot (Nombre) VALUES ('Explorador')");
                    System.out.println("  → Datos de TipoExobot insertados");
                }
            }

            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Arma")) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    stmt.execute("INSERT INTO Arma (Tipo, Accion, Entrenada) VALUES ('Misil', 'Disparar', 0)");
                    stmt.execute("INSERT INTO Arma (Tipo, Accion, Entrenada) VALUES ('Mortero', 'Disparar', 0)");
                    System.out.println("  → Datos de Arma insertados");
                }
            }

            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Exobot")) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    stmt.execute(
                            "INSERT INTO Exobot (IdTipoExobot, IdArmaDerecha, IdArmaIzquierda, Serie, Nombre) " +
                                    "VALUES (1, 1, 2, 'INF-001', 'ExoTrooper')");
                    stmt.execute(
                            "INSERT INTO Exobot (IdTipoExobot, IdArmaDerecha, IdArmaIzquierda, Serie, Nombre) " +
                                    "VALUES (2, 2, 1, 'DEF-001', 'ExoScout')");
                    System.out.println("  → Datos de Exobot insertados");
                }
            }

            // Crear vista (recrearla siempre por si hay cambios)
            stmt.execute("DROP VIEW IF EXISTS ExobotDetalle");
            stmt.execute("CREATE VIEW ExobotDetalle AS\n" +
                    "SELECT\n" +
                    "    e.IdExobot,\n" +
                    "    e.Nombre,\n" +
                    "    e.Serie,\n" +
                    "    t.Nombre AS TipoExobot,\n" +
                    "    e.Entreno,\n" +
                    "    e.NoAccion,\n" +
                    "    ad.Tipo AS ArmaDerecha,\n" +
                    "    ai.Tipo AS ArmaIzquierda\n" +
                    "FROM Exobot e\n" +
                    "JOIN TipoExobot t ON e.IdTipoExobot = t.IdTipoExobot\n" +
                    "LEFT JOIN Arma ad ON e.IdArmaDerecha = ad.IdArma\n" +
                    "LEFT JOIN Arma ai ON e.IdArmaIzquierda = ai.IdArma\n" +
                    "WHERE e.Estado = 'A'");

            stmt.close();
            System.out.println("✓ Base de datos inicializada correctamente");

        } catch (SQLException e) {
            throw new AJAppException(null, e, AJDatabaseInitializer.class, "initializeDatabase");
        }
    }
}
