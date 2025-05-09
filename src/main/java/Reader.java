import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Reader {

    private static Reader reader;
    public static int count=0;

    private Reader(){}

    public static Reader generateReader() {
        if(reader == null) {
            reader = new Reader();
            count++;
        }
        return reader;
    }

    public String dataEntry(String receivedData){
        InputStreamReader data = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(data);

        try{
            receivedData = buffer.readLine();
        }catch(IOException ioe){
            System.out.println("Entrada inválida.");
        }
        return receivedData;

    }
    
}
