package imageboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

@Service
public class ImageboardServiceImpl implements ImageboardService {
	@Autowired
	private ImageboardDAO imageboardDAO;

	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		imageboardDAO.imageboardWrite(imageboardDTO);
	}

	@Override
	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map) {
		return imageboardDAO.getImageboardList(map);
	}

	@Override
	public void imageboardDelete(Map<String, String[]> map) {
			imageboardDAO.imageboardDelete(map);
	}

	@Override
	public ImageboardDTO getImageboardView(int seq) {
		return imageboardDAO.getImageboardView(seq);
	}

	@Override
	public int getImageboardTotalA() {
		return imageboardDAO.getImageboardTotalA();
	}



}











