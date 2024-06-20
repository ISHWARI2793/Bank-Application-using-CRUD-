package bank.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bank.model.Login;
import bank.model.Register;



public class RegisterDaoImpl implements RegisterDao {

	@Override
	public int createRecord(List<Register> lst) {
		int i=0;
		Connection con=DBConnection.myConnection();
		Register rmodel=lst.get(0);
		
		try {
			PreparedStatement pstate=con.prepareStatement("insert into ibank values(?,?,?,?,?)");
			pstate.setInt(1, rmodel.getAccNumber());
			pstate.setString(2, rmodel.getAccFname());
			pstate.setString(3, rmodel.getAccUname());
			pstate.setString(4, rmodel.getAccPassword());
			pstate.setFloat(5, rmodel.getAccBal());
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return i;
	}

	@Override
	public int deleteRecord(int accNumber) {
		Connection con=DBConnection.myConnection();
		int i=0;
		
		try {
			PreparedStatement pstate=con.prepareStatement("delete from ibank where acc_number=?");
			pstate.setInt(1, accNumber);
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public int updateRecord(List<Register> lst) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Register> retriveRecord(int accNumber) {
		Connection con=DBConnection.myConnection();
		
		List<Register> lstrecord=null;
		
		try {
			PreparedStatement pstate=con.prepareStatement("select * from ibank where acc_number=?");
			pstate.setInt(1, accNumber);
			ResultSet result=pstate.executeQuery();
			
			if(result.next())
			{
				int accNumber1=result.getInt(1);
				String accFname=result.getString(2);
				String accUname=result.getString(3);
				String accPassword=result.getString(4);
				float accBal=result.getFloat(5);
				Register rmodel=new Register(accNumber, accFname, accUname, accPassword, accBal);
				lstrecord=new LinkedList<Register>();
				lstrecord.add(rmodel);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstrecord;
	}

	@Override
	public List<Register> displayAll() {

		Connection con=DBConnection.myConnection();
		ResultSet result=null;
		
		List<Register> lstallrecord=new LinkedList<Register>();
		
		
		
		try {
			Statement state= con.createStatement();
			result=state.executeQuery("Select * from ibank");
			while(result.next())
			{
				Register robj=new Register(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5));
				lstallrecord.add(robj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstallrecord;
	}

	public boolean validateUser(List<Login> lst) {
		
		boolean b=false;
		Connection con=DBConnection.myConnection();
		Login lobj=lst.get(0);
		
		try {
			PreparedStatement pstate=con.prepareStatement("select * from ibank where acc_uname=? and acc_password=?");
			pstate.setString(1,lobj.getUserName());
			pstate.setString(2, lobj.getPassword());
			ResultSet  result=pstate.executeQuery();
			if(result.next())
			{
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return b;
	}
	
	
	
	
	
	
	
	

}
