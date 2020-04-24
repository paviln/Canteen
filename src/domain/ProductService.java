package domain;

import persistence.ProductDao;
import presentation.models.Product;

import java.util.List;

public class ProductService
{
    public static void addProduct(Product product)
    {
        ProductDao productDao = new ProductDao();

        boolean alreadyExists = false;
        for (Product p : productDao.getAll())
        {
            if (p.equals(product))
            {
                alreadyExists = true;
            }
        }
        if (!alreadyExists)
        {
            productDao.save(product);
        }
    }

    public static void updateProduct(Product product)
    {
        ProductDao productDao = new ProductDao();
        productDao.update(product);
    }

    public static void deleteProduct(Product product)
    {
        ProductDao productDao = new ProductDao();
        productDao.delete(product);
    }
    public static List<Product> getProducts()
    {
        ProductDao productDao = new ProductDao();
        return productDao.getAll();
    }
}
