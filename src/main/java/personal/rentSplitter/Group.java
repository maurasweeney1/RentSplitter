package personal.rentSplitter;

import java.util.*;

public class Group {
    ArrayList<Roommate> members;

    Group() {
        members = new ArrayList<Roommate>();
    }

    Group(ArrayList<Roommate> members) {
        this.members = members;
    }

    public void addMembers(Roommate newMember) {
        members.add(newMember);
    }

    public void removeMembers(Roommate oldMember) {
        for (Roommate roommate : members) {
            if (roommate == oldMember) {
                members.remove(oldMember);
            }
        }
    }
}