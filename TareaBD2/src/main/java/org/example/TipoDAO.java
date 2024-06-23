package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDAO {
    public void createTipo(Tipo tipo) throws SQLException {
        String query = "INSERT INTO Tipo (nombre, capacidad) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, tipo.getNombre());
                stmt.setInt(2, tipo.getCapacidad());
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

    public Tipo getTipo(int idTipo) throws SQLException {
        String query = "SELECT * FROM Tipo WHERE id_tipo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tipo(rs.getInt("id_tipo"), rs.getString("nombre"), rs.getInt("capacidad"));
                }
            }
        }
        return null;
    }

    public List<Tipo> getAllTipos() throws SQLException {
        String query = "SELECT * FROM Tipo";
        List<Tipo> tipos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tipo tipo = new Tipo(rs.getInt("id_tipo"), rs.getString("nombre"), rs.getInt("capacidad"));
                tipos.add(tipo);
            }
        }
        return tipos;
    }

    public void updateTipo(Tipo tipo) throws SQLException {
        String query = "UPDATE Tipo SET nombre = ?, capacidad = ? WHERE id_tipo = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, tipo.getNombre());
                stmt.setInt(2, tipo.getCapacidad());
                stmt.setInt(3, tipo.getIdTipo());
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

    public void deleteTipo(int idTipo) throws SQLException {
        String query = "DELETE FROM Tipo WHERE id_tipo = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idTipo);
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

    public Tipo getTipoById(int idTipo) throws SQLException {
        String query = "SELECT * FROM Tipo WHERE id_tipo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tipo(rs.getInt("id_tipo"), rs.getString("nombre"), rs.getInt("capacidad"));
                }
            }
        }
        return null;
    }
}
