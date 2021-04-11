module Chess {
    requires javafx.fxml;
    requires javafx.controls;

    requires org.junit.jupiter.api;

    exports com.company.GUI;

    opens com.company;
    opens com.company.GUI;
}