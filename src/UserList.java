import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;
    private static UserList userList;
    private UserList() {
        users = new ArrayList<User>();
    }

    public void addUser(User u) {
        users.add(u);
    }
    //Singleton
    public static UserList getInstance() {
        if (null == userList) {
            userList = new UserList();
        }
        return userList;
    }

    public User getUser(String u) {
       for (User x : users) {
           if (x.getName().compareTo(u) == 0) {
               return x;
           }
       }
       return null;
    }
    public void accept(Visitor visitor) {
        visitor.visitUsers(this);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
