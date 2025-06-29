import java.util.ArrayList;
import java.util.Scanner;


class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + 
               ", Status: " + (isAvailable ? "Available" : "Issued");
    }
}


public class LibraryManagement {
    private ArrayList<Book> books;
    private Scanner scanner;

    public LibraryManagement() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    
    public void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    
    public void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    
    public void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book is already available!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }


    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library!");
            return;
        }

        System.out.println("\nLibrary Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    System.out.println("Thank you for using the Library Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagement library = new LibraryManagement();
        library.showMenu();
    }
}