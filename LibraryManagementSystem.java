import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("Available books in the library:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class User {
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        boolean exit = false;

        // Adding some initial books to the library
        library.addBook(new Book("Data Structure", " helwet "));
        library.addBook(new Book("C++", " james "));
        library.addBook(new Book("Digital Electronics", " K P Singh "));

        System.out.println("Welcome to the Library Management System!");

        while (!exit) {
            System.out.println("\nOptions:");
            System.out.println("1. Display available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Library Management System!");
    }

    private static void borrowBook() {
        System.out.print("Enter the title of the book you want to borrow: ");
        String title = scanner.nextLine();
        Book book = library.findBook(title);
        if (book != null && book.isAvailable()) {
            System.out.println("Book found. Borrowing...");
            User user = createUser();
            user.borrowBook(book);
            System.out.println("Book successfully borrowed by " + user.getName());
        } else {
            System.out.println("Book not found or not available for borrowing.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter the title of the book you want to return: ");
        String title = scanner.nextLine();
        Book book = library.findBook(title);
        if (book != null && !book.isAvailable()) {
            System.out.println("Book found. Returning...");
            User user = createUser();
            user.returnBook(book);
            System.out.println("Book successfully returned by " + user.getName());
        } else {
            System.out.println("Book not found or already available in the library.");
        }
    }

    private static User createUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        return new User(name);
    }
}
