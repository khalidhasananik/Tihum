import java.io.*;

import java.util.*;

// Caller for food items.
interface FoodItem {
    String getName();

    void setName(String name);

    double getPrice();

    void setPrice(double price);

    double getDiscountedPrice();
}

// implementation of burger under food items.
class Burger implements FoodItem {
    private String name;
    private double price;

    public Burger(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 0.9; // Apply 10% discount
    }
}

// implementation of Fries under food items.
class Fries implements FoodItem {
    private String name;
    private double price;

    public Fries(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 1.0; // Apply 10% discount
    }
}

// implementation of sides/add ons under food items.
class AddOns implements FoodItem {
    private String name;
    private double price;

    public AddOns(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 1.0; // Apply 10% discount
    }
}

// implementation of Drinks under food items.
class Drinks implements FoodItem {
    private String name;
    private double price;

    public Drinks(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getDiscountedPrice() {
        return getPrice() * 1.0; // Apply 10% discount
    }
}

// Start of the user class.
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

// Admin class under User. //Here I have applied concepts of Polymorphism and
// Inheritance.
class Admin extends User {
    private String password;

    public Admin(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// Deliveryman class under User
class DeliveryMan extends User {
    private String phoneNumber;

    public DeliveryMan(String name, String phoneNumber) {
        super(name);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static DeliveryMan loadDeliveryManDetails(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String name = scanner.nextLine();
            String phoneNumber = scanner.nextLine();
            return new DeliveryMan(name, phoneNumber);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load Customer details.");
            return null;
        }
    }
}

// Customer class under User
class Customer extends User {
    private String address;
    private String phoneNumber;
    List<FoodItem> currentOrder;
    private String assignedDeliveryMan;

    public Customer(String name, String address, String phoneNumber, String deliveryMan) {
        super(name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.currentOrder = new ArrayList<>();
        this.assignedDeliveryMan = deliveryMan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setdeliveryMan(String deliveryMan) {
        this.assignedDeliveryMan = deliveryMan;
    }

    public String getdeliveryMan() {
        return assignedDeliveryMan;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // method to add an order
    public void addToOrder(FoodItem item) {
        currentOrder.add(item);
        System.out.println("Added " + item.getName() + " to your order.");
    }

    // method to remove an order
    public void removeFromOrder(FoodItem item) {
        currentOrder.remove(item);
        System.out.println("Removed " + item.getName() + " from your order.");
    }

    // method to calculate the total cost after adding discounts.
    public double calculateTotalCost() {
        double totalCost = 0;
        for (FoodItem item : currentOrder) {
            totalCost += item.getDiscountedPrice();
        }
        return totalCost;
    }

    // method to confirm an order
    public void placeOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Your order is empty.");
        } else {
            System.out.println("Order placed. Total cost: " + calculateTotalCost() + "Tk.");
            // currentOrder.clear();
        }
    }

    // method to view order that has been added to cart.
    public void viewOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Oops! Your cart is empty.");
        } else {
            System.out.println("Current Order:");
            for (FoodItem item : currentOrder) {
                System.out.println(item.getName() + " - " + item.getDiscountedPrice() + "Tk.");
            }
            System.out.println("Total cost: " + calculateTotalCost() + "Tk.");
        }
    }

    // method that cancels an order
    public void cancelOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("You Haven't ordered anything yet! ");
        } else {
            currentOrder.clear();
            System.out.println("Your order has been cancelled.");
        }
    }

    public DeliveryMan getAssignedDeliveryMan() {

        setdeliveryMan(assignedDeliveryMan);

        return DeliveryMan.loadDeliveryManDetails(assignedDeliveryMan);
    }

    // Method to clear the assigned delivery man
    public void clearAssignedDeliveryMan() {
        assignedDeliveryMan = null;
    }

    // method that assigns a delivery man
    public void assignDeliveryMan(DeliveryMan deliveryMan) {

        assignedDeliveryMan = deliveryMan.getName();
        System.out.println("Delivery man assigned: " + deliveryMan.getName() + " " + deliveryMan.getPhoneNumber());
    }

    // method that tracks the delivery man's info
    public void trackOrder() {
        if (assignedDeliveryMan != null) {
            System.out.println("Your order is being delivered by " + assignedDeliveryMan);
            System.out.println("Your order will be delivered soon!");
        } else {
            System.out.println("Oops! No delivery man assigned yet :(.");
        }
    }

    // method to save customer details.
    public void saveCustomerDetails(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(getName());
            writer.println(getAddress());
            writer.println(getPhoneNumber());
            writer.println(getdeliveryMan());

            for (FoodItem item : currentOrder) {
                writer.println(item.getName() + ":" + item.getPrice());
            }

            // clear the current order

            // currentOrder.clear();

        } catch (IOException e) {
            System.out.println("Failed to save your details.");
        }
    }

    // method to load customer detail from a file
    public static Customer loadCustomerDetails(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String name = scanner.nextLine();
            String address = scanner.nextLine();
            String phoneNumber = scanner.nextLine();
            String deliveryManName = scanner.nextLine();

            return new Customer(name, address, phoneNumber, deliveryManName);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load Customer details.");
            return null;
        }
    }
}

class Main {
    private static List<Customer> customers;
    private static List<Admin> admins;
    private static List<DeliveryMan> deliveryMen;

    public static void main(String[] args) {
        customers = new ArrayList<>();
        admins = new ArrayList<>();
        deliveryMen = new ArrayList<>();

        // admin log in database
        Admin admin1 = new Admin("admin1", "burger");

        admins.add(admin1);

        // delivery man name database
        DeliveryMan deliveryMan1 = new DeliveryMan("Naruto Uzumaki", "+8801711223344");
        DeliveryMan deliveryMan2 = new DeliveryMan("Kakashi Hatake", "+8801911223344");
        DeliveryMan deliveryMan3 = new DeliveryMan("Sasuke Uchiha", "+8801511223344");

        deliveryMen.add(deliveryMan1);
        deliveryMen.add(deliveryMan2);
        deliveryMen.add(deliveryMan3);

        Scanner scanner = new Scanner(System.in);
        // log in menu
        while (true) {
            System.out.println("|*** BURGERILICIOUS ***|");
            System.out.println();
            System.out.println("1. Register as a customer");
            System.out.println("2. Login as an admin");
            System.out.println("3. Quit");
            System.out.println("-------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // calls out method for handling customer.
                handleCustomerRegistration(scanner);
            } else if (choice == 2) {
                // calls out method for admin log in.
                handleAdminLogin(scanner);
            } else if (choice == 3) {
                System.out.println("Thank you for visiting BURGERILICIOUS. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // method for handling customer registration
    private static void handleCustomerRegistration(Scanner scanner) {
        System.out.print("Please enter your name: ");
        String name = scanner.next();

        System.out.print("Your address: ");
        scanner.nextLine();
        String address = scanner.nextLine();

        System.out.print("Your phone number: ");
        String phoneNumber = scanner.next();

        Customer customer = new Customer(name, address, phoneNumber, null);
        customers.add(customer);
        System.out.println("Registration successful." + "\nWelcome, " + name + "!");
        handleCustomerMenu(customer, scanner);
    }

    // method for handling admin login.
    private static void handleAdminLogin(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        Admin admin = findAdminByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + admin.getName() + "!");
            handleAdminMenu(admin, scanner);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    // method which views customer menu.
    private static void handleCustomerMenu(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("|__BURGERILICIOUS__||");
            System.out.println(
                    "\n1: View Menu \t2: Remove item from order\t3: View Cart\t4: Place Order\n5: Cancel Order \t6: Assign Delivery Man \t7: Track Order\t8: Save My Details\t9: Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // method call for adding orders.
                handleAddToOrder(customer, scanner);
            } else if (choice == 2) {
                // method call for removing orders.
                handleRemoveFromOrder(customer, scanner);
            } else if (choice == 3) {
                // method call from customer class to view orders.
                customer.viewOrder();
            } else if (choice == 4) {
                // method call from customer class to place orders.
                customer.placeOrder();
            } else if (choice == 5) {
                // method call from customer class to cancel orders.
                customer.cancelOrder();
            } else if (choice == 6) {
                // method call for assigning delivery man.
                handleAssignDeliveryMan(customer, scanner);

            } else if (choice == 7) {
                // method call from customer class to track order.
                customer.trackOrder();
            } else if (choice == 8) {
                // method to save customer details.
                handleSaveCustomerDetails(customer, scanner);
                System.out.println("Saved Your Details.");
            } else if (choice == 9) {
                System.out.println("Returning to main menu.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    // method to handle admin menu.
    private static void handleAdminMenu(Admin admin, Scanner scanner) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1: View customer details" + "\t 2: View delivery men");
            System.out.println("3: Remove customer order" + "\t 4: Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                handleViewCustomerDetails(scanner);
            } else if (choice == 2) {
                handleViewDeliveryMen();
            } else if (choice == 3) {
                handleRemoveCustomerOrder(scanner);
            } else if (choice == 4) {
                System.out.println("Returning to main menu.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // food menu
    private static void handleAddToOrder(Customer customer, Scanner scanner) {
        System.out.println("Available Food Items:");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("*| CRAFTED BURGERS |*: ");
        System.out.println("1: Chicken Signature - 259tk");
        System.out.println("2: Beef Katsu - 329tk" + "\t 3: Chicken Katsu - 309tk");
        System.out.println("4: 365 DAYS a burger - 299tk");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("*| CRAFTED SIDES |*");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("5: Crispy Fried Chicken (2pc) - 229tk");
        System.out.println("6: Naga Wings (6pc) - 209tk" + "\t 7: BBQ Wings (6pc) - 219tk");
        System.out.println("8: Regular Fries (M) - 89tk" + "\t 9: Regular Fries (L) - 109tk");
        System.out.println("10: Spicy Cheese Nuggets (6pc) - 249tk ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("*| LIQUIDS |*");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("11: Green Apple Fizz - 109tk" + "\t 12: Strawberry Mojito - 109tk");
        System.out.println("13: Choco Cold Coffee - 99tk" + "\t 14: Soft Drinks - 69tk");
        System.out.println("15: Mineral Water - 20tk");
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.print("Choose item (0 TO GO BACK):");

        int choice = scanner.nextInt();
        // option to choose items.
        FoodItem item;

        switch (choice) {

            case 1:
                item = new Burger("Chicken Signature", 259);
                break;
            case 2:
                item = new Burger("Beef Katsu", 329);
                break;
            case 3:
                item = new Burger("Chciken Katsu", 309);
                break;
            case 4:
                item = new Burger("365D", 299);
                break;
            case 5:
                item = new AddOns("Crispy Fried Chicken(2pc)", 229);
                break;
            case 6:
                item = new AddOns("Naga Wings (6pc)", 209);
                break;
            case 7:
                item = new AddOns("BBQ Wings (6pc)", 219);
                break;
            case 8:
                item = new AddOns("Regular Fries (M)", 89);
                break;
            case 9:
                item = new AddOns("Regular Fries (L)", 109);
                break;
            case 10:
                item = new AddOns("Spicy Cheese Nuggets (6pc)", 249);
                break;
            case 11:
                item = new Drinks("Green Apple Fizz", 109);
                break;
            case 12:
                item = new Drinks("Strawberry Mojito", 109);
                break;
            case 13:
                item = new Drinks("Choco Cold Coffee", 99);
                break;
            case 14:
                item = new Drinks("Soft Drinks", 69);
                break;
            case 15:
                item = new Drinks("Mineral Water", 20);
                break;
            default:
                System.out.println("Returning to main menu...");
                return;
        }

        customer.addToOrder(item);
    }

    // method for handling remove order.
    private static void handleRemoveFromOrder(Customer customer, Scanner scanner) {
        if (customer.currentOrder.isEmpty()) {
            System.out.println("Your order is already empty.");
            return;
        }

        System.out.println("Current Order:");
        int index = 1;
        for (FoodItem item : customer.currentOrder) {
            System.out.println(index + ". " + item.getName() + " - " + item.getDiscountedPrice() + "tk");
            index++;
        }

        System.out.print("Enter the number of the item to be removed: ");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= customer.currentOrder.size()) {
            FoodItem item = customer.currentOrder.get(choice - 1);
            customer.removeFromOrder(item);
        } else {
            System.out.println("Invalid choice. Item not removed from order.");
        }
    }

    private static void handleAssignDeliveryMan(Customer customer, Scanner scanner) {
        if (deliveryMen.isEmpty()) {
            System.out.println("No delivery men available at the moment.");
            return;
        }

        System.out.println("Delivery Men:");
        int index = 1;
        for (DeliveryMan deliveryMan : deliveryMen) {
            System.out.println(index + ". " + deliveryMan.getName() + " " + deliveryMan.getPhoneNumber());
            index++;
        }

        System.out.print("Enter index of any delivery man to be assigned: ");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= deliveryMen.size()) {
            DeliveryMan deliveryMan = deliveryMen.get(choice - 1);
            customer.setdeliveryMan(deliveryMan.getName());
        } else {
            System.out.println("Invalid choice. Delivery man not assigned.");
        }
    }

    // method to save customer inside file.
    private static void handleSaveCustomerDetails(Customer customer, Scanner scanner) {
        System.out.print("Enter file name to save customer details: ");
        String fileName = scanner.next();
        customer.saveCustomerDetails(fileName);
        System.out.println("Customer details saved successfully.");
    }

    // method to call for viewing customer details
    private static void handleViewCustomerDetails(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        Customer customer = findCustomerByName(name);
        if (customer != null) {

            System.out.println("\nCustomer Details:");
            System.out.println("Name: " + customer.getName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Delivery Man : " + customer.getdeliveryMan());

            System.out.println("Current Order:");

            if (customer.currentOrder.isEmpty()) {
                System.out.println("Empty");
            } else {
                int index = 1;
                for (FoodItem item : customer.currentOrder) {
                    System.out.println(index + ". " + item.getName() + " - " + item.getDiscountedPrice() + "tk");
                    index++;
                }
            }

            System.out.println();

        } else {
            System.out.println("Customer not found.");
        }
    }

    // method for viewing delivery man details.
    private static void handleViewDeliveryMen() {
        System.out.println("Delivery Men:");
        for (DeliveryMan deliveryMan : deliveryMen) {
            System.out.println("Name: " + deliveryMan.getName());
            System.out.println("Phone Number: " + deliveryMan.getPhoneNumber());
            System.out.println();
        }

    }

    // method to remove customer ordered stored inside file.
    private static void handleRemoveCustomerOrder(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        Customer customer = findCustomerByName(name);
        if (customer != null) {
            customer.cancelOrder();
        } else {
            System.out.println("Customer not found.");
        }
    }

    // method to check admin details.
    private static Admin findAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getName().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    // method to find customer by their names.
    private static Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

}
