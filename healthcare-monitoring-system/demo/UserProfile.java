import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserProfile {

    public static void displayUserProfile(int userId) {
        String dbURL = "jdbc:mysql://localhost:3306/healthcare_system";
        String dbUser = "root";
        String dbPassword = "password";

        String query = "SELECT * FROM users WHERE user_id = ?";
        
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Contact: " + rs.getString("contact"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("User not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}