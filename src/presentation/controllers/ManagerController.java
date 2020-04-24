package presentation.controllers;

import domain.CategoryService;
import domain.ProductService;
import domain.SupplierService;
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
import presentation.models.Supplier;

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
    private TableColumn<Product, String> fldProductName, fldProductCategory, fldProductPrice, fldProductCurrentStock, fldProductMinimumStock, fldProductSupplier;

    @FXML
    private TableColumn<Supplier, String> fldSupplierName, fldSupplierPhoneNumber, fldSupplierDeliveryTime;

    @FXML
    private TextField productName, productCategory, productPrice, productCurrent, productMinimum, productSupplier, supplierName, supplierPhoneNumber, supplierDeliveryTime;

    @FXML
    private ListView<Category> tblCategories;

    @FXML
    private TextField categoryField;

    @FXML
    private TableView<Supplier> tblSupplier;


    @FXML
    public void initialize()
    {
        // Set the products table fields to the appropriate type
        fldProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        fldProductCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        fldProductPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        fldProductCurrentStock.setCellValueFactory(new PropertyValueFactory<Product, String>("currentStock"));
        fldProductMinimumStock.setCellValueFactory(new PropertyValueFactory<Product, String>("minimumStock"));
        fldProductSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplierId"));

        // Set the suppliers table fields to the appropriate type
        fldSupplierName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        fldSupplierPhoneNumber.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
        fldSupplierDeliveryTime.setCellValueFactory(new PropertyValueFactory<Supplier, String>("deliveryTime"));

        tblProduct.setOnMouseClicked((MouseEvent event) ->
        {
            productEdit();
        });

        tblSupplier.setOnMouseClicked((MouseEvent event) ->
        {
            supplierEdit();
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
        // Show the current products
        showProducts();
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
                showProducts();
                productsBtn.getStyleClass().add("current");
                productsDisplay.setVisible(true);
                break;
            case Categories:
                showCategories();
                categoriesBtn.getStyleClass().add("current");
                categoriesDisplay.setVisible(true);
                break;
            case Suppliers:
                showSuppliers();
                suppliersBtn.getStyleClass().add("current");
                suppliersDisplay.setVisible(true);
                break;
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

    private void showSuppliers()
    {
        tblSupplier.getItems().clear();
        tblSupplier.getItems().addAll(SupplierService.getSuppliers());
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
        if (product != null)
        {
            ProductService.addProduct(product);
            showProducts();
        }
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

    private Supplier supplierInput()
    {
        if (!supplierName.getText().isEmpty() && !supplierPhoneNumber.getText().isEmpty() && !supplierDeliveryTime.getText().isEmpty())
        {
            Supplier supplier = new Supplier();
            supplier.setName(supplierName.getText());
            supplier.setPhone(supplierPhoneNumber.getText());
            supplier.setDeliveryTime(supplierDeliveryTime.getText());
            return supplier;
        }
        return null;
    }

    private void supplierEdit()
    {
        if (tblSupplier.getSelectionModel().getSelectedItem() != null)
        {
            Supplier selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
            supplierName.setText(selectedSupplier.getName());
            supplierPhoneNumber.setText(selectedSupplier.getPhone());
            supplierDeliveryTime.setText(selectedSupplier.getDeliveryTime());
        }
    }

    @FXML
    private void addSupplier()
    {
        Supplier supplier = supplierInput();
        if (supplier != null)
        {
            SupplierService.addSupplier(supplier);
            showSuppliers();
        }
    }

    @FXML
    private void updateSupplier()
    {
        if (tblSupplier.getSelectionModel().getSelectedItem() != null)
        {
            Supplier supplier = supplierInput();
            if (supplier != null)
            {
                supplier.setId(tblSupplier.getSelectionModel().getSelectedItem().getId());
                SupplierService.updateSupplier(supplier);
                showSuppliers();
            }
        }
    }

    @FXML
    private void removeSupplier()
    {
        if (tblSupplier.getSelectionModel().getSelectedItem() != null)
        {
            Supplier selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
            SupplierService.deleteProduct(selectedSupplier);
            tblSupplier.getItems().remove(selectedSupplier);
        }
    }
}
