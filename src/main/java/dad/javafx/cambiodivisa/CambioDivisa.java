package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa pound = new Divisa("Libra", 0.90);
	private Divisa dollar = new Divisa("Dólar", 1.17);
	private Divisa yen = new Divisa("Yen", 124.17);
	
	private Divisa[] currency = { euro, pound, dollar, yen};
	
	private TextField textOrigin, textDestiny;
	private ComboBox<Divisa> comboOrigin, comboDestiny;
	private Button button;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		textOrigin = new TextField("0");
		textOrigin.setPrefColumnCount(5);
		textOrigin.setMaxWidth(50);
		textOrigin.setPrefColumnCount(4);
		textOrigin.setAlignment(Pos.CENTER);
		
		textDestiny = new TextField("0");
		textDestiny.setPrefColumnCount(5);
		textDestiny.setMaxWidth(50);
		textDestiny.setPrefColumnCount(4);
		textDestiny.setAlignment(Pos.CENTER);
		textDestiny.setEditable(false);
		
		
		comboOrigin = new ComboBox<>();
		comboOrigin.getItems().addAll(currency);
		comboOrigin.getSelectionModel().selectFirst();
		comboDestiny = new ComboBox<>();
		comboDestiny.getItems().addAll(currency);
		comboDestiny.getSelectionModel().selectFirst();
		
		button = new Button();
		button.setText("Cambiar");
		button.setDefaultButton(true);
		button.setOnAction(e -> Change(e));
		
		HBox origenHbox = new HBox();
		origenHbox.setSpacing(5);
		origenHbox.setAlignment(Pos.CENTER);
		origenHbox.getChildren().addAll(textOrigin, comboOrigin);
		
		HBox destinyHbox = new HBox();
		destinyHbox.setSpacing(5);
		destinyHbox.setAlignment(Pos.CENTER);
		destinyHbox.getChildren().addAll(textDestiny, comboDestiny);
		
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(origenHbox, destinyHbox, button);
		
		Scene scene = new Scene(vbox, 320, 200);
		
		stage.setScene(scene);
		stage.setTitle("Cambio de divisa");
		stage.show();
	}
	
	private void Change(ActionEvent e) {
		
		Double amount = Double.parseDouble(textOrigin.getText());
		Divisa currencyOrigin = comboOrigin.getSelectionModel().getSelectedItem();
		Divisa currencyDestiny = comboDestiny.getSelectionModel().getSelectedItem();	
		Double finalAmount = currencyDestiny.fromEuro(currencyOrigin.toEuro(amount));
		textDestiny.setText("" + finalAmount);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
