public class Group {
    private long creationTime;
    public Group () {
        creationTime = System.currentTimeMillis();
        System.out.println("Group Creation Time: " + creationTime);
    }
    public long getCreationTime () {
        return creationTime;
    }
    public void setCreationTime(long t) {
        creationTime = t;
    }
}
