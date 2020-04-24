package presentation.models;

public class Product
{
    private int id;
    private String name;
    private int category;
    private String price;
    private int currentStock;
    private int minimumStock;
    private int supplierId;

    public Product(){}

    public Product(String name, int category, String price, int currentStock, int minimumStock)
    {
        this.name = name;
        this.category = category;
        this.price = price;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
    }

    public boolean equals(Product product)
    {
        return this.name.equals(product.name) && this.supplierId == product.supplierId;
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
        return category;
    }

    public void setCategory(int category)
    {
        this.category = category;
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
