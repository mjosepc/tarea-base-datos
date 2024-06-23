package org.example;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.time.format.DateTimeParseException;
import java.sql.SQLException;


public class Main {
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static HabitacionDAO habitacionDAO = new HabitacionDAO();
    private static ReservaDAO reservaDAO = new ReservaDAO();
    private static ServicioDAO servicioDAO = new ServicioDAO();
    private static PagoDAO pagoDAO = new PagoDAO();
    private static TipoDAO tipoDAO = new TipoDAO();
    private static ContratoServicioDAO contratoServicioDAO = new ContratoServicioDAO();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenuPrincipal();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    gestionarClientes();
                    break;
                case 2:
                    gestionarHabitaciones();
                    break;
                case 3:
                    gestionarReservas();
                    break;
                case 4:
                    gestionarPagos();
                    break;
                case 5:
                    gestionarServicios();
                    break;
                case 6:
                    gestionarContratosServicios();
                    break;
                case 7:
                    System.out.println("Gracias por usar el sistema de gestión. ¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- Sistema de Gestión de Hotel ---");
        System.out.println("1. Gestionar Clientes");
        System.out.println("2. Gestionar Habitaciones");
        System.out.println("3. Gestionar Reservas");
        System.out.println("4. Gestionar Pagos");
        System.out.println("5. Gestionar Servicios");
        System.out.println("6. Gestionar Contratos de Servicios");
        System.out.println("7. Salir");
        System.out.println("Seleccione una opción:");
    }

    private static void gestionarClientes() {
        System.out.println("\n--- Gestionar Clientes ---");
        System.out.println("1. Ver Todos los Clientes");
        System.out.println("2. Agregar Nuevo Cliente");
        System.out.println("3. Actualizar Información de Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Volver al Menú Principal");
        System.out.println("Seleccione una opción:");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                verTodosLosClientes();
                break;
            case 2:
                agregarNuevoCliente();
                break;
            case 3:
                actualizarCliente();
                break;
            case 4:
                eliminarCliente();
                break;
            case 5:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void verTodosLosClientes() {
        try {
            List<Cliente> clientes = clienteDAO.getAllClientes();
            for (Cliente cliente : clientes) {
                System.out.println("DNI: " + cliente.getDni() +
                        ", Nombre: " + cliente.getNombre() +
                        ", Apellido: " + cliente.getApellido() +
                        ", Dirección: " + cliente.getDireccion() +
                        ", Teléfono: " + cliente.getTelefono() +
                        ", Correo Electrónico: " + cliente.getCorreoElectronico());
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
    }

    private static void agregarNuevoCliente() {
        System.out.println("Ingrese DNI del Cliente:");
        int dni = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese Nombre del Cliente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese Apellido del Cliente:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese Dirección del Cliente:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese Teléfono del Cliente:");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese Correo Electrónico del Cliente:");
        String correoElectronico = scanner.nextLine();

        Cliente cliente = new Cliente(dni, nombre, apellido, direccion, telefono, correoElectronico);

        try {
            clienteDAO.createCliente(cliente);
            System.out.println("Cliente agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    private static void actualizarCliente() {
        System.out.println("Ingrese DNI del Cliente a actualizar:");
        int dni = Integer.parseInt(scanner.nextLine());

        try {
            Cliente cliente = clienteDAO.getClienteByDni(dni);
            if (cliente == null) {
                System.out.println("Cliente no encontrado.");
                return;
            }

            System.out.println("Ingrese Nuevo Nombre del Cliente (dejar vacío para mantener):");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                cliente.setNombre(nombre);
            }

            System.out.println("Ingrese Nuevo Apellido del Cliente (dejar vacío para mantener):");
            String apellido = scanner.nextLine();
            if (!apellido.isEmpty()) {
                cliente.setApellido(apellido);
            }

            System.out.println("Ingrese Nueva Dirección del Cliente (dejar vacío para mantener):");
            String direccion = scanner.nextLine();
            if (!direccion.isEmpty()) {
                cliente.setDireccion(direccion);
            }

            System.out.println("Ingrese Nuevo Teléfono del Cliente (dejar vacío para mantener):");
            String telefono = scanner.nextLine();
            if (!telefono.isEmpty()) {
                cliente.setTelefono(telefono);
            }

            System.out.println("Ingrese Nuevo Correo Electrónico del Cliente (dejar vacío para mantener):");
            String correoElectronico = scanner.nextLine();
            if (!correoElectronico.isEmpty()) {
                cliente.setCorreoElectronico(correoElectronico);
            }

            clienteDAO.updateCliente(cliente);
            System.out.println("Cliente actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    private static void eliminarCliente() {
        System.out.println("Ingrese DNI del Cliente a eliminar:");
        int dni = Integer.parseInt(scanner.nextLine());

        try {
            clienteDAO.deleteCliente(dni);
            System.out.println("Cliente eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }


    private static void gestionarHabitaciones() {
        System.out.println("\n--- Gestionar Habitaciones ---");
        System.out.println("1. Ver Todas las Habitaciones");
        System.out.println("2. Ver Detalles de una Habitación");
        System.out.println("3. Reservar Habitación");
        System.out.println("4. Agregar Servicio a Habitación");
        System.out.println("5. Volver al Menú Principal");
        System.out.println("Seleccione una opción:");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                verTodasLasHabitaciones();
                break;
            case 2:
                verDetallesHabitacion();
                break;
            case 3:
                reservarHabitacion();
                break;
            case 4:
                agregarServicioAHabitacion();
                break;
            case 5:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void verTodasLasHabitaciones() {
        try {
            List<Habitacion> habitaciones = habitacionDAO.getAllHabitaciones();
            for (Habitacion habitacion : habitaciones) {
                Tipo tipoHabitacion = tipoDAO.getTipoById(habitacion.getIdTipo());

                if (tipoHabitacion != null) {
                    System.out.println("ID: " + habitacion.getIdHabitacion() +
                            ", Nombre: " + habitacion.getNombreHabitacion() +
                            ", Tipo: " + tipoHabitacion.getNombre() +
                            ", Precio por Noche: " + habitacion.getPrecioNoche());
                } else {
                    System.out.println("Tipo de habitación no encontrado para ID: " + habitacion.getIdTipo());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener habitaciones: " + e.getMessage());
        }
    }

    private static void verDetallesHabitacion() {
        System.out.println("Ingrese ID de la Habitación:");
        int idHabitacion = Integer.parseInt(scanner.nextLine());

        try {
            Habitacion habitacion = habitacionDAO.getHabitacion(idHabitacion);
            if (habitacion != null) {
                System.out.println("ID: " + habitacion.getIdHabitacion());
                System.out.println("Nombre: " + habitacion.getNombreHabitacion());

                Tipo tipoHabitacion = tipoDAO.getTipoById(habitacion.getIdTipo());

                if (tipoHabitacion != null) {
                    System.out.println("Tipo: " + tipoHabitacion.getNombre());
                    System.out.println("Capacidad: " + tipoHabitacion.getCapacidad());
                } else {
                    System.out.println("Tipo de habitación no encontrado.");
                }

                System.out.println("Precio por Noche: " + habitacion.getPrecioNoche());
            } else {
                System.out.println("Habitación no encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener detalles de la habitación: " + e.getMessage());
        }
    }

    private static void reservarHabitacion() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese DNI del Cliente:");
            int dniCliente = scanner.nextInt();

            System.out.println("Ingrese ID de la Habitación:");
            int idHabitacion = scanner.nextInt();

            scanner.nextLine(); // Limpiar el buffer después de nextInt

            System.out.println("Ingrese Fecha de Check-in (YYYY-MM-DD):");
            String fechaCheckinStr = scanner.nextLine();
            Date fechaCheckin = Date.valueOf(fechaCheckinStr); // Convertir String a java.sql.Date

            System.out.println("Ingrese Fecha de Check-out (YYYY-MM-DD):");
            String fechaCheckoutStr = scanner.nextLine();
            Date fechaCheckout = Date.valueOf(fechaCheckoutStr); // Convertir String a java.sql.Date

            // Validar si la habitación está disponible para las fechas seleccionadas
            if (habitacionDisponible(idHabitacion, fechaCheckin, fechaCheckout)) {
                reservaDAO.createReserva(new Reserva(0, dniCliente, idHabitacion, fechaCheckin, fechaCheckout));
                System.out.println("Reserva creada correctamente.");
            } else {
                System.out.println("Error: La habitación no está disponible para las fechas seleccionadas.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: El valor ingresado no es válido.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: No se pudo leer la entrada del usuario.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Debe ser YYYY-MM-DD.");
        } catch (SQLException e) {
            System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }

    private static boolean habitacionDisponible(int idHabitacion, Date fechaCheckin, Date fechaCheckout) throws SQLException {
        List<Reserva> reservas = reservaDAO.getReservasByHabitacion(idHabitacion);

        for (Reserva reserva : reservas) {
            Date inicioReserva = reserva.getFechaCheckin();
            Date finReserva = reserva.getFechaCheckout();

            // Verificar si las fechas se solapan
            if (fechaCheckin.before(finReserva) && fechaCheckout.after(inicioReserva)) {
                return false; // Hay solapamiento, la habitación no está disponible
            }
        }

        return true; // No hay solapamiento, la habitación está disponible
    }


    private static void agregarServicioAHabitacion() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese DNI del Cliente asociado a la reserva:");
            int dniCliente = Integer.parseInt(scanner.nextLine());

            Cliente cliente = clienteDAO.getClienteByDni(dniCliente);
            if (cliente == null) {
                System.out.println("No se encontró cliente con DNI " + dniCliente);
                return;
            }

            Reserva reserva = reservaDAO.getReservaByCliente(cliente.getDni());
            if (reserva == null) {
                System.out.println("No se encontró reserva para el cliente con DNI " + dniCliente);
                return;
            }

            System.out.println("Ingrese ID del Servicio:");
            int idServicio = Integer.parseInt(scanner.nextLine());
            Servicio servicio = servicioDAO.getServicioById(idServicio);
            if (servicio == null) {
                System.out.println("No se encontró servicio con ID " + idServicio);
                return;
            }

            ContratoServicio contratoServicio = new ContratoServicio(idServicio, servicio.getPrecioServicio(), new Date(System.currentTimeMillis()), reserva.getIdReserva());

            contratoServicioDAO.createContratoServicio(contratoServicio, reserva.getIdReserva());

            System.out.println("Contrato de servicio creado y asociado a la reserva exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar servicio a la habitación: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para obtener más detalles del error
        }
    }






    private static void gestionarReservas() {
        System.out.println("\n--- Gestionar Reservas ---");
        System.out.println("1. Ver Todas las Reservas");
        System.out.println("2. Eliminar Reserva");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("4. Buscar habitaciones disponibles");
        System.out.println("Seleccione una opción:");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                verTodasLasReservas();
                break;
            case 2:
                eliminarReserva();
                break;
            case 3:
                return;
            case 4:
                buscarHabitacionesDisponibles();
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void verTodasLasReservas() {
        try {
            List<Reserva> reservas = reservaDAO.getAllReservas();
            for (Reserva reserva : reservas) {
                Habitacion habitacion = null;
                try {
                    habitacion = habitacionDAO.getHabitacionById(reserva.getIdHabitacion());
                } catch (SQLException ex) {
                    System.out.println("Error al obtener la habitación para la reserva " + reserva.getIdReserva() + ": " + ex.getMessage());
                }

                if (habitacion != null) {
                    System.out.println("ID Reserva: " + reserva.getIdReserva() +
                            ", DNI Cliente: " + reserva.getDniCliente() +
                            ", Habitación: " + habitacion.getNombreHabitacion() +
                            ", Check-in: " + reserva.getFechaCheckin() +
                            ", Check-out: " + reserva.getFechaCheckout());
                } else {
                    System.out.println("ID Reserva: " + reserva.getIdReserva() +
                            ", DNI Cliente: " + reserva.getDniCliente() +
                            ", Habitación: (Habitación no encontrada)" +
                            ", Check-in: " + reserva.getFechaCheckin() +
                            ", Check-out: " + reserva.getFechaCheckout());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reservas: " + e.getMessage());
        }
    }

    private static void buscarHabitacionesDisponibles() {
        System.out.println("\n--- Buscar Habitaciones Disponibles ---");
        System.out.println("Ingrese Fecha de Check-in (YYYY-MM-DD):");
        String fechaCheckinStr = scanner.nextLine();
        Date fechaCheckin = Date.valueOf(fechaCheckinStr);

        System.out.println("Ingrese Fecha de Check-out (YYYY-MM-DD):");
        String fechaCheckoutStr = scanner.nextLine();
        Date fechaCheckout = Date.valueOf(fechaCheckoutStr);

        try {
            List<Habitacion> habitacionesDisponibles = habitacionDAO.getHabitacionesDisponibles(fechaCheckin, fechaCheckout);
            if (habitacionesDisponibles.isEmpty()) {
                System.out.println("No hay habitaciones disponibles para las fechas seleccionadas.");
            } else {
                System.out.println("Habitaciones disponibles para las fechas del " + fechaCheckinStr + " al " + fechaCheckoutStr + ":");
                for (Habitacion habitacion : habitacionesDisponibles) {
                    Tipo tipoHabitacion = tipoDAO.getTipoById(habitacion.getIdTipo());
                    System.out.println("ID: " + habitacion.getIdHabitacion() +
                            ", Nombre: " + habitacion.getNombreHabitacion() +
                            ", Tipo: " + tipoHabitacion.getNombre() +
                            ", Precio por Noche: " + habitacion.getPrecioNoche());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar habitaciones disponibles: " + e.getMessage());
        }
    }


    private static void eliminarReserva() {
        System.out.println("Ingrese ID de la Reserva a eliminar:");
        int idReserva = Integer.parseInt(scanner.nextLine());

        try {
            reservaDAO.deleteReserva(idReserva);
            System.out.println("Reserva eliminada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
        }
    }

    private static void gestionarPagos() {
        System.out.println("\n--- Gestionar Pagos ---");
        System.out.println("1. Realizar Pago de Reserva");
        System.out.println("2. Ver Detalles de Pago");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("Seleccione una opción:");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                realizarPagoReserva();
                break;
            case 2:
                verDetallesPago();
                break;
            case 3:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void realizarPagoReserva() {
        System.out.println("Ingrese ID de la Reserva:");
        int idReserva = Integer.parseInt(scanner.nextLine());

        try {
            // Obtener la reserva para calcular el monto del pago
            Reserva reserva = reservaDAO.getReservaById(idReserva);
            if (reserva == null) {
                System.out.println("No se encontró reserva con ID " + idReserva);
                return;
            }

            // Obtener el precio por noche de la habitación asociada a la reserva
            int precioNoche = habitacionDAO.getHabitacionById(reserva.getIdHabitacion()).getPrecioNoche();

            // Calcular el monto total del pago basado en la duración de la reserva y el precio por noche
            long diferenciaMillis = reserva.getFechaCheckout().getTime() - reserva.getFechaCheckin().getTime();
            int diasReserva = (int) (diferenciaMillis / (1000 * 60 * 60 * 24)); // Convertir a días

            int montoTotal = precioNoche * diasReserva;

            // Mostrar las opciones de tipo de pago al usuario
            System.out.println("Seleccione el tipo de pago:");
            System.out.println("1. Débito");
            System.out.println("2. Crédito");
            System.out.println("3. Efectivo");

            // Leer la opción del usuario y validarla
            int opcion;
            do {
                System.out.print("Opción: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    System.out.print("Opción: ");
                    scanner.next(); // Limpiar el buffer
                }
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer después de leer el entero
            } while (opcion < 1 || opcion > 3);

            String tipoPago;
            switch (opcion) {
                case 1:
                    tipoPago = "Débito";
                    break;
                case 2:
                    tipoPago = "Crédito";
                    break;
                case 3:
                    tipoPago = "Efectivo";
                    break;
                default:
                    tipoPago = "Desconocido"; // Esto no debería ocurrir si la validación es correcta
                    break;
            }

            // Fecha actual para el pago
            java.sql.Date fechaPago = new java.sql.Date(System.currentTimeMillis());

            // Crear el objeto Pago con los datos calculados y el tipo de pago seleccionado
            Pago pago = new Pago(0, idReserva, tipoPago, montoTotal, fechaPago); // 0 para idPago, ya que se generará automáticamente

            // Llamar al método de DAO para crear el pago
            pagoDAO.createPago(pago);
            System.out.println("Pago realizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al realizar pago: " + e.getMessage());
        }
    }





    private static void verDetallesPago() {
        System.out.println("Ingrese ID de la Reserva:");
        int idReserva = Integer.parseInt(scanner.nextLine());

        try {
            Pago pago = pagoDAO.getPagoByReserva(idReserva);
            if (pago != null) {
                Reserva reserva = reservaDAO.getReservaById(idReserva);
                if (reserva != null) {
                    int precioNoche = obtenerPrecioNochePorReserva(reserva);
                    int nochesTotales = calcularDiasReserva(reserva.getFechaCheckin(), reserva.getFechaCheckout());
                    int montoTotalNoches = precioNoche * nochesTotales;
                    List<ContratoServicio> contratosServicios = contratoServicioDAO.getContratosServicioByReserva(idReserva);
                    int montoTotalServicios = calcularMontoTotalServicios(contratosServicios);

                    System.out.println("=== Detalles Pago ===");
                    System.out.println("Cobro por noche de la habitación: $" + precioNoche);
                    System.out.println("Noches totales: " + nochesTotales);
                    System.out.println("Cobro total de noches: $" + montoTotalNoches);

                    System.out.println("Servicios:");
                    for (ContratoServicio contrato : contratosServicios) {
                        Servicio servicio = servicioDAO.getServicioById(contrato.getIdServicio());
                        System.out.println(" - " + servicio.getNombreServicio() + ": $" + servicio.getPrecioServicio());
                    }

                    System.out.println("Monto total por servicios: $" + montoTotalServicios);
                    System.out.println("Monto total a pagar: $" + (montoTotalNoches + montoTotalServicios));
                    System.out.println("Fecha de Pago: " + pago.getFechaPago());
                    System.out.println("Tipo de Pago: " + pago.getTipoPago()); // Mostrar el tipo de pago
                } else {
                    System.out.println("No se encontró reserva para el ID especificado.");
                }
            } else {
                System.out.println("No se encontró pago para la reserva especificada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener detalles del pago: " + e.getMessage());
        }
    }


    private static int obtenerPrecioNochePorReserva(Reserva reserva) throws SQLException {
        Habitacion habitacion = habitacionDAO.getHabitacionById(reserva.getIdHabitacion());
        return habitacion.getPrecioNoche();
    }

    private static int calcularDiasReserva(Date fechaCheckin, Date fechaCheckout) {
        long diferenciaMillis = fechaCheckout.getTime() - fechaCheckin.getTime();
        return (int) (diferenciaMillis / (1000 * 60 * 60 * 24)); // Convertir a días
    }

    private static int calcularMontoTotalServicios(List<ContratoServicio> contratosServicios) throws SQLException {
        int montoTotal = 0;
        for (ContratoServicio contrato : contratosServicios) {
            Servicio servicio = servicioDAO.getServicioById(contrato.getIdServicio());
            montoTotal += servicio.getPrecioServicio();
        }
        return montoTotal;
    }



    private static void gestionarServicios() {
        System.out.println("\n--- Gestionar Servicios ---");
        System.out.println("1. Ver Todos los Servicios");
        System.out.println("2. Agregar Nuevo Servicio");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("Seleccione una opción:");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                verTodosLosServicios();
                break;
            case 2:
                agregarNuevoServicio();
                break;
            case 3:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void verTodosLosServicios() {
        try {
            List<Servicio> servicios = servicioDAO.getAllServicios();
            for (Servicio servicio : servicios) {
                System.out.println("ID Servicio: " + servicio.getIdServicio() +
                        ", Nombre: " + servicio.getNombreServicio() +
                        ", Descripción: " + servicio.getTipoServicio() +
                        ", Precio: " + servicio.getPrecioServicio());
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener servicios: " + e.getMessage());
        }
    }


    private static void agregarNuevoServicio() {
        System.out.println("Ingrese nombre de Servicio:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese tipo de Servicio:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese Precio del Servicio:");
        int precio = Integer.parseInt(scanner.nextLine());

        Servicio servicio = new Servicio(0, nombre, descripcion, precio);

        try {
            servicioDAO.createServicio(servicio);
            System.out.println("Servicio creado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear servicio: " + e.getMessage());
        }
    }

    private static void gestionarContratosServicios() {
        System.out.println("\n--- Gestionar Contratos de Servicios ---");
        System.out.println("1. Ver Contratos de Servicios por Reserva");
        System.out.println("2. Agregar Contrato de Servicio a Reserva");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("Seleccione una opción:");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                verContratosServiciosPorReserva();
                break;
            case 2:
                agregarContratoServicioAReserva();
                break;
            case 3:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void verContratosServiciosPorReserva() {
        System.out.println("Ingrese ID de la Reserva:");
        int idReserva = Integer.parseInt(scanner.nextLine());

        try {
            List<ContratoServicio> contratos = contratoServicioDAO.getContratosServicioByReserva(idReserva);
            if (contratos.isEmpty()) {
                System.out.println("No hay contratos de servicios asociados a esta reserva.");
            } else {
                System.out.println("Contratos de Servicio para la Reserva ID " + idReserva + ":");
                for (ContratoServicio contrato : contratos) {
                    System.out.println("ID Contrato: " + contrato.getIdContrato() +
                            ", ID Servicio: " + contrato.getIdServicio() +
                            ", Monto Total: " + contrato.getMontoTotal() +
                            ", Fecha de Contrato: " + contrato.getFechaContrato());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener contratos de servicios: " + e.getMessage());
        }
    }

    private static void agregarContratoServicioAReserva() {
        System.out.println("Ingrese ID de la Reserva:");
        int idReserva = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese ID del Servicio:");
        int idServicio = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese Monto Total:");
        int montoTotal = Integer.parseInt(scanner.nextLine());

        // Crear objeto ContratoServicio con los cuatro parámetros requeridos
        Date fechaContrato = new Date(System.currentTimeMillis()); // Fecha actual
        ContratoServicio contratoServicio = new ContratoServicio(idServicio, montoTotal, fechaContrato, idReserva);

        try {
            // Guardar el contrato de servicio en la base de datos y asociarlo a la reserva
            contratoServicioDAO.createContratoServicio(contratoServicio, idReserva);
            System.out.println("Contrato de servicio creado y asociado a la reserva exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear contrato de servicio y asociarlo a la reserva: " + e.getMessage());
        }
    }

}


