package com.unibg.ticketgenerator.srv;

import com.unibg.ticketgenerator.srv.dto.ArrayCb;
import com.unibg.ticketgenerator.srv.dto.ObjectCb;
import com.unibg.ticketgenerator.srv.library.BasicSRV;
import com.unibg.ticketgenerator.srv.ope.AllStackOPE;
import com.unibg.ticketgenerator.srv.ope.IncrementaOPE;
import com.unibg.ticketgenerator.srv.ope.ServeNextOPE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
public class ServiceBean extends BasicSRV {


	@RequestMapping(value="/"+ IncrementaOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> incrementaOPE(ObjectCb cb) { return execOPE(IncrementaOPE.NAME,cb); }

	@RequestMapping(value="/"+ AllStackOPE.NAME, method= RequestMethod.GET)
	public ResponseEntity<?> allStackOPE(ArrayCb cb) {
		return execOPE(AllStackOPE.NAME,cb);
	}


	@RequestMapping(value="/"+ ServeNextOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> serveNext(@RequestBody ObjectCb cb) {
		return  execOPE(ServeNextOPE.NAME,cb);
	}


}