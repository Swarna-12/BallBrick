import java.util.*;
public class BallBrick {
    static int bc = 0, bpos = 0;
    public static void initMatrix(String a[][]){
        int n = a[0].length;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                a[i][j] = " ";
        for(int i=0;i<n;i++){
            a[i][0] = "W"; 
            a[0][i] = "W";
            a[i][n-1] = "W";
        }
        for(int i=1;i<n-1;i++)
            a[n-1][i] = (i==n/2) ? "o" : "W";
    }
    public static void printMatrix(String a[][]){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void l2r(String a[][], int i){
        int x = 1;
        while(a[i][x].equals(" "))
            x++;
        if(a[i][x].equals("W")){
            bc--;
        }
        else{
            int temp = Integer.parseInt(a[i][x])-1;
            a[i][x] = temp==0 ? " " : Integer.toString(temp);
            a[a.length-1][bpos] = "W";
            bpos = x;
            bc--;
            a[a.length-1][bpos] = "o";
        }
    }
    public static void r2l(String a[][], int i){
        int x = a.length-2;
        while(a[i][x].equals(" "))
            x--;
        if(a[i][x].equals("W")){
            bc--;
        }
        else{
            int temp = Integer.parseInt(a[i][x])-1;
            a[i][x] = temp==0 ? " " : Integer.toString(temp);
            a[a.length-1][bpos] = "W";
            bpos = x;
            bc--;
            a[a.length-1][bpos] = "o";
        }
    }
    public static void traverseStraight(String a[][], int x){
        int i = a.length-2;
        while(a[i][x].equals(" "))
            i--;
        if(a[i][x].equals("W"))
            bc--;
        else{
            int temp = Integer.parseInt(a[i][x])-1;
            a[i][x] = temp==0 ? " " : Integer.toString(temp);
        }
    }
    public static void traverseLeftDiagonal(String a[][], int x){
        int i = a.length-2;
        x--;
        while(a[i][x].equals(" ")){
            i--;
            x--;
        }
        if(a[i][x].equals("W")){
            l2r(a,i);
        }
        else{
            int temp = Integer.parseInt(a[i][x])-1;
            a[i][x] = temp==0 ? " " : Integer.toString(temp);
        }
    }
    public static void traverseRightDiagonal(String a[][], int x){
        int i = a.length-2;
        x--;
        while(a[i][x].equals(" ")){
            i--;
            x++;
        }
        if(a[i][x].equals("W")){
            r2l(a,i);
        }
        else{
            int temp = Integer.parseInt(a[i][x])-1;
            a[i][x] = temp==0 ? " " : Integer.toString(temp);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of the NxN matrix : ");
        int n = sc.nextInt();
        String a[][] = new String[n][n];
        String ch = "";
        initMatrix(a);
        do{
            System.out.println("Enter the brick's position and the brick type:");
            int r = sc.nextInt();
            int c = sc.nextInt();
            int bp = sc.nextInt();
            a[r][c] = bp+"";
            System.out.println("Do you want to continue(Y or N)?");
            ch = sc.next();
        }
        while(ch.equalsIgnoreCase("Y"));
        System.out.println("Enter ball Count : ");
        bc = sc.nextInt();
        printMatrix(a);
        System.out.println("Ball count is "+bc);
        bpos = n/2;
        while(bc!=0){
            System.out.println("Enter the direction in which the ball need to traverse : ");
            String dir = sc.next();
            switch(dir){
                case "ST":
                    traverseStraight(a,bpos);
                    break;
                case "LD":
                    traverseLeftDiagonal(a,bpos);
                    break;
                case "RD":
                    traverseRightDiagonal(a,bpos);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            printMatrix(a);
            System.out.println("Ball count is "+bc);
        }
    }
}
