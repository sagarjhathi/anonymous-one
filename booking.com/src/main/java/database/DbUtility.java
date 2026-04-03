package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtility {

	
	
	
	// =========================
    // GET CONNECTION
    // =========================
    public static Connection getConnection() {

        try {
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

       
        	
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to DB", e);
        }
    }

    // =========================
    // EXECUTE QUERY (SELECT)
    // =========================
    public static List<Map<String, String>> executeQuery(String query) {

        List<Map<String, String>> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            while (rs.next()) {

                Map<String, String> map = new HashMap<>();

                for (int i = 1; i <= colCount; i++) {

                    String key = meta.getColumnName(i);
                    String value = rs.getString(i);

                    map.put(key, value);
                }

                list.add(map);
            }

        } catch (Exception e) {
            throw new RuntimeException("Query execution failed", e);
        }

        return list;
    }

    // =========================
    // EXECUTE UPDATE (INSERT/UPDATE/DELETE)
    // =========================
    public static int executeUpdate(String query) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Update failed", e);
        }
    }
	
    
   

//        public static void executeAndPrint(String query) {
//
//        	String url = "jdbc:mysql://localhost:3306/practice";
//        	
//
//            try (Connection conn = DriverManager.getConnection(url, user, password);
//                 Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(query)) {
//
//                ResultSetMetaData meta = rs.getMetaData();
//                int colCount = meta.getColumnCount();
//
//                while (rs.next()) {
//
//                    for (int i = 1; i <= colCount; i++) {
//
//                        String columnName = meta.getColumnName(i);
//                        String value = rs.getString(i);
//
//                        System.out.print(columnName + ": " + value + "  |  ");
//                    }
//
//                    System.out.println(); // new row
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

