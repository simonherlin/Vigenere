package controller;

import java.io.*;

public class LectureEcritureFichier {

    private File f;

    public void EcritureFichier (String Text, String Path) throws IOException {
        File f = new File (Path);
        FileWriter fw = new FileWriter (f);
        fw.write (Text);
        fw.close();
    }

    public String LectureFichier (String Path) throws IOException {
        f = new File (Path);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
        StringWriter out = new StringWriter();
        int b;
        while ((b=in.read()) != -1)
            out.write(b);
        out.flush();
        out.close();
        in.close();
        return out.toString();
    }
}

