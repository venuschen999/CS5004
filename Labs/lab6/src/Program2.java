public class Program2 {

  public static void printArray(Integer[] arr) {
    for (Integer item : arr) {
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public static void printArray(Double[] arr) {
    for (Double item : arr) {
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public static void printArray(Character[] arr) {
    for (Character item : arr) {
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public static void printArray(String[] arr) {
    for (String item : arr) {
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {

    Integer[] intArry = {1, 2, 3, 4, 5};
    Double[] doubArry = {1.1, 2.2, 3.3, 4.4};
    Character[] charArry = {'H', 'E', 'L', 'L', 'O'};
    String[] strArry = {"once", "upon", "a", "time"};

    printArray(intArry);
    printArray(doubArry);
    printArray(charArry);
    printArray(strArry);
  }
}