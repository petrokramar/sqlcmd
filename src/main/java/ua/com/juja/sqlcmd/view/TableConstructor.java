package ua.com.juja.sqlcmd.view;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import ua.com.juja.sqlcmd.model.DataSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableConstructor {

    private final Set<String> columns;
    private final Table table;
    private final List<DataSet> tableData;

    public TableConstructor(Set<String> columns, List<DataSet> tableData) {
        this.columns = columns;
        table = new Table(columns.size(), BorderStyle.CLASSIC, ShownBorders.ALL);
        this.tableData = tableData;
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
        for (DataSet row : tableData) {
            for (Object value : row.getValues()) {
                table.addCell(value.toString());
            }
        }
    }
}