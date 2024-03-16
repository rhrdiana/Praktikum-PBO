import java.util.Scanner;

public class RaniSistem {
    private static Scanner scan = new Scanner(System.in);
    private static boolean MahasiswaLogin= false;
    private static boolean AdminLogin = false;
    private static final String studentNIM []= {"202310370311129"};
    

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");

            
            System.out.print("Choose option (1-3): ");
            int choice = scan.nextInt();

            if (choice == 1) {
                if (MahasiswaLogin) {
                    System.out.println("You are already logged in as a student.");
                } else {
                    System.out.print("Enter your NIM: ");
                    String nim = scan.next();
                    for (int i = 0; i < studentNIM.length; i++) {
                        if (nim.equals(studentNIM[i])) {
                            System.out.println("Successful Login as Student");
                        } else {
                            System.out.println("User Not Found");
                        }
                    }
                        
                }
            } else if (choice == 2) {
                if (AdminLogin) {
                    System.out.println("You are already logged in as an admin.");
                } else {
                    System.out.print("Enter your username (admin): ");
                    String username = scan.next();
                    System.out.print("Enter your password (admin): ");
                    String password = scan.next();
                    if ("admin".equals(username) && "adm1n".equals(password)) {
                        AdminLogin = true;
                        System.out.println("Successful Login as Admin");
                    } else {
                        System.out.println("Admin User Not Found!!");
                    }
                }
            } else if (choice == 3) {
                System.out.println("adios");
                break;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }

            System.out.println();
        }

        scan.close();
    }
}

