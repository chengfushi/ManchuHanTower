package view;

import daomain.Menu;
import daomain.Order;
import service.ManagerService;
import service.MenuService;
import service.OrderService;
import service.UserService;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author Cheng fu
 * @Date 2025/4/1 19:24
 */
public class Main {
    //判断是否退出满汉楼
    private boolean loop = true;
    
    //用户的输出
    private String key = "";
    
    //创建各个服务对象
    private UserService userService = new UserService();
    private ManagerService managerService = new ManagerService();
    private MenuService menuService = new MenuService();
    private OrderService orderService = new OrderService();
    private Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        new Main().mainMenu();
    }
    
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        //循环展示菜单
        while (loop) {
            System.out.println("=======欢迎来到满汉楼======");
            System.out.println("\t\t1.用户登录");
            System.out.println("\t\t2.管理员登录");
            System.out.println("\t\t3.退出满汉楼");
            System.out.println("请输入你的选择：");
            System.out.println("=========================");
            key = scanner.nextLine();
            switch (key) {
                case "1":
                    System.out.println("请输入用户名和密码");
                    System.out.println("用户名：");
                    String username = scanner.nextLine();
                    System.out.println("密码：");
                    String password = scanner.nextLine();
                    if (userService.login(username,password)) {
                        userMenu();
                    }
                    break;
                case "2":
                    System.out.println("请输入用户名和密码");
                    System.out.println("用户名：");
                    String managerName = scanner.nextLine();
                    System.out.println("密码：");
                    String managerPassword = scanner.nextLine();
                    if (managerService.login(managerName,managerPassword)){
                        managerMenu();
                    }
                    break;
                case "3":
                    loop = false;
                    System.out.println("退出满汉楼");
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
        scanner.close();
    }
    public void userMenu() {
        boolean userLoop = true; // 单独控制用户菜单循环
        while (userLoop) {
            System.out.println("=======欢迎来到满汉楼======");
            System.out.println("\t\t1.查看菜品");
            System.out.println("\t\t2.点菜");
            System.out.println("\t\t3.修改密码");
            System.out.println("\t\t4.返回主菜单");
            System.out.println("请输入你的选择：");
            System.out.println("=========================");
            key = scanner.nextLine();
            boolean dishCount = true;
            switch (key) {
                case "1":
                    menuService.showDish();
                    break;
                case "2":
                    String dishName = "";
                    int count = 0;
                    List<Menu> dishList = new ArrayList<>();
                    while (dishCount) {
                        System.out.println("请输入菜品名");
                        dishName = scanner.nextLine();
                        if (menuService.queryDish(dishName)) {
                            dishList.add(new Menu(dishName, count));
                        }
                        System.out.println("是否继续点菜？y/n");
                        String answer = scanner.nextLine();
                        if (!answer.equalsIgnoreCase("y")) {
                            dishCount = false;
                            for (Menu menu : dishList) {
                                orderService.addOrder(menu.getDishName(), menu.getDishCount(), menu.getPrice(), LocalDateTime.now());
                            }
                        }
                    }
                    break; // 修复：添加 break
                case "3":
                    System.out.println("请输入用户名");
                    String username = scanner.nextLine();
                    System.out.println("请输入旧密码");
                    String oldPassword = scanner.nextLine();
                    System.out.println("请输入新密码");
                    String newPassword = scanner.nextLine(); // 修复：改用 nextLine()
                    if (userService.changePassword(username, oldPassword, newPassword)) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("修改失败");
                    }
                    break; // 修复：添加 break
                case "4":
                    userLoop = false; // 仅退出用户菜单，返回主菜单
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }
    public void managerMenu() {
        boolean managerLoop = true;
        while (managerLoop) {
            System.out.println("=======欢迎来到满汉楼======");
            //管理员的登录、修改密码、添加菜品、删除菜品、修改菜品
            System.out.println("\t\t1.查看菜品");
            System.out.println("\t\t2.添加菜品");
            System.out.println("\t\t3.删除菜品");
            System.out.println("\t\t4.修改菜品");
            System.out.println("\t\t5.修改密码");
            System.out.println("\t\t6.返回主菜单");
            System.out.println("请输入你的选择：");
            System.out.println("=========================");
            key = scanner.nextLine();
            switch (key) {
                case "1":
                    menuService.showDish();
                    break;
                case "2":
                    System.out.println("请输入菜品名");
                    String dishName = scanner.nextLine();
                    System.out.println("请输入菜品价格");
                    double price = scanner.nextDouble();
                    if (menuService.addDish(dishName, price)) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("添加失败");
                    }
                    break;
                    case "3":
                        System.out.println("请输入菜品名");
                        dishName = scanner.nextLine();
                        if (menuService.deleteDish(dishName)) {
                            System.out.println("删除成功");
                        } else {
                            System.out.println("删除失败");
                        }
                        break;
                        case "4":
                            System.out.println("请输入菜品名");
                            dishName = scanner.nextLine();
                            System.out.println("请输入菜品价格");
                            price = scanner.nextDouble();
                            if (menuService.updateDish(dishName, price)) {
                                System.out.println("修改成功");
                            } else {
                                System.out.println("修改失败");
                            }
                            break;
                            case "5":
                                break;
            }
        }
    }
}