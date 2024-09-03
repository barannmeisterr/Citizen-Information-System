
public class TaxAndAddressQueryHelper {
/*Title:TaxAndAddressQueryHelper
 * Author:Arda Baran
 * Description: This class is a helper class in order to obtain citizien's tax and address records from adress database
 * 
 * 
 * 
 * 
 */
	
	String tckn,nameSurname,birthPlace,taxNo,adress;

public TaxAndAddressQueryHelper(String tckn,String nameSurname,String birthPlace,String taxNo,String adress) {
	this.tckn=tckn;
	this.nameSurname=nameSurname;
	this.birthPlace=birthPlace;
	this.taxNo=taxNo;
	this.adress=adress;
	
}

public String getTckn() {
	return tckn;
}

public void setTckn(String tckn) {
	this.tckn = tckn;
}

public String getNameSurname() {
	return nameSurname;
}

public void setNameSurname(String nameSurname) {
	this.nameSurname = nameSurname;
}

public String getBirthPlace() {
	return birthPlace;
}

public void setBirthPlace(String birthPlace) {
	this.birthPlace = birthPlace;
}

public String getTaxNo() {
	return taxNo;
}

public void setTaxNo(String taxNo) {
	this.taxNo = taxNo;
}

public String getAdress() {
	return adress;
}

public void setAdress(String adress) {
	this.adress = adress;
}


}
