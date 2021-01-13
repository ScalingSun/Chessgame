package DTO;

public class DTOUser {
    private int id;
    private String name;
    private String password;


    public DTOUser(int i, String n, String p){
        id = i;
        name = n;
        password = p;
    }
    public DTOUser(String n, String p){
        name = n;
        password = p;
    }
    public DTOUser(){

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
