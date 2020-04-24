package domain;

import persistence.ProductDao;
import presentation.models.Product;

import java.util.List;

public class ProductService
{
    private static boolean alreadyExist(Product product)
    {
        ProductDao productDao = new ProductDao();
        for (Product p : productDao.getAll())
        {
            if (p.equals(product))
            {
                return true;
            }
        }
        return false;
    }

    public static void addProduct(Product product)
    {
        ProductDao productDao = new ProductDao();

        if (!alreadyExist(product))
        {
            productDao.save(product);
        }
    }

    public static void updateProduct(Product product)
    {
        ProductDao productDao = new ProductDao();

        if (!alreadyExist(product))
        {
            productDao.update(product);
        }
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
