package cz.kul.spring_intoduction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.kul.spring_intoduction.services.CsvService;
import cz.kul.spring_intoduction.services.CsvServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvServiceUnitTest // unit test
{

    @Test
    void shouldConvertCsvToJson() throws Exception {
        CsvService csvService = new CsvServiceImpl();
        String json = csvService.csvToJson("""
            x,y,visible
            1,2,true
            3,4,false
            """);
        String expected = """
            {
            "rows": [
              {
                "x" : "1",
                "y" : "2",
                "visible" : "true"
              },
              {
                "x" : "3",
                "y" : "4",
                "visible" : "false"
              }
            ]
            }
            """;
        assertJsonEquals(expected, json);
    }

    @Test
    void shouldReturnEmptyJsonWhenTheInputIsEmpty() {
        CsvService csvService = new CsvServiceImpl();
        String json = csvService.csvToJson("");
        Assertions.assertEquals("{}", json);
    }

    @Test
    void shouldReturnEmptyJsonWhenCsvContainOnlyHeader() {
        CsvService csvService = new CsvServiceImpl();
        String json = csvService.csvToJson("a,b,c");
        Assertions.assertEquals("{}", json);
    }

    @Test
    void shouldThrowsAnExceptionWhenNumberOfItemsIsDifferentThanNumberOfHeaders() {
        CsvService csvService = new CsvServiceImpl();
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> csvService.csvToJson("""
            x,y
            1,2,
            1,
            1,2
            """));
        Assertions.assertEquals("Number of cells in row 2 is different than number of cells in header.", exception.getMessage());
    }

    void assertJsonEquals(String expected, String actual) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode expectedJsonNode = objectMapper.readTree(expected);
            JsonNode actualJsonNode = objectMapper.readTree(actual);
            Assertions.assertEquals(expectedJsonNode, actualJsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
