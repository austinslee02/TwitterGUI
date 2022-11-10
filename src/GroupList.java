import java.util.ArrayList;
//Contains all groups
public class GroupList {
    private ArrayList<String> groups;
    private static GroupList groupList;
    private GroupList() {
        groups = new ArrayList<String>();
    }
    //Singleton pattern
    public static GroupList getInstance() {
        if (null == groupList) {
            groupList = new GroupList();
        }
        return groupList;
    }
    //Visitor pattern
    public void accept(Visitor visitor) {
        visitor.visitGroups(this);
    }
    public ArrayList<String> getGroups() {
        return groups;
    }
    public void addGroup(String group) {
        groups.add(group);
    }
}
