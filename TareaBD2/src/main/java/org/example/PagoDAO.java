package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    private static final String SELECT_PAGO_BY_RESERVA = "SELECT * FROM Pagos WHERE id_reserva = ?";
    private static final String SELECT_FECHA_PAGO_BY_RESERVA = "SELECT fechaPago FROM Pagos WHERE id_reserva = ?";
    private static final String INSERT_PAGO = "INSERT INTO Pagos (id_reserva, tipo_pago, fechaPago, monto) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PAGO_BY_ID = "SELECT * FROM Pagos WHERE id_pago = ?";
    private static final String SELECT_ALL_PAGOS = "SELECT * FROM Pagos";
    private static final String UPDATE_PAGO = "UPDATE Pagos SET id_reserva = ?, tipo_pago = ?, fechaPago = ?, monto = ? WHERE id_pago = ?";
    private static final String DELETE_PAGO = "DELETE FROM Pagos WHERE id_pago = ?";

    public Pago getPagoByReserva(int idReserva) throws SQLException {
        Pago pago = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_PAGO_BY_RESERVA)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pago = new Pago(
                            rs.getInt("id_pago"),
                            rs.getInt("id_reserva"),
                            rs.getString("tipo_pago"),
                            rs.getInt("monto"),
                            rs.getDate("fechaPago")  // Usa el nombre correcto de la columna
                    );
                }
            }
        }
        return pago;
    }

    public Date getFechaPagoByReserva(int idReserva) throws SQLException {
        Date fechaPago = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_FECHA_PAGO_BY_RESERVA)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fechaPago = rs.getDate("fechaPago");  // Usa el nombre correcto de la columna
                }
            }
        }
        return fechaPago;
    }

    public Pago getPago(int idPago) throws SQLException {
        Pago pago = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_PAGO_BY_ID)) {
            stmt.setInt(1, idPago);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pago = new Pago(
                            rs.getInt("id_pago"),
                            rs.getInt("id_reserva"),
                            rs.getString("tipo_pago"),
                            rs.getInt("monto"),
                            rs.getDate("fechaPago")  // Usa el nombre correcto de la columna
                    );
                }
            }
        }
        return pago;
    }

    public List<Pago> getAllPagos() throws SQLException {
        List<Pago> pagos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_PAGOS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("id_pago"),
                        rs.getInt("id_reserva"),
                        rs.getString("tipo_pago"),
                        rs.getInt("monto"),
                        rs.getDate("fechaPago")  // Usa el nombre correcto de la columna
                );
                pagos.add(pago);
            }
        }
        return pagos;
    }

    public void updatePago(Pago pago) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_PAGO)) {
                stmt.setInt(1, pago.getIdReserva());
                stmt.setString(2, pago.getTipoPago());
                stmt.setDate(3, pago.getFechaPago());  // Usa el nombre correcto de la columna
                stmt.setInt(4, pago.getMonto());
                stmt.setInt(5, pago.getIdPago());
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

    public void deletePago(int idPago) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(DELETE_PAGO)) {
                stmt.setInt(1, idPago);
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

    public void createPago(Pago pago) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(INSERT_PAGO)) {
                stmt.setInt(1, pago.getIdReserva());
                stmt.setString(2, pago.getTipoPago());
                stmt.setDate(3, pago.getFechaPago());  // Usa el nombre correcto de la columna
                stmt.setInt(4, pago.getMonto());
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
