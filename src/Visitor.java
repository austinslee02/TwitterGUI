//Visitor interface for visitor pattern
public interface Visitor {
    int visitMessages(MessageList messageList);
    int visitUsers(UserList userList);
    int visitGroups(GroupList groupList);
}
