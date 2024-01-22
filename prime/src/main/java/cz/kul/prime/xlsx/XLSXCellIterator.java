package cz.kul.prime.xlsx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import cz.kul.prime.PrimeException;

/**
 * It can iterate through XLSX file cells.
 * 
 * It iterates from the first row and the first cell and continue by next cells in the
 * row. Then continue to the next row etc.
 * 
 * You can set by coordinates, what cells are you interested in. It works like a filter.
 * When you set more coordinates, there is OR operator: if the cell match at least one
 * ginev coordinates, it is returned.
 * 
 * @author kulhalad
 * @since 7.4.5
 */
public class XLSXCellIterator implements Iterator<Cell> {

    private String filePath;

    private InputStream input;

    private Set<Coordinates> coordinates = new HashSet<Coordinates>();

    private List<Cell> cells = new ArrayList<>();

    private Iterator<Cell> iterator;

    public XLSXCellIterator(InputStream input) {
        this(null, input, null);
    }

    public XLSXCellIterator(String filePath) {
        this(filePath, null, null);
    }

    public XLSXCellIterator(InputStream input, Collection<Coordinates> coordinates) {
        this(null, input, coordinates);
    }

    public XLSXCellIterator(String filePath, Collection<Coordinates> coordinates) {
        this(filePath, null, coordinates);
    }

    private XLSXCellIterator(String filePath, InputStream input, Collection<Coordinates> coordinates) {
        this.filePath = filePath;
        this.input = input;
        if (CollectionUtils.isEmpty(coordinates)) {
            this.coordinates.add(Coordinates.getForAll());
        } else {
            this.coordinates.addAll(coordinates);
        }
        readCells();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Cell next() {
        return iterator.next();
    }

    private void readCells() {
        try {
            readCellsCore();
        } catch (IOException e) {
            throw new PrimeException(e);
        }
    }

    private void readCellsCore() throws IOException {
        try (XSSFWorkbook workbook = getWorkbook()) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Iterator<Row> rows = sheet.iterator(); rows.hasNext();) {
                Row row = rows.next();
                for (Iterator<Cell> cells = row.cellIterator(); cells.hasNext();) {
                    Cell cell = cells.next();
                    if (match(cell)) {
                        this.cells.add(cell);
                    }
                }
            }
        }
        this.iterator = cells.iterator();
    }

    private XSSFWorkbook getWorkbook() throws IOException {
        if (filePath != null) {
            return new XSSFWorkbook(filePath);
        } else if (input != null) {
            return new XSSFWorkbook(input);
        } else {
            throw new PrimeException("Can not open XSSFWorkbook, no input specified");
        }
    }

    private boolean match(Cell cell) {
        for (Coordinates c : coordinates) {
            if (c.match(cell.getRowIndex(), cell.getColumnIndex())) {
                return true;
            }
        }
        return false;
    }

}
