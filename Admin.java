import Notes.*;
import java.util.ArrayList;

public class Admin extends Account { // Declaring POJO Admin class that extends Account

    private Notes notes; // Declaring Notes object to manage currency notes

    // Parameterized constructor
    public Admin(String id, String password) {
        super(id, password); // Calling constructor of the superclass Account to initialize id and password
        this.notes = new Notes(); // Initializing the notes object to manage currency notes
    }

    // Getter method for notes
    public Notes getNotes() {
        return notes; // Returning notes
    }

    // Setter method for notes
    public void setNotes(Notes notes) {
        this.notes = notes; // Setting notes
    }

    // Method to find an admin by ID
    public static Admin findAdminById(String adminId, ArrayList<Admin> admins) {
        // Loop through each admin in the list
        for (Admin admin : admins) {
            // Check if the admin ID matches
            if (admin.getId().equals(adminId)) {
                return admin; // Return the admin if the ID matches
            }
        }
        return null; // Return null if no admin matches the ID
    }
}
