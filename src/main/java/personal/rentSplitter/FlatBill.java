package personal.rentSplitter;

public class FlatBill extends Bill {
    public FlatBill(String billName, Double totalCost, Integer dateDueToCompany, Integer dateDueToPayer,
            Roommate payer, String company) {
        this.billName = billName;
        this.totalCost = totalCost;
        this.dateDueToPayer = dateDueToPayer;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
        this.company = company;
    }

    public FlatBill(String billName, Double totalCost, Integer dateDueToCompany, Integer dateDueToPayer,
            Roommate payer) {
        this.billName = billName;
        this.totalCost = totalCost;
        this.dateDueToPayer = dateDueToPayer;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
        this.company = null;
    }

    public FlatBill(String billName, Double totalCost, Integer dateDueToCompany, Roommate payer) {
        this.billName = billName;
        this.totalCost = totalCost;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
    }

    public FlatBill(Double totalCost, Integer dateDueToCompany, Roommate payer) {
        this.totalCost = totalCost;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
    }
}
