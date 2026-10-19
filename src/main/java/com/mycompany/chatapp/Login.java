package com.mycompany.chatapp;

public class Login {
    private String username, password, cell, firstName, lastName;
    public void setNames(String fn, String ln){ firstName=fn; lastName=ln; }
    public boolean checkUsername(String u){ return u.contains("_") && u.length()<=5; }
    public boolean checkPassword(String p){
        return p.length()>=8 && p.matches(".*[A-Z].*") && p.matches(".*[0-9].*") && p.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }
    public boolean checkCell(String c){ return c.matches("^\\+27\\d{9}$"); }
    public String getUsernameStatus(String u){ return checkUsername(u) ? "Username successfully captured." : "Username is not correctly formatted."; }
    public String getPasswordStatus(String p){ return checkPassword(p) ? "Password successfully captured." : "Password is not correctly formatted."; }
    public String getCellStatus(String c){ return checkCell(c) ? "Cell number successfully captured." : "Cell number is incorrectly formatted."; }
    public String registerUser(String u, String p, String c){
        if(!checkUsername(u)) return "Username is not correctly formatted.";
        if(!checkPassword(p)) return "Password is not correctly formatted.";
        if(!checkCell(c)) return "Cell number is incorrectly formatted.";
        this.username=u; this.password=p; this.cell=c;
        return "User registered successfully.";
    }
    public boolean loginUser(String u, String p){ return u.equals(username) && p.equals(password); }
    public String returnLoginStatus(String u, String p){
        return loginUser(u,p) ? "Welcome " + firstName + " " + lastName + " it is great to see you again." : "Username or password incorrect, please try again.";
    }
}
