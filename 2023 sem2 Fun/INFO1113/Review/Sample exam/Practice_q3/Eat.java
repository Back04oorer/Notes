public interface Eat {
    public default void drink(){System.out.println("I drink milk");};
    public void eat();
}