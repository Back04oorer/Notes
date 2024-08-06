import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BinaryIO_write{
    public static void main(String[] args){
        try{
            FileOutputStream f  = new FileOutputStream("file.bin");
            DataOutputStream output = new DataOutputStream(f);
            output.writeUTF("Fuck info1112");
            output.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}