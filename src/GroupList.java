import java.util.ArrayList;
public class GroupList {
    private ArrayList<String> groups;
    private static GroupList groupList;
    private GroupList() {
        groups = new ArrayList<String>();
    }
    public static GroupList getInstance() {
        if (null == groupList) {
            groupList = new GroupList();
        }
        return groupList;
    }
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
