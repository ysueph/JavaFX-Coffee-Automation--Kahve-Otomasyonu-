package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.ResourceBundle;

import com.MySQL.util.MD5SİFRE;
import com.MySQL.util.SQLconnection;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminLoginController {
	
public  AdminLoginController() {
		
		baglanti=SQLconnection.baglan(); 
		
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Agiris;

    @FXML
    private TextField Akullanici_txt;

    @FXML
    private PasswordField Asifre_txt;

    @FXML
    private Button geri;
    
    @FXML
    private TextField sonuc;

    @FXML
    private TextField textA;

    @FXML
    private TextField textB;
    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;

    @FXML
    void Agiris_clk(ActionEvent event) {
    	
    	sql="select*from admin_db where kul_ad=? and sifre=?";
    	try {sorguifadesi=baglanti.prepareStatement(sql);
    	sorguifadesi.setString(1, Akullanici_txt.getText().trim());
    	sorguifadesi.setString(2, Asifre_txt.getText().trim());
    	
    	ResultSet getirilen =sorguifadesi.executeQuery();
		
    	if(!getirilen.next()) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("hatalı giriş");
		alert.setHeaderText("bilgilendirme mesajı");
		alert.setContentText("kullanıcı adı veya şifre hatalı...");
		alert.showAndWait();
		
	    }
    	
    	else {
    		
    		
    		
    	if(C !=Integer.parseInt(sonuc.getText()))
    	{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("hatalı giriş");
    		alert.setHeaderText("bilgilendirme mesajı");
    		alert.setContentText("güvenlik sorgusu hatalı...");
    		alert.showAndWait();
    	}
    	
    	else {     
    		//-----------------------------------------------
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
    	    	
    	      	 Stage stage = (Stage) Agiris.getScene().getWindow();
    	 	    stage.close();
    	//------------------  
    	 	   
    	}
    	
    	
    	//-----------------------------------------------------------	
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
    void geri_clk(ActionEvent event) {
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
   	 Stage stage = (Stage) geri.getScene().getWindow();
	    stage.close();
    }
    
     int A;
     int B;
     int C;
    public void GuvenlikSorgusu() {
        
        Random random = new Random();
         A = random.nextInt(50);
         B = random.nextInt(50);
        
         textA.setText(Integer.toString(A));
         textB.setText(Integer.toString(B));
         C=A+B;
       
         
    }

    @FXML
    void initialize() {
    	GuvenlikSorgusu();
    }

}

