package presentation.models;

public class Product
{
    private int id;
    private String name;
    private int Category;
    private String price;
    private int currentStock;
    private int minimumStock;
    private int supplierId;

    public Product(){}

    public Product(String name, String price, int currentStock, int minimumStock)
    {
        this.name = name;
        this.price = price;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
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

    public int getCategory()
    {
        return Category;
    }

    public void setCategory(int category)
    {
        Category = category;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public int getCurrentStock()
    {
        return currentStock;
    }

    public void setCurrentStock(int currentStock)
    {
        this.currentStock = currentStock;
    }

    public int getMinimumStock()
    {
        return minimumStock;
    }

    public void setMinimumStock(int minimumValue)
    {
        this.minimumStock = minimumValue;
    }

    public int getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(int supplierId)
    {
        this.supplierId = supplierId;
    }
}
