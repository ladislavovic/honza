package cz.kul.prime.xlsx;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

public class XLXSCellIteratorTest {

    static final String FILE_1 = "testFile1.xlsx";
    private static final String FILE_2 = "testFile2.xlsx";

    @Test
    public void test_shouldWorkWithInputStream() throws IOException {
        try (InputStream input = XLXSCellIteratorTest.class.getClassLoader().getResourceAsStream(FILE_1)) {
            XLSXCellIterator i = new XLSXCellIterator(input);
            Cell cell = i.next();
            assertEquals(0, cell.getRowIndex());
            assertEquals(0, cell.getColumnIndex());
            assertEquals("value1", cell.getStringCellValue());
        }
    }

    @Test
    public void test_shouldWorkWithFileName() throws IOException {
        try {
            TmpDir.copyFromClasspathToTmp(FILE_1);
            String path = TmpDir.getAbsolutePath(FILE_1);
            XLSXCellIterator i = new XLSXCellIterator(path);
            Cell cell = i.next();
            assertEquals(0, cell.getRowIndex());
            assertEquals(0, cell.getColumnIndex());
            assertEquals("value1", cell.getStringCellValue());
        } finally {
            TmpDir.remove(FILE_1);
        }
    }

    @Test
    public void test_shouldReturnsCellsAccordingToCoordinates() throws IOException {
        try (InputStream input = XLXSCellIteratorTest.class.getClassLoader().getResourceAsStream(FILE_2)) {
            List<Coordinates> coordinates = new ArrayList<Coordinates>();
            coordinates.add(Coordinates.getForRow(1));
            coordinates.add(Coordinates.getForColumn(2));
            coordinates.add(Coordinates.get(0, 1));
            List<String> values = new ArrayList<>();
            XLSXCellIterator i = new XLSXCellIterator(input, coordinates);
            while (i.hasNext()) {
                values.add(i.next().getStringCellValue());
            }
            assertEquals(Arrays.asList("1b", "1c", "2a", "2b", "2c", "3c"), values);
        }
    }

}
