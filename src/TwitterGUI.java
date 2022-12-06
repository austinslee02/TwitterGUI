import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Uses java swing to make a GUI for the admin page
public class TwitterGUI extends JFrame {
    private JTree TreeView;
    private JTextField UserID;
    private JButton AddUser;
    private JTextField GroupID;
    private JButton AddGroup;
    private JButton UserView;
    private JButton ShowGroupTotal;
    private JButton ShowUserTotal;
    private JButton ShowMessagesTotal;
    private JButton ShowPositivePercentage;
    private JPanel AdminView;
    private JLabel UserTotal;
    private JLabel GroupTotal;
    private JLabel MessagesTotal;
    private JLabel PositivePercentage;
    private JButton ValidButton;
    private JLabel ValidText;
    private JButton LastUpdatedUserButton;
    private JLabel LastUpdatedUserText;
    private JLabel LastUpdatedUser;
    private UserList userList;
    private MessageList messageList;
    private GroupList groupList;

    private DefaultMutableTreeNode node;
    public TwitterGUI() {
        add(AdminView);
        //Singleton initializations
        userList = UserList.getInstance();
        messageList = MessageList.getInstance();
        groupList = GroupList.getInstance();
        //Redefines tree to be empty and editable
        DefaultTreeModel model = (DefaultTreeModel)TreeView.getModel();
        node = (DefaultMutableTreeNode)model.getRoot();
        node.removeAllChildren();
        model.reload();
        node = new DefaultMutableTreeNode("Root");
        model.setRoot(node);
        final boolean[] valid = {true};
        //Composite (add component)
        AddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(UserID.getText());
                node.add(new DefaultMutableTreeNode(user));
                if (userList.getUser(UserID.getText())!=null || UserID.getText().contains(" ") ) {
                    valid[0] = false;
                }
                userList.addUser(user);
                UserID.setText("");
            }
        });
        AddGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String group = GroupID.getText();
                Group g = new Group();
                node.add(new DefaultMutableTreeNode("Group: " + group));
                if (groupList.getGroups().contains(group) || group.contains(" ")) {
                    valid[0] = false;
                }
                groupList.addGroup(group);
                GroupID.setText("");
            }
        });
        TreeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        TreeView.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                node = (DefaultMutableTreeNode)TreeView.getLastSelectedPathComponent();
            }
        });
        UserView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserGUI userGUI = new UserGUI(node.getUserObject());
                userGUI.setTitle("User GUI");
                userGUI.pack();
                userGUI.setLocationRelativeTo(null);
                userGUI.setVisible(true);
            }
        });
        ShowUserTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTotal.setText("Number of Users: " + userList.getUsers().size());
            }
        });

        ShowGroupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupTotal.setText("Number of Groups: " + groupList.getGroups().size());
            }
        });
        ShowMessagesTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessagesTotal.setText("Number of Messages: " + messageList.getMessages().size());
            }
        });

        ShowPositivePercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PositivePercentage.setText("Positive Percentage: " + String.format("%.2f", messageList.getPositivePercentage()) + "%");
            }
        });
        ValidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (valid[0]) {
                    ValidText.setText("Valid IDs");
                }
                else {
                    ValidText.setText("Invalid IDs");
                }
            }
        });
        LastUpdatedUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LastUpdatedUserText.setText("Last Updated User: " + userList.getLastUpdatedUser());
            }
        });
    }
    public User getUserID(String u) {
        return userList.getUser(u);
    }
    public void setLastUpdatedUser(String u) {
        LastUpdatedUser.setText("Last Updated User: " + u);
    }
}
