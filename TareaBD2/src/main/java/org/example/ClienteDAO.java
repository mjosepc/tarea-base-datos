package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String SELECT_CLIENTE_BY_DNI = "SELECT * FROM Clientes WHERE dni = ?";
    private static final String CREATE_CLIENTE_QUERY = "INSERT INTO Clientes (dni, nombre, apellido, direccion, telefono, correo_electronico) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CLIENTE_QUERY = "UPDATE Clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, correo_electronico = ? WHERE dni = ?";
    private static final String DELETE_CLIENTE_QUERY = "DELETE FROM Clientes WHERE dni = ?";

    public void createCliente(Cliente cliente) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(CREATE_CLIENTE_QUERY)) {
                stmt.setInt(1, cliente.getDni());
                stmt.setString(2, cliente.getNombre());
                stmt.setString(3, cliente.getApellido());
                stmt.setString(4, cliente.getDireccion());
                stmt.setString(5, cliente.getTelefono());
                stmt.setString(6, cliente.getCorreoElectronico());
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

    public Cliente getCliente(int dni) throws SQLException {
        Cliente cliente = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CLIENTE_BY_DNI)) {
            stmt.setInt(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo_electronico")
                    );
                }
            }
        }
        return cliente;
    }

    public List<Cliente> getAllClientes() throws SQLException {
        String query = "SELECT * FROM Clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_CLIENTE_QUERY)) {
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getApellido());
                stmt.setString(3, cliente.getDireccion());
                stmt.setString(4, cliente.getTelefono());
                stmt.setString(5, cliente.getCorreoElectronico());
                stmt.setInt(6, cliente.getDni());
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

    public void deleteCliente(int dni) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción
            try (PreparedStatement stmt = conn.prepareStatement(DELETE_CLIENTE_QUERY)) {
                stmt.setInt(1, dni);
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

    public Cliente getClienteByDni(int dni) throws SQLException {
        Cliente cliente = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CLIENTE_BY_DNI)) {
            stmt.setInt(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo_electronico")
                    );
                }
            }
        }
        return cliente;
    }
}
