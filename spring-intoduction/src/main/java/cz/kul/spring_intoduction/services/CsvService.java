package cz.kul.spring_intoduction.services;

public interface CsvService
{

    String csvToJson(final String csv);

    String csvToJson(final String csv, final Character delimiter);

}
