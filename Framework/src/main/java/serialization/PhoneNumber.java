package serialization;
import java.io.*;

public class PhoneNumber implements Serializable{
	private String countryCode,areaCode,number;
	public PhoneNumber(String countryCode,String areaCode,String number){
		this.countryCode=countryCode;
		this.areaCode=areaCode;
		this.number=number;
	}
	public PhoneNumber(){
		this("?","?","?");
	}
	public String toString(){
		return "+"+countryCode+" "+areaCode+" "+number;
	}
	public String getCountryCode(){
		return countryCode;
	}
	public String getAreaCode(){
		return areaCode;
	}
	public String getNumber(){
		return number;
	}
}
