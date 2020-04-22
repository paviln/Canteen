package presentation.controllers;

import javafx.fxml.FXML;
import persistence.ProductDao;
import presentation.models.Product;

import java.util.List;

public class ManagerController
{
    @FXML
    private void showProducts()
    {
        // Test DAO methods
        ProductDao productDao = new ProductDao();

        Product product = productDao.get(3);

        System.out.println(product.getName());

        List<Product> products = productDao.getAll();

        for (Product p : products)
        {
            System.out.println(p.getName());
        }

        //productDao.save(new Product("test", "1", 1,1));
    }
}
