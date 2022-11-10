import java.util.ArrayList;
public class MessageList extends Subject {
    private ArrayList<String> messages;
    private static MessageList messageList;
    public double positiveCount;
    public User lastUser;
    private MessageList() {
        messages = new ArrayList<String>();
        positiveCount = 0;
    }
    public static MessageList getInstance() {
        if (null == messageList) {
            messageList = new MessageList();
        }
        return messageList;
    }
    public void accept(Visitor visitor) {
        visitor.visitMessages(this);
    }
    public ArrayList<String> getMessages() {
        return messages;
    }
    public void addMessage(String message, User user) {
        messages.add(message);
        lastUser = user;
        if (message.toLowerCase().contains("good") || message.toLowerCase().contains("positive") || message.toLowerCase().contains("happy")) {
            positiveCount+=1;
        }
        notifyObservers();
    }
    public String getMessage() {
        return messages.get(messages.size()-1);
    }
    public User getUser() {
        return lastUser;
    }
    public double getPositivePercentage() {
        return positiveCount/(double)messages.size() * 100;
    }
}
