package com.test.toy.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import com.test.toy.DBUtil;

public class Dummy {
	
	public static void main(String[] args) {
		
		try {
			
			Connection conn = null;
			PreparedStatement stat = null;
			
			conn = DBUtil.open();
			
			String sql = "insert into tblBoard (seq, subject, content, regdate, readcount, id) values (seqBoard.nextVal, ?, '내용', default, default, 'hong')";
			
			String temp = "앞으로 한국에서도 넷플릭스 계정을 다른 사람들과 공유하면 추가 요금을 내야 한다. 넷플릭스는 2일 넷플릭스 계정의 이용 대상은 회원 본인과 함께 거주하는 사람, 즉 한 가구의 구성원이라며 이런 내용의 계정 공유 방침을 알렸다. 이번 방침으로 넷플릭스 회원과 같은 가구에 속하지 않는 이용자와 계정을 공유하려면 매달 5000원을 추가로 내야 한다. 계정 공유 이용자가 별도 계정을 만들어 기존 프로필 정보를 새 계정으로 이전하려면, 프리미엄 계정 월 1만7000원, 스탠다드 계정 월 1만3500원을 각각 내야 한다. 넷플릭스는 다른 가구 구성원과 계정을 공유하는 회원들에게 이날부터 순차적으로 이메일을 보내 새로운 계정 공유 정책을 안내하고 있다. 계정 공유 제한은 점진적으로 진행된다. 회원과 같은 가구에 살지 않는 외부 이용자나 외부 디바이스가 넷플릭스 계정에 접근할 경우 안내 메시지가 표시될 예정이다. 넷플릭스는 회원들이 가입 때 동의한 개인정보 취급 방침에 따라 IP 주소와 디바이스 ID, 계정 활동 등 정보를 활용해 이용자가 회원과 같은 가구에 사는지를 확인할 것으로 전해졌다. 한국에 앞서 북미와 남미, 유럽, 아시아 등 넷플릭스가 진출한 대부분의 국가에서는 이미 새 계정 공유 정책이 적용됐다.최근 넷플릭스뿐 아니라 디즈니플러스도 계정 공유 제한 정책을 도입하는 등 온라인동영상서비스(OTT) 업체들이 잇따라 가격을 올리고 있다. 국내 OTT 중에서는 티빙이 오는 12월부터 구독료를 올리고, 내년 1분기에 광고요금제를 도입한다.";
			String[] templist = temp.split(" ");
			
			Random rnd = new Random();
			
			
			
			stat = conn.prepareStatement(sql);
			
			for (int i=0; i<250; i++) {
				
				String subject = "";
				
				for (int j=0; j<5; j++) {
					subject += templist[rnd.nextInt(templist.length)] + " ";
				}
				
				stat.setString(1, subject);
				stat.executeUpdate();
				System.out.println(i);
				
			}
			
			stat.close();
			conn.close();			
			
		} catch (Exception e) {
			System.out.println("Dummy.main()");
			e.printStackTrace();
		}
		
	}

}











