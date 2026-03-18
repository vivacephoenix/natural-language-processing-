import java.util.ArrayList;
import java.util.Scanner;

public class MyNLP {
    private TextProcessor processor;
    
    // Requirement: Use at least two ArrayLists
    private ArrayList<String> searchHistory; // Stores the words/phrases searched
    private ArrayList<Integer> countHistory;  // Stores the resulting counts

    public MyNLP() {
        // Initialize the processor with your essay file
        processor = new TextProcessor("essay.txt");
        searchHistory = new ArrayList<String>();
        countHistory = new ArrayList<Integer>();
    }

    /*
     * Algorithm: Searches the text for a specific word or phrase
     * NLP Technique: Keyword Spotting / Frequency Analysis
     * @param target The word or phrase to look for
     * @return The number of occurrences found
     */
    public int countOccurrences(String target) {
        String text = processor.getFullText();
        String lookFor = target.toLowerCase();
        int count = 0;
        int index = text.indexOf(lookFor);

        // Algorithm: Loop using indexOf to find every instance of the phrase
        while (index != -1) {
            count++;
            // Move the index forward to find the next occurrence
            index = text.indexOf(lookFor, index + lookFor.length());
        }

        // Store data in our ArrayLists to track session history
        searchHistory.add(target);
        countHistory.add(count);

        return count;
    }

    public void prompt() {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Welcome to the Essay Analyzer ---");
        System.out.println("Enter a word or phrase to count (or 'quit' to stop):");
        
        while (true) {
            System.out.print("\nSearch for: ");
            String userInput = input.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                break;
            }

            int found = countOccurrences(userInput);
            System.out.println("The phrase '" + userInput + "' appeared " + found + " times.");
        }

        printSummary();
        input.close();
    }

    /*
     * Prints a summary of all searches performed in this session
     */
    public void printSummary() {
        System.out.println("\n--- Session Summary ---");
        for (int i = 0; i < searchHistory.size(); i++) {
            System.out.println("Keyword: [" + searchHistory.get(i) + "] | Count: " + countHistory.get(i));
        }
        System.out.println("Goodbye!");
    }
}
