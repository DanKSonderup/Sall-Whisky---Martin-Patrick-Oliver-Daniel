package model;

public class Employee {
    private int Id;
    private String navn;

    public Employee(int id, String navn) {
        Id = id;
        this.navn = navn;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn + " (" + Id + ")";
    }
}
