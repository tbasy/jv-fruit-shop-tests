package core.basesyntax.service;

import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String TITLE = "fruit,quantity";

    @Override
    public String getReport(Map<String, Integer> storage) {
        if (storage == null) {
            throw new NullPointerException("Storage map must not be null");
        }
        StringBuilder report = new StringBuilder();
        report.append(TITLE).append(System.lineSeparator());

        for (Map.Entry<String, Integer> entry : storage.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new NullPointerException("Neither fruit name nor quantity can be null");
            }
            report.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }
        return report.toString();
    }
}
