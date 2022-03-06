package Exercise;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.in;

public class Test {
    public static void main(String[] args) {
        var input = new Scanner(in);
        // lớp ArrayList danh mục
        ArrayList<Category> categories = new ArrayList<>();
        Product product = new Product();
        Category category = new Category();

        var choice = 0;
        do {
            out.println("======================MENU======================");
            out.println("= 1. Thêm danh mục với thông tin sản phẩm      =");
            out.println("= 2. Hiển thị danh mục theo mã                 =");
            out.println("= 3. Cập nhật thông tin sản phẩm               =");
            out.println("= 4. Xóa sản phẩm bất kì trong danh mục        =");
            out.println("= 5. Thống kê tổng giá trị của sản phẩm        =");
            out.println("= 6. Liệt kê sản phẩm xuất xứ từ Trung Quốc    =");
            out.println("= 0. Kết thúc chương trình                     =");
            out.println("================================================");
            out.println("=============> Xin mời lựa chọn <=============== ");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    out.println("========> Chương trình kết thúc !<=========");
                    break;
                case 1:
                    var s = createNewCategory(input, product, categories);
                    if (s) {
                        out.println("======> Thêm mới danh mục và sản phẩm thành công <=====");
                    }
                    break;
                case 2:
                    if (categories.size() > 0) {
                        out.println("Nhập mã danh mục hiển thị : ");
                        input.nextLine();
                        var id = input.nextLine();
                        var isValid = finCategroyById(categories, id);
                        if (isValid != null) {
                            showCategory(isValid);
                        }
                    } else {
                        out.println("======> Danh sách danh mục rỗng <=======");
                    }
                    break;
                case 3:
                    if (categories.size() > 0) {
                        out.println("Nhập mã danh mục cần cập nhật : ");
                        input.nextLine();
                        var categoryId = input.nextLine();
                        out.println("Nhập mã sản phầm cần sửa : ");
                        var productId = input.nextLine();
                        var isValid = updateProductOfCategroy(categories, categoryId, productId);
                        if (isValid) {
                            out.println("=======> Cập nhật danh mục thành công <======");
                        } else {
                            out.println("=======> Cập nhật danh mục thất bại <========");
                        }
                    } else {
                        out.println("=======> Danh sách danh mục rỗng <======");
                    }
                    break;
                case 4:
                    if (categories.size() > 0) {
                        out.println("Nhập mã danh mục : ");
                        input.nextLine();
                        var categoryId = input.nextLine();
                        out.println("Nhập mã sản phẩm cần xóa : ");
                        var productId = input.nextLine();
                        var isSuccess = removeProductById(categories, categoryId, productId);
                        if (isSuccess) {
                            out.println("=======> Xóa mã sản phẩm : " + productId + " thành công <=======");
                        } else {
                            out.println("=======> Xóa mã sản phẩm : " + productId + " thất bại <=======");
                        }
                    } else {
                        out.println("=======> Danh sách danh mục rỗng <======");
                    }
                    break;
                case 5:
                    if (categories.size() > 0) {
                        out.println("Nhập mã danh mục cần tính giá trị : ");
                        input.nextLine();
                        var categoryId = input.nextLine();
                        culcalProductsOfCategory(categories, categoryId);
                    } else {
                        out.println("=====> Danh sách danh mục rỗng <======");
                    }
                    break;
                case 6:
                    if (categories.size() > 0) {
                        showProduct(categories);
                    } else {
                        out.println("=====> Danh sách danh mục rông <=====");
                    }
                    break;
                default:
                    out.println("===> Sai chức năng.Vui lòng nhập lại <====");
                    break;
            }
        } while (choice != 0);
    }

    /**
     * phương thức hiển thị sản phẩm có xuất xứ từ trung quốc
     *
     * @param categories danh mục gốc
     */
    private static void showProduct(ArrayList<Category> categories) {
        out.printf("%-15s%-20s%-15s%-20s%-15s%-20s\n", "Mã DM", "Tên DM", "Mã SP", "Tên SP", "Giá", "Xuất Xứ");
        for (var item : categories) {
            for (int i = 0; i < item.getProducts().size(); i++) {
                if (item.getProducts().get(i).getOrigin().compareToIgnoreCase("Trung Quốc") == 0) {
                    out.printf("%-15s%-20s%-15s%-20s%-15.2f%-20s\n", item.getId(), item.getName(),
                            item.getProducts().get(i).getId(), item.getProducts().get(i).getName(),
                            item.getProducts().get(i).getPrice(), item.getProducts().get(i).getOrigin());
                }
            }
        }
    }

    /**
     * phương thức hiển thị sản phẩm trong danh mục và giá trị
     *
     * @param categories danh mục gốc
     * @param categoryId mã danh mục cần hiển thị
     */
    private static void culcalProductsOfCategory(ArrayList<Category> categories, String categoryId) {
        var category = finCategroyById(categories, categoryId);
        if (category != null) {
            var s = culcalproductOfCategory(category.getProducts());
            showCategory(category);
            out.println("=========");
            out.println("Tổng giá trị : " + s);
        } else {
            out.println("=====> Mã danh mục : " + categoryId + " không hợp lệ <======");
        }
    }

    /**
     * phương thức tính giá trị sản phẩm
     *
     * @param products danh sách sản phẩm gốc
     * @return giá trị
     */
    private static double culcalproductOfCategory(ArrayList<Product> products) {
        var amount = 0;
        for (var item : products) {
            amount += item.getPrice();
        }
        return amount;
    }

    /**
     * phương thức xóa mã sản phẩm theo mã
     * mặc định mỗi mã 1 sản phẩm
     *
     * @param categories danh sách danh mục gốc
     * @param categoryId mã danh mục
     * @param productId  mã sản phẩm cần xóa
     * @return true nếu xóa thành công ,false nếu thất bại
     */
    private static boolean removeProductById(ArrayList<Category> categories, String categoryId, String productId) {
        var category = finCategroyById(categories, categoryId);
        if (category != null) {
            var product = finProductById(category.getProducts(), productId);
            if (product != null) {
                category.getProducts().remove(product);
                return true;
            } else {
                out.println("======> Mã sản phẩm : " + productId + " không hợp lệ <======");
                return false;
            }
        }
        out.println("======> Mã danh mục " + categoryId + " không hợp lệ ");
        return false;
    }

    /**
     * phương thức cập nhật thông tin sản phẩm qua mã
     *
     * @param categories danh sách danh mục gốc
     * @param categoryId mã danh mục cần duyệt
     * @param productId  mã sản phẩm cần duyệt
     * @return true nếu cập nhật thành công,false nếu thất bại
     */
    private static boolean updateProductOfCategroy(ArrayList<Category> categories, String categoryId, String productId) {
        var input = new Scanner(System.in);
        var category = finCategroyById(categories, categoryId);
        if (category == null) {
            out.println("======> Mã danh mục không hợp lệ <=======");
            return false;
        } else {
            var product = finProductById(category.getProducts(), productId);
            if (product != null) {
                out.println("Nhập tên sản phẩm : ");
                product.setName(input.nextLine());
                out.println("Nhập giá : ");
                product.setPrice(Float.parseFloat(input.nextLine()));
                out.println("Nhập xuất xứ : ");
                product.setOrigin(input.nextLine());
                return true;
            } else {
                out.println("=====> Mã thông tin không hợp lệ <======");
                return false;
            }
        }
    }

    /**
     * phương thức tìm kiếm mã sản phẩm theo id
     *
     * @param products  danh sách sản phẩm gốc
     * @param productId mã sản phẩm cần duyệt
     * @return item nếu có ,null nếu k có
     */
    private static Product finProductById(ArrayList<Product> products, String productId) {
        for (var iteam : products) {
            if (iteam.getId().compareToIgnoreCase(productId) == 0) {
                return iteam;
            }
        }
        return null;
    }

    /**
     * phương thức hiển thị danh mục theo dạng cột
     *
     * @param s kiểu Category
     */
    private static void showCategory(Category s) {
        out.printf("%-15s%-20s%-15s%-20s%-15s%-20s\n", "Mã DM", "Tên DM", "Mã SP", "Tên SP", "Giá", "Xuất Xứ");
        for (var item : s.getProducts()) {
            showItem(item, s);
        }
    }

    /**
     * phương thức hiển thị 1 danh mục và sản phẩm
     *
     * @param item sp cần hiển thị
     * @param s    kiểucategory
     */
    private static void showItem(Product item, Category s) {
        out.printf("%-15s%-20s%-15s%-20s%-15.2f%-20s\n", s.getId(), s.getName(), item.getId(), item.getName(),
                item.getPrice(), item.getOrigin());
    }

    /**
     * phương thức thêm mới 1 danh mục
     * mặc định mỗi id 1 danh mục có 1 hoặc nhiều sản phẩm
     * nếu id nhập vào mà đã có ,thì thêm vào id đó sản phẩm
     * nếu id đó chưa có thì tạo
     *
     * @param input      đối tượng lớp scanner
     * @param product    kiểu product(sản phẩm )
     * @param categories danh sách danh mục gốc
     * @return danh mục vừa tạo
     */
    private static boolean createNewCategory(Scanner input, Product product, ArrayList<Category> categories) {
        out.println("Nhập mã danh mục : ");
        input.nextLine();
        var categroyId = input.nextLine();
        out.println("Nhập tên danh mục : ");
        var name = input.nextLine();
        var s = finCategroyById(categories, categroyId);
        Category category = new Category();
        if (s != null) {
            var pro = createNewproduct(product, input);
            s.getProducts().add(pro);
            return true;
        } else {
            var pro1 = createNewproducts(product, input);
            categories.add(new Category(categroyId, name, pro1));
        }

        return true;
    }

    /**
     * phương thức tìm kiếm danh mục theo id
     *
     * @param categories danh mục gốc
     * @param categroyId mã danh mục cần tìm
     * @return item nếu id có ,null nếu 0
     */
    private static Category finCategroyById(ArrayList<Category> categories, String categroyId) {
        for (var item : categories) {
            if (item.getId().compareToIgnoreCase(categroyId) == 0) {
                return item;
            }
        }
        return null;
    }

    /**
     * phương thức tạo mới 1 sản phẩm
     * id mã danh mục đã có
     *
     * @param product kiểu product(sản phẩm )
     * @param input   đối tượng lớp scanner
     * @return đối tượng vừa tạo
     */
    private static Product createNewproduct(Product product, Scanner input) {
        out.println("Nhập mã sản phẩm : ");
        var id = input.nextLine();
        out.println("Nhập tên sản phẩm : ");
        var name = input.nextLine();
        out.println("Nhập giá : ");
        var price = Float.parseFloat(input.nextLine());
        out.println("Nhập nơi xuất xứ : ");
        var origin = input.nextLine();
        return new Product(id, name, price, origin);
    }

    /**
     * phương thức tạo mới 1 sản phẩm
     * id mã danh mục chưa có
     *
     * @param product kiểu product(sản phẩm )
     * @param input   đối tượng lớp scanner
     * @return đối tượng vừa tạo
     */
    private static ArrayList<Product> createNewproducts(Product product, Scanner input) {
        ArrayList<Product> products = new ArrayList<>();
        out.println("Nhập mã sản phẩm : ");
        var id = input.nextLine();
        out.println("Nhập tên sản phẩm : ");
        var name = input.nextLine();
        out.println("Nhập giá : ");
        var price = Float.parseFloat(input.nextLine());
        out.println("Nhập nơi xuất xứ : ");
        var origin = input.nextLine();
        products.add(new Product(id, name, price, origin));
        return products;
    }


}
