package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.MySQL.util.MD5SİFRE;
import com.MySQL.util.SQLconnection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HesapController {
	public  HesapController() {
		
		baglanti=SQLconnection.baglan(); 
		
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Kid_lbl;

    @FXML
    private Label Kad_soyad_lbl;
    
    @FXML
    private Label yeni_bakiye_lbl;

    @FXML
    private Label Ktel_lbl;

    @FXML
    private Button cikis_btn;

    @FXML
    private Button geri_btn;

    @FXML
    private Button yukle_btn;

    @FXML
    private Slider yukle_slid;

    @FXML
    private Label bakiye_lbl;

    @FXML
    private TextField yukle_txt;

    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;
    
    @FXML
    void cikis_btn_clk(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/coffee_48px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
      	 Stage stage = (Stage) geri_btn.getScene().getWindow();
  	    stage.close();
    }

    @FXML
    void geri_btn_clk(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AnaMenu.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/coffee_beans_60px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
      	 Stage stage = (Stage) geri_btn.getScene().getWindow();
  	    stage.close();
    }
    
     public static int para;  
    @FXML
    void yukle_btn_clk(ActionEvent event) {
	    	
    	Alert Emin=new Alert(AlertType.CONFIRMATION);
    	Emin.setTitle("Dikkat");
    	Emin.setHeaderText("Yükleme Tutarı= "+sayi+" TL");
    	Emin.setContentText("Yükleme işlemi gerçekleşsin mi ?");
    	
    	ButtonType btn1=new ButtonType("Evet");
    	ButtonType btn2=new ButtonType("Hayır");
    	Emin.getButtonTypes().setAll(btn1,btn2);
    	
    	Optional<ButtonType> sonuc= Emin.showAndWait();
    	
    	if(sonuc.get()==btn1) {
		 sql="insert into yuklemeler_db(Ymiktar,kul_tel,kul_id) values(?,?,?)";
	        
	        try {
	     	   sorguifadesi=baglanti.prepareStatement(sql);
	            sorguifadesi.setString(1, yukle_txt.getText().trim());
	            sorguifadesi.setString(2, Ktel_lbl.getText().trim());
	            sorguifadesi.setString(3, Kid_lbl.getText().trim());
	            sorguifadesi.executeUpdate();
	 		
	 	} catch (Exception e) {
	 		Alert alert = new Alert(AlertType.CONFIRMATION);
	 		alert.setTitle("HATTA!");
	        	alert.setHeaderText("bilgilendirme mesajı");
	        	alert.setContentText("Kullanıcı işlemi gerçekleşmedi..\n"+e.getMessage().toString());
	        	alert.showAndWait();
	 	}
    	
	   	 //-----------------
    	sql="update  kullanici_db set bakiye=? where id=?";
        
        try {
     	   sorguifadesi=baglanti.prepareStatement(sql);
            sorguifadesi.setString(1,yeni_bakiye_lbl.getText().trim() );;
            sorguifadesi.setString(2,Kid_lbl.getText().trim() );
            sorguifadesi.executeUpdate();
            bakiye_lbl.setText(Integer.toString(para));
            
         Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Dikkat!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("para yükleme gerçekleşti...");
        	alert.showAndWait();
        	
	  	    
 	} catch (Exception e) {
 		Alert alert = new Alert(AlertType.CONFIRMATION);
 		alert.setTitle("HATTA!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("para yükleme gerçekleşmedi...\n"+e.getMessage().toString());
        	alert.showAndWait();
 	}
    	}
    	else { }
    }

	int myTemperature;
	int sayi;
	int yeni_say= AnaMenuController.yenisayi;
    @FXML
    void initialize() {
    	int id= LoginController.session_id;
    	String ad= LoginController.session_ad;
    	String tel= LoginController.session_telefon;
    	String soyad= LoginController.session_soyad;
    	
    	Kid_lbl.setText(String.valueOf(id));
    	Ktel_lbl.setText(tel);
    	Kad_soyad_lbl.setText(ad+" "+soyad);
    	bakiye_lbl.setText(String.valueOf(yeni_say));
        
    	myTemperature = (int) yukle_slid.getValue();
    	yukle_txt.setPromptText(Integer.toString(myTemperature));
    	
    	yukle_slid.valueProperty().addListener(new ChangeListener<Number>() {

    		public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
    			
    			myTemperature = (int) yukle_slid.getValue();
    			yukle_txt.setText(Integer.toString(myTemperature));
    			//-------------
    	    sayi=Integer.parseInt(yukle_txt.getText());
    	    para= yeni_say + sayi;
    	   	yeni_bakiye_lbl.setText(Integer.toString(para));
    			
    		}			
    	});	
    }

}