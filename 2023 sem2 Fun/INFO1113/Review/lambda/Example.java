interface Service{
    public void NiceService(String name);
}

interface Service_mutiple_abstract_methods{
    public void NiceService(String name);
    public void RegularService();
}


public class Example {
    public static void main(String[] args){
        Service s = (String name) -> System.out.println("Hi " + name + " , how much did you get in INFO1113!");
        s.NiceService("Ba");
        /*
        this is a bad example cause lambda can only implements interfaces which have 1 abstract method.
        try {
            Service_mutiple_abstract_methods bad_s = (String name )-> System.out.println("Hi " + name + " , how much did you get in INFO1113!");
            s.NiceService();
        }catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            // Handle exception here
        }
        */
    }
}