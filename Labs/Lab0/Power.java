public class Power {
  public static void main(String[] args) {
    long result = (long) Math.pow(2, 31);
    System.out.println(result);
  }

}


/*
Three differences between this Java program and the Python version:

1. Java requires a class and a main method to run a program, while Python can
   execute statements directly.

2. Java is statically typed, so variable types must be declared explicitly at the beginning,
  whereas Python is dynamically typed and does not require type declarations.

3. Java does not have an exponentiation operator like Python’s **, so computing
   2 raised to the 31st power requires using Math.pow, while Python can use 2 ** 31 directly.
*/

