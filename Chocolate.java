import java.lang.Math;
import java.util.LinkedList;
import java.util.*;

public class Chocolate {

  public static int[] c;


public static String[][][] init() {
  int l = c.length;
  int forL = c.length;
  String[][][] M = new String[c.length][c.length][2];
  for (int y = 0; y < l; y++) {
    for (int x = 0; x < forL; x++) {
      M[y][x][0] = "?";
      M[y][x][1] = "-1";
    }
    forL = forL - 1;
  }
  forL = c.length;
  for (int y = 0; y < c.length; y++) {
    for (int x = c.length - 1; x >= forL; x--) {
      M[y][x][0] = "#";
      M[y][x][1] = "";
    }
    forL = forL - 1;
  }
  return M;
}

  public static void main(String[] args) {
    c = new int[args.length];

    for (int i = 0; i < args.length; i++) {
      c[i] = Integer.parseInt(args[i]);
    }

    optimalChoice();


  }

  public static void optimalChoice() {
    String[][][] M = init();

    for (int i = 0; i < c.length; i++) {
      M[0][i][0] = "" + i;
      M[0][i][1] = "" + c[i]*c.length;
    }
    for (int y = 1; y < c.length; y++) {
      int k = Math.abs(c.length - y);
      for (int x = 0; x < k; x++) {
        String a = M[y-1][x][1];
        int lowest = x;
        String b = M[y-1][x+1][1];
        int highest = (x+y);
        System.out.println("y:" + y);
        if ((c[lowest]*k + Integer.parseInt(b)) > (c[highest]*k + Integer.parseInt(a))) {
          M[y][x][0] = "" + lowest + M[y-1][x+1][0];
          M[y][x][1] = "" + (c[lowest]*k + Integer.parseInt(b));
        }
        else {
          M[y][x][0] = "" + highest + M[y-1][x][0];
          M[y][x][1] = "" + (c[highest]*k + Integer.parseInt(a));
        }

      }
      toStringM(M);
    }

    toStringM(M);
    String sequence = M[c.length - 1][0][0];
    int left = 0;
    int right = c.length - 1;
    StringBuilder instr = new StringBuilder();
    for(int i = 0; i < c.length; i++) {
      System.out.println(sequence);
      String c = sequence.substring(i,i+1);
      System.out.println(c);
      if(Integer.parseInt(c) == left) {
        instr.append("vänster ");
        left = left + 1;
      }
      else {
        instr.append("höger ");
        right = right - 1;
      }
    }
    System.out.println(instr.toString());
  }

  public static void toStringM(String[][][] M) {
    for (int i = M.length - 1; i >= 0; i--) {
      System.out.print("|");
      for (int j = 0; j < M.length; j++) {
        System.out.print(" " + M[i][j][0] + " ");
      }
      System.out.print("|");
      System.out.print("\n");
    }
    System.out.print("\n");
  }
}
