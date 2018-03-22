package homework.view;

import java.util.List;
import java.util.Map;

public class ResultsParser {
    private static final ResultsParser RESULTS_PARSER = new ResultsParser();

    private ResultsParser() {
    }

    public static ResultsParser getInstance() {
        return RESULTS_PARSER;
    }

    public String parseResults(List<Map<String, Object>> result, boolean printHeader,
                               String separator, int startColumn, int endColumn) {

        if (result.isEmpty()) {
            return "";
        }

        if (endColumn < 0) {
            endColumn = result.get(1).keySet().size();
        }

        StringBuilder sb = new StringBuilder();

        if (printHeader) {
            int column = 0;
            for (String title : result.get(1).keySet()) {
                if (column >= startColumn && column <= endColumn) {
                    sb.append(title).append(separator);
                }
                column++;
            }
            sb.append(System.lineSeparator());
        }

        for (Map<String, Object> row : result) {
            int column = 0;
            for (Object obj : row.values()) {
                if (column >= startColumn && column <= endColumn) {
                    sb.append(obj).append(separator);
                }
                column++;
            }

            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
