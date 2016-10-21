package ua.com.juja.sqlcmd.view;

/**
 * Created by kramar on 21.10.16.
 */

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableConstructor {

    private final Set<String> columns;
    private final Table table;
    private final List<Map<String, Object>> tableData;

    public TableConstructor(Set<String> columns, List<Map<String, Object>> tableData) {
        this.columns = columns;
        this.tableData = tableData;
        table = new Table(columns.size(), BorderStyle.CLASSIC, ShownBorders.ALL);
    }

    public String getTableString() {
        build();
        return table.render();
    }

    private void build() {
        buildHeader();
        buildRows();
    }

    private void buildHeader() {
        for (String column : columns) {
            table.addCell(column);
        }
    }

    private void buildRows() {
        for (Map<String, Object> row : tableData) {
            for (Object value : row.values()) {
                table.addCell(value.toString());
            }
        }
    }
}