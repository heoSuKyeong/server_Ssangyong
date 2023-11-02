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
			
			conn = DBUtil.open("localhost", "toy", "java1234");
			
			String sql = "insert into tblBoard (seq, subject, content, regdate, readcount, id) values (seqBoard.nextVal, ?, '더미데이터 내용입니다.', default, default, 'test')";
			
			String temp = "유튜브가 애드블록(광고 차단 프로그램) 차단 정책을 전 세계 모든 국가에 확장 적용했다. 지난 6월 일부 국가에서 애드블록 차단을 시작한 지 약 5개월 만이다. 대가를 지불하지 않고 플랫폼을 이용하는 사용자를 확실하게 걸러내겠다는 의지다. 31일(현지시간) 미 IT전문지 더 버지에 따르면 유튜브는 이날부터 애드블록을 활성화한 시청자에게 광고를 띄우고, 광고 없는 환경을 원할 시 유튜브 프리미엄사용을 권하는 글로벌 프로젝트를 시작했다. 유튜브 관계자는 (애드블록을 활성화한 사용자의) 유튜브 화면에는 광고 차단기는 유튜브 서비스 약관을 위반합니다라는 메시지를 표시하고, 애드블록 프로그램에서 유튜브 광고 차단을 해제하지 않으면 영상을 볼 수 없게 했다고 설명했다. 이어 광고는 전 세계 다양한 크리에이터의 생태계를 지원해 수십억명의 사용자가 유튜브에서 좋아하는 콘텐츠를 즐길 수 있게 해준다며 유튜브 광고의 중요성을 강조했다..";
			
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
