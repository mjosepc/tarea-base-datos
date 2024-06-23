package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private static final String SELECT_SERVICIO_BY_ID = "SELECT * FROM Servicios WHERE id_servicio = ?";

    public Servicio getServicioById(int idServicio) throws SQLException {
        Servicio servicio = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SERVICIO_BY_ID)) {
            stmt.setInt(1, idServicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio(
                            rs.getInt("id_servicio"),
                            rs.getString("tipo_servicio"),
                            rs.getString("nombre_servicio"),
                            rs.getInt("precio_servicio")
                    );
                }
            }
        }
        return servicio;
    }

    public void createServicio(Servicio servicio) throws SQLException {
        String query = "INSERT INTO Servicios (tipo_servicio, nombre_servicio, precio_servicio) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, servicio.getTipoServicio());
                stmt.setString(2, servicio.getNombreServicio());
                stmt.setInt(3, servicio.getPrecioServicio());
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

    public Servicio getServicio(int idServicio) throws SQLException {
        String query = "SELECT * FROM Servicios WHERE id_servicio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idServicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Servicio(
                            rs.getInt("id_servicio"),
                            rs.getString("tipo_servicio"),
                            rs.getString("nombre_servicio"),
                            rs.getInt("precio_servicio")
                    );
                }
            }
        }
        return null;
    }

    public List<Servicio> getAllServicios() throws SQLException {
        String query = "SELECT * FROM Servicios";
        List<Servicio> servicios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Servicio servicio = new Servicio(
                        rs.getInt("id_servicio"),
                        rs.getString("tipo_servicio"),
                        rs.getString("nombre_servicio"),
                        rs.getInt("precio_servicio")
                );
                servicios.add(servicio);
            }
        }
        return servicios;
    }

    public void updateServicio(Servicio servicio) throws SQLException {
        String query = "UPDATE Servicios SET tipo_servicio = ?, nombre_servicio = ?, precio_servicio = ? WHERE id_servicio = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, servicio.getTipoServicio());
                stmt.setString(2, servicio.getNombreServicio());
                stmt.setInt(3, servicio.getPrecioServicio());
                stmt.setInt(4, servicio.getIdServicio());
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

    public void deleteServicio(int idServicio) throws SQLException {
        String query = "DELETE FROM Servicios WHERE id_servicio = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idServicio);
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
}
