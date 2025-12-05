package addressbook;

import javax.swing.*;
import java.awt.*;

public class AddressBookGUI extends JFrame 
{
    private ContactManager manager;
    private DefaultListModel<Contact> listModel;
    private JList<Contact> contactList;

    private JTextField nameField, phoneField, emailField;

    public AddressBookGUI() 
    {
        // Initialize Contact Manager and List Model
        manager = new ContactManager();
        listModel = new DefaultListModel<>();

        for (Contact c : manager.getContacts()) 
        {
            listModel.addElement(c);
        }

        // Window Setup
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Contact List Area
        contactList = new JList<>(listModel);
        add(new JScrollPane(contactList), BorderLayout.CENTER);

        // Auto-fill fields when contact is selected
        contactList.addListSelectionListener(e -> 
        {
            if (!e.getValueIsAdjusting()) 
            {
                Contact selected = contactList.getSelectedValue();

                if (selected != null) 
                {
                    nameField.setText(selected.getName());
                    phoneField.setText(selected.getPhone());
                    emailField.setText(selected.getEmail());
                }
            }
        });

        // =============== BOTTOM SECTION (FORM and BUTTONS) ===============

        JPanel bottom = new JPanel(new BorderLayout());

        // =============== FORM ROW ===============

        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));

        // Name
        form.add(new JLabel("Name:"));
        nameField = new JTextField();
        form.add(nameField);

        // Phone
        form.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        form.add(phoneField);

        // Email
        form.add(new JLabel("Email:"));
        emailField = new JTextField();
        form.add(emailField);

        // Fillers to keep grid aligned
        form.add(new JLabel(""));
        form.add(new JLabel(""));

        bottom.add(form, BorderLayout.CENTER);

        // =============== BUTTON ROW ===============

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        JPanel buttons = new JPanel(new GridLayout(1, 3, 5, 5));
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);

        bottom.add(buttons, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        // =============== BUTTON LOGIC ===============

        addBtn.addActionListener(e -> addContact());
        updateBtn.addActionListener(e -> updateContact());
        deleteBtn.addActionListener(e -> deleteContact());
    }

    private void addContact() 
    {
        Contact c = new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
        manager.addContact(c); listModel.addElement(c); clear();
    }

    private void updateContact() 
    {
        int idx = contactList.getSelectedIndex();
        if (idx == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a contact first.");
            return;
        }

        Contact c = new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
        manager.updateContact(idx, c); listModel.set(idx, c); clear();
    }

    private void deleteContact() 
    {
        int idx = contactList.getSelectedIndex();
        if (idx == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a contact first.");
            return;
        }
        
        manager.deleteContact(idx); listModel.remove(idx); clear();
    }

    private void clear() 
    {
        nameField.setText(""); phoneField.setText(""); emailField.setText("");
    }
}
