package Exercise;


import java.util.ArrayList;

public class Category  {
    private String id;  //mã danh mục
    private String name;    //tên danh mục
    private ArrayList<Product> products = new ArrayList<>();

    public Category() {
        id="";
        name="";
        products=new ArrayList<>();
    }

    public Category(String id, String name, ArrayList<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}

