package personal.rentSplitter;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class RentSplitter {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            ArrayList<Roommate> roommatesList = new ArrayList<>();
            ArrayList<Bill> billsList = new ArrayList<>();
            String userInput;

            // add each person to the list of roommates
            Integer i = 1;
            Boolean keepAdding = true;
            Roommate newRoomate;
            do {
                System.out.println("Please enter name of roommate " + i + ": ");
                String name = scan.nextLine().trim().toLowerCase();
                // check if not null, unique
                System.out.println("Please enter " + name + "'s phone number: ");
                Integer phoneNo = Integer.parseInt(scan.nextLine());
                newRoomate = new Roommate(name, phoneNo);
                roommatesList.add(newRoomate);
                System.out.println("Do you have another roommate to add? (yes/no)");
                userInput = scan.nextLine().trim().toLowerCase();
                if ("no".equals(userInput)) {
                    keepAdding = false;
                } else if (!"no".equals(userInput) && !"yes".equals(userInput)) {
                    System.out.println("Please enter either yes or no");
                }
                i++;
            } while (keepAdding);

            System.out.println("Great \nNow please enter the total rent payment: ");
            Double rentTotal = Double.parseDouble(scan.nextLine());
            System.out.println("When is the rent due? Please enter just the day");
            Integer dateRentDue = Integer.parseInt(scan.nextLine());
            System.out.println("What day would you like each roommate to pay? Please enter just the day");
            Integer RentDueToPayer = Integer.parseInt(scan.nextLine());
            System.out.println("Which roommate is paying the rent?");
            Roommate rentPayer = findBillPayer(scan.nextLine().trim().toLowerCase(), roommatesList);
            FlatBill newBill = new FlatBill("Rent", rentTotal, dateRentDue, (dateRentDue - RentDueToPayer), rentPayer);
            billsList.add(newBill);
            do {
                System.out.println("Do you have another bill to add? (yes/no)");
                userInput = scan.nextLine().trim().toLowerCase();
                if ("yes".equals(userInput)) {
                    System.out.println("Please enter the name of the next bill:");
                    String billName = scan.nextLine();
                    System.out.println("Please enter the total amount of the bill:");
                    Double totalAmount = Double.parseDouble(scan.nextLine());
                    System.out.println("Which company should this be paid to?");
                    String company = scan.nextLine();
                    System.out.println("Which roommate is paying this bill?");
                    Roommate payer = findBillPayer(scan.nextLine(), roommatesList);
                    System.out.println("When is this bill due? Please enter just the day");
                    Integer dateDue = Integer.parseInt(scan.nextLine());
                    // check format is correct
                    System.out.println("What day would you like each roommate to pay? Please enter just the day");
                    Integer dateDueToPayer = Integer.parseInt(scan.nextLine());
                    Boolean billCreated = false;
                    while (!billCreated) {
                        System.out.println("Is this bill (a) a flat fee or (b) based on usage (ie. electric, water)");
                        if (scan.nextLine() == "a") {
                            billCreated = true;
                            billsList.add(
                                    new FlatBill(billName, totalAmount, dateDue, dateDueToPayer, payer));
                        } else if (scan.nextLine() == "b") {
                            System.out.println("What day should you get the statement each month?");
                            Integer statementDate = Integer.parseInt(scan.nextLine());
                            billCreated = true;
                            billsList.add(
                                    new UsageBill(billName, totalAmount, dateDue, dateDueToPayer, payer,
                                            statementDate, company));
                        } else {
                            System.out.println("Please enter either \"a\" or \"b\"");
                        }
                    }
                } else if (!"no".equals(userInput)) {
                    System.out.println("Please enter either yes or no");
                }
            } while (!"no".equals(userInput));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static Roommate findBillPayer(String payer, ArrayList<Roommate> roommatesList) {
        Roommate roommatePaying = new Roommate();
        Scanner scan = new Scanner(System.in);
        for (Roommate roommate : roommatesList) { // FIXME: doesnt match even though they do match >:(
            if (roommate.getRoommateName() == payer) {
                roommatePaying = roommate;
                System.exit(0);
            }
        }
        System.out.println("Please name one of the current roommates \nWhich roommate is paying the rent?");
        payer = scan.nextLine();
        findBillPayer(payer, roommatesList);
        scan.close();
        return roommatePaying;
    }

    private void notifyRoommates(ArrayList<Roommate> roommatesList, Bill bill) {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        Integer numRoommates = 0;
        // usage fees
        if (bill instanceof UsageBill) {
            ArrayList<Roommate> roommatesAtHome = new ArrayList<>();
            for (Roommate roommate : roommatesList) {
                numRoommates++;
                if (roommate.checkIfHome()) {
                    roommatesAtHome.add(roommate);
                }
            }
            Integer numRoommatesAtHome = roommatesAtHome.size();
            for (Roommate roommate : roommatesAtHome) {
                System.out.println(roommate.getRoommateName() + " you owe " + bill.getPayer().getRoommateName()
                        + (bill.getTotalCost() / numRoommatesAtHome) + " by " + currentMonth + bill.getDueDateToPayer()
                        + " for " + bill.getBillName());
            }
        }
        // flat fees
        else if (bill instanceof FlatBill) {
            for (Roommate roommate : roommatesList) {
                System.out.println(roommate.getRoommateName() + " you owe " + bill.getPayer().getRoommateName()
                        + (bill.getTotalCost() / numRoommates) + " by " + currentMonth + bill.getDueDateToPayer()
                        + " for " + bill.getBillName());
            }
        } else {
            System.out.println("error in type of bills in notifyRoommates()");
        }
    }

    private void notifyPayer(Roommate payingRoommate, Bill bill, Integer date) {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        // usage fees
        if (bill instanceof UsageBill) {
            // notify for statement
            if (date == bill.getStatementDate()) {
                System.out.println("You should have gotten your statement for this month's " + bill.getBillName()
                        + " bill, make sure to enter in the total cost!");
            }
            if (date == bill.getDueDateToPayer()) {
                // notify when all roommates should have paid
                System.out.println(payingRoommate.getRoommateName()
                        + " your roommates due date has passed, make sure they've all paid you for "
                        + bill.getBillName());
            }
            if (date == bill.getDueDateToCompany()) {
                // notify for payment to company
                if (!bill.getCompany().isEmpty()) {
                    System.out.println(payingRoommate.getRoommateName() + " you owe " + bill.getTotalCost() + " by "
                            + currentMonth + bill.getDueDateToCompany() + " for " + bill.getBillName());
                } else {
                    System.out.println(payingRoommate.getRoommateName() + " you owe " + bill.getTotalCost() + " to "
                            + bill.getCompany() + " by " + currentMonth + bill.getDueDateToCompany() + " for "
                            + bill.getBillName());
                }
            }
        }
        // flat fees
        else if (bill instanceof FlatBill) {
            // notify when all roommates should have paid
            if (date == bill.getDueDateToPayer()) {
                System.out.println(payingRoommate.getRoommateName()
                        + " your roommates due date has passed, make sure they've all paid you for "
                        + bill.getBillName());
                // notify for payment to company
            } else if (date == bill.getDueDateToCompany()) {
                if (!bill.getCompany().isEmpty()) {
                    System.out.println(payingRoommate.getRoommateName() + " you owe " + bill.getTotalCost() + " by "
                            + currentMonth + bill.getDueDateToCompany() + " for " + bill.getBillName());
                } else {
                    System.out.println(payingRoommate.getRoommateName() + " you owe " + bill.getTotalCost() + " to "
                            + bill.getCompany() + " by " + currentMonth + bill.getDueDateToCompany() + " for "
                            + bill.getBillName());
                }
            }
        } else {
            System.out.println("error in type of bills in notifyPayer()");
        }
    }

    private void addNotification(ArrayList<Bill> billsList, ArrayList<Roommate> roommatesList) {
        Scanner scan = new Scanner(System.in);
        Boolean billNotFound = true;
        Bill billToNotifyFor = new Bill();
        // what type of notification do you wanna add
        // for who
        // for what bill
        while (billNotFound) {
            System.out.println("What bill would you like to create a notification for?\n");
            String billName = scan.nextLine().toLowerCase();
            for (Bill bill : billsList) {
                if (billName == bill.getBillName()) {
                    billToNotifyFor = bill;
                    billNotFound = false;
                }
            }
            System.out.println("Please enter one of your current bills");
        }

        while (true) {
            System.out.println(
                    "Would you like to create a notification for \na) all roommates to pay the payer \nb) the payer to pay the company \nc) both\n");
            char userInput = scan.nextLine().toLowerCase().charAt(0);
            switch (userInput) {
                case 'a':
                    notifyRoommates(roommatesList, billToNotifyFor);
                    scan.close();
                    return;
                case 'b':
                    notifyPayer(billToNotifyFor.getPayer(), billToNotifyFor, billToNotifyFor.getDueDateToCompany());
                    scan.close();
                    return;
                case 'c':
                    notifyPayer(billToNotifyFor.getPayer(), billToNotifyFor, billToNotifyFor.getDueDateToCompany());
                    notifyRoommates(roommatesList, billToNotifyFor);
                    scan.close();
                    return;
                default:
                    System.out.println("Please enter either a, b, or c");
                    break;
            }
        }
    }

    private void editRoommate(ArrayList<Roommate> roommateList) {
        Scanner scan = new Scanner(System.in);
        Boolean roommateNotFound = true;
        Roommate roommateToEdit = new Roommate();
        // which roommate

        while (roommateNotFound) {
            System.out.println("Which roommate would you like to edit?");
            String roommateName = scan.nextLine().toLowerCase();
            for (Roommate roommate : roommateList) {
                if (roommate.getRoommateName() == roommateName) {
                    roommateToEdit = roommate;
                    roommateNotFound = false;
                }
            }
            System.out.println("Please enter one of your current roommates' names");
        }
        System.out.println("What would you like to edit? \na) Name \nb) Phone number \nc) Location status");
        char userInput = scan.nextLine().toLowerCase().charAt(0);
        switch (userInput) {
            case 'a':
                System.out.println("Please enter new name:");
                roommateToEdit.setName(scan.nextLine());
                scan.close();
                return;
            case 'b':
                System.out.println("Please enter new phone number:");
                roommateToEdit.setPhoneNo(Integer.parseInt(scan.nextLine()));
                scan.close();
                return;
            case 'c':
                System.out.println("Please enter new location status (either \'home\' or \'not home\'):");
                String status = scan.nextLine().toLowerCase();
                if (status == "home") {
                    roommateToEdit.setifHome(true);
                } else if (status == "not home") {
                    roommateToEdit.setifHome(false);
                }
                scan.close();
                return;
            default:
                System.out.println("Please enter one of the given choices");
                break;
        }
        scan.close();
    }

    private void editBill(ArrayList<Bill> billList, ArrayList<Roommate> roommateList) {
        Scanner scan = new Scanner(System.in);
        Boolean billNotFound = true;
        Bill billToEdit = new Bill();
        Boolean PayerNotFound = true;

        while (billNotFound) {
            System.out.println("Which bill would you like to edit?");
            String billName = scan.nextLine().toLowerCase();
            for (Bill bill : billList) {
                if (bill.getBillName() == billName) {
                    billToEdit = bill;
                    billNotFound = false;
                }
            }
            System.out.println("Please name one of your current bills");
        }
        System.out.println(
                "What would you like to edit? \na) Bill name \nb) Company name \nc) This month's total cost \nd) Date due to company \ne) Date due to payer \nf) Payer \ng) Date statement is recieved");
        char userInput = scan.nextLine().toLowerCase().charAt(0);
        switch (userInput) {
            case 'a':
                System.out.println("Please enter new bill name: ");
                billToEdit.setBillName(scan.nextLine());
                scan.close();
                return;
            case 'b':
                System.out.println("Please enter new company name: ");
                billToEdit.setCompany(scan.nextLine());
                scan.close();
                return;
            case 'c':
                System.out.println("Please enter the total cost for the " + billToEdit.getBillName() + " this month: ");
                billToEdit.setTotalCost(Double.parseDouble(scan.nextLine()));
                scan.close();
                return;
            case 'd':
                System.out.println("Please enter the new day each month the " + billToEdit.getBillName()
                        + " payment is due to the company: ");
                billToEdit.setDateDueToCompany(Integer.parseInt(scan.nextLine()));
                scan.close();
                return;
            case 'e':
                System.out.println("Please enter the new day each month the " + billToEdit.getBillName()
                        + " payment is due to the payer: ");
                billToEdit.setDateDueToPayer(Integer.parseInt(scan.nextLine()));
                scan.close();
                return;
            case 'f':
                while (PayerNotFound) {
                    System.out.println("Please enter the new payer's name: ");
                    String payerName = scan.nextLine();
                    for (Roommate roommate : roommateList) {
                        if (roommate.getRoommateName() == payerName) {
                            billToEdit.setPayer(roommate);
                            PayerNotFound = false;
                        }
                    }
                    System.out.println("Please enter the name of one of your current roommates");
                }
                scan.close();
                return;
            case 'g':
                System.out.println("Please enter the new day each month the bill statement will be recieved: ");
                billToEdit.setStatementDate(Integer.parseInt(scan.nextLine()));
                scan.close();
                return;
            default:
                System.out.println("Please enter one of the given choices");
                break;
        }
        scan.close();
    }
}
