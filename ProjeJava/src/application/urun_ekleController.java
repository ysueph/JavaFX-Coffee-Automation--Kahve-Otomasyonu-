package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.MySQL.util.SQLconnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class urun_ekleController {
	
	public  urun_ekleController() {
		baglanti=SQLconnection.baglan(); 
		
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Uad_txt;

    @FXML
    private Button geridon_btn;

    
    @FXML
    private TextField Ufiyat_txt;

    @FXML
    private ChoiceBox<String> Utur;

    @FXML
    private Button ekle_urun_btn;

    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;
    
    @FXML
    void ekle_urun_btn_clk(ActionEvent event) {
		 sql="insert into urun_db(urun_adi,fiyat,urun_tur) values(?,?,?)";
	        
	        try {
	     	   sorguifadesi=baglanti.prepareStatement(sql);
	            sorguifadesi.setString(1, Uad_txt.getText().trim());
	            sorguifadesi.setString(2, Ufiyat_txt.getText().trim());
	            sorguifadesi.setString(3, Utur.getSelectionModel().getSelectedItem());
	            sorguifadesi.executeUpdate();
	         Alert alert = new Alert(AlertType.CONFIRMATION);
	        	alert.setTitle("DİKKAT!");
	        	alert.setHeaderText("bilgilendirme mesajı");
	        	alert.setContentText("Ürün ekleme gerçekleşti.");
	        	alert.showAndWait();
	            
	   	    	try {
    	    		Stage stage=new Stage();
    	        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
    	        	Scene scene1=new Scene(pane1);
    	        	stage.setScene(scene1);
    	        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/administrative_tools_48px.png"));
    	        	stage.show();
    	        	
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    	    	
    	      	 Stage stage = (Stage) ekle_urun_btn.getScene().getWindow();
    	 	    stage.close();
	 		
	 	} catch (Exception e) {
	 		Alert alert = new Alert(AlertType.CONFIRMATION);
	 		alert.setTitle("HATTA!");
	        	alert.setHeaderText("bilgilendirme mesajı");
	        	alert.setContentText("Ürün ekleme gerçekleşmedi..\n"+e.getMessage().toString());
	        	alert.showAndWait();
	 	}
    }
    
    @FXML
    void geridon_btn_clk(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/administrative_tools_48px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	//pencere kapatma
    	 Stage stage = (Stage) geridon_btn.getScene().getWindow();
    	    stage.close();
    }

    @FXML
    void initialize() {
    	ObservableList<String> dizi= FXCollections.observableArrayList("tatlı", "kahve", "soğuk içecek","sıcak içecek","yiyecek");
    	Utur.getItems().addAll(dizi);
    }

}
