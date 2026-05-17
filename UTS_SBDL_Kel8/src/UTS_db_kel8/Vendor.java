package UTS_db_kel8;

public class Vendor extends Organization {
    private int vendorID;
    private String contactPerson;

    public Vendor(String name, String contactInfo, int vendorID, String contactPerson) {
        super(name, contactInfo);
        this.vendorID = vendorID;
        this.contactPerson = contactPerson;
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}