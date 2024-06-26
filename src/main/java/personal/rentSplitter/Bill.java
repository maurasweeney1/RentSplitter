package personal.rentSplitter;

public class Bill {
    String billName;
    Double totalCost;
    Integer dateDueToCompany;
    Integer dateDueToPayer;
    Roommate payer;

    public Roommate getPayer() {
        return payer;
    }

    public Integer getDueDateToPayer() {
        return dateDueToPayer;
    }

    public Integer getDueDateToCompany() {
        return dateDueToCompany;
    }

    public String getBillName() {
        return billName;
    }

    public Double getTotalCost() {
        return totalCost;
    }
}
