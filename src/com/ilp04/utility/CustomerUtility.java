package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.customerServiceImpl;

public class CustomerUtility {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new customerServiceImpl();
        int choice;

        do {
            System.out.println("1. Display All Customers");
            System.out.println("2. Insert a Customer");
            System.out.println("3. Update a Customer's Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    getAllCustomers(customerService);
                    break;
                case 2:
                    insertCustomer(scanner, customerService);
                    break;
                case 3:
                    updateCustomer(scanner, customerService);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void getAllCustomers(CustomerService customerService) {
        ArrayList<Customer> customerList = customerService.getAllcustomers();

        System.out.printf("%-12s %-15s %-15s %-30s %-15s %-15s%n", "CustomerCode", "First Name", "Last Name", "Address", "Phone Number", "Aadhaar No");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (Customer customer : customerList) {
            System.out.printf("%-12d %-15s %-15s %-30s %-15d %-15d%n",
                customer.getCustomerCode(),
                customer.getCustomerFirstname(),
                customer.getCustomerLastname(),
                customer.getAddress(),
                customer.getPhNumber(),
                customer.getAadharNo());
        }
    }

    private static void insertCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        long phoneNumber = scanner.nextLong();
        System.out.print("Enter Aadhaar No: ");
        long aadhaarNo = scanner.nextLong();
        scanner.nextLine();  // Consume newline

        Customer customer = new Customer();
        customer.setCustomerFirstname(firstName);
        customer.setCustomerLastname(lastName);
        customer.setAddress(address);
        customer.setPhNumber(phoneNumber);
        customer.setAadharNo(aadhaarNo);

        int result = customerService.insertIntoCustomer(customer);
        if (result > 0) {
            System.out.println("Customer inserted successfully.");
        } else {
            System.out.println("Failed to insert customer.");
        }
    }

    private static void updateCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("Enter Customer Code to update: ");
        int customerCode = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSelect field to update");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. Phone Number");
        System.out.println("5. Aadhaar No");
        System.out.print("Enter your choice: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine();

        // Fetch the existing customer details first
        ArrayList<Customer> customerList = customerService.getAllcustomers();
        Customer customer = null;
        for (Customer c : customerList) {
            if (c.getCustomerCode() == customerCode) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer with code " + customerCode + " not found.");
            return;
        }

        switch (fieldChoice) {
            case 1:
                System.out.print("Enter new First Name: ");
                String firstName = scanner.nextLine();
                customer.setCustomerFirstname(firstName);
                break;
            case 2:
                System.out.print("Enter new Last Name: ");
                String lastName = scanner.nextLine();
                customer.setCustomerLastname(lastName);
                break;
            case 3:
                System.out.print("Enter new Address: ");
                String address = scanner.nextLine();
                customer.setAddress(address);
                break;
            case 4:
                System.out.print("Enter new Phone Number: ");
                long phoneNumber = scanner.nextLong();
                scanner.nextLine();  // Consume newline
                customer.setPhNumber(phoneNumber);
                break;
            case 5:
                System.out.print("Enter new Aadhaar Number: ");
                long aadhaarNo = scanner.nextLong();
                scanner.nextLine();  // Consume newline
                customer.setAadharNo(aadhaarNo);
                break;
            default:
                System.out.println("Invalid choice. No updates performed.");
                return;
        }

        customer.setCustomerCode(customerCode);  // Ensure customerCode is set

        int result = customerService.updateCustomer(customer);
        if (result > 0) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }
}
