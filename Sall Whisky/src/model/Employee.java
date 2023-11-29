package model;

public class Employee {
    private int Id;
    private String navn;

    public Employee(int id, String navn) {
        Id = id;
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn + " (" + Id + ")";
    }
}
