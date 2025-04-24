package ie.atu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Library {
    static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connected to database!");

            while (true) {
                System.out.println("\n1. Add Book\n2. View Books\n3. Update Book\n4. Delete Book\n5. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
            }
            switch (choice) {
                case 1 -> addBook(conn, scanner);
                case 2 -> viewBooks(conn);
                case 3 -> updateBook(conn, scanner);
                case 4 -> deleteBook(conn, scanner);
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void addBook(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter PublisherID: ");
        int publisherId = scanner.nextInt();


        String sql = "INSERT INTO Books (Title, ISBN, PublishedYear, PublisherID) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.setInt(3, publisherId);
            stmt.executeUpdate();
            System.out.println("Book added!");
        }
    }
