package domain;

import persistence.SupplierDao;
import presentation.models.Supplier;

import java.util.List;

public class SupplierService
{

    private static boolean doesNotExist(Supplier supplier)
    {
        SupplierDao supplierDao = new SupplierDao();
        for (Supplier item : supplierDao.getAll())
        {
            if (item.equals(supplier))
            {
                return false;
            }
        }
        return true;
    }

    public static void addSupplier(Supplier supplier)
    {
        SupplierDao supplierDao = new SupplierDao();

        if (doesNotExist(supplier))
        {
            supplierDao.save(supplier);
        }
    }

    public static Supplier getSupplier(int id)
    {
        SupplierDao supplierDao = new SupplierDao();
        return supplierDao.get(id);
    }

    public static List<Supplier> getSuppliers()
    {
        SupplierDao supplierDao = new SupplierDao();
        return supplierDao.getAll();
    }

    public static void updateSupplier(Supplier supplier)
    {
        SupplierDao supplierDao = new SupplierDao();
        supplierDao.update(supplier);
    }

    public static void deleteProduct(Supplier supplier)
    {
        SupplierDao supplierDao = new SupplierDao();
        supplierDao.delete(supplier);
    }
}
