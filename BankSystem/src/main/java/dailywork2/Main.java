package dailywork2;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		String config = "classpath:applicationCtx_beans.xml";//src/main/resources
		
		//ioc컨테이너 : 객체를 만들고 조립 - 이 정보가 위의 xml 파일에 있음.
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(config);
		ctx.refresh();
		
		int menu = 0;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("--- bank system ---");
			System.out.println("1. 입금");
			System.out.println("2. 출금");
			System.out.println("3. 이체");
			System.out.println("4. 종료");
			System.out.print("사용할 메뉴 입력하렴 : ");
			String menuString = s.nextLine();
			menu = Integer.parseInt(menuString);
			
			if (menu == 1) {
				System.out.println("입금 업무.");
				System.out.print("입금할 사람 입력 : ");
				String id = s.nextLine();
				System.out.print("입금할 금액 입력 : ");
				String moneyString = s.nextLine();
				int money = Integer.parseInt(moneyString);
				BankDTO dto = new BankDTO();
				dto.setUserid(id);
				dto.setBalance(money);
				BankService service = new BankService();
				dto = service.deposit(dto);
				System.out.println("처리 결과");
				System.out.println("user id : " + dto.getUserid() + ", 잔액 : " + dto.getBalance());
			}
			
			if (menu == 2) {
				System.out.println("출금 업무.");
				System.out.print("출금할 사람 입력 : ");
				String id = s.nextLine();
				System.out.print("출금할 금액 입력 : ");
				String moneyString = s.nextLine();
				int money = Integer.parseInt(moneyString);
				BankDTO dto = ctx.getBean("dto", BankDTO.class);
				
				dto.setUserid(id);
				dto.setBalance(money);
				BankService service = ctx.getBean("service", BankService.class);
				dto = service.withdraw(dto);
				System.out.println("처리 결과");
				System.out.println("user id : " + dto.getUserid() + ", 잔액 : " + dto.getBalance());
			}
			
			if (menu == 3) {
				System.out.println("이체 업무.");
				System.out.print("출금할 사람 입력 : ");
				String id = s.nextLine();
				System.out.print("입금할 사람 입력 : ");
				String id2 = s.nextLine();
				System.out.print("이체할 금액 입력 : ");
				String moneyString = s.nextLine();
				int money = Integer.parseInt(moneyString);
				BankDTO dto = ctx.getBean("dto", BankDTO.class);
				BankDTO dto2 = ctx.getBean("dto", BankDTO.class);

				dto.setUserid(id);
				dto.setBalance(money);
				dto2.setUserid(id2);
				dto2.setBalance(money);
				BankService service = ctx.getBean("service", BankService.class);
				ArrayList<BankDTO> list= service.transfer(dto, dto2);
				System.out.println("처리 결과 a to b");
				System.out.println("user a id : " + list.get(0).getUserid() 
						+ ", 잔액 : " + list.get(0).getBalance()
						+ "user b id : " + list.get(1).getUserid() 
						+ ", 잔액 : " + list.get(1).getBalance());
			
			}

			if (menu == 4) {
				System.out.println("시스템 종료");
				
			}
		} while (menu != 4);
	}

}
