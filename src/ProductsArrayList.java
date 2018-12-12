import java.io.*;
import java.util.ArrayList;

public class ProductsArrayList implements Serializable{

    private ArrayList<Product> products = new ArrayList<>();

    public void addNewProductItem(Product productArrayList){
        this.products.add(productArrayList);
    }

    public String deleteProductWithIndicatedPrice (double price){
        String result = "";
        int i = 0;
        int counterItems = 0;
        if(products.size() != 0) {
            while (i < products.size()) {
                if (products.get(i).getPrice() == price) {
                    products.remove(products.get(i));
                    counterItems++;
                } else
                    i++;
            }
        }
        else
            result = "Список товаров не содержит никаких записей.";
        if(counterItems != 0)
            result = "Удалено " + counterItems + " записей с ценой " + price + ".";
        else
            result = "Не найдено ни одного товара с указанной ценой.";
        return result;
    }

    public String showIndicatedProducts(String nameOfProduct) {
        String s = "";
        int counterItems = 0;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getNameOfProduct().equalsIgnoreCase(nameOfProduct)) {
                s += "Искомый товар находится в магазине " + products.get(i).getShop() + " в количестве "
                        + products.get(i).getAmountOfProducts() + " штук.";
                counterItems++;
            }
        }
        if(counterItems == 0)
            s = "Не найдено ни одной записи указанного товара.";
        return s;
    }

    public String totalCostOfIndicatedProduct(String nameOfProduct){
        String s = "";
        double totalCost = 0;
        int counterItems = 0;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getNameOfProduct().equalsIgnoreCase(nameOfProduct)) {
                totalCost += products.get(i).getTotal();
                counterItems++;
            }
        }
        if(counterItems != 0)
            s  = "Суммарная цена указанного товара во всех магазинах равна " + totalCost + ".";
        else
            s = "Не найдено ни одной записи указанного товара.";
        return s;
    }

    public String shopsWithChipestProduct(String nameOfProduct){
        String result = "";
        double chipestPrice = -1;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getNameOfProduct().equalsIgnoreCase(nameOfProduct)){
                while (chipestPrice == -1){
                    chipestPrice = products.get(i).getPrice();
                }
                if(products.get(i).getPrice() < chipestPrice){
                    chipestPrice = products.get(i).getPrice();
                }
            }
        }
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getNameOfProduct().equalsIgnoreCase(nameOfProduct) && (products.get(i).getPrice() == chipestPrice))
                result = "Указанный товар самый дешевый в магазине " + products.get(i).getShop() + " по адресу " + products.get(i).getAddress() + ".";
        }
        return result;
    }

    public String showProductArrayList() {
        String s = "";
        if (products.size() != 0) {
            for (Product product : products) {
                s += (product.toString());
            }
        } else
            s = "Список товаров пуст!";
        return s;
    }

    public void Save (String f1){
        try{
            FileOutputStream outputFile = new FileOutputStream(f1);
            ObjectOutputStream outputObject = new ObjectOutputStream(outputFile);

            outputObject.writeObject(products);
            outputObject.flush();
            outputObject.close();
            outputFile.flush();
            outputFile.close();
            //System.out.println("Данные записаны в файл " + f1);
        }
        catch (IOException exception){
            //System.out.println("Ошибка работы с файлом или потоком");
            exception.printStackTrace();
        }
    }

    public void Load (String file){
        try{
            FileInputStream inputFile = new FileInputStream(file);
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);
            this.products = (ArrayList<Product>) inputObject.readObject();
            inputFile.close();
            inputObject.close();
            //System.out.println("Данные успешно загружены из файла " + file);
        }
        catch (IOException exception){
           // System.out.println("Ошибка загрузки из файла.");
            return;
        }
        catch (ClassNotFoundException exception){
           // System.out.println("Класс не найден");
            exception.printStackTrace();
        }
    }
}
