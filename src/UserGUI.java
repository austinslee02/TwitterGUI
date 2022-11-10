import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class UserGUI extends JFrame implements Observer {
    private JButton followUserButton;
    private JTextField FollowUsername;
    private JList CurrentFollowing;
    private JTextArea TweetMessage;
    private JButton PostTweet;
    private JList NewsFeed;
    private JTextField Username;
    private JPanel UserView;
    private JLabel NewsFeedLabel;
    private JLabel CurrentFollowingLabel;
    private User user;
    private TwitterGUI twitterGUI;
    private UserList userList;
    DefaultListModel following;
    private MessageList messageList;
    DefaultListModel messages;

    public UserGUI(Object u) {
        add(UserView);
        messageList = MessageList.getInstance();
        userList = UserList.getInstance();
        user = userList.getUser(u.toString());
        following = new DefaultListModel();
        messages = new DefaultListModel();
        CurrentFollowing.setModel(following);
        NewsFeed.setModel(messages);
        Username.setText("User: " + user.getName());
        messageList.attach(this);
        followUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.addFollowing(userList.getUser(FollowUsername.getText()));
                userList.getUser(FollowUsername.getText()).addFollower(user);
                following.addElement(userList.getUser(FollowUsername.getText()));
                FollowUsername.setText("");
            }
        });

        PostTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageList.addMessage(TweetMessage.getText(), user);
                messages.addElement(user.toString() + ": " + TweetMessage.getText());
                TweetMessage.setText("");
            }
        });
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof MessageList) {
            if (following.contains(((MessageList)subject).getUser())) {
                messages.addElement((((MessageList)subject).getUser().toString() + ": " + ((MessageList)subject).getMessage()));
            }
        }
    }
}
