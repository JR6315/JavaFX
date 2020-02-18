import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFile {
	public static void main (String[] args) {
		StringBuffer sb = new StringBuffer("TEST Ajout dans le fichier");
		try (
			FileOutputStream fos = new FileOutputStream("test.dat")){
			fos.write(sb.toString().getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
