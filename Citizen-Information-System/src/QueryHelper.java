import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class QueryHelper {
	/*
     * Title: QueryHelper Class
     * Author: Arda Baran
     * 
     * Description:
     * The QueryHelper class provides various utility methods for querying citizen information from connected databases.
     * This class is designed to perform specific tasks such as:
     * 1. Searching for citizens by their name and surname.
     * 2. Finding citizens associated with a specific GSM (mobile) number.
     * 3. Retrieving GSM numbers associated with a particular citizen's TCKN (Turkish National Identification Number).
     * 4. Retrieving Tax and Address records associated with a particular citizien's TCKN.
     * 
     * The class maintains internal collections to store query results:
     * - `searchByNameSurnameResults`: Stores the list of citizens found by name and surname.
     * - `gsmNumberRegisteredTCNumbers`: Stores the set of TCKN numbers that are registered with a specific GSM number.
     * - `gsmNumberBelongToTCNumber`: Stores the set of GSM numbers associated with a specific TCKN number.
     * - 'taxNumbersAndAdresses' :Stores the list of tax numbers and current living addresses.
     * The QueryHelper class integrates with the DatabaseConnection class to establish connections to the required databases.
     * Each method handles SQL queries and manages the results, providing easy access to citizen information for further processing.
     * 
     * The design of this class ensures that all database resources, such as connections and statements, are properly managed and closed,
     * minimizing the risk of resource leaks.
     */
	
List <Citizien> searchByNameSurnameResults;
HashSet <String> gsmNumberRegisteredTCNumbers;
HashSet <String> gsmNumberBelongToTCNumber;
List <TaxAndAddressQueryHelper> taxNumbersAndAdresses;
public QueryHelper(){
	this.searchByNameSurnameResults=new ArrayList<>();
	this.gsmNumberRegisteredTCNumbers=new HashSet<>();
    this.gsmNumberBelongToTCNumber=new HashSet<>();
    this.taxNumbersAndAdresses=new ArrayList<>();
}

public void addTaxAndAdressRecords(TaxAndAddressQueryHelper c) {
	if(c==null) {
		return ;
		
	}
	taxNumbersAndAdresses.add(c);
}
public void addCitizien(Citizien c) {
	if(c==null) {
		return;
		
	}
	searchByNameSurnameResults.add(c);
	
}
public void addTCToSet(String gsm) {
	if(gsm==null) {
		return;
		
	}
	gsmNumberRegisteredTCNumbers.add(gsm);
}
public void addGsmToSet(String tc) {
	if(tc==null) {
		return;
		
	}
	gsmNumberBelongToTCNumber.add(tc);
}
public List<TaxAndAddressQueryHelper> getTaxNumbersAndAdresses() {
	return taxNumbersAndAdresses;
}
public void setTaxNumbersAndAdresses(List<TaxAndAddressQueryHelper> taxNumbersAndAdresses) {
	this.taxNumbersAndAdresses = taxNumbersAndAdresses;
}

public HashSet<String> getGsmNumberBelongToTCNumber() {
	return gsmNumberBelongToTCNumber;
}
public void setGsmNumberBelongToTCNumber(HashSet<String> gsmNumberBelongToTCNumber) {
	this.gsmNumberBelongToTCNumber = gsmNumberBelongToTCNumber;
}
public List<Citizien> getSearchByNameSurnameResults() {
	return searchByNameSurnameResults;
}
public void setSearchByNameSurnameResults(List<Citizien> searchByNameSurnameResults) {
	this.searchByNameSurnameResults = searchByNameSurnameResults;
}
public HashSet<String> getGsmNumberRegisteredTCNumbers() {
	return gsmNumberRegisteredTCNumbers;
}
public void setGsmNumberRegisteredTCNumbers(HashSet<String> gsmNumberRegisteredTCNumbers) {
	this.gsmNumberRegisteredTCNumbers = gsmNumberRegisteredTCNumbers;
}
public void getCitiziensByNameSurname(String name,String surname) throws SQLException {
	Connection connection = DatabaseConnection.getConnectionTocitizienDatabase(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT * FROM citizienDatabase WHERE ADI REGEXP ? AND SOYADI = ? "; 
        statement = connection.prepareStatement(sql);
        statement.setString(1, name); 
        statement.setString(2, surname);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Citizien citizen = new Citizien( // Create a new Citizen object
                    resultSet.getInt("id"),
                    resultSet.getString("TC"),
                    resultSet.getString("ADI"),
                    resultSet.getString("SOYADI"),
                    resultSet.getString("DOGUMTARIHI"),
                    resultSet.getString("NUFUSIL"),
                    resultSet.getString("NUFUSILCE"),
                    resultSet.getString("ANNEADI"),
                    resultSet.getString("ANNETC"),
                    resultSet.getString("BABAADI"),
                    resultSet.getString("BABATC"),
                    resultSet.getString("UYRUK")
            );
            addCitizien(citizen);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Re-throw the exception for handling in the calling code
    } finally {
        // Close resources in a finally block to ensure proper cleanup
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); // Assuming connection.close() is implemented
    }
}	
public void searchGsmNumber(String gsm) throws SQLException {
	Connection connection = DatabaseConnection.getConnectionToGsm(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;	
    try {
    	 String sql ="SELECT TC FROM data WHERE GSM = ?";
    	 statement = connection.prepareStatement(sql);
         statement.setString(1, gsm); 

         resultSet = statement.executeQuery();
         while (resultSet.next()) {
        	 String tc  = resultSet.getString("TC");
        addTCToSet(tc);
        
         }
    }
    catch (SQLException e) {
        e.printStackTrace();
        throw e; 
    } finally {
       
        if (resultSet != null) {   
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); 
    }
}
public void findGsmNumbersRegisteredToTc(String tc) throws SQLException {
	Connection connection = DatabaseConnection.getConnectionToGsm(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;	
    try {
    	 String sql ="SELECT GSM FROM data WHERE TC = ?";
    	 statement = connection.prepareStatement(sql);
         statement.setString(1, tc); 

         resultSet = statement.executeQuery();
         while (resultSet.next()) {
        	 String gsm  = resultSet.getString("GSM");
        addGsmToSet(gsm);
        
         }
    }
    catch (SQLException e) {
        e.printStackTrace();
        throw e; 
    } finally {
       
        if (resultSet != null) {   
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); 
    }
}
public void getTaxAndAdressRecordsOfCitizien(String tc) throws SQLException{
	Connection connection = DatabaseConnection.getConnectionToAddress(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;	
    try {
    	 String sql ="SELECT KimlikNo,AdSoyad,DogumYeri,VergiNumarasi,Ikametgah FROM datam WHERE KimlikNo = ?";
    	 statement = connection.prepareStatement(sql);
         statement.setString(1, tc); 

         resultSet = statement.executeQuery();
         while (resultSet.next()) {
        TaxAndAddressQueryHelper records =new TaxAndAddressQueryHelper(
        		resultSet.getString("KimlikNo"),
        		resultSet.getString("AdSoyad"),
        		resultSet.getString("DogumYeri"),
        		resultSet.getString("VergiNumarasi"),
        		resultSet.getString("Ikametgah")
        		);
        addTaxAndAdressRecords(records);
         }
    }
    catch (SQLException e) {
        e.printStackTrace();
        throw e; 
    } finally {
       
        if (resultSet != null) {   
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); 
    }	
	
	
	
}
}
