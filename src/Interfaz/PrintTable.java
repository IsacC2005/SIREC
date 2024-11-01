package Interfaz;

import javax.swing.table.TableModel;
import impresRiven.dynamicjasper.DynamicJasperPrint;
import impresRiven.dynamicjasper.builder.DataSource;
import impresRiven.dynamicjasper.template.Column;
import impresRiven.dynamicjasper.template.Label;
import impresRiven.dynamicjasper.template.PageFormat;
import impresRiven.dynamicjasper.template.Template_Report_Master_Detail;

/**
 *
 * @author Raven
 */
public class PrintTable {

    public DynamicJasperPrint printTable(TableModel model, String title) {
        return printTable(model, title, null);
    }

    public DynamicJasperPrint printTable(TableModel model, String title, PageFormat pageFormat) {
        DynamicJasperPrint dynamicJasperPrint = new DynamicJasperPrint();
        pageFormat = null;
        Template_Report_Master_Detail tem = pageFormat == null ? new Template_Report_Master_Detail(title) : new Template_Report_Master_Detail(title, pageFormat);
        //  setup column report
        int column = model.getColumnCount();
        for (int i = 0; i < column; i++) {
            tem.addColumn(new Column(model.getColumnName(i), "ex_" + i, Column.Type.STRING));
        }
        //  setup header report
        //tem.addLabelHeader(new Label());
        //tem.addLabelHeader(new Label("Developer :", "dev", Label.Type.STRING));
        dynamicJasperPrint.setTemplate(tem);

        //  init data
        String[] columns = new String[column];
        for (int i = 0; i < column; i++) {
            columns[i] = "ex_" + i;
        }
        DataSource dataSource = new DataSource(columns);
        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] row = new Object[column];
            for (int j = 0; j < column; j++) {
                row[j] = model.getValueAt(i, j);
            }
            dataSource.add(row);
        }
        dynamicJasperPrint.setDataSource(dataSource);
        //dynamicJasperPrint.addParameter("dev", "Raven");
        return dynamicJasperPrint;
    }

    private Column createColumn(TableModel model) {

        return null;
    }
}
