package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.biz.bank.vo.BankVO;

public class BankService {

	List<BankVO> bankList;
	String balanceFile;
	String writeAcc;
	String File;
	
	Scanner scan;
	
public BankService(String balanceFile, String File) {
		bankList = new ArrayList();
		this.balanceFile = balanceFile;
		this.File = File;
		scan = new Scanner(System.in);
	}



public void readBalance() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(balanceFile);
			br = new BufferedReader(fr);
			
			while(true) {
				String readF = br.readLine();
				if(readF == null) break;
				String[] sBF = readF.split(":");
				BankVO vo = new BankVO();
				vo.setStrID(sBF[0]);
				vo.setIntBalance(Integer.valueOf(sBF[1]));
				vo.setStrLastDate(sBF[2]);
				bankList.add(vo);
				
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


public void bankMenu() {
		
		while(true) {
			System.out.println("=============================================");
			System.out.println("업무선택 : 입금(1)  출금(2)  계좌조회(3)  종료(0)");
			System.out.println("=============================================");
			System.out.print(">> ");
			String strMenu = scan.nextLine();
			
			if(strMenu.equals("0")) {
				System.out.println("안녕히가십시요");
				return;
			}
			
			
			
			if(strMenu.equals("1")) {
				System.out.println("입금을 선택하셨습니다.");
				this.bankInput();
//				return;
			}
			
			if(strMenu.equals("2")) {
				System.out.println("출금을 선택하셨습니다.");
				this.bankOutput();
//				return;
			}
			
			if(strMenu.equals("3")) {
				System.out.println("계좌조회를 선택하셨습니다.");
				this.view();
//				return;
			}
		}
		
	}
	


public void bankMenu01() {
		
		while(true) {
			System.out.println("=============================================");
			System.out.println("업무선택 : 입금(1)  출금(2)  계좌조회(3)  종료(0)");
			System.out.println("=============================================");
			System.out.print(">> ");
			String strMenu = scan.nextLine();
			
			if(strMenu.equals("0")) {
				System.out.println("안녕히가십시요");
				return;
			}
			
			
			if(strMenu.equals("1")) {
				System.out.println("입금을 선택하셨습니다.");
				this.bankInput01();
				this.bankIOWrite01();
//				return;
			}
			
			if(strMenu.equals("2")) {
				System.out.println("출금을 선택하셨습니다.");
				this.bankOutput01();
				this.bankIOWrite01();
//				return;
			}
			
			if(strMenu.equals("3")) {
				System.out.println("계좌조회를 선택하셨습니다.");
				for(BankVO vo : bankList) {
					if(writeAcc.equals(vo.getStrID())) {
						System.out.println("계좌번호 : " + vo.getStrID());
						System.out.println("계좌금액 : " + vo.getIntBalance());
						System.out.println("거래마지막날짜 : " + vo.getStrLastDate());
					}
				}
//				return;
			}
		}
		
	}
	


public void bankInput() {
		System.out.println("계좌번호를 입력해주세요");
		System.out.print(">> ");
		writeAcc = scan.nextLine();
		BankVO vo = bankFindID(writeAcc);
		if(vo == null) return;
		
		System.out.print("입금액 >> ");
		String strIn = scan.nextLine();
		int intIO = Integer.valueOf(strIn);
		
		vo.setStrIO("입금");
		vo.setIntIOCash(intIO);
		vo.setIntBalance(vo.getIntBalance() + intIO);
		vo.setStrLastDate(this.lastDate());
		System.out.println("입금처리 완료!!");
		this.bankIOWrite(vo);
	}
	


public void bankInput01() {
		System.out.println("계좌번호를 입력해주세요");
		System.out.print(">> ");
		writeAcc = scan.nextLine();
		
		for(BankVO vo : bankList) {
			if(writeAcc.equals(vo.getStrID())) {
				System.out.print("입금액 >> ");
				String strIn = scan.nextLine();
				int intIO = Integer.valueOf(strIn);
				
				vo.setStrIO("입금");
				vo.setIntIOCash(intIO);
				vo.setIntBalance(vo.getIntBalance() + intIO);
				vo.setStrLastDate(this.lastDate01());
				System.out.println("입금처리 완료!!");
			}
		}
	}
		


public void bankOutput() {
			System.out.println("계좌번호를 입력해주세요");
			System.out.print(">> ");
			writeAcc = scan.nextLine();
			BankVO vo = bankFindID(writeAcc);
			if(vo == null) return;
			
			System.out.print("출금액 >> ");
			String strIn = scan.nextLine();
			int intIO = Integer.valueOf(strIn);
			
			if(intIO > vo.getIntBalance()) {
				System.out.println("잔액이 부족합니다.");
				return;
			}
			
			vo.setStrIO("출금");
			vo.setIntIOCash(intIO);
			vo.setIntBalance(vo.getIntBalance() - intIO);
			vo.setStrLastDate(this.lastDate());
			System.out.println("출금처리 완료!!");
			this.bankIOWrite(vo);
		}
		


public void bankOutput01() {
		System.out.println("계좌번호를 입력해주세요");
		System.out.print(">> ");
		writeAcc = scan.nextLine();
		
		for(BankVO vo : bankList) {
			if(writeAcc.equals(vo.getStrID())) {
				System.out.print("출금액 >> ");
				String strIn = scan.nextLine();
				int intIO = Integer.valueOf(strIn);
				
				if(intIO > vo.getIntBalance()) {
					System.out.println("잔액이 부족합니다.");
					return;
				}
				
				vo.setStrIO("출금");
				vo.setIntIOCash(intIO);
				vo.setIntBalance(vo.getIntBalance() - intIO);
				vo.setStrLastDate(this.lastDate01());
				System.out.println("출금처리 완료!!");
			}
		}
	}
	


public BankVO bankFindID(String writeAcc) {
		for(BankVO vo : bankList) {
			if(writeAcc.equals(vo.getStrID())) {
				return vo;
			}
		}
		return null;
	}
	


public void view() {
		System.out.println("계좌번호를 입력해주세요");
		System.out.print(">> ");
		writeAcc = scan.nextLine();
		BankVO vo = bankFindID(writeAcc);
		
		if(vo == null) return;
		
		System.out.println(vo.getStrID() + ":" + vo.getIntBalance() + ":" + vo.getStrLastDate());
	}
	


public void printWrite() {
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(balanceFile);
			
			for(BankVO vo : bankList) {
				pw.println(vo.getStrID() + ":" + vo.getIntBalance() + ":" + vo.getStrLastDate());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


public String lastDate() {
		LocalDate ld = LocalDate.now();
		String today = ld.toString();
		return today;
	}
	


public String lastDate01() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date d = new Date();
		String today = sdf.format(d);
		return today;
	}
	


public void bankIOWrite(BankVO vo) {
		FileWriter fw;
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(balanceFile);
			
			for(BankVO v : bankList) {
				pw.println(v.getStrID() + ":" + v.getIntBalance() + ":" + v.getStrLastDate());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			fw = new FileWriter(File+writeAcc, true);
			pw = new PrintWriter(fw);
			
			BankVO vv = vo;
			
			if(vo.getStrIO().equals("입금")) {
				pw.println(vo.getStrID() + ":" + vo.getStrLastDate() 
							+ ":" + vo.getStrIO() + ":" + vo.getIntIOCash() 
							+ ":" + "0" + ":" + vo.getIntBalance());
			} else {
				pw.println(vo.getStrID() + ":" + vo.getStrLastDate() 
							+ ":" + vo.getStrIO() + ":" + "0" + ":"
							+ vo.getIntIOCash() + ":" + vo.getIntBalance());
			}
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


public void bankIOWrite01() {
		FileWriter fw;
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(balanceFile);
			
			for(BankVO vo : bankList) {
				pw.println(vo.getStrID() + ":" + vo.getIntBalance() + ":" + vo.getStrLastDate());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fw = new FileWriter(File+writeAcc, true);
			pw = new PrintWriter(fw);
			
			BankVO vo = bankFindID(writeAcc);
			
			pw.println(vo.getStrID() + ":" + vo.getStrLastDate() + ":" + vo.getStrIO() + ":" + vo.getIntIOCash() + ":" + vo.getIntBalance());
			
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
