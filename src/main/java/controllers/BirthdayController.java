package controllers;

import database.dao.PersonDaoImpl;
import database.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;

public class BirthdayController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;


    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private List<Person> persons = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
//        System.out.println(barChart.getBarGap());
        barChart.barGapProperty().setValue(1);
        initPersonsList();

        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.setAll(months);
        xAxis.setCategories(monthNames);

        initChart();
    }

    private void initChart() {
        int[] monthCounter = new int[12];
        for (Person person : persons) {
            int month = person.getBirthday().getMonthOfYear() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);

    }

    private void initPersonsList() {
        PersonDaoImpl dao = new PersonDaoImpl();
        persons.addAll(dao.getAll());
    }
}
