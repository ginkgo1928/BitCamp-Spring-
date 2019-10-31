package imageboard.service;

import java.util.List;
import java.util.Map;

import imageboard.bean.ImageboardDTO;

public interface ImageboardService {

	public void imageboardWrite(ImageboardDTO imageboardDTO);

	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map);

	public void imageboardDelete(Map<String, String[]> map);

	public ImageboardDTO getImageboardView(int seq);

	public int getImageboardTotalA();

	

}
