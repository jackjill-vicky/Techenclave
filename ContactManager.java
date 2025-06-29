import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class Contact {
    private String name;
    private String phone;
    private String email;

    
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}


public class ContactManager {
    private ArrayList<Contact> contacts;
    private Scanner scanner;
    private static final String FILE_NAME = "contacts.txt";

    public ContactManager() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadContacts();
    }

    
    private boolean validatePhone(String phone) {
        return phone.matches("\\d{10}");
    }

    
    private String getValidPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = scanner.nextLine();
            if (validatePhone(phone)) {
                return phone;
            } else {
                System.out.println("Invalid phone number! Must be exactly 10 digits with no letters or special characters.");
            }
        }
    }

    
    public void addContact() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        String phone = getValidPhone("Enter Phone (10 digits): ");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        contacts.add(new Contact(name, phone, email));
        saveContacts();
        System.out.println("Contact added successfully!");
    }

    
    public void editContact() {
        System.out.print("Enter Name or Phone of contact to edit: ");
        String searchTerm = scanner.nextLine();
        Contact contact = searchContact(searchTerm);

        if (contact != null) {
            System.out.print("Enter new Name (" + contact.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) contact.setName(name);

            String phonePrompt = "Enter new Phone (" + contact.getPhone() + "): ";
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) {
                if (validatePhone(phone)) {
                    contact.setPhone(phone);
                } else {
                    System.out.println("Invalid phone number! Keeping original phone number.");
                }
            }

            System.out.print("Enter new Email (" + contact.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) contact.setEmail(email);

            saveContacts();
            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    
    public void deleteContact() {
        System.out.print("Enter Name or Phone of contact to delete: ");
        String searchTerm = scanner.nextLine();
        Contact contact = searchContact(searchTerm);

        if (contact != null) {
            contacts.remove(contact);
            saveContacts();
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    
    public Contact searchContact(String searchTerm) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchTerm) || 
                contact.getPhone().equalsIgnoreCase(searchTerm)) {
                System.out.println(contact);
                return contact;
            }
        }
        return null;
    }

    
    public void viewAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available!");
            return;
        }

        System.out.println("\nAll Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    
    public void saveContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhone() + "," + contact.getEmail());
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    
    public void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    
                    if (validatePhone(parts[1])) {
                        contacts.add(new Contact(parts[0], parts[1], parts[2]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing contacts file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

   
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Contact Manager System ===");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. View All Contacts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addContact();
                        break;
                    case 2:
                        editContact();
                        break;
                    case 3:
                        deleteContact();
                        break;
                    case 4:
                        System.out.print("Enter Name or Phone to search: ");
                        String searchTerm = scanner.nextLine();
                        if (searchContact(searchTerm) == null) {
                            System.out.println("Contact not found!");
                        }
                        break;
                    case 5:
                        viewAllContacts();
                        break;
                    case 6:
                        System.out.println("Thank you for using the Contact Manager System!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }

    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        manager.showMenu();
    }
}