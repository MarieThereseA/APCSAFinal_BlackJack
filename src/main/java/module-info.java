module com.example.apcsafinal_blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.media;

    opens com.example.apcsafinal_blackjack to javafx.fxml;
    exports com.example.apcsafinal_blackjack;
}