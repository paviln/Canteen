package presentation.controllers;

import domain.CategoryService;
import domain.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.models.Category;
import presentation.models.Display;
import presentation.models.Product;

import java.util.List;

public class ManagerController
{
    @FXML
    private StackPane center;

    @FXML
    private VBox productsDisplay, categoriesDisplay, suppliersDisplay;

    @FXML
    private Button productsBtn, categoriesBtn, suppliersBtn;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TableColumn<Product, String> fldName, fldCategory, fldPrice, fldCurrentStock, fldMinimumStock, fldSupplier;

    @FXML
    private TextField productName, productCategory, productPrice, productCurrent, productMinimum, productSupplier;

    @FXML
    private ListView<Category> tblCategories;

    @FXML
    private TextField categoryField;

    @FXML
    public void initialize()
    {
        fldName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        fldCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        fldPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        fldCurrentStock.setCellValueFactory(new PropertyValueFactory<Product, String>("currentStock"));
        fldMinimumStock.setCellValueFactory(new PropertyValueFactory<Product, String>("minimumStock"));
        fldSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplierId"));

        showProducts();

        tblProduct.setOnMouseClicked((MouseEvent event) ->
        {
            productEdit();
        });

        tblCategories.setOnMouseClicked(event ->
        {
            Category selectedCategory = tblCategories.getSelectionModel().getSelectedItem();
            if (selectedCategory != null)
            {
                categoryField.setText(tblCategories.getSelectionModel().getSelectedItem().getName());
            }
        });

        tblCategories.setCellFactory(param -> new ListCell<Category>()
        {
            @Override
            protected void updateItem(Category item, boolean empty)
            {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null)
                {
                    setText(null);
                } else
                {
                    setText(item.getName());
                }
            }
        });
    }

    /**
     * Handles menu button presses
     *
     * @param e
     */
    @FXML
    private void menuHandler(ActionEvent e)
    {
        Button button = ((Button) e.getSource());

        switch (button.getText())
        {
            case "Products":
                changeDisplay(Display.Products);
                break;
            case "Categories":
                changeDisplay(Display.Categories);
                break;
            case "Suppliers":
                changeDisplay(Display.Suppliers);
                break;
        }
    }

    /**
     * Changes the display according to the menu
     *
     * @param display
     */
    private void changeDisplay(Display display)
    {
        productsBtn.getStyleClass().remove("current");
        categoriesBtn.getStyleClass().remove("current");
        suppliersBtn.getStyleClass().remove("current");
        productsDisplay.setVisible(false);
        categoriesDisplay.setVisible(false);
        suppliersDisplay.setVisible(false);

        switch (display)
        {
            case Products:
                productsBtn.getStyleClass().add("current");
                productsDisplay.setVisible(true);
                break;
            case Categories:
                showCategories();
                categoriesBtn.getStyleClass().add("current");
                categoriesDisplay.setVisible(true);
                break;
            case Suppliers:
                suppliersBtn.getStyleClass().add("current");
                suppliersDisplay.setVisible(true);
                break;
        }
    }

    private Product productInput()
    {
        if (!productName.getText().isEmpty() && !productCategory.getText().isEmpty() && !productPrice.getText().isEmpty() && !productCurrent.getText().isEmpty() && !productMinimum.getText().isEmpty())
        {
            Product product = new Product();
            product.setName(productName.getText());
            product.setCategory(Integer.parseInt(productCategory.getText()));
            product.setPrice(productPrice.getText());
            product.setCurrentStock(Integer.parseInt(productCurrent.getText()));
            product.setMinimumStock(Integer.parseInt(productMinimum.getText()));
            product.setSupplierId(Integer.parseInt(productSupplier.getText()));

            return product;
        }
        return null;
    }

    private void productEdit()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product selectedProduct = tblProduct.getSelectionModel().getSelectedItem();
            productName.setText(selectedProduct.getName());
            productCategory.setText(String.valueOf(selectedProduct.getCategory()));
            productPrice.setText(selectedProduct.getPrice());
            productCurrent.setText(String.valueOf(selectedProduct.getCurrentStock()));
            productMinimum.setText(String.valueOf(selectedProduct.getMinimumStock()));
            productSupplier.setText(String.valueOf(selectedProduct.getSupplierId()));
        }
    }

    @FXML
    private void addProduct()
    {
        Product product = productInput();
        ProductService.addProduct(product);
        showProducts();
    }

    @FXML
    private void updateProduct()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product product = productInput();
            if (product != null)
            {
                product.setId(tblProduct.getSelectionModel().getSelectedItem().getId());
                ProductService.updateProduct(product);
                showProducts();
            }
        }
    }

    @FXML
    private void removeProduct()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product selectedProduct = tblProduct.getSelectionModel().getSelectedItem();
            ProductService.deleteProduct(selectedProduct);
            tblProduct.getItems().remove(selectedProduct);
        }
    }

    private void showProducts()
    {
        tblProduct.getItems().clear();
        tblProduct.getItems().addAll(ProductService.getProducts());
    }

    /**
     * Display contents of categories
     */
    private void showCategories()
    {
        tblCategories.getItems().clear();

        List<Category> categories = CategoryService.getCategories();

        for (Category category : categories)
        {
            tblCategories.getItems().add(0, category);
        }
    }

    @FXML
    private void addCategory()
    {
        if (!categoryField.getText().isEmpty())
        {
            Category category = new Category(categoryField.getText());
            CategoryService.saveCategory(category);
            showCategories();
        }
    }

    @FXML
    private void renameCategory()
    {
        Category selectedCategory = tblCategories.getSelectionModel().getSelectedItem();
        if (selectedCategory != null)
        {
            selectedCategory.setName(categoryField.getText());
            CategoryService.updateName(selectedCategory);
            tblCategories.getItems().set(tblCategories.getSelectionModel().getSelectedIndex(), selectedCategory);
        }
    }

    @FXML
    private void removeCategory()
    {
        Category selectedCategory = tblCategories.getSelectionModel().getSelectedItem();
        if (selectedCategory != null)
        {
            CategoryService.deleteCategory(selectedCategory);
            tblCategories.getItems().remove(selectedCategory);
        }
    }
}
