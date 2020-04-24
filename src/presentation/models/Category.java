package presentation.models;

public class Category
{
    private int id;
    private String name;

    public Category(){}

    public Category(String name)
    {
        this.name = name;
    }

    public boolean equals(Category category)
    {
        return this.name.equals(category.name);
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
}
