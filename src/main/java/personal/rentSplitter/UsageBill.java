package personal.rentSplitter;

public class UsageBill extends Bill {

    public UsageBill(String billName, Double totalCost, Integer dateDueToCompany, Integer dateDueDueToPayer,
            Roommate payer, Integer statementDate, String company) {
        this.billName = billName;
        this.totalCost = totalCost;
        this.dateDueToPayer = dateDueDueToPayer;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
        this.statementDate = statementDate;
        this.company = company;
    }

    public UsageBill(String billName, Double totalCost, Integer dateDueToCompany, Integer dateDueDueToPayer,
            Roommate payer) {
        this.billName = billName;
        this.totalCost = totalCost;
        this.dateDueToPayer = dateDueDueToPayer;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
    }

    public UsageBill(String billName, Double totalCost, Integer dateDueToCompany, Roommate payer) {
        this.billName = billName;
        this.totalCost = totalCost;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
    }

    public UsageBill(Double totalCost, Integer dateDueToCompany, Roommate payer) {
        this.totalCost = totalCost;
        this.dateDueToCompany = dateDueToCompany;
        this.payer = payer;
    }

    public UsageBill(Double totalCost, Integer dateDueToCompany) {
        this.totalCost = totalCost;
        this.dateDueToCompany = dateDueToCompany;
    }

    public void setPayer(Roommate payer) {
        this.payer = payer;
    }

    public void setCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setCoDueDate(Integer dateDueToCompany) {
        this.dateDueToCompany = dateDueToCompany;
    }

    public void setRoommateDueDate(Integer dateDueToPayer) {
        this.dateDueToPayer = dateDueToPayer;
    }

}
