import java.util.Scanner;

public class Main {
  public static void main(String args[]) {
    Scanner s = new Scanner(System.in);
    int numLines = Integer.valueOf(s.nextLine());
    int arr[] = new int[numLines];
    for (int i = 0; i < numLines; i++) {
      int palindromeLength = Integer.valueOf(s.nextLine());
      if (palindromeLength % 2 == 0)
        arr[i] = palindromeLength;
      else
        arr[i] = palindromeLength - 1;
    }

    for (int i : arr)
      System.out.println(i);
  }
}
