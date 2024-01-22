package cz.kul.prime.xlsx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import cz.kul.prime.NumberSource;

/**
 * Number source which gets numbers from excel like workbooks. It does not work directly
 * with files, it uses Iterator which returns workbook Cells.
 * 
 * It get numbers from String and Number cells. If the cell contains a content which is
 * not a number, it is skipped. If the number has decimal places, it is round to whole
 * number.
 */
public class WorkbookNumberSource implements NumberSource {

    private Iterator<Cell> iterator;

    private List<Long> numbers;

    public WorkbookNumberSource(Iterator<Cell> iterator) {
        this.iterator = iterator;
    }

    @Override
    public List<Long> getNumbers() {
        if (numbers == null) {
            List<Long> result = new ArrayList<>();
            while (iterator.hasNext()) {
                Cell cell = iterator.next();
                Long number = getNumber(cell);
                if (number != null) {
                    result.add(number);
                }
            }
            numbers = result;
        }
        return numbers;
    }

    private Long getNumber(Cell cell) {
        Long result = null;
        switch (cell.getCellType()) {
        case NUMERIC:
            double number = cell.getNumericCellValue();
            result = (long) number;
            break;
        case STRING:
            String val = cell.getStringCellValue();
            try {
                double doubleVal = Double.parseDouble(val);
                result = doubleToLongWithOverflowCheck(doubleVal);
            } catch (NumberFormatException e) {
                ;
            }
            break;
        default:
            break;
        }
        return result;
    }

    private long doubleToLongWithOverflowCheck(double x) {
        if (x > Long.MAX_VALUE) {
            StringBuilder err = new StringBuilder();
            err.append("Can not cast double value \"" + x + "\" to long type.");
            err.append("It is bigger than Long.MAX_VALUE.");
            throw new ArithmeticException(err.toString());
        }
        return (long) x;
    }

}
