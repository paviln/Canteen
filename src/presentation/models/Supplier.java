package presentation.models;

public class Supplier
{
    private int id;
    private String name;
    private String phone;
    private String deliveryTime;

    public Supplier(){}

    public Supplier(int id, String name, String phone, String deliveryTime)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.deliveryTime = deliveryTime;
    }

    public boolean equals(Product product)
    {
        return this.id == product.getSupplierId();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getDeliveryTime()
    {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime)
    {
        this.deliveryTime = deliveryTime;
    }
}
