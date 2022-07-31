package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.MySQL.util.MD5SİFRE;
import com.MySQL.util.SQLconnection;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnaMenuController {

	public  AnaMenuController() {
		baglanti=SQLconnection.baglan(); 
		
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
//--------------------------hamburger menü
    @FXML
    private AnchorPane anchor_hamb;
    
    @FXML
    private ToggleButton kahve_btn;

    @FXML
    private ToggleButton sicak_icecek_btn;

    @FXML
    private ToggleButton soguk_icecek_btn;

    @FXML
    private ToggleButton tatli_btn;

    @FXML
    private ToggleButton yiyecek_btn;
//-----------------------------------------
    @FXML
    private Label Kbakiye_lbl;

    @FXML
    private Button hesap_btn;

    @FXML
    private Label hos_lbl;

    @FXML
    private Button menu_btn;

    @FXML
    private Button satinal_btn;
    
    @FXML
    private Button ckss_btn;
    
    @FXML
    private TableColumn<?, ?> menuClm_urun_ad;

    @FXML
    private TableColumn<?, ?> menuClm_urun_fiyat;

    @FXML
    private TableColumn<?, ?> menuClm_urun_tur;
    

    @FXML
    private TableColumn<?, ?> clm_adet;
    
    @FXML
    private TextField ara_txt;
    
    @FXML
    private TextField sepet_txt;
    
    @FXML
    private ListView<String> liste;
    
    @FXML
    private Button sepet_sil_btn;

    @FXML
    private TableView<?> table_uruns;

    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;
    
    ToggleGroup grup1=new ToggleGroup();
//------------------  menü seçimleri  
    @FXML
    void kahve_btn_clk(ActionEvent event) {
    	
    	boolean secim= kahve_btn.isSelected();
    	if(kahve_btn.isSelected()) {
			String sql="select * from urun_db where urun_tur like 'kahve'";
			DegerlerMenuU(table_uruns, sql);
    	}    	else {
    		sql="select * from urun_db";
    		DegerlerMenuU(table_uruns, sql);
    	}
    }

    @FXML
    void sicak_icecek_btn_clk(ActionEvent event) {
    	boolean secim= sicak_icecek_btn.isSelected();
    	if(sicak_icecek_btn.isSelected()) {
			String sql="select * from urun_db where urun_tur like 'sıcak içecek'";
			DegerlerMenuU(table_uruns, sql);
    	}    	else {
    		sql="select * from urun_db";
    		DegerlerMenuU(table_uruns, sql);
    	}
    }

    @FXML
    void soguk_icecek_btn_clk(ActionEvent event) {
    	boolean secim= soguk_icecek_btn.isSelected();
    	if(soguk_icecek_btn.isSelected()) {
			String sql="select * from urun_db where urun_tur like 'soğuk içecek'";
			DegerlerMenuU(table_uruns, sql);
    	}
    	else {
    		sql="select * from urun_db";
    		DegerlerMenuU(table_uruns, sql);
    	}
    }

    @FXML
    void tatli_btn_clk(ActionEvent event) {
    	boolean secim= tatli_btn.isSelected();
    	if(tatli_btn.isSelected()) {
			String sql="select * from urun_db where urun_tur like 'tatlı'";
			DegerlerMenuU(table_uruns, sql);
    	}    	else {
    		sql="select * from urun_db";
    		DegerlerMenuU(table_uruns, sql);
    	}
    }

    @FXML
    void yiyecek_btn_clk(ActionEvent event) {
    	boolean secim= yiyecek_btn.isSelected();
    	if(yiyecek_btn.isSelected()) {
			String sql="select * from urun_db where urun_tur like 'yiyecek'";
			DegerlerMenuU(table_uruns, sql);
    	}    	else {
    		sql="select * from urun_db";
    		DegerlerMenuU(table_uruns, sql);
    	}
    }
  //--------------------  
    @FXML
    void ckss_btn_clk(ActionEvent event) {
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
    	
      	 Stage stage = (Stage) ckss_btn.getScene().getWindow();
 	    stage.close();
     }

    @FXML
    void hesap_btn_clk(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("Hesap.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/test_account_60px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Stage stage = (Stage) hesap_btn.getScene().getWindow();
  	    stage.close();
    }
    
    
    public int durum=0;
    
    @FXML
    void menu_clk(ActionEvent event) {
    	
     	if(durum==0) {
    		FadeTransition fd1=new FadeTransition(Duration.seconds(0.5), anchor_hamb);
        	fd1.setFromValue(0);
        	fd1.setToValue(0.8);
        	fd1.play();
        	
        	TranslateTransition tt1=new TranslateTransition(Duration.seconds(0.5), anchor_hamb);
        	tt1.setByX(+200); //X dogrultusunda 
        	//tt1.setByY(0);//Y dogrultusunda
        	tt1.play();
        	durum=1;
    	}
    	else {
        	TranslateTransition tt1=new TranslateTransition(Duration.seconds(0.5), anchor_hamb);
        	tt1.setByX(-200); //X dogrultusunda 
        	//tt1.setByY(0);//Y dogrultusunda
        	tt1.play();
        	durum=0;
    	}
    	
    	
    }
    
    
    
    
    public static int yenisayi;
    @FXML
    void satinal_btn_clk(ActionEvent event) {
    	 yenisayi = Integer.parseInt(Kbakiye_lbl.getText());
    	 
    	 
    	 
    	//-------Sepet boş değilse
    	if(!sepet_txt.getText().equals("")) {
    		
    	
    	
        if(harcama>yenisayi) {
	         Alert alert = new Alert(AlertType.CONFIRMATION);
	        	alert.setTitle("DİKKAT!");
	        	alert.setHeaderText("bilgilendirme mesajı");
	        	alert.setContentText("bakiyeniz bulunmamaktadır");
	        	alert.showAndWait();
        }
        //----------------Bakiye yeterse
        else {
        	
            //-------------------------------------Onaylama
        	Alert Emin=new Alert(AlertType.CONFIRMATION);
        	Emin.setTitle("Dikkat");
        	Emin.setHeaderText("Sepet Tutarı= "+harcama+" TL");
        	Emin.setContentText("Satın alma işlemi gerçekleşsin mi ?");
        	
        	ButtonType btn1=new ButtonType("Evet");
        	ButtonType btn2=new ButtonType("Hayır");
        	Emin.getButtonTypes().setAll(btn1,btn2);
        	
        	Optional<ButtonType> sonuc= Emin.showAndWait();
        	//---------------------------------------Satın ALma tamamlandı
        	if(sonuc.get()==btn1) {
            	yenisayi=yenisayi-harcama;
            	Kbakiye_lbl.setText(String.valueOf(yenisayi));
            	sql="update  kullanici_db set bakiye=? where id=?";
                
                try {
                	int id =LoginController.session_id;
             	   sorguifadesi=baglanti.prepareStatement(sql);
                    sorguifadesi.setString(1,Kbakiye_lbl.getText().trim() );
                    sorguifadesi.setInt(2,id);
                    sorguifadesi.executeUpdate();
                       		
         	} catch (Exception e) {
         		Alert alert = new Alert(AlertType.CONFIRMATION);
         		alert.setTitle("HATTA!");
                	alert.setHeaderText("bilgilendirme mesajı");
                	alert.setContentText("işlem gerçekleşmedi...\n"+e.getMessage().toString());
                	alert.showAndWait();
         	  }
                
          		 sql="insert into satin_almalar_db(miktar,Skul_tel,Skul_id) values(?,?,?)";
       	        
       	        try {
       	        	String tel= LoginController.session_telefon;
       	        	int id= LoginController.session_id;
       	     	   sorguifadesi=baglanti.prepareStatement(sql);
       	            sorguifadesi.setString(1, sepet_txt.getText().trim());
       	            sorguifadesi.setString(2, tel);
       	            sorguifadesi.setInt(3, id);
       	            sorguifadesi.executeUpdate();
       	            
       	         Alert alert = new Alert(AlertType.CONFIRMATION);
       	        	alert.setTitle("DİKKAT!");
       	        	alert.setHeaderText("bilgilendirme mesajı");
       	        	alert.setContentText("Satın alma işlemi gerçekleşti.");
       	        	alert.showAndWait();
       	 		
       	 	} catch (Exception e) {
       	 		Alert alert = new Alert(AlertType.CONFIRMATION);
       	 		alert.setTitle("HATTA!");
       	        	alert.setHeaderText("bilgilendirme mesajı");
       	        	alert.setContentText("Satın alma işlemi gerçekleşmedi..\n"+e.getMessage().toString());
       	        	alert.showAndWait();
       	 	}
       	    	sepet_txt.setText("");
       	    	harcama=0;
       	    	sayac=0;
       	    	liste.getItems().clear();

        	}
        	//---------------------------------------Satın ALma tamamlandı
        	else {

        	}
    	       }
        //----------------Bakiye yeterse
    	}
    	//-------Sepet boş değilse
    	
    	    
    	
    	//---------------------
    	
    	else {
        	 		Alert alert = new Alert(AlertType.CONFIRMATION);
        	 		alert.setTitle("HATTA!");
        	        	alert.setHeaderText("bilgilendirme mesajı");
        	        	alert.setContentText("sepet boş..");
        	        	alert.showAndWait();
    	               }
    	
    	
    }
    
    
    
    public int harcama=0;
    public int sayac=0;
    public 	String a;
    @FXML
    void table_uruns_clk(MouseEvent event) {
    	urun kayit=new urun();
    	kayit=(urun) table_uruns.getItems().get(table_uruns.getSelectionModel().getSelectedIndex());
    	harcama= harcama + (kayit.getFiyat());
    	sepet_txt.setText(String.valueOf(harcama));
        sayac++;    
        
    	    a =(sayac+". "+kayit.getUrun_adi()+"  " +kayit.getFiyat()+" TL"+"  " +kayit.getUrun_tur());
       liste.getItems().addAll(a);
       
       
        DegerlerMenuU(table_uruns, "select * from urun_db");
    	
    	//((String.valueOf(tikla)));
    }
    
  //-----------------Temizleme  
    @FXML
    void sepet_sil_btn_clk(ActionEvent event) {
    	sepet_txt.setText("");
    	harcama=0;
    	sayac=0;
    	liste.getItems().clear();

    }
    
//-----------------------------  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void DegerlerMenuU(TableView table_uruns,String sql) {
    	
    	ObservableList<urun> urunler=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen =sorguifadesi.executeQuery();
    	    while(getirilen.next()) {
    	    	
    	    	urunler.addAll(new urun(getirilen.getInt("Uid"),getirilen.getString("urun_adi"),getirilen.getInt("fiyat"),getirilen.getString("urun_tur")));
    	    }
    	    menuClm_urun_ad.setCellValueFactory(new PropertyValueFactory<>("urun_adi"));
    	    menuClm_urun_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    	    menuClm_urun_tur.setCellValueFactory(new PropertyValueFactory<>("urun_tur"));
    	    
    	    
    	    table_uruns.setItems(urunler);
    	    
    	}
    	
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("hata!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("hatalı birşeyler oldu..."+e.getMessage().toString());
        	alert.showAndWait();
    	}
    }
	
//-----------------------------
	@FXML
    void ara_txtKeyPressed(KeyEvent event) {
		String sql="select * from urun_db where urun_adi like '%"+ara_txt.getText()+"%'";
		DegerlerMenuU(table_uruns, sql);
    }
	
	
    @FXML
    void initialize() {
    	DegerlerMenuU(table_uruns,"select * from urun_db");
    	//----
    	String ad= LoginController.session_ad;
    	String soyad= LoginController.session_soyad;
    	int bakiye =LoginController.session_bakiye;
    	hos_lbl.setText("Hoşgeldiniz " + ad +" "+ soyad);
    	
    	if(HesapController.para==0) {
    	Kbakiye_lbl.setText(String.valueOf(bakiye));
    	yenisayi=bakiye;
    	}else {
    	int aaa=HesapController.para;
    	Kbakiye_lbl.setText(String.valueOf(aaa));
    	}
    	//---
    	
    	
    	
    	kahve_btn.setToggleGroup(grup1);
    	sicak_icecek_btn.setToggleGroup(grup1);
    	soguk_icecek_btn.setToggleGroup(grup1);
    	tatli_btn.setToggleGroup(grup1);
    	yiyecek_btn.setToggleGroup(grup1);
    }

}