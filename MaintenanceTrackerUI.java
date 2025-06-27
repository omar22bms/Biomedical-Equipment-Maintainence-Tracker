import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MaintenanceTrackerUI {
    private final EquipmentManager manager = new EquipmentManager();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"Name", "ID", "Last name", "Last Serviced", "Next Service", "Status"}, 0);
    private JTable table;
    private JTextField lastnameField;


    public void createUI() {
        JFrame frame = new JFrame("Biomedical Equipment Maintenance Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Top Panel - Form Inputs
        JPanel formPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField lastServiceField = new JTextField();
        JTextField nextServiceField = new JTextField();
        JTextField statusField = new JTextField();
        lastnameField=new JTextField(15);

        formPanel.setBorder(BorderFactory.createTitledBorder("Add New Equipment"));
        formPanel.add(new JLabel("Name"));
        formPanel.add(new JLabel("Last Name"));
        formPanel.add(new JLabel("ID"));
        formPanel.add(new JLabel("Last Serviced"));
        formPanel.add(new JLabel("Next Service"));
        formPanel.add(new JLabel("Status"));

        formPanel.add(nameField);
        formPanel.add(lastnameField);
        formPanel.add(idField);
        formPanel.add(lastServiceField);
        formPanel.add(nextServiceField);
        formPanel.add(statusField);

        JButton addButton = new JButton("Add Equipment");

        // Center Panel - Table with Scroll
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                String status = (String) getValueAt(row, 4);
                if ("Critical".equalsIgnoreCase(status)) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE);
                } else if ("Pending".equalsIgnoreCase(status)) {
                    c.setBackground(Color.ORANGE);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.GREEN);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);

        // Bottom Panel - Search and Delete
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete Selected");

        bottomPanel.add(new JLabel("Search by Name:"));
        bottomPanel.add(searchField);
        bottomPanel.add(searchButton);
        bottomPanel.add(deleteButton);

        // Add functionality
        addButton.addActionListener(e -> {
            Equipment eq = new Equipment(
                    nameField.getText(),
                    lastServiceField.getName(), idField.getText(),
                    lastServiceField.getText(), nextServiceField.getText(),
                    statusField.getText()
            );
            manager.addEquipment(eq);
            tableModel.addRow(eq.toArray());

            // Clear fields
            nameField.setText("");
            idField.setText("");
            lastServiceField.setText("");
            nextServiceField.setText("");
            statusField.setText("");
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                manager.removeEquipment(selectedRow);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
            }
        });

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            tableModel.setRowCount(0); // Clear table

            for (Equipment eq : manager.getEquipmentList()) {
                if (eq.getName().toLowerCase().contains(keyword)) {
                    tableModel.addRow(eq.toArray());
                }
            }
        });

        // Layout panels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
