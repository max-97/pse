package de.sswis.util;


import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataSetHelper {

    /**
     * Erstellt ein KeyedValuesDataset, mit den übergegebenen Strings,
     * bei dem ein Datenpaar aus eienm key, welches einem Element der Liste entspricht und einer Value,
     * was der Anzahl der Vorkommen des Elements in der Liste entspricht, ist.
     *
     * @param data Die Strings mit dem ein Datenset erstellt wird
     * @return ein Dataset mit Datenpaaren aus Listeneinträgen und deren Häufigkeit in der Liste.
     */
    public static KeyedValuesDataset getKeyedValuesDataSet(List<String> data) {
        DefaultKeyedValuesDataset dataset = new DefaultKeyedValuesDataset();
        while (!data.isEmpty()) {
            int value = 0;
            String key = data.get(0);
            while (data.remove(key)) {
                value++;
            }

            dataset.setValue(key, value);
        }

        return dataset;
    }


    public static CategoryDataset getCategoryDataset(ArrayList<String> strategies, ArrayList<Integer> points, int range) {

        List<String> rowKeys = new ArrayList<>();
        List<String> columnKeys = new ArrayList<>();
        int highest = 0;

        //create row keys
        for (int i = 0; i < strategies.size(); i++) {
            if (!rowKeys.contains(strategies.get(i))) {
                rowKeys.add(strategies.get(i));
            }
            if (highest < points.get(i)) {
                highest = points.get(i);
            }
        }

        //create column key
        for (int i = 0; i < highest; i += range) {
            columnKeys.add(i + " - " + (i + range));
        }

        //count data
        double[][] data = new double[rowKeys.size()][columnKeys.size()];

        for (int i = 0; i < strategies.size(); i++) {
            data[rowKeys.indexOf(strategies.get(i))][(points.get(i)/range)] += 1.0;
        }

        return  DatasetUtilities.createCategoryDataset((String[]) rowKeys.toArray(), (String[]) columnKeys.toArray(), data);

    }



    public static int calculateNiceRange(List<Integer> values) {
        Collections.sort(values);
        int highest = values.get((values.size() - 1));
        //TODO: implement me
        return 10;
    }
}
