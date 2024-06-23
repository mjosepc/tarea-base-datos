package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static final String SELECT_RESERVA_BY_ID = "SELECT * FROM Reservas WHERE id_reserva = ?";
    private static final String SELECT_RESERVA_BY_CLIENTE = "SELECT * FROM Reservas WHERE dni_cliente = ?";

    public Reserva getReservaById(int idReserva) throws SQLException {
        Reserva reserva = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_RESERVA_BY_ID)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva(
                            rs.getInt("id_reserva"),
                            rs.getInt("dni_cliente"),
                            rs.getInt("id_habitacion"),
                            rs.getDate("fecha_checkin"),
                            rs.getDate("fecha_checkout")
                    );
                }
            }
        }
        return reserva;
    }

    public void createReserva(Reserva reserva) throws SQLException {
        String query = "INSERT INTO Reservas (dni_cliente, id_habitacion, fecha_checkin, fecha_checkout) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, reserva.getDniCliente());
                stmt.setInt(2, reserva.getIdHabitacion());
                stmt.setDate(3, new java.sql.Date(reserva.getFechaCheckin().getTime())); // Convertir Date a java.sql.Date
                stmt.setDate(4, new java.sql.Date(reserva.getFechaCheckout().getTime())); // Convertir Date a java.sql.Date
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

    public Reserva getReserva(int idReserva) throws SQLException {
        Reserva reserva = null;
        String query = "SELECT * FROM Reservas WHERE id_reserva = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva(
                            rs.getInt("id_reserva"),
                            rs.getInt("dni_cliente"),
                            rs.getInt("id_habitacion"),
                            rs.getDate("fecha_checkin"),
                            rs.getDate("fecha_checkout")
                    );
                }
            }
        }
        return reserva;
    }

    public List<Reserva> getAllReservas() throws SQLException {
        String query = "SELECT * FROM Reservas";
        List<Reserva> reservas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        rs.getInt("dni_cliente"),
                        rs.getInt("id_habitacion"),
                        rs.getDate("fecha_checkin"),
                        rs.getDate("fecha_checkout")
                );
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public void updateReserva(Reserva reserva) throws SQLException {
        String query = "UPDATE Reservas SET dni_cliente = ?, id_habitacion = ?, fecha_checkin = ?, fecha_checkout = ? WHERE id_reserva = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, reserva.getDniCliente());
                stmt.setInt(2, reserva.getIdHabitacion());
                stmt.setDate(3, reserva.getFechaCheckin());
                stmt.setDate(4, reserva.getFechaCheckout());
                stmt.setInt(5, reserva.getIdReserva());
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

    public void deleteReserva(int idReserva) throws SQLException {
        String query = "DELETE FROM Reservas WHERE id_reserva = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idReserva);
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

    public Reserva getReservaByCliente(int dniCliente) throws SQLException {
        Reserva reserva = null;
        String query = "SELECT * FROM Reservas WHERE dni_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dniCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva(
                            rs.getInt("id_reserva"),
                            rs.getInt("dni_cliente"),
                            rs.getInt("id_habitacion"),
                            rs.getDate("fecha_checkin"),
                            rs.getDate("fecha_checkout")
                    );
                }
            }
        }
        return reserva; // Retorna null si no se encontró ninguna reserva para el cliente dado
    }
    public List<Reserva> getReservasByHabitacion(int idHabitacion) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM Reservas WHERE id_habitacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idHabitacion);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reserva reserva = new Reserva(
                            rs.getInt("id_reserva"),
                            rs.getInt("dni_cliente"),
                            rs.getInt("id_habitacion"),
                            rs.getDate("fecha_checkin"),
                            rs.getDate("fecha_checkout")
                    );
                    reservas.add(reserva);
                }
            }
        }
        return reservas;
    }

}
