package conect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author isacc-PC
 */
public class respaldar {

    private static final String clave = "XIQcpOhKJiD0Lyda8x4XNrfWNmEuUZjweu1nmHiQ";

    public static void respaldar(String ruta) {
        try {
            Process p;
            p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\bd\\bin\\mysqldump -u root -p" + clave + " -B nucleocomunal");

            InputStream is = p.getInputStream();
            FileOutputStream fos = new FileOutputStream(ruta + ".sql");
            byte[] buffer = new byte[1000];

            //System.out.println(is.);
            int leer = is.read(buffer);
            while (leer > 0) {
                fos.write(buffer, 0, leer);
                leer = is.read(buffer);
            }
            fos.close();
            JOptionPane.showMessageDialog(null, "El respaldo se creo sin problema alguno :))");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(respaldar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
