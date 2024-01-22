package cz.kul.prime.xlsx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StreamUtils;

/**
 * Class can work with machine tmp dir. It is userful when test filesystem operations.
 */
public class TmpDir {

    public static String getTmpDir() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        return tmpDir;
    }

    public static void copyFromClasspathToTmp(String resource) throws IOException {
        String path = FilenameUtils.concat(getTmpDir(), XLXSCellIteratorTest.FILE_1);
        try (InputStream in = XLXSCellIteratorTest.class.getClassLoader().getResourceAsStream(resource);
                FileOutputStream out = new FileOutputStream(path);) {
            StreamUtils.copy(in, out);
        }
    }

    static String getAbsolutePath(String relativePath) {
        String result = FilenameUtils.concat(getTmpDir(), relativePath);
        return result;
    }

    static void remove(String relativePath) {
        File file = new File(getAbsolutePath(relativePath));
        file.delete();
    }
}