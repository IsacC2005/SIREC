
package conect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author isacc-PC
 */
public class restaurar {
    
    private static final String clave = "XIQcpOhKJiD0Lyda8x4XNrfWNmEuUZjweu1nmHiQ";
    
    public static void restaurar (String archivo){
        try {
            String arch = System.getProperty("os.arch");
            System.out.println("Arquitectura del sistema: " + arch);
            Process p;
                p = Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\bd\\bin\\mysql -u root -p"+clave+" -B nucleocomunal");
            OutputStream os = p.getOutputStream();
            FileInputStream fis= new FileInputStream(archivo);
            
            byte [] buffer = new byte[1000];
            int leer = fis.read(buffer);
            
            while(leer > 0){
                os.write(buffer, 0 , leer);
                leer = fis.read(buffer);
            }
            os.flush();
            os.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "la base de datos fue restaurada sin problemas :))");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
