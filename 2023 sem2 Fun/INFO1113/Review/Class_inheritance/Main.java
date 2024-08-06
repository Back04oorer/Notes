public class Main {
    public static void main(String[] args) {


        //
        Animal a1 = new DomesticDog();//Declared as Animal
        System.out.println("a1 is still a "+a1.getClass());
        a1.bark();
        //a1.sits();  invalid(It is a method in DomesticDog,but a1 was declared as an Animal)
        Animal a2 = new Wolf();
        a2.eat();//eat() is a method in Animal Class
       // a2.hunt;  invalid(It is a method in Wolf,but a2 was declared as an Animal)


        Wolf a3 = new Wolf();
        a3.bark();//use the method from SuperClass Animal
        a3.hunt();//use the method of Wolf class
    }
}