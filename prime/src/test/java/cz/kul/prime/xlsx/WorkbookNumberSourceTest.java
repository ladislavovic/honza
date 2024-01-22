package cz.kul.prime.xlsx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;
import org.mockito.Mockito;

public class WorkbookNumberSourceTest {

    @Test
    public void testGetNumbers_shouldReturnNumbersFromNumberCells() {
        Cell mock = Mockito.mock(Cell.class);
        doReturn(CellType.NUMERIC).when(mock).getCellType();
        doReturn(10.2).when(mock).getNumericCellValue();
        WorkbookNumberSource source = new WorkbookNumberSource(Arrays.asList(mock).iterator());
        assertEquals(Arrays.asList(10L), source.getNumbers());
    }

    @Test
    public void testGetNumbers_shouldReturnNumbersFromStringCells() {
        Cell mock1 = Mockito.mock(Cell.class);
        doReturn(CellType.STRING).when(mock1).getCellType();
        doReturn("10").when(mock1).getStringCellValue();

        Cell mock2 = Mockito.mock(Cell.class);
        doReturn(CellType.STRING).when(mock2).getCellType();
        doReturn("11.5").when(mock2).getStringCellValue();

        Cell mock3 = Mockito.mock(Cell.class);
        doReturn(CellType.STRING).when(mock3).getCellType();
        doReturn("not a number").when(mock3).getStringCellValue();

        WorkbookNumberSource source = new WorkbookNumberSource(Arrays.asList(mock1, mock2, mock3).iterator());
        assertEquals(Arrays.asList(10L, 11L), source.getNumbers());
    }

    @Test(expected = ArithmeticException.class)
    public void testGetNumbers_shouldThrowAnExceptionIfTheNumberIsGreaterThan_Long_MAX_VALUE() {
        String veryBigStrNum = "10223372036854775807";
        Cell mock1 = Mockito.mock(Cell.class);
        doReturn(CellType.STRING).when(mock1).getCellType();
        doReturn(veryBigStrNum).when(mock1).getStringCellValue();

        WorkbookNumberSource source = new WorkbookNumberSource(Arrays.asList(mock1).iterator());
        source.getNumbers();
    }

    @Test
    public void testGetNumbers_shouldReturnNothingFromBlankCells() {
        Cell mock = Mockito.mock(Cell.class);
        doReturn(CellType.BLANK).when(mock).getCellType();
        WorkbookNumberSource source = new WorkbookNumberSource(Arrays.asList(mock).iterator());
        assertTrue(source.getNumbers().isEmpty());
    }

    @Test
    public void testGetNumbers_shouldBePossibleCallItMultipleTimes() {
        Cell mock = Mockito.mock(Cell.class);
        doReturn(CellType.NUMERIC).when(mock).getCellType();
        doReturn(10.2).when(mock).getNumericCellValue();
        WorkbookNumberSource source = new WorkbookNumberSource(Arrays.asList(mock).iterator());
        List<Long> numbers1 = source.getNumbers();
        List<Long> numbers2 = source.getNumbers();
        assertEquals(Arrays.asList(10L), numbers1);
        assertEquals(numbers1, numbers2);

    }
}
