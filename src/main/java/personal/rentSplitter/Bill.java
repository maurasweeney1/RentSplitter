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

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setDateDueToCompany(Integer dateDueToCompany) {
        this.dateDueToCompany = dateDueToCompany;
    }

    public void setDateDueToPayer(Integer dateDueToPayer) {
        this.dateDueToPayer = dateDueToPayer;
    }

    public void setPayer(Roommate payer) {
        this.payer = payer;
    }

    public void setStatementDate(Integer statementDate) {
        this.statementDate = statementDate;
    }
}
