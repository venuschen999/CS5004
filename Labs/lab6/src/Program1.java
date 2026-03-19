public class Program1 {

  // Method that prints array of objects
  public static void printArray(Object[] anArray) {

    // for-each loop
    for (Object item : anArray) {

      // print each element
      System.out.print(item + " ");
    }

    // move to next line after printing
    System.out.println();
  }

  public static void main(String[] args) {

    // create arrays of different types
    Integer[] intArry = {1, 2, 3, 4, 5};
    Double[] doubArry = {1.1, 2.2, 3.3, 4.4};
    Character[] charArry = {'H', 'E', 'L', 'L', 'O'};
    String[] strArry = {"once", "upon", "a", "time"};

    // call print method
    printArray(intArry);
    printArray(doubArry);
    printArray(charArry);
    printArray(strArry);
  }
}