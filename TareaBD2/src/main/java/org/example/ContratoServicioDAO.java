package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoServicioDAO {

    public void createContratoServicio(ContratoServicio contratoServicio, int idReserva) throws SQLException {
        String query = "INSERT INTO Contratos_Servicios (id_servicio, monto_total, fecha_contrato, id_reserva) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, contratoServicio.getIdServicio());
                stmt.setInt(2, contratoServicio.getMontoTotal());
                stmt.setDate(3, contratoServicio.getFechaContrato());
                stmt.setInt(4, idReserva);
                stmt.executeUpdate();

                // Obtener el ID generado del contrato
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idContrato = generatedKeys.getInt(1);
                        contratoServicio.setIdContrato(idContrato);
                    } else {
                        throw new SQLException("Fallo al obtener el ID generado del contrato.");
                    }
                }

                conn.commit(); // Confirmar transacción
            } catch (SQLException ex) {
                conn.rollback(); // Deshacer transacción en caso de error
                throw ex;
            } finally {
                conn.setAutoCommit(true); // Restaurar el modo de auto-commit
            }
        }
    }

    public List<ContratoServicio> getContratosServicioByReserva(int idReserva) throws SQLException {
        String query = "SELECT * FROM Contratos_Servicios WHERE id_reserva = ?";
        List<ContratoServicio> contratosServicios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ContratoServicio contratoServicio = new ContratoServicio(
                            rs.getInt("id_contrato"),
                            rs.getInt("id_servicio"),
                            rs.getInt("monto_total"),
                            rs.getDate("fecha_contrato"),
                            rs.getInt("id_reserva")
                    );
                    contratosServicios.add(contratoServicio);
                }
            }
        }
        return contratosServicios;
    }
}
