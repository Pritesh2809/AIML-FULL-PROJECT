import java.util.ArrayList;

public class Notes { // Declaring Notes class to manage currency notes
    private ArrayList<NotesAction.Note> notes; // List to store notes, each representing a specific currency denomination

    // Constructor for the Notes class
    public Notes() {
        this.notes = new ArrayList<>(); // Initializing the notes list to store currency notes
    }

    // Getter method for notes
    public ArrayList<NotesAction.Note> getNotes() {
        return notes; // Returning the notes list
    }
}
