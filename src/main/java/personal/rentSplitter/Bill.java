package personal.rentSplitter;

public class Bill {
    String billName;
    String company;
    Double totalCost;
    Integer dateDueToCompany;
    Integer dateDueToPayer;
    Roommate payer;
    Integer statementDate;

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

    public String getCompany() {
        return company;
    }

    public Integer getStatementDate() {
        return statementDate;
    }
}
