package domain;

import persistence.ProductDao;
import persistence.SupplierDao;
import presentation.models.Product;
import presentation.models.Supplier;

import java.util.List;

public class ProductService
{
    private static boolean validSupplier(Product product)
    {
        SupplierDao supplierDao = new SupplierDao();
        for (Supplier supplier : supplierDao.getAll())
        {
            if (supplier.equals(product))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean doesNotExist(Product product)
    {
        ProductDao productDao = new ProductDao();
        for (Product p : productDao.getAll())
        {
            if (p.equals(product))
            {
                return false;
            }
        }
        return true;
    }

    public static void addProduct(Product product)
    {
        ProductDao productDao = new ProductDao();

        if (doesNotExist(product) && validSupplier(product))
        {
            productDao.save(product);
        }
    }

    public static void updateProduct(Product product)
    {
        ProductDao productDao = new ProductDao();

        if (doesNotExist(product) && validSupplier(product))
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
