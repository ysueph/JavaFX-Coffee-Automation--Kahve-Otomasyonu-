package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminPanel {
	
	public AdminPanel() {
		baglanti=SQLconnection.baglan(); 
		
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<kullanici,String> ad;
    
    @FXML
    private Label bakiye_lbl;
    
    @FXML
    private Label id_lbl;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_kullanici_guncelle;

    @FXML
    private Button btn_kullanici_sil;
//--------------------------------------tab satın alma    
    @FXML
    private TableColumn<satin_almalar, Integer> clm_miktar;

    @FXML
    private TableColumn<satin_almalar, Integer> clm_Sid;

    @FXML
    private TableColumn<satin_almalar,Date> clm_Starih;

    @FXML
    private Button btn_Ssorgu;

    @FXML
    private TableColumn<satin_almalar,String> clm_Skul_tel;

    @FXML
    private TableColumn<satin_almalar,Integer> clm_Skul_id;
    
    @FXML
    private TableView<satin_almalar> table_satin;;
    
    @FXML
    private TextField Satin_arama_txt;
    
    @FXML
    private DatePicker Satin_date_bas;

    @FXML
    private DatePicker Satin_date_bit;


//------------------------------------
    @FXML
    private TableColumn<kullanici, Integer> id;

    @FXML
    private TableColumn<kullanici,String> soyad;
    
    @FXML
    private TableColumn<kullanici,Integer> bakiye;
    
    @FXML
    private TableView<kullanici> table_kullanicilar;

    @FXML
    private TableColumn<kullanici,String> telefon;
//--------------------------------------------------    
    @FXML
    private TextField ad_txt;
    
    @FXML
    private TextField soyad_txt;

    @FXML
    private TextField telefon_txt;
//--------------------------------------
    @FXML
    private TextField ara_urun_txt;
    
    @FXML
    private TextField txt_urun_ad;

    @FXML
    private TextField txt_urun_fiyat;

    @FXML
    private TextField txt_urun_id;

    @FXML
    private TextField txt_urun_tur;
 //--------------------------------------------   
    @FXML
    private TableColumn<urun, Integer> clm_id;
    
    @FXML
    private TableColumn<urun, String> clm_ad;

    @FXML
    private TableColumn<urun, Integer> clm_fiyat;
    
    @FXML
    private TableColumn<urun, String> clm_urun_tur;

    @FXML
    private TableView<urun> table_urun;
    
    @FXML
    private Button btn_kullanici_ekle;
    
    @FXML
    private Button btn_urun_ekle;

    @FXML
    private Button btn_urun_guncelle;

    @FXML
    private Button btn_urun_sil;
 //-----------------------------------tab yükle
    @FXML
    private TextField Yukle_ara_txt;
    
    @FXML
    private TableColumn<yuklemeler,Integer> clm_yukle_Kid;

    @FXML
    private TableColumn<yuklemeler, Integer> clm_yukle_id;

    @FXML
    private TableColumn<yuklemeler,Integer> clm_yukle_miktar;

    @FXML
    private TableColumn<yuklemeler, Date> clm_yukle_tarih;

    @FXML
    private TableColumn<yuklemeler, String> clm_yukle_tel;
    
    @FXML
    private TableView<yuklemeler> table_yukle;
    
    
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null; 	
    String sql;

    @FXML
    void btn_cikis_clk(ActionEvent event) {
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
	    	
	      	 Stage stage = (Stage) btn_cikis.getScene().getWindow();
	 	    stage.close();
    }
    

    @FXML
    void btn_Ssorgu_clk(ActionEvent event) {
    	sql="select * from satin_almalar_db where Starih>'"+Satin_date_bas.getValue() +"'and Starih<'"+Satin_date_bit.getValue()+"'"; 
    	DegerlerSatin(table_satin,sql);
    }

    //arama
    
    @FXML
    void ara_urun_txt_keypress(KeyEvent event) {
    	
    	if(ara_urun_txt.getText().equals("")) {
    		sql="select * from urun_db"; 
    	}
    	else {
    		sql="select * from urun_db where urun_adi like '%"+ara_urun_txt.getText()+"%'";
    	}
    	
    	DegerlerU(table_urun,sql);
    }
    
    @FXML
    void Yukle_ara_txt_press(KeyEvent event) {
    	
    	if(Yukle_ara_txt.getText().equals("")) {
    		sql="select * from yuklemeler_db"; 
    	}
    	else {
    		sql="select * from yuklemeler_db where kul_tel like '%"+Yukle_ara_txt.getText()+"%'";
    	}
    	
    	DegerlerYukle(table_yukle,sql);
    }
    //-------------------------------------------
    @FXML
    void satin_arama_txt_press(KeyEvent event) {
    	
    	if(Satin_arama_txt.getText().equals("")) {
    		sql="select * from satin_almalar_db"; 
    	}
    	else {
    		sql="select * from satin_almalar_db where Skul_tel like'%"+Satin_arama_txt.getText()+"%'";
    	}
    	
    	DegerlerSatin(table_satin,sql);
    }
//--------------------------------------------------
    @FXML
    void btn_kullanici_guncelle_clk(ActionEvent event) {
    	sql="update  kullanici_db set ad=?,soyad=?,telefon=? where id=?";
        
        try {
     	   sorguifadesi=baglanti.prepareStatement(sql);
            sorguifadesi.setString(1,ad_txt.getText().trim() );
            sorguifadesi.setString(2,soyad_txt.getText().trim() );
            sorguifadesi.setString(3,telefon_txt.getText().trim() );
            sorguifadesi.setString(4,id_lbl.getText().trim() );
            sorguifadesi.executeUpdate();
         Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Dikkat!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("Kullanıcı güncelleştirme gerçekleşti...");
        	alert.showAndWait();
            
        	DegerlerK(table_kullanicilar,"select * from kullanici_db");
            
 		
 	} catch (Exception e) {
 		Alert alert = new Alert(AlertType.CONFIRMATION);
 		alert.setTitle("HATTA!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("Kullanıcı güncelleştirme gerçekleşmedi...\n"+e.getMessage().toString());
        	alert.showAndWait();
 	}
    }
//------------------------------------
    @FXML
    void btn_kullanici_sil_clk(ActionEvent event) {
    	sql="delete from kullanici_db where ad=? and soyad=?";
    	
    	try{
    		 sorguifadesi=baglanti.prepareStatement(sql);
             sorguifadesi.setString(1,ad_txt.getText().trim() );
             sorguifadesi.setString(2,soyad_txt.getText().trim() );
             sorguifadesi.executeUpdate();
         	
            sorguifadesi.executeUpdate();
           
           Alert alert = new Alert(AlertType.CONFIRMATION);
       	alert.setTitle("Dikkat!");
       	alert.setHeaderText("bilgilendirme mesajı");
       	alert.setContentText("şifre silme gerçekleşti...");
       	alert.showAndWait();
       	
           DegerlerK(table_kullanicilar,sql);
       }
    	
    	catch (Exception e){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
     		alert.setTitle("HATTA!");
            	alert.setHeaderText("bilgilendirme mesajı");
            	alert.setContentText("şifre silme gerçekleşmedi...\n"+e.getMessage().toString());
            	alert.showAndWait();
       }
    	
       }
 //tablodan seçme   
    @FXML
    void table_clk(MouseEvent event) {
    	kullanici kayit=new kullanici();
    	kayit=(kullanici) table_kullanicilar.getItems().get(table_kullanicilar.getSelectionModel().getSelectedIndex());
    	id_lbl.setText(String.valueOf(kayit.getId()));
    	ad_txt.setText(kayit.getAd());
    	soyad_txt.setText(kayit.getSoyad());
    	telefon_txt.setText(kayit.getTelefon());
    	bakiye_lbl.setText(String.valueOf(kayit.getBakiye()));
    }
    
    @FXML
    void table_urun_clk(MouseEvent event) {
    	urun kayit=new urun();
    	kayit=(urun) table_urun.getItems().get(table_urun.getSelectionModel().getSelectedIndex());
    	txt_urun_id.setText(String.valueOf(kayit.getUid()));
    	txt_urun_ad.setText(kayit.getUrun_adi());
    	txt_urun_fiyat.setText(String.valueOf(kayit.getFiyat()));
    	txt_urun_tur.setText(kayit.getUrun_tur());
    }

  //ekleme 
    @FXML
    void btn_urun_ekle_clk(ActionEvent event) {
    	try {
    		Stage stage=new Stage();
        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("urun_ekle.fxml"));
        	Scene scene1=new Scene(pane1);
        	stage.setScene(scene1);
        	scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.getIcons().add(new Image("file:///C:/Users/YUSUF/eclipse-workspace/ProjeJava/src/resimler/add_60px.png"));
        	stage.show();
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	//pencere kapatma
    	 Stage stage = (Stage) btn_urun_ekle.getScene().getWindow();
    	    stage.close();
    }
    

//güncelleme 
    @FXML
    void btn_urun_guncelle_clk(ActionEvent event) {
        sql="update urun_db set urun_adi=?,fiyat=?,urun_tur=? where Uid=?";
        
        try {
     	   sorguifadesi=baglanti.prepareStatement(sql);
            sorguifadesi.setString(1,txt_urun_ad.getText().trim() );
            sorguifadesi.setString(2,txt_urun_fiyat.getText().trim() );
            sorguifadesi.setString(3,txt_urun_tur.getText().trim() );
            sorguifadesi.setString(4,txt_urun_id.getText().trim() );
            sorguifadesi.executeUpdate();
         Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Dikkat!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("Ürün güncelleştirme gerçekleşti...");
        	alert.showAndWait();
            
        	DegerlerU(table_urun,"select * from urun_db");
            
 		
 	} catch (Exception e) {
 		Alert alert = new Alert(AlertType.CONFIRMATION);
 		alert.setTitle("HATTA!");
        	alert.setHeaderText("bilgilendirme mesajı");
        	alert.setContentText("Ürün güncelleştirme gerçekleşmedi...\n"+e.getMessage().toString());
        	alert.showAndWait();
 	}
      }
    
    
    
//silme
    @FXML
    void btn_urun_sil_clk(ActionEvent event) {
    	sql="delete from urun_db where Uid=?";
    	
    	try{
    		 sorguifadesi=baglanti.prepareStatement(sql);
             sorguifadesi.setString(1,txt_urun_id.getText().trim() );
             sorguifadesi.executeUpdate();
         	
            sorguifadesi.executeUpdate();
           
           Alert alert = new Alert(AlertType.CONFIRMATION);
       	alert.setTitle("Dikkat!");
       	alert.setHeaderText("bilgilendirme mesajı");
       	alert.setContentText(" silme gerçekleşti...");
       	alert.showAndWait();
       	
           DegerlerU(table_urun,sql);
       }
    	
    	catch (Exception e){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
     		alert.setTitle("HATTA!");
            	alert.setHeaderText("bilgilendirme mesajı");
            	alert.setContentText("silme gerçekleşmedi...\n"+e.getMessage().toString());
            	alert.showAndWait();
       }
    	 DegerlerU(table_urun,"select * from urun_db");
    }
    
   //-------------------------------------------------- getirme
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void DegerlerK(TableView table_kullanicilar,String sql) {
    	ObservableList<kullanici> kullanicilar=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen =sorguifadesi.executeQuery();
    	    while(getirilen.next()) {
    	    	
   kullanicilar.addAll(new kullanici(getirilen.getInt("id"),getirilen.getString("ad"),getirilen.getString("soyad"),getirilen.getString("telefon"),getirilen.getString("sifre"),getirilen.getInt("bakiye")));
    	    }
    	    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	    ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
    	    soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
    	    telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
    	    bakiye.setCellValueFactory(new PropertyValueFactory<>("bakiye"));
    	    
    	    table_kullanicilar.setItems(kullanicilar);
    	    
    	    
    	}
    	
    	catch (SQLException e) {

    	}
    }
	
	//-------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void DegerlerU(TableView table_urun,String sql) {
    	ObservableList<urun> urunler=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen =sorguifadesi.executeQuery();
    	    while(getirilen.next()) {
    	    	 
    	    	urunler.addAll(new urun(getirilen.getInt("Uid"),getirilen.getString("urun_adi"),getirilen.getInt("fiyat"),getirilen.getString("urun_tur")));
    	    }   
    	    clm_id.setCellValueFactory(new PropertyValueFactory<>("Uid"));
    	    clm_ad.setCellValueFactory(new PropertyValueFactory<>("urun_adi"));
    	    clm_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    	    clm_urun_tur.setCellValueFactory(new PropertyValueFactory<>("urun_tur"));
    	    
    	    table_urun.setItems(urunler);
    	    
    	    
    	}
    	
    	catch (SQLException e) {
    	}
    }
	
	//---------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void DegerlerSatin(TableView table_satin,String sql) {
    	ObservableList<satin_almalar> satin=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen =sorguifadesi.executeQuery();
    	    while(getirilen.next()) {
    	    	
    	    	satin.addAll(new satin_almalar(getirilen.getInt("Sid"),getirilen.getInt("miktar"),getirilen.getString("Skul_tel"),getirilen.getInt("Skul_id"),getirilen.getDate("Starih")));
    	    }
    	    clm_Sid.setCellValueFactory(new PropertyValueFactory<>("Sid"));
    	    clm_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
    	    clm_Skul_tel.setCellValueFactory(new PropertyValueFactory<>("Skul_tel"));
    	    clm_Skul_id.setCellValueFactory(new PropertyValueFactory<>("Skul_id"));
    	    clm_Starih.setCellValueFactory(new PropertyValueFactory<>("Starih"));
    	    
    	    table_satin.setItems(satin);
    	    
    	    
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
	//-----------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void DegerlerYukle(TableView table_yukle,String sql) {
    	ObservableList<yuklemeler> yuklee=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen =sorguifadesi.executeQuery();
    	    while(getirilen.next()) {
    	    	
    	    	yuklee.addAll(new yuklemeler(getirilen.getInt("Yid"),getirilen.getInt("Ymiktar"),getirilen.getString("kul_tel"),getirilen.getInt("kul_id"),getirilen.getDate("Ytarih")));
    	    }
    	    clm_yukle_id.setCellValueFactory(new PropertyValueFactory<>("Yid"));
    	    clm_yukle_miktar.setCellValueFactory(new PropertyValueFactory<>("Ymiktar"));
    	    clm_yukle_tel.setCellValueFactory(new PropertyValueFactory<>("kul_tel"));
    	    clm_yukle_Kid.setCellValueFactory(new PropertyValueFactory<>("kul_id"));
    	    clm_yukle_tarih.setCellValueFactory(new PropertyValueFactory<>("Ytarih"));
    	    
    	    table_yukle.setItems(yuklee);
    	    
    	    
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
    @FXML
    void initialize() {
        DegerlerK(table_kullanicilar,"select * from kullanici_db");
        DegerlerU(table_urun,"select * from urun_db");
        DegerlerSatin(table_satin,"select * from satin_almalar_db");
        DegerlerYukle(table_yukle,"select * from yuklemeler_db");
        
        Satin_date_bas.setValue(LocalDate.now().of(2022,05 ,1));
        Satin_date_bit.setValue(LocalDate.now().of(2022,05, 31));
    }

}

