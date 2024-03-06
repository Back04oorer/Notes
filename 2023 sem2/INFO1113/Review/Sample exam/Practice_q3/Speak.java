public interface Speak extends eat{
    public default void speak(){
        System.out.println("I eat some food");
    }
    public void speak();
}