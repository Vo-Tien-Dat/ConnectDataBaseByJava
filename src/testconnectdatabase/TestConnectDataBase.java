/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testconnectdatabase;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
/**
 *
 * @author __Vo_Dat__
 */
public class TestConnectDataBase {
    private static Connection ketnoi = null;

    public TestConnectDataBase() {
        connectDataBase();
    }
    public static boolean addDAUSACH(DAUSACH ds){
        String sql = "INSERT INTO SACH(MASACH, TUASACH, TACGIA, NHAXUATBAN)" + "VALUES(?,?,?,?)";
        try{
            PreparedStatement ps = ketnoi.prepareStatement(sql);
            ps.setString(1, ds.getMASACH());
            ps.setString(2, ds.getTUASACH());
            ps.setString(3, ds.getTACGIA());
            ps.setString(4, ds.getNHASANXUAT());
            return ps.executeUpdate() > 0 ;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public static ArrayList<DAUSACH> getDAUSACH(){
        ArrayList<DAUSACH> listdausach = new ArrayList<>();
        String sql = "SELECT * FROM SACH ";
        try{
            PreparedStatement ps = ketnoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DAUSACH dausach = new DAUSACH();
                dausach.setMASACH(rs.getString("MASACH"));
                dausach.setTUASACH(rs.getString("TUASACH"));
                dausach.setTACGIA(rs.getString("TACGIA"));
                dausach.setNHASANXUAT(rs.getString("NHAXUATBAN"));
                listdausach.add(dausach);
            }
            System.out.println("lay du lieu ra thanh cong");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return listdausach;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void connectDataBase(){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLSACH";
        
        String username = "sa";
        String password = "12345678";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketnoi = DriverManager.getConnection(url, username, password);
            System.out.println("ket noi csdl thanh cong");
        }
        catch(Exception ex){
            System.out.println("khong the ket noi voi csdl");
        }
    }
    
    public static boolean deleteDAUSACH(DAUSACH ds){
        String sql = "DELETE FROM SACH WHERE MASACH = ?";
        
        try{
            PreparedStatement ps = ketnoi.prepareStatement(sql);
            ps.setString(1, ds.getMASACH());
            return ps.executeUpdate() > 0 ;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
        
    }
       
}
