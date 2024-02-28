package cz.kul.spring_intoduction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class CsvConversionTest // integration test
{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldConvertCsv() throws Exception {
        MvcResult mvcResult = mockMvc
            .perform(get("/csv/to-json").content("""
            x,y,visible
            1,2,true
            3,4,false
            """))
            .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("""
            {
            [
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
            """,
            responseBody
        );

    }

}
