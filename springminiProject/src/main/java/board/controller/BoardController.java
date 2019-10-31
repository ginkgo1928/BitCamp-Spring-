package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.service.BoardService;



@Controller
@RequestMapping(value = "board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardPaging boardPaging;

	@RequestMapping(value = "boardWriteForm", method = RequestMethod.GET)
	public String boardWriteForm(Model model) {
		model.addAttribute("display","/board/boardWriteForm.jsp");
		return "/main/index";
	}

	@RequestMapping(value = "boardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@RequestParam Map<String,String>map,HttpSession session) {
		String id=(String)session.getAttribute("memId");
		String email=(String)session.getAttribute("memEmail");
		String name=(String)session.getAttribute("memName");
		map.put("id", id);
		map.put("name",name);
		map.put("email",email);
		
		boardService.boardWrite(map);
	}
	
	@RequestMapping(value="boardList",method=RequestMethod.GET)
	public String boardList(@RequestParam(required=false,defaultValue="1")String pg,Model model) {
		System.out.println("boardList pg="+pg);
		model.addAttribute("pg",pg);
		model.addAttribute("display","/board/boardList.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="getBoardList",method = RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required=false,defaultValue="1") String pg, HttpSession session) {
		
		String memId =(String)session.getAttribute("memId");
		System.out.println("getBoardList memId="+memId);
		System.out.println("getBoardList pg="+pg);
		if(pg.equals("undefined"))
			pg="1";
		int endNum = Integer.parseInt(pg)*5;
		int startNum = endNum - 4;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		System.out.println("getBoardList pg="+pg);
		List<BoardDTO> list = boardService.getBoardList(map);
		//페이징 처리
		int totalA =boardService.getTotalA();
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();

		ModelAndView mav = new ModelAndView();
		mav.addObject("memId",memId);
		mav.addObject("list",list);
		mav.setViewName("jsonView");
		mav.addObject("boardPaging",boardPaging);
		return mav;
	}
	@RequestMapping(value="boardView",method=RequestMethod.GET)
	public String boardView(@RequestParam String seq,@RequestParam String pg,Model model) {
		System.out.println("board view 창이야");
		model.addAttribute("seq",seq);
		model.addAttribute("pg",pg);
		System.out.println("boardView seq 어디야="+seq);
		System.out.println("boardView pg 어디야="+pg);
		model.addAttribute("display","/board/boardView.jsp");
		return "/main/index";
	}
	
	//pg 안줘도됨
	@RequestMapping(value = "getBoardView",method=RequestMethod.POST)
	public ModelAndView getBoardView(@RequestParam String seq,@RequestParam String pg,HttpSession session) {
		String memId=(String)session.getAttribute("memId");
		System.out.println("getBoardView seq="+seq);
		BoardDTO boardDTO=boardService.getBoardView(seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memId",memId);
		mav.addObject("boardDTO",boardDTO);
		mav.setViewName("jsonView");
		mav.addObject("boardPaging",boardPaging);
		return mav;
		
	}
	@RequestMapping(value="boardSearch", method = RequestMethod.POST)
	public ModelAndView boardSearch(@RequestParam Map<String, String>map) {
		//검색 게시판 1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg"))*5;
		int startNum = endNum-4;
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		List<BoardDTO>list=boardService.boardSearch(map);
		
		//페이징 처리
		int totalA=boardService.getBoardSearchTotalA(map);
		
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makeSearchPagingHTML();
		
		System.out.println("검색 옵션"+map.get("searchOption"));
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list",list);
		mav.addObject("searchOption",map.get("searchOption"));
		mav.addObject("keyword",map.get("keyword"));
		mav.addObject("boardPaging",boardPaging);
		mav.setViewName("jsonView");
		return mav;	
	}

	@RequestMapping(value = "boardReplyForm", method = RequestMethod.POST)
	public String boardReplyForm(@RequestParam String seq, @RequestParam String pg, Model model) {
		// 이때 submit해서 pg,seq id 속성이면 안됨
		model.addAttribute("pseq", seq);
		model.addAttribute("pg", pg);
		//System.out.println(seq + "원글" + pg + "페이지" + "답글 컨트롤러 메서드");
		model.addAttribute("display", "/board/boardReplayForm.jsp");
		return "/main/index";
	}

	@RequestMapping(value = "boardReply", method = RequestMethod.POST)
	@ResponseBody
	public void boardReply(@RequestParam Map<String, String> map, HttpSession session) {
		String id = (String) session.getAttribute("memId");
		String email = (String) session.getAttribute("memEmail");
		String name = (String) session.getAttribute("memName");
		//map안에 pseq,subject,content가 있다.
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		//원글(증가는 mappe에서 했음)
		BoardDTO pDTO=boardService.getBoardView(map.get("pseq"));
		map.put("ref",pDTO.getRef()+""); //답글 ref = 원글 ref
		map.put("lev",pDTO.getLev()+""); //답글lev = 원글 lev
		map.put("step",pDTO.getStep()+"");//답글step= 원글 step
		
		boardService.boardReply(map);		
	}
	
	@RequestMapping(value="boardDelete",method=RequestMethod.POST)
	public String boardDelete(@RequestParam String seq,Model model){
		boardService.boardDelete(seq);
		model.addAttribute("display", "/board/boardDelete.jsp");
		return "/main/index";	
	}
	
	@RequestMapping(value="boardModifyForm", method=RequestMethod.POST)
	public String boardModifyForm(@RequestParam String seq, @RequestParam String pg, Model model) {
		BoardDTO boardDTO = boardService.getBoardView(seq);
		
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("display", "/board/boardModifyForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="boardModify", method=RequestMethod.POST)
	@ResponseBody
	public void boardModify(@RequestParam(required=false) Map<String, String> map) {
		boardService.boardModify(map);
	}
	
}
