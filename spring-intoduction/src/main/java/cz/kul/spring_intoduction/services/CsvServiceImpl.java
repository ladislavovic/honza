package cz.kul.spring_intoduction.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService
{

    @Override
    public String csvToJson(final String csv)
    {
        return csvToJson(csv, null);
    }

    @Override
    public String csvToJson(final String csv, final Character delimiter)
    {
        if (csv == null) {
            throw new IllegalArgumentException("csv argument can not be null");
        }

        List<String> lines = csv.lines().toList();
        if (lines.size() < 2) {
            return "{}";
        }

        String headerLine = lines.getFirst();
        final var effectiveDelimiter = delimiter == null ? ',' : delimiter;
        List<String> headers = splitToItems(headerLine, effectiveDelimiter);

        int cells = headers.size();
        int rows = lines.size();

        String[][] values = new String[rows][cells];
        for (int cell = 0; cell < headers.size(); cell++) {
            values[0][cell] = headers.get(cell);
        }
        for (int row = 1; row < lines.size(); row++) {
            String line = lines.get(row);
            List<String> items = splitToItems(line, effectiveDelimiter);
            if (items.size() != cells) {
                throw new IllegalArgumentException("Number of cells in row " + row + " is different than number of cells in header.");
            }
            for (int cell = 0; cell < cells; cell++) {
                values[row][cell] = items.get(cell);
            }
        }

        return createJson(rows, cells, values);
    }

    private String createJson(final int rows, final int cells, final String[][] values)
    {
        StringBuilder result = new StringBuilder();
        result.append("{\n[\n");
        for (int i = 1; i < rows; i++) {
            result.append("  {\n");
            for (int j = 0; j < cells; j++) {
                String property = String.format("    \"%s\" : \"%s\"", values[0][j], values[i][j]);
                result.append(property);
                if (j + 1 < cells) {
                    result.append(",");
                }
                result.append("\n");
            }
            result.append("  }");
            if (i + 1 < rows) {
                result.append(",");
            }
            result.append("\n");
        }
        result.append("]\n}\n");
        return result.toString();
    }

    private List<String> splitToItems(String line, char delimiter) {
        return Arrays.asList(line.split(Character.toString(delimiter))); // TODO escaping
    }

}
