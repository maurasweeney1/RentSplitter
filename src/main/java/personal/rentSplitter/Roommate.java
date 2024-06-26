package personal.rentSplitter;

public class Roommate {
    String name;
    Integer phoneNo;
    Boolean isHome;

    public Roommate() {
        name = "Roommate";
        isHome = true;
    }

    public Roommate(String name) {
        this.name = name;
        isHome = true;
    }

    public Roommate(String name, Integer phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
        isHome = true;
    }

    public String getRoommateName() {
        return name;
    }

    public void setifHome(Boolean isHome) {
        this.isHome = isHome;
    }

    public Boolean checkIfHome() {
        return isHome;
    }
}