package hu.isrv.adamdezsi.gameoflife.rest;

import hu.isrv.adamdezsi.gameoflife.service.EngineService;
import hu.isrv.adamdezsi.gameoflife.service.StepRequestDto;
import hu.isrv.adamdezsi.gameoflife.service.StepResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dezsiadam on 2/1/17.
 */
@RestController
@RequestMapping("/game")
public class EngineController{

	@Autowired
	private EngineService engineService;

	@RequestMapping(path = "/step", method = RequestMethod.POST)
	public StepResponseDto step(@RequestBody StepRequestDto stepRequestDto){
		return engineService.step(stepRequestDto);
	}

}
