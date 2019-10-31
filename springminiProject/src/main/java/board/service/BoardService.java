package board.service;

import java.util.List;
import java.util.Map;
import board.bean.BoardDTO;

public interface BoardService {

	public void boardWrite(Map<String, String> map);
	
	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getTotalA();

	public BoardDTO getBoardView(String seq);

	public List<BoardDTO> boardSearch(Map<String, String> map);

	public int getBoardSearchTotalA(Map<String, String> map);

	public void boardReply(Map<String, String> map);

	public void boardDelete(String seq);

	public void boardModify(Map<String, String> map);



}
