/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package omarproject;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Luiso
 */
public class DatFileFilter extends FileFilter{
    
    /** Creates a new instance of MyFileFilter */

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.dat)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Estimate Manager Database File (.dat)";
    }
    
}
