package Exercise;



public class Product { //thông tin sản phầm
    private String id; //mã sản phẩm
    private String name;    //tên sản phẩm
    private float price;    //giá cả
    private String origin;  //xuất xứ

    public Product() {
        id = "";
        name = "";
        price = 0f;
        origin = "";
    }

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name) {
        this(id);
        this.name = name;
    }

    public Product(String id, String name, float price) {
        this(id, name);
        this.price = price;
    }

    public Product(String id, String name, float price, String origin) {
        this(id, name, price);
        this.origin = origin;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


}
