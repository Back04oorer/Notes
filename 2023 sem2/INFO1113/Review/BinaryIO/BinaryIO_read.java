import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BinaryIO_read {
    public static void main(String[] args) {
        try {
            FileInputStream f = new FileInputStream("file.bin");
            DataInputStream input = new DataInputStream(f);
            System.out.println(input.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}