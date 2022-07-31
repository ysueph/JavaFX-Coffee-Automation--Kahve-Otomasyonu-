package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.MySQL.util.MD5SİFRE;
import com.MySQL.util.SQLconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class KayitController {
	
	public  KayitController() {
		baglanti=SQLconnection.baglan(); 
		
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ad_txt;

    @FXML
    private Button kayit;

    @FXML
    private TextField sifre2_txt;

    @FXML
    private TextField sifre_txt;

    @FXML
    private TextField soyad_txt;

    @FXML
    private TextField tel_txt;
    
    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;
    
    
    @FXML
    void kayit_clk(ActionEvent event) {
    	
    	 if(!sifre2_txt.getText().equals(sifre_txt.getText())) {
    		 
 	 		Alert alert = new Alert(AlertType.CONFIRMATION);
 	 		alert.setTitle("HATTA!");
 	        	alert.setHeaderText("bilgilendirme mesajı");
 	        	alert.setContentText("şifre uyuşmuyor");
 	        	alert.showAndWait(); 
        }
      
    	 else {
    		 sql="insert into kullanici_db(ad,soyad,telefon,sifre) values(?,?,?,?)";
    	        
    	        try {
    	     	   sorguifadesi=baglanti.prepareStatement(sql);
    	            sorguifadesi.setString(1, ad_txt.getText().trim());
    	            sorguifadesi.setString(2, soyad_txt.getText().trim());
    	            sorguifadesi.setString(3, tel_txt.getText().trim());
    	            sorguifadesi.setString(4, MD5SİFRE.md5(sifre_txt.getText().trim()));
    	            sorguifadesi.executeUpdate();
    	         Alert alert = new Alert(AlertType.CONFIRMATION);
    	        	alert.setTitle("DİKKAT!");
    	        	alert.setHeaderText("bilgilendirme mesajı");
    	        	alert.setContentText("Kullanıcı işlemi gerçekleşti.");
    	        	alert.showAndWait();
    	            
    	         	 Stage stage = (Stage) kayit.getScene().getWindow();
    	      	    stage.close();
    	 		
    	 	} catch (Exception e) {
    	 		Alert alert = new Alert(AlertType.CONFIRMATION);
    	 		alert.setTitle("HATTA!");
    	        	alert.setHeaderText("bilgilendirme mesajı");
    	        	alert.setContentText("Kullanıcı işlemi gerçekleşmedi..\n"+e.getMessage().toString());
    	        	alert.showAndWait();
    	 	} 
    		 
    	 }
        
    }

    @FXML
    void initialize() {


    }

}
