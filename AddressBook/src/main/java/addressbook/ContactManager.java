package addressbook;

import java.io.*;
import java.util.*;

public class ContactManager 
{
    private List<Contact> contacts = new ArrayList<>();
    private final File file;

    public ContactManager() 
    {
        File desktop = new File(System.getProperty("user.home"), "Desktop");

        file = new File(desktop, "contacts.txt");   // contacts.txt directly on the Desktop

        loadFromFile();
    }

    public List<Contact> getContacts() 
    { 
        return contacts;
    }

    public void addContact(Contact c) 
    {
        contacts.add(c); saveToFile(); 
    }

    public void updateContact(int index, Contact updated) 
    {
        contacts.set(index, updated); saveToFile();
    }

    public void deleteContact(int index) 
    {
        contacts.remove(index); saveToFile();
    }

    private void loadFromFile() 
    {
        try 
        {
            if (!file.exists()) 
            { 
                file.createNewFile(); return; 
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) 
            {
                String[] p = line.split("\\|");
                if (p.length == 3)
                {
                    contacts.add(new Contact(p[0].trim(), p[1].trim(), p[2].trim()));
                }
            }

            br.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("Error loading: " + e.getMessage()); 
        }
    }

    private void saveToFile() 
    {
        try (PrintWriter w = new PrintWriter(new FileWriter(file, false))) 
        {
            for (Contact c : contacts)
            {
                w.println(c.getName() + " | " + c.getPhone() + " | " + c.getEmail());
            }
        } 
        catch (IOException e) 
        { 
            System.out.println("Error saving: " + e.getMessage()); 
        }
    }
}
