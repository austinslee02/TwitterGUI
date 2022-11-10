import java.util.ArrayList;
//Class for each user
//Contains the users ID, who they are following, and their followers
public class User {
    private String name;
    private ArrayList<User> following;

    private ArrayList<User> followers;
    public User(String n) {
        name = n;
        following = new ArrayList<User>();
        followers = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public void addFollowing(User u) { following.add(u); }

    public void addFollower(User u) { followers.add(u); }

    public String toString() {
        return name;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }
}
