import java.util.*;

public class MMUpdate {
    private Map<Integer, Integer> right = new HashMap<>();
    private Map<Integer, Integer> guess = new HashMap<>();
    private boolean check = false;
    private Scanner scan = new Scanner(System.in);
    private int answerCount, guessCount = 0;
    
    public static void main(String[] args) {
        MMUpdate game = new MMUpdate();
        game.run();
    }
    
    public void run() {
        
        System.out.println("\nEnter count of numbers to guess (1-6):");
        answerCount = scan.nextInt();
        while (answerCount > 6 || answerCount < 1) {
            System.out.println("Enter new number 1-6:");
            answerCount = scan.nextInt();
        }
        
        GenerateNew(answerCount);
        // ShowAnswers(answerCount);

        System.out.println("\nEnter " + answerCount + " different numbers (1-6):");
        while (!check) {
            guess.clear();
            FillGuess(answerCount);
            PlayGame(answerCount);
            guessCount++;
        }
        System.out.print("You won in " + guessCount + " guesses!\n\n");
    }

    private void GenerateNew(int count) {
        for (int i = 0; i < count; i++) {
            int temp = (int)(Math.random() * 6) + 1;
            while (right.containsValue(temp)) {
                temp = (int)(Math.random() * 6) + 1;
            }
            right.put(i, temp);
        }
    }

    private void FillGuess(int count) {
        int guessNum = scan.nextInt();
        for (int i = count - 1; i >= 0; i--) {
            guess.put(i, guessNum % 10);
            guessNum /= 10;
        }
    }

    private void PlayGame(int count) {
        Map<Integer, Integer> temp = new HashMap<>(right);
        int correct = 0;
        int half = 0;
        for (int i = 0; i < count; i++) {
            if (guess.get(i).equals(right.get(i))) {
                correct++;
                temp.remove(i);
            }
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (temp.containsKey(i) && guess.get(j).equals(temp.get(i))) {
                    half++;
                    temp.remove(i);
                    break;
                }
            }
        }
        
        if (correct == 0 && half == 0) {
            System.out.println("NOTHING\n");
        } else if (correct == count) {
            check = true;
        } else {
            System.out.println(correct + " right number right location\n" + half + " right number wrong location\n");
        }
    }
    
    private void ShowAnswers(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(right.get(i));
        }
        System.out.println("");
    }
}