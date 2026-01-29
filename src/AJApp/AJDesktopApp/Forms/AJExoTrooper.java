package AJApp.AJDesktopApp.Forms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import AJDataAccess.AJDAOs.AJExobotDAO;
import AJDataAccess.AJDTOs.AJExobotDTO;
import AJInfrastructura.AJAppException;

import java.util.List;

public class AJExoTrooper extends JFrame {

    private JTable tblExobot;
    private DefaultTableModel model;
    private JComboBox<String> cboExtremidad;

    private JButton btnEntrenar;
    private JButton btnAccion;

    public AJExoTrooper() {
        setTitle("ExoTrooper");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
        try {
            cargarGrilla();
        } catch (Exception e) {
            System.err.println("ERROR al cargar grilla: " + e.getMessage());
            e.printStackTrace();
        }

        setVisible(true);
    }

    // ---------------- UI ----------------

    private void initUI() {

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {
                "IdExobot", "Nombre", "Serie", "TipoExobot",
                "Entreno", "NoAccion",
                "ArmaDerecha", "ArmaIzquierda"
        });

        tblExobot = new JTable(model);
        JScrollPane scroll = new JScrollPane(tblExobot);

        // Panel de Alumno (solo estética)
        JPanel pnlAlumno = new JPanel();
        pnlAlumno.setLayout(new BoxLayout(pnlAlumno, BoxLayout.Y_AXIS));
        pnlAlumno.setBorder(BorderFactory.createTitledBorder("Alumno(s)"));
        pnlAlumno.setBackground(new Color(240, 240, 240));

        JLabel lblAlumnoNombre = new JLabel("Josué Astudillo");
        lblAlumnoNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblAlumnoNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlAlumno.add(Box.createVerticalStrut(5));
        pnlAlumno.add(lblAlumnoNombre);
        pnlAlumno.add(Box.createVerticalStrut(5));

        // Panel de controles
        cboExtremidad = new JComboBox<>();
        cboExtremidad.addItem("DER");
        cboExtremidad.addItem("IZQ");

        btnEntrenar = new JButton("EntrenarArma");
        btnAccion = new JButton("AcciónArma");

        JPanel pnlControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlControles.add(new JLabel("Extremidad:"));
        pnlControles.add(cboExtremidad);
        pnlControles.add(btnEntrenar);
        pnlControles.add(btnAccion);

        // Panel superior contenedor (Alumno + Controles)
        JPanel pnlSuperior = new JPanel(new BorderLayout());
        pnlSuperior.add(pnlAlumno, BorderLayout.NORTH);
        pnlSuperior.add(pnlControles, BorderLayout.CENTER);

        add(pnlSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        btnEntrenar.addActionListener(e -> entrenarArma());
        btnAccion.addActionListener(e -> accionArma());
    }

    // ---------------- LÓGICA ----------------

    private void cargarGrilla() {

        model.setRowCount(0);

        try {
            AJExobotDAO dao = new AJExobotDAO();
            List<AJExobotDTO> exobots = dao.readAll();

            System.out.println("Total de exobots cargados: " + (exobots != null ? exobots.size() : 0));

            if (exobots != null) {
                for (AJExobotDTO exo : exobots) {
                    model.addRow(new Object[] {
                            exo.getIdExobot(),
                            exo.getNombre(),
                            exo.getSerie(),
                            exo.getTipoExobot(),
                            exo.getEntreno(),
                            exo.getNoAccion(),
                            exo.getArmaDerecha(),
                            exo.getArmaIzquierda()
                    });
                }
            }

        } catch (AJAppException e) {
            System.err.println("ERROR : No se pudo cargar la grilla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int getFilaSeleccionada() {
        int fila = tblExobot.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un Exobot");
        }
        return fila;
    }

    // ---------------- BOTONES ----------------

    private void entrenarArma() {

        int fila = getFilaSeleccionada();
        if (fila == -1)
            return;

        int idExobot = (int) model.getValueAt(fila, 0);
        String entrenoActual = model.getValueAt(fila, 4).toString();
        String nuevoEstado = "NO".equalsIgnoreCase(entrenoActual) ? "SI" : "NO";

        try {
            AJExobotDAO dao = new AJExobotDAO();

            if ("SI".equalsIgnoreCase(nuevoEstado)) {
                dao.setEntreno(idExobot);
                JOptionPane.showMessageDialog(this,
                        "✓ ARMA ENTRENADA",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                dao.setNoEntreno(idExobot);
                JOptionPane.showMessageDialog(this,
                        "✗ ARMA DESENTRENADA",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

            cargarGrilla();
        } catch (AJAppException e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }

    private void accionArma() {

        int fila = getFilaSeleccionada();
        if (fila == -1)
            return;

        int idExobot = (int) model.getValueAt(fila, 0);
        String extremidad = cboExtremidad.getSelectedItem().toString();
        String arma = ("DER".equalsIgnoreCase(extremidad))
                ? model.getValueAt(fila, 6).toString()
                : model.getValueAt(fila, 7).toString();

        try {
            AJExobotDAO dao = new AJExobotDAO();
            dao.incrementarAccion(idExobot);

            JOptionPane.showMessageDialog(this,
                    "ACCIÓN EJECUTADA\nArma: " + arma + "\nExtremidad: " + extremidad,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            cargarGrilla();
        } catch (AJAppException e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
