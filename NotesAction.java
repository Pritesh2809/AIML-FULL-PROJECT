import java.util.ArrayList;

public class NotesAction { // NotesAction class

    // Nested Note class to represent a currency note
    public static class Note {
        private int value; // Variable for note value

        // Constructor for Note class
        public Note(int value) {
            this.value = value; // Initializing note value
        }

        // Getter method for note value
        public int getValue() {
            return value; // Returning note value
        }
    }

    // Method to add notes to the Notes object
    // This method is static because it does not depend on instance-specific data and can be shared across all instances
    public static void addNotes(Notes notes, int num2000, int num500, int num200, int num100) {
        // Loop to add 2000 Rs notes
        for (int i = 0; i < num2000; i++) {
            notes.getNotes().add(new Rs2000Note()); // Add a new Rs2000 note to the notes list
        }
        // Loop to add 500 Rs notes
        for (int i = 0; i < num500; i++) {
            notes.getNotes().add(new Rs500Note()); // Add a new Rs500 note to the notes list
        }
        // Loop to add 200 Rs notes
        for (int i = 0; i < num200; i++) {
            notes.getNotes().add(new Rs200Note()); // Add a new Rs200 note to the notes list
        }
        // Loop to add 100 Rs notes
        for (int i = 0; i < num100; i++) {
            notes.getNotes().add(new Rs100Note()); // Add a new Rs100 note to the notes list
        }
    }

    // Method to withdraw notes from the Notes object
    // This method is static because it does not depend on instance-specific data and can be shared across all instances
    public static boolean withdrawNotes(Notes notes, double amount) {
        // List to store the withdrawn notes temporarily
        ArrayList<Note> withdrawnNotes = new ArrayList<>();

        // The while loop is used to continuously attempt to withdraw notes of a specific value until the remaining amount is less than the note value or there are no more notes of that value left
        // Loop to withdraw 2000 Rs notes
        while (amount >= 2000 && containsNoteOfValue(notes, 2000)) {
            amount -= 2000; // Reduce the amount by the value of the note
            withdrawnNotes.add(removeNoteOfValue(notes, 2000)); // Add the withdrawn note to the list
        }
        // Loop to withdraw 500 Rs notes
        while (amount >= 500 && containsNoteOfValue(notes, 500)) {
            amount -= 500; // Reduce the amount by the value of the note
            withdrawnNotes.add(removeNoteOfValue(notes, 500)); // Add the withdrawn note to the list
        }
        // Loop to withdraw 200 Rs notes
        while (amount >= 200 && containsNoteOfValue(notes, 200)) {
            amount -= 200; // Reduce the amount by the value of the note
            withdrawnNotes.add(removeNoteOfValue(notes, 200)); // Add the withdrawn note to the list
        }
        // Loop to withdraw 100 Rs notes
        while (amount >= 100 && containsNoteOfValue(notes, 100)) {
            amount -= 100; // Reduce the amount by the value of the note
            withdrawnNotes.add(removeNoteOfValue(notes, 100)); // Add the withdrawn note to the list
        }

        // Check if the exact amount has been withdrawn
        if (amount == 0) {
            System.out.println("Withdrawal successful. Notes dispensed: ");
            displayWithdrawnNotes(withdrawnNotes); // Display the withdrawn notes
            return true; // Return true if withdrawal is successful
        } else {
            notes.getNotes().addAll(withdrawnNotes); // Restore the withdrawn notes back to the Notes object if exact amount cannot be withdrawn
            return false; // Return false if withdrawal is unsuccessful
        }
    }

    // Helper method to remove a note of a specific value from the Notes object
    private static Note removeNoteOfValue(Notes notes, int value) {
        // Loop through the notes list
        for (int i = 0; i < notes.getNotes().size(); i++) {
            // Check if the note value matches
            if (notes.getNotes().get(i).getValue() == value) {
                return notes.getNotes().remove(i); // Remove and return the note
            }
        }
        return null; // This should never happen if called correctly
    }

    // Helper method to check if a note of a specific value exists in the Notes object
    private static boolean containsNoteOfValue(Notes notes, int value) {
        // Loop through the notes list
        for (Note note : notes.getNotes()) {
            // Check if the note value matches
            if (note.getValue() == value) {
                return true; // Return true if the note exists
            }
        }
        return false; // Return false if the note does not exist
    }

    // Helper method to display the withdrawn notes
    private static void displayWithdrawnNotes(ArrayList<Note> withdrawnNotes) {
        // Variables to count each denomination
        int count2000 = 0, count500 = 0, count200 = 0, count100 = 0;
        // Loop through the withdrawn notes
        for (Note note : withdrawnNotes) {
            // Switch case to count notes based on their value
            switch (note.getValue()) {
                case 2000:
                    count2000++;
                    break;
                case 500:
                    count500++;
                    break;
                case 200:
                    count200++;
                    break;
                case 100:
                    count100++;
                    break;
            }
        }

        // Print the count of each denomination
        System.out.println(count2000 + " x 2000 Rs, " +
                count500 + " x 500 Rs, " +
                count200 + " x 200 Rs, " +
                count100 + " x 100 Rs");
    }

    // Method to convert Notes object to a string representation
    // This method is static because it does not depend on instance-specific data and can be shared across all instances
    public static String notesToString(Notes notes) {
        // Variables to count each denomination
        int count2000 = 0, count500 = 0, count200 = 0, count100 = 0;
        // Loop through the notes list
        for (Note note : notes.getNotes()) {
            // Switch case to count notes based on their value
            switch (note.getValue()) {
                case 2000:
                    count2000++;
                    break;
                case 500:
                    count500++;
                    break;
                case 200:
                    count200++;
                    break;
                case 100:
                    count100++;
                    break;
            }
        }

        // Return the string representation of the notes count
        return "2000 Rs notes: " + count2000 + "\n" +
                "500 Rs notes: " + count500 + "\n" +
                "200 Rs notes: " + count200 + "\n" +
                "100 Rs notes: " + count100;
    }
}
