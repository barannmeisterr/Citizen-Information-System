import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Citizien {
	/*
	 * Title: Citizen Class
	 * Author: Arda Baran
	 * 
	 * Description:
	 * The Citizen class models a citizen residing in Turkey, encapsulating personal and familial information. Each citizen has 
	 * attributes such as their name, surname, a unique ID number, TCKN (Turkish National Identification Number), city of residence, 
	 * county, mother's name, father's name, and the TCKN numbers of both parents. Nationality is also an essential attribute 
	 * represented within this class.
	 * 
	 * Furthermore, the Citizen class extends its functionality to represent the citizen's family relationships. This includes 
	 * immediate family members like mother, father, siblings, and children, as well as extended family members such as uncles, 
	 * aunts (both maternal and paternal sides), cousins, and grandparents. Additionally, the class maintains a record of the 
	 * citizen's phone numbers and living addresses.
	 * 
	 * The Citizen class integrates with three separate databases: one for GSM (Global System for Mobile Communications) 
	 * citizienDatabase (Turkey citizien database inluding foreign and deceased persons) and address database(Tax and Adress Records), to manage and retrieve comprehensive citizen information. 
	 * SQL queries are used to extract and manipulate data from these databases, allowing us to gather all necessary details about 
	 * a citizen's identity and familial connections.
	 * 
	 * The purpose of this class is to provide a unified representation of a citizen, combining personal identity data with 
	 * extensive family and contact details, ensuring seamless integration and data retrieval across multiple databases.
	 */

	
	
	
	
	
String tc,relation;
String name ,surname,birthDate,city,county,motherName,motherTc,fatherName,fatherTc,nationality;
List <Citizien> family;
HashSet<String> phoneNumbers;
HashSet<String> address;
int id;
public Citizien(int id ,String tc,String name,String surname,String birthDate,String city,String county,String motherName,String motherTc,String fatherName,String fatherTc,String nationality) {
//this constructor is for citizien's informations.
	this.id=id;
	this.tc=tc;
	this.name=name;
	this.surname=surname;
	this.birthDate=birthDate;
	this.city=city;
	this.county=county;
	this.motherName=motherName;
	this.motherTc=motherTc;
	this.fatherName=fatherName;
	this.fatherTc=fatherTc;
	this.nationality=nationality;	
this.relation="";
this.family=new ArrayList<>();
this.phoneNumbers = new HashSet<>();
this.address= new HashSet<>();
}
public Citizien(String relation,int id ,String tc,String name,String surname,String birthDate,String city,String county,String motherName,String motherTc,String fatherName,String fatherTc,String nationality) {
/*
this constructor is for citizien's family connections.
*/	
	this.relation=relation;
	this.id=id;
	this.tc=tc;
	this.name=name;
	this.surname=surname;
	this.birthDate=birthDate;
	this.city=city;
	this.county=county;
	this.motherName=motherName;
	this.motherTc=motherTc;
	this.fatherName=fatherName;
	this.fatherTc=fatherTc;
	this.nationality=nationality;	
this.family=new ArrayList<>();
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public HashSet<String> getAddress() {
	return address;
}
public void setAddress(HashSet<String> address) {
	this.address = address;
}
public List<Citizien> getFamily() {
	return family;
}

public HashSet<String> getPhoneNumbers() {
	return phoneNumbers;
}
public void setPhoneNumbers(HashSet<String> phoneNumbers) {
	this.phoneNumbers = phoneNumbers;
}
public void setFamily(List<Citizien> family) {
	this.family = family;
}
public void addFamilyMember(Citizien familyMember) {
//----------------------------------------------------------------------------
//Summary:adds family member to the list from citizenDatabase database.
//-----------------------------------------------------------------------------	
	if(familyMember==null) {
		return;
		
	}
family.add(familyMember);
}
public void addPhoneNumber(String phoneNumber) {
//----------------------------------------------------------------------------
//Summary:adds gsm number to the set if a phone number is found.hashset is used to remove duplicates
//-----------------------------------------------------------------------------	
	
	if(phoneNumber==null) {
		return;
	}
phoneNumbers.add(phoneNumber);
}
public void addAddress(String adres) {
//----------------------------------------------------------------------------
//Summary:adds adress  to the set if a current living address  is found.hashset is used to remove duplicates
//-----------------------------------------------------------------------------	
	
	if(adres==null) {
		return;
	}
address.add(adres);
}
public static Citizien getCitizenByTc(String tcNo) throws SQLException {
//----------------------------------------------------------------------------
//Summary:searches citizien from citizienDatabase and returns that citizien if is found.
//-----------------------------------------------------------------------------	
	
	Connection connection = DatabaseConnection.getConnectionTocitizienDatabase(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT * FROM citizienDatabase WHERE TC = ?"; //  prepared statement 
        statement = connection.prepareStatement(sql);
        statement.setString(1, tcNo); // Set the TC  in the prepared statement

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
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
            return citizen;
        } else {
            System.out.println("Citizen with TC " + tcNo + " not found.");
            return null;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Re-throw the exception 
    } finally {
        // Close resources in a finally block to ensure proper cleanup
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); 
    }
}
public void findAdresses() throws SQLException{
//----------------------------------------------------------------------------
//Summary:finds citizien's current living adress from datam database and adds found adressed to the set.

//-----------------------------------------------------------------------------		
	
	
	String tckn = getTc();
	Connection connection=DatabaseConnection.getConnectionToAddress();
	PreparedStatement statement = null;
	ResultSet resultSet=null;
	try {
		String sql = "SELECT Ikametgah FROM datam WHERE KimlikNo = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, tckn);
		
		resultSet =statement.executeQuery();
	while(resultSet.next()) {
		String adres = resultSet.getString("Ikametgah");
	addAddress(adres);
	}
	}
	   catch (SQLException e) {
	        e.printStackTrace();
	        throw e; // Re-throw the exception 
	    } finally {
	        // Close resources in a finally block to ensure proper cleanup
	        if (resultSet != null) {   
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        connection.close(); 
	    }
}
public void findPhoneNumbers() throws SQLException{
//----------------------------------------------------------------------------
//Summary:finds citizien's gsm numbers from gsm database and adds found gsm numbers to the set.

//-----------------------------------------------------------------------------		
	
	String tcNo = getTc();
	Connection connection = DatabaseConnection.getConnectionToGsm(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;	
    try {
    	 String sql ="SELECT GSM FROM data WHERE TC = ?";
    	 statement = connection.prepareStatement(sql);
         statement.setString(1, tcNo); // Set the TC value

         resultSet = statement.executeQuery();
         while (resultSet.next()) {
        	 String gsm  = resultSet.getString("GSM");
        addPhoneNumber(gsm);
        
         }
    }
    catch (SQLException e) {
        e.printStackTrace();
        throw e; // Re-throw the exception 
    } finally {
        // Close resources in a finally block to ensure proper cleanup
        if (resultSet != null) {   
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); 
    }
 
}
public void findFamilyMembers() throws SQLException {
/*	
Summary:
This SQL script is designed to retrieve detailed family information for a specific individual based on their TC number (a unique identifier in Turkey). 
The code uses multiple Common Table Expressions (CTEs) to first identify the individual's immediate family members and then expand to grandparents, aunts, uncles,
and cousins. 
 
	
*/	
	String tcNo = getTc();
	Connection connection = DatabaseConnection.getConnectionTocitizienDatabase(); 
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        String sql ="WITH AnneninTC AS (\n"
        		
        		+ "  SELECT ANNETC AS AnneTC, BABATC AS AnneBabaTC\n"
        		+ "    FROM `citizienDatabase` \n"
        		+ "    WHERE TC = ? \n"
        		+ "), \n"
        		
        		
        		+ "BabaninTC AS (\n"
        	
        		+ " SELECT BABATC AS BabaTC, ANNETC AS BabaAnneTC\n"
        		+ "    FROM `citizienDatabase` \n"
        		+ "    WHERE TC = ? \n"
        		+ "),\n"
        		
        		+ "AnneVeBaba AS (\n"
        
        		+ "  SELECT \n"
        		+ "        'Anne' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN AnneninTC AT ON A.TC = AT.AnneTC \n"
        		+ "    \n"
        		+ "    UNION ALL \n"
        		+ "     \n"
        		+ "    SELECT \n"
        		+ "        'Baba' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN BabaninTC BT ON A.TC = BT.BabaTC \n"
        		+ "),\n"
        	
        		+ "Cocuklar AS (\n"
        		+ "    SELECT \n"
        		+ "        'Çocuk' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    WHERE A.ANNETC = ? OR A.BABATC = ? \n"
        		+ "), \n"
        	
        		+ "AnneninAnnesiVeBabasiTC AS ( \n"
     
        		+ "    SELECT \n"
        		+ "        ANNETC AS AnneanneTC, \n"
        		+ "        BABATC AS DedeTC \n"
        		+ "    FROM `citizienDatabase`\n"
        		+ "    WHERE TC = (SELECT AnneTC FROM AnneninTC) \n"
        	    + "), \n"
        		+ "BabaninAnnesiVeBabasiTC AS ( \n"
       
        		+ "    SELECT \n"
        		+ "        ANNETC AS BabaanneTC, \n"
        		+ "        BABATC AS BuyukbabaTC \n"
        		+ "    FROM `citizienDatabase` \n"
        		+ "    WHERE TC = (SELECT BabaTC FROM BabaninTC) \n"
        		+ "), \n"
        		
        		+ "DayiVeTeyze AS (\n"
        
        		
        		+ "  SELECT \n"
        		+ "        'Dayı/Teyze' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.TC \n"
        		+ "    WHERE (B.ANNETC = (SELECT AD.AnneanneTC FROM AnneninAnnesiVeBabasiTC AD) \n"
        		+ "       OR B.BABATC = (SELECT AD.DedeTC FROM AnneninAnnesiVeBabasiTC AD) ) \n"
        		+ "       AND B.TC <> (SELECT AT.AnneTC FROM AnneninTC AT)   \n"
        		+ "), \n"
        		
        		+ "DayiTeyzeCocuklari AS ( \n"
        	
        		+ "   SELECT  \n"
        		+ "        'Kuzen' AS Yakinlik,  \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN DayiVeTeyze DT ON A.ANNETC = DT.TC OR A.BABATC = DT.TC \n"
        		+ "), \n"
        		+ "\n"
        		+ "AmcaVeHala AS (\n"
        	
        		+ "  SELECT \n"
        		+ "        'Amca/Hala' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A\n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.TC\n"
        		+ "    WHERE (B.ANNETC = (SELECT AK.BabaanneTC FROM BabaninAnnesiVeBabasiTC AK) \n"
        		+ "       OR B.BABATC = (SELECT AK.BuyukbabaTC FROM BabaninAnnesiVeBabasiTC AK) )\n"
        		+ "       AND B.TC <> (SELECT AT.BabaTC FROM BabaninTC AT)  \n"
        		+ "),\n"
        		+ "AmcaHalaCocuklari AS(\r\n"
        	
        		+ "SELECT \n"
        		+ "'Kuzen' AS Yakinlik, \n"
        		+ "A.* \n"
        		+ "FROM `citizienDatabase` A \n"
        		+ "JOIN AmcaVeHala DM  ON A.ANNETC = DM.TC OR A.BABATC = DM.TC \n"
        		+ "), \n"
        		+ "\n"
        
        		+ "AnneanneVeDede AS ( \n"
        		
        		+ "  SELECT \n"
        		+ "        'Anneanne' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.ANNETC \n"
        		+ "    JOIN AnneninTC AT ON B.TC = AT.AnneTC \n"
        		+ "     \n"
        		+ "    UNION ALL \n"
        		+ "    \n"
        		+ "    SELECT \n"
        		+ "        'Dede' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.BABATC \n"
        		+ "    JOIN AnneninTC AT ON B.TC = AT.AnneTC \n"
        		+ "), \n"
        	
        		+ "BabaanneVeBuyukbaba AS (\n"
 
        		+ "    SELECT \n"
        		+ "        'Babaanne' AS Yakinlik, \n"
        		+ "        A.* \n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.ANNETC \n"
        		+ "    JOIN BabaninTC BT ON B.TC = BT.BabaTC \n"
        		+ "    \n"
        		+ "    UNION ALL\n"
        		+ "    \n"
        		+ "    SELECT \n"
        		+ "        'Büyükbaba' AS Yakinlik, \n"
        		+ "        A.*\n"
        		+ "    FROM `citizienDatabase` A\n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.BABATC \n"
        		+ "    JOIN BabaninTC BT ON B.TC = BT.BabaTC \n"
        		+ "),\n"
        		
        		+ "Kardesler AS (\n"
        
        		+ "  SELECT \n"
        		+ "        'Kardeş' AS Yakinlik, \n"
        		+ "        A.*\n"
        		+ "    FROM `citizienDatabase` A \n"
        		+ "    JOIN `citizienDatabase` B ON A.TC = B.TC \n"
        		+ "    WHERE (B.ANNETC = (SELECT ANNETC FROM AnneninTC) \n"
        		+ "       OR B.BABATC = (SELECT BABATC FROM BabaninTC) ) \n"
        		+ "       AND B.TC <> ? \n"
        		+ ")\n"
        
        		+ "SELECT * FROM AnneVeBaba\n"
        		+ "UNION ALL\n"
        		+ "SELECT * FROM Kardesler\n"
        		+ "UNION ALL\n"
        		+ "\n"
        		+ "SELECT * FROM Cocuklar\n"
        		+ "UNION ALL\n"
        		+ "SELECT * FROM DayiVeTeyze\n"
        		+ "UNION ALL\n"
        		+ "\n"
        		+ "\n"
        		+ "SELECT * FROM AmcaVeHala\n"
        		+ "UNION ALL\n"
        		+ "\n"
        		+ "SELECT * FROM DayiTeyzeCocuklari\n"
        		+ "UNION ALL\n"
        		+ "SELECT * FROM AmcaHalaCocuklari\n"
        		+ "UNION ALL\n"
        		+ "\n"
        		+ "SELECT * FROM AnneanneVeDede\n"
        		+ "UNION ALL\n"
        		
        		+ "SELECT * FROM BabaanneVeBuyukbaba;"
        	
        		
        		 ; 
        statement = connection.prepareStatement(sql);
        statement.setString(1,tcNo ); 
        statement.setString(2,tcNo ); 
        statement.setString(3,tcNo ); 
        statement.setString(4,tcNo ); 
        statement.setString(5,tcNo ); 
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Citizien citizen = new Citizien( // Create a new Citizen object
                    resultSet.getString("Yakinlik"),
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
           addFamilyMember(citizen);
        } 
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Re-throw the exception 
    } finally {
        // Close resources in a finally block to ensure proper cleanup
        if (resultSet != null) {   
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        connection.close(); 
    }
}


public String getTc() {
	return tc;
}
public void setTc(String tc) {

	
	this.tc = tc;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getBirthDate() {
	return birthDate;
}
public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCounty() {
	return county;
}
public void setCounty(String county) {
	this.county = county;
}
public String getMotherName() {
	return motherName;
}
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
public String getMotherTc() {
	return motherTc;
}
public void setMotherTc(String motherTc) {
	this.motherTc = motherTc;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getFatherTc() {
	return fatherTc;
}
public void setFatherTc(String fatherTc) {
	this.fatherTc = fatherTc;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String toString() {
	return getTc() +" "+ getName()+" "+getSurname()+" "+getBirthDate()+" "+getCity() + " "+getCounty()+" "+getMotherName()+ " "+ getMotherTc()+" "+getFatherName()+" "+ getFatherTc()+" "+getNationality();
}
public void printFamilyMembers() {
	
List <Citizien> familyMembers = getFamily();
for(Citizien a : familyMembers) {
	if(a!=null) {
System.out.println(a.getRelation()+" "+a.getId()+" "+a.getTc()+" "+a.getName()+" "+a.getSurname()+" "+a.getBirthDate()+" "+a.getCity()+" "+a.getCounty()+" "+a.getMotherName()+" "+a.getMotherTc()+" "+a.getFatherName()+" "+a.getFatherTc()+" "+a.getNationality());		
	}
}
	
}
public void printPhoneNumbers() {
List <String> numbers = new ArrayList<>(getPhoneNumbers());
for (String p : numbers) {
	System.out.println(p);
}
}
public String getRelation() {
	return relation;
}
public void setRelation(String relation) {
	this.relation = relation;
}

}
