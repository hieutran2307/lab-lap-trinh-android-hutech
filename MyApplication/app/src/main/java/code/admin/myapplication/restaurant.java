package code.admin.myapplication;

public class restaurant
{
    private String Name = "";
    private String Address = "";
    private String Type = "";

    public String toString()
    {
        return getName() + " " + getAddress() + " " + getType();
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
