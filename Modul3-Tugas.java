import java.util.ArrayList;
import java.util.Scanner;

class LibrarySystem {
    private static ArrayList<Student> userStudents = new ArrayList<>();
    private static Admin admin = new Admin("adm1n", "321");
    private static ArrayList<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        bookList.add(new HistoryBook("1", "Ikigai", "Rani", "Self Development", 10, 7));
        bookList.add(new TextBook("2", "Belajar Java dengan Mudah", "Alex", "Pemrograman", 8, 10));
        bookList.add(new TextBook("3","ada apa dengan cinta","Silvarani", "Novel",9, 10 ));
        
        userStudents.add(new Student("202310370311129", "Rani", "Engineering", "Informatics"));
        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    studentLogin();
                    break;
                case 3:
                    System.out.println("Exiting program. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void studentLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your NIM: ");
        String nim = scanner.next();
        Student student = StudentDatabase.getStudentByNim(nim);

        if (student != null) {
            student.menuStudent();
        } else {
            System.out.println("Student not found. Please try again.");
        }
    }

    private static void adminLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter admin username: ");
        String username = scanner.next();
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        if (admin.validateLogin(username, password)) {
            admin.menuAdmin();
        } else {
            System.out.println("Invalid admin credentials. Please try again.");
        }
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }
}

class StudentDatabase {
    private static ArrayList<Student> students = new ArrayList<>();

    public static Student getStudentByNim(String nim) {
        for (Student student : students) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void displayStudents() {
        System.out.println("List of Registered Students:");
        System.out.println("NIM\tName\t\tFaculty\tProgram Studi");
        for (Student student : students) {
            System.out.println(student.getNim() + "\t" + student.getName() + "\t" + student.getFaculty() + "\t"
                    + student.getProgramStudi());
        }
    }
}

class Student {
    private String nim;
    private String name;
    private String faculty;
    private String programStudi;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String nim, String name, String faculty, String programStudi) {
        this.nim = nim;
        this.name = name;
        this.faculty = faculty;
        this.programStudi = programStudi;
        LibrarySystem.getBookList();
        StudentDatabase.addStudent(this);
    }

    public void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Student Menu");
            System.out.println("1. Display Personal Information");
            System.out.println("2. Display Available Books");
            System.out.println("3. Show Borrowed Books");
            System.out.println("4. Return Books");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayInfo();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    showBorrowedBooks();
                    break;
                case 4:
                    returnBooks();
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayInfo() {
        System.out.println("Student Information:");
        System.out.println("Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Program Studi: " + programStudi);
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books currently borrowed.");
        } else {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle());
            }
        }
    }

    public void logout() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed. Logging out. Goodbye, " + name + "!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to return borrowed books? (yes/no)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes")) {
                for (Book book : borrowedBooks) {
                    book.returnBook();
                }
                System.out.println("Books returned. Logging out. Goodbye, " + name + "!");
            } else {
                System.out.println("Returning to main menu. Goodbye, " + name + "!");
            }
        }
        System.exit(0);
    }

    public void displayBooks() {
        System.out.println("List of Available Books:");
        System.out.printf("%-5s%-30s%-17s%-10s%-5s\n", "ID", "Title", "Author", "Category", "Stock");
        for (Book book : LibrarySystem.getBookList()) {
            System.out.println(book.getBookId() + "\t" + book.getTitle() + "\t" + book.getAuthor() + "\t"
                    + book.getCategory() + "\t" + book.getStock());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the book you want to borrow: ");
        String id = scanner.next();
        for (Book book : LibrarySystem.getBookList()) {
            if (book.getBookId().equals(id)) {
                System.out.println("Enter number of days to borrow: ");
                int days = scanner.nextInt();
                book.borrowBook(this, days);
                borrowedBooks.add(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the book you want to return: ");
        String id = scanner.next();
        for (Book book : borrowedBooks) {
            if (book.getBookId().equals(id)) {
                book.returnBook();
                borrowedBooks.remove(book);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("You have not borrowed this book.");
    }

    public String getNim() {
        return nim;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgramStudi() {
        return programStudi;
    }
}

class Admin {
    private String adminUsername;
    private String adminPassword;
    private ArrayList<Student> studentList = new ArrayList<>();

    public Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public void addStudent(String nim, String name, String faculty, String programStudi) {
        if (nim.length() == 15) {
            Student student = new Student(nim, name, faculty, programStudi);
            studentList.add(student);
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Invalid NIM length. Please enter a valid NIM.");
        }
    }

    public void inputBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select book type:");
        System.out.println("1. Story Book");
        System.out.println("2. History Book");
        System.out.println("3. Text Book");
        int choice = scanner.nextInt();

        System.out.print("Enter book title: ");
        String title = scanner.next();
        System.out.print("Enter author name: ");
        String author = scanner.next();
        System.out.print("Enter stock: ");
        int stock = scanner.nextInt();

        Book book;
        switch (choice) {
            case 1:
                book = new StoryBook(title, author, author, author, stock, stock);
                break;
            case 2:
                book = new HistoryBook(title, author, author, author, stock, stock);
                break;
            case 3:
                book = new TextBook(title, author, author, author, stock, stock);
                break;
            default:
                System.out.println("Invalid choice. Book not added.");
                return;
        }

        LibrarySystem.getBookList().add(book);
        System.out.println("Book added successfully!");
    }

    public void displayBooks() {
        System.out.println("List of Available Books:");
        System.out.printf("%-5s%-30s%-17s%-10s%-5s\n", "ID", "Title", "Author", "Category", "Stock");
        for (Book book : LibrarySystem.getBookList()) {
            System.out.println(book.getBookId() + "\t" + book.getTitle() + "\t" + book.getAuthor() + "\t"
                    + book.getCategory() + "\t" + book.getStock());
        }
    }

    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            System.out.println("List of Registered Students:");
            System.out.println("NIM\tName\t\tFaculty\tProgram Studi");
            for (Student student : studentList) {
                System.out.println(student.getNim() + "\t" + student.getName() + "\t" + student.getFaculty() + "\t"
                        + student.getProgramStudi());
            }
        }
    }
  

    public boolean validateLogin(String username, String password) {
        return this.adminUsername.equals(username) && this.adminPassword.equals(password);
    }

    public void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Students");
            System.out.println("4. Display Books");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudentMenu();
                    break;
                case 2:
                    inputBook();
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    displayBooks();
                    break;
                case 5:
                    System.out.println("Logging out. Goodbye, Admin!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addStudentMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student NIM: ");
        String nim = scanner.next();
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student faculty: ");
        String faculty = scanner.next();
        System.out.print("Enter student program studi: ");
        String programStudi = scanner.next();

        addStudent(nim, name, faculty, programStudi);
    }
}

class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String bookId, String title, String author, String category, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public void borrowBook(Student student, int days) {
        if (stock > 0) {
            stock--;
            System.out.println("Book " + title + " borrowed by " + student.getName() + " for " + days + " days.");
        } else {
            System.out.println("Book " + title + " is out of stock.");
        }
    }

    public void returnBook() {
        stock++;
    }
}

class HistoryBook extends Book {
    private int duration;

    public HistoryBook(String bookId, String title, String author, String category, int stock, int duration) {
        super(bookId, title, author, category, stock);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}

class StoryBook extends Book {
    private int duration;

    public StoryBook(String bookId, String title, String author, String category, int stock, int duration) {
        super(bookId, title, author, category, stock);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}

class TextBook extends Book {
    private int duration;

    public TextBook(String bookId, String title, String author, String category, int stock, int duration) {
        super(bookId, title, author, category, stock);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}

