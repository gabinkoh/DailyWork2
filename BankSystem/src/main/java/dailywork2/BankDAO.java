package dailywork2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class BankDAO {
	DBTemplate dbtemplate;

	public BankDAO() {
	}

	public BankDAO(DBTemplate dbtemplate) {
		this.dbtemplate = dbtemplate;
	}

	public DBTemplate getDbtemplate() {
		return dbtemplate;
	}

	public void setDbtemplate(DBTemplate dbtemplate) {
		this.dbtemplate = dbtemplate;
	}

	public BankDTO update(BankDTO dto) {
		Connection con = dbtemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			//3 preparedStatement 생성 (sql가지고 있는)
			String sql = "update bank set balance = balance + ? where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBalance());
			pstmt.setString(2, dto.getUserid());
			//4쿼리 실행
			int c = pstmt.executeUpdate();

			if (c == 1) {
				String sql1 = "select userid, balance from bank where userid = ?";
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, dto.getUserid());
				rs = pstmt1.executeQuery();
				if (rs.next()) {
					dto.setBalance(rs.getInt("balance"));
				}

				dto.setResult(true);
				try {
					rs.close();
					pstmt1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				dto.setResult(false);
			}



		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public BankDTO updateWithdraw(BankDTO dto2) {
		Connection con = dbtemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			//3 preparedStatement 생성 (sql가지고 있는)
			String sql = "update bank set balance = balance - ? where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto2.getBalance());
			pstmt.setString(2, dto2.getUserid());
			//4쿼리 실행
			int c = pstmt.executeUpdate();

			if (c == 1) {
				String sql1 = "select userid, balance from bank where userid = ?";
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, dto2.getUserid());
				rs = pstmt1.executeQuery();
				if (rs.next()) {
					dto2.setBalance(rs.getInt("balance"));
				}
				if(dto2.getBalance()<0){ 
					System.out.println("예금금액이 작아서 출금할 수 없다"); 
					dto2.setResult(false);	
				} else{ 
					dto2.setResult(true);
				} 

				try {
					rs.close();
					pstmt1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

			}



		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dto2;
	}

}
