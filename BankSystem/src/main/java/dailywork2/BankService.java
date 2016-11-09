package dailywork2;

import java.util.ArrayList;

public class BankService {
	private BankDAO dao;
	private DBTemplate dbtemplate;


	public BankService() {
		super();
	}

	public BankService(BankDAO dao, DBTemplate dbtemplate) {
		super();
		this.dao = dao;
		this.dbtemplate = dbtemplate;
	}
	
	public BankDAO getDao() {
		return dao;
	}

	public void setDao(BankDAO dao) {

		this.dao = dao;
	}

	public DBTemplate getDbtemplate() {
		return dbtemplate;
	}

	public void setDbtemplate(DBTemplate dbtemplate) {
		this.dbtemplate = dbtemplate;
	}

	public BankDTO deposit(BankDTO dto) {
		dao.setDbtemplate(dbtemplate);
		dto = dao.update(dto);
		if (dto.isResult()) {
			dbtemplate.commit();
		} else {
			dbtemplate.rollback();
		}

		try {
			dbtemplate.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public BankDTO withdraw(BankDTO dto2) {
		dao.setDbtemplate(dbtemplate);
		dto2 = dao.updateWithdraw(dto2);
		if (dto2.isResult()) {
			dbtemplate.commit();
		} else {
			dbtemplate.rollback();
		}

		try {
			dbtemplate.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto2;
	}
	
	public ArrayList<BankDTO> transfer(BankDTO dto1, BankDTO dto2) {
		ArrayList<BankDTO> list = new ArrayList<BankDTO>();
		dao.setDbtemplate(dbtemplate);
		dto1 = dao.updateWithdraw(dto1);
		dto2 = dao.update(dto2);
		if (dto1.isResult() && dto2.isResult()) {
			dbtemplate.commit();
		} else {
			dbtemplate.rollback();
		}
		
		list.add(dto1);
		list.add(dto2);
		
		try {
			dbtemplate.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
