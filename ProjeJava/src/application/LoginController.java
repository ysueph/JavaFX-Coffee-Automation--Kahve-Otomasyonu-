package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.MySQL.util.MD5SİFRE;
import com.MySQL.util.SQLconnection;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {
	
	public  LoginController() {
		
		baglanti=SQLconnection.baglan(); 
		
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button admin_giris;

    @FXML
    private Button giris;

    @FXML
    private Button kayit;


    @FXML
    private PasswordField txt_sifre;


    @FXML
    private TextField txt_tel;
    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;

    @FXML
    void admin_giris_clk(ActionEvent event) {

    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/login_48px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
   	 Stage stage = (Stage) admin_giris.getScene().getWindow();
	    stage.close();
    	
    }
    //--- 	
    public  static int session_id;
    public  static String session_ad;
    public  static String session_soyad;
    public  static String session_telefon;
    public  static int session_bakiye;
    //----
    @FXML
    void giris_clk(ActionEvent event) {
    	sql="select*from kullanici_db where telefon=? and sifre=?";
    	try {sorguifadesi=baglanti.prepareStatement(sql);
    	sorguifadesi.setString(1, txt_tel.getText().trim());
    	sorguifadesi.setString(2, MD5SİFRE.md5(txt_sifre.getText().trim()));
    	
    	ResultSet getirilen =sorguifadesi.executeQuery();
    	
    	
    	    	
    	if(!getirilen.next()) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("hatalı giriş");
    		alert.setHeaderText("bilgilendirme mesajı");
    		alert.setContentText("telefon veya şifre hatalı...");
    		alert.showAndWait();
    		
    		
    	}
    	
    	else {
    		
    		//---
    		session_id=getirilen.getInt("id");
    		session_ad=getirilen.getString("ad");
    		session_soyad=getirilen.getString("soyad");
    	     session_telefon=getirilen.getString("telefon");
    	     session_bakiye=getirilen.getInt("bakiye");
        	//---
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
    	    	
    	      	 Stage stage = (Stage) giris.getScene().getWindow();
    	  	    stage.close();
    	     }
    	
    	} catch (Exception e) {
     		Alert alert = new Alert(AlertType.CONFIRMATION);
	 		alert.setTitle("HATTA!");
	        	alert.setHeaderText("bilgilendirme mesajı");
	        	alert.setContentText("Kullanıcı işlemi gerçekleşmedi..\n"+e.getMessage().toString());
	        	alert.showAndWait();	
    	}
    }
    

   


	@FXML
    void kayit_clk(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("Kayit.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/add_user_male_60px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	@FXML
	private ImageView img;
    @FXML
    void initialize() {
    	RotateTransition donme = new RotateTransition(Duration.millis(1700), img);
    	donme.setByAngle(-50);
    	donme.setAutoReverse(true);
    	donme.setCycleCount(Animation.INDEFINITE);
    	donme.setInterpolator(Interpolator.LINEAR);
    	donme.play();
    }

}
