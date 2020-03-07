package cn.net.bhe.basics.io;

import java.util.Scanner;

public class ScannerTest {
    
    static Scanner input = new Scanner(System.in);
    
    public static void
    next()
    // main(String[] args) 
    {
        String next = input.next();
        System.out.println(next);
        input.close();
    }
    
    public static void 
    // nextLine() 
    main(String[] args) 
    {
        String nextLine = input.nextLine();
        System.out.println(nextLine);
        input.close();
    }

}
