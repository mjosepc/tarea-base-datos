package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private static final String SELECT_HABITACION_BY_ID = "SELECT * FROM Habitaciones WHERE id_habitacion = ?";

    public void createHabitacion(Habitacion habitacion) throws SQLException {
        String query = "INSERT INTO Habitaciones (nombre_habitacion, id_tipo, precio_noche) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, habitacion.getNombreHabitacion());
                stmt.setInt(2, habitacion.getIdTipo());
                stmt.setInt(3, habitacion.getPrecioNoche());
                stmt.executeUpdate();
                conn.commit(); // Confirmar transacción
            } catch (SQLException ex) {
                conn.rollback(); // Deshacer transacción en caso de error
                throw ex;
            } finally {
                conn.setAutoCommit(true); // Restaurar el modo de auto-commit
            }
        }
    }

    public Habitacion getHabitacion(int idHabitacion) throws SQLException {
        String query = "SELECT * FROM Habitaciones WHERE id_habitacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idHabitacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Habitacion(
                            rs.getInt("id_habitacion"),
                            rs.getString("nombre_habitacion"),
                            rs.getInt("id_tipo"),
                            rs.getInt("precio_noche")
                    );
                }
            }
        }
        return null;
    }

    public List<Habitacion> getAllHabitaciones() throws SQLException {
        String query = "SELECT * FROM Habitaciones";
        List<Habitacion> habitaciones = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getInt("id_habitacion"),
                        rs.getString("nombre_habitacion"),
                        rs.getInt("id_tipo"),
                        rs.getInt("precio_noche")
                );
                habitaciones.add(habitacion);
            }
        }
        return habitaciones;
    }

    public void updateHabitacion(Habitacion habitacion) throws SQLException {
        String query = "UPDATE Habitaciones SET nombre_habitacion = ?, id_tipo = ?, precio_noche = ? WHERE id_habitacion = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, habitacion.getNombreHabitacion());
                stmt.setInt(2, habitacion.getIdTipo());
                stmt.setInt(3, habitacion.getPrecioNoche());
                stmt.setInt(4, habitacion.getIdHabitacion());
                stmt.executeUpdate();
                conn.commit(); // Confirmar transacción
            } catch (SQLException ex) {
                conn.rollback(); // Deshacer transacción en caso de error
                throw ex;
            } finally {
                conn.setAutoCommit(true); // Restaurar el modo de auto-commit
            }
        }
    }

    public void deleteHabitacion(int idHabitacion) throws SQLException {
        String query = "DELETE FROM Habitaciones WHERE id_habitacion = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idHabitacion);
                stmt.executeUpdate();
                conn.commit(); // Confirmar transacción
            } catch (SQLException ex) {
                conn.rollback(); // Deshacer transacción en caso de error
                throw ex;
            } finally {
                conn.setAutoCommit(true); // Restaurar el modo de auto-commit
            }
        }
    }

    public Habitacion getHabitacionById(int idHabitacion) throws SQLException {
        Habitacion habitacion = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_HABITACION_BY_ID)) {
            stmt.setInt(1, idHabitacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    habitacion = new Habitacion(
                            rs.getInt("id_habitacion"),
                            rs.getString("nombre_habitacion"),
                            rs.getInt("id_tipo"),
                            rs.getInt("precio_noche")
                    );
                }
            }
        }
        return habitacion;
    }
    public List<Habitacion> getHabitacionesDisponibles(Date fechaCheckin, Date fechaCheckout) throws SQLException {
        String query = "SELECT * FROM Habitaciones h " +
                "LEFT JOIN Reservas r ON h.id_habitacion = r.id_habitacion " +
                "WHERE r.id_reserva IS NULL"; // Checking if there are no bookings

        List<Habitacion> habitacionesDisponibles = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getInt("id_habitacion"),
                        rs.getString("nombre_habitacion"),
                        rs.getInt("id_tipo"),
                        rs.getInt("precio_noche")
                );
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }

}
