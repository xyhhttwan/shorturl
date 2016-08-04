
package com.platform.soft.filter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/service")
public class Sniffer {

	@RequestMapping(value = "/sniffer", method = RequestMethod.GET)
	@ResponseBody
	public String sniffer() {
		return "OK";
	}
}
