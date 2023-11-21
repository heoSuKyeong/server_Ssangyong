package com.test.toy.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.toy.DBUtil;
import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.model.CommentDTO;

public class BoardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public BoardDAO() {
		this.conn = DBUtil.open();
	}

	public int add(BoardDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "insert into tblBoard (seq, subject, content, regdate, readcount, id) values (seqBoard.nextVal, ?, ?, default, default, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getId()); //null			

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<BoardDTO> list(HashMap<String, String> map) {
		
		//queryNoParamListReturn
		try {
			
			
			String where = "";
			
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}
			
			
			String sql = String.format("select * from (select a.*, rownum as rnum from vwBoard a %s) where rnum between %s and %s"
										, where
										, map.get("begin")
										, map.get("end"));
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setName(rs.getString("name"));
				dto.setIsnew(rs.getInt("isnew"));
				dto.setCcnt(rs.getInt("ccnt"));
				
				list.add(dto);
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public BoardDTO get(String seq) {
		
		//queryParamDTOReturn
		try {
			
			String sql = "select tblBoard.*, (select name from tblUser where id = tblBoard.id) as name from tblBoard where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setId(rs.getString("id"));
				
				dto.setName(rs.getString("name"));
				
				return dto;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void updateReadcount(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set readcount = readcount + 1 where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int edit(BoardDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set subject = ?, content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int del(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblBoard where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getTotalCount() {
		
		try {

			String sql = "select count(*) as cnt from tblBoard";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int addComment(CommentDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "insert into tblComment (seq, content, regdate, id, bseq) values (seqComment.nextVal, ?, default, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getBseq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<CommentDTO> listComment(String bseq) {
		
		//queryParamListReturn
		try {
			
			String sql = "select c.*, (select name from tblUser where id = c.id) as name from tblComment c where bseq = ? order by seq desc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bseq);
			
			rs = pstat.executeQuery();	
			
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setBseq(rs.getString("bseq"));
				
				dto.setName(rs.getString("name"));
				
				list.add(dto);
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int delComment(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblComment where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public void delCommentAll(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblComment where bseq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int editComment(CommentDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblComment set content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}






















