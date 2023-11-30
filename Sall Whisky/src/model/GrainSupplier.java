package model;

public class GrainSupplier {
    private String name;
    private String address;
    private String country;
    private String vatID;

    public GrainSupplier(String name, String address, String country, String vatID) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.vatID = vatID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getVatID() {
        return vatID;
    }
}
