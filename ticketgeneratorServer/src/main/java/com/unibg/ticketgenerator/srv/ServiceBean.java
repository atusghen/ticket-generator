package com.unibg.ticketgenerator.srv;

import com.unibg.ticketgenerator.srv.dto.ArrayCb;
import com.unibg.ticketgenerator.srv.dto.ObjectCb;
import com.unibg.ticketgenerator.srv.dto.UserCb;
import com.unibg.ticketgenerator.srv.library.BasicSRV;
import com.unibg.ticketgenerator.srv.ope.AllStackOPE;
import com.unibg.ticketgenerator.srv.ope.IncrementaOPE;
import com.unibg.ticketgenerator.srv.ope.LoginOPE;
import com.unibg.ticketgenerator.srv.ope.ServeNextOPE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBean extends BasicSRV {

	@RequestMapping(value="/"+ LoginOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> login(UserCb cb) {
		return execOPE(LoginOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ IncrementaOPE.NAME, method= RequestMethod.POST)
//	"localhost:8080/IncrementaOPE"
	public ResponseEntity<?> incrementaOPE(ObjectCb cb) {
		return execOPE(IncrementaOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ AllStackOPE.NAME, method= RequestMethod.GET)
	public ResponseEntity<?> allStackOPE(ArrayCb cb) {
		return execOPE(AllStackOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ ServeNextOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> serveNext(@RequestBody ObjectCb cb) {
		return  execOPE(ServeNextOPE.NAME,cb);
	}


}