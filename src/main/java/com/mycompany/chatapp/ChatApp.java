package com.mycompany.chatapp;
import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();
        System.out.print("First Name: "); String fn = sc.nextLine();
        System.out.print("Last Name: "); String ln = sc.nextLine();
        login.setNames(fn, ln);
        System.out.print("Username (kyl_1): "); String u = sc.nextLine();
        System.out.println(login.getUsernameStatus(u));
        System.out.print("Password (Ch&&sec@ke99!): "); String p = sc.nextLine();
        System.out.println(login.getPasswordStatus(p));
        System.out.print("Cell (+27831234567): "); String c = sc.nextLine();
        System.out.println(login.getCellStatus(c));
        System.out.println(login.registerUser(u,p,c));
        System.out.print("Login Username: "); String lu = sc.nextLine();
        System.out.print("Login Password: "); String lp = sc.nextLine();
        System.out.println(login.returnLoginStatus(lu,lp));
    }
}