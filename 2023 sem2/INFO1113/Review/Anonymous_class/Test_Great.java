interface Great {
    public void great();
}

public class Test_Great implements Great{
    public void great(){
        System.out.println("waibibabu");
    }
    public void Say() {
        Great Faker = new Great() {
            public void great() {
                System.out.println("Faker: 들리나요?");
            }
        };

        Great Oner = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Oner: 들을 수");
            }
        };

        Great Zeus = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Zeus: 들을 수");
            }
        };

        Great Gumayusi = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Gumayusi: 들을 수");
            }
        };

        Great Keria = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Keria: 들을 수");
            }
        };

        Great Tarzan = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Tarzan: 들을 수");
            }
        };

        Great Scout = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Scout: 들을 수");
            }
        };

        Great Hang = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Hang: 听得到(谷歌实时翻译已开启)");
            }
        };

        Great Zika = new Great() {
            @java.lang.Override
            public void great() {
                System.out.println("Zika: 听得到(谷歌实时翻译已开启)");
            }
        };

        Faker.great();
        Oner.great();
        Gumayusi.great();
        Zeus.great();
        Keria.great();

        Scout.great();
        Tarzan.great();
        Hang.great();
        Zika.great();



    }

    public static void main(String[] args){
        Test_Great t = new Test_Great();
        t.Say();
    }
}