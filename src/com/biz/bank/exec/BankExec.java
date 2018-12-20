package com.biz.bank.exec;

import com.biz.bank.service.BankService;

public class BankExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String balanceFile = "src/com/biz/bank/bankBalance.txt";
		String bFile = "src/com/biz/bank/";
		
		BankService bs = new BankService(balanceFile, bFile);
		
		bs.readBalance();
		
		bs.bankMenu();
		
//		bs.view();
	}

}
