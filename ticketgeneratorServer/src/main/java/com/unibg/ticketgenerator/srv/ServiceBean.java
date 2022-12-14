package com.unibg.ticketgenerator.srv;

import com.unibg.ticketgenerator.srv.dto.*;
import com.unibg.ticketgenerator.srv.library.BasicSRV;
import com.unibg.ticketgenerator.srv.library.JwtUtils;
import com.unibg.ticketgenerator.srv.ope.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBean extends BasicSRV {

//	@Autowired private IncrementaOPE service; //= new OperationService(); questo non serve ci pensa spring a inizializzare

	@RequestMapping(value="/"+ LoginOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> loginOPE(@RequestBody LoginCb cb) {
		execOPE(LoginOPE.NAME,cb);
		//implementare la risposta quando è sbagliato il login
		return ResponseEntity.ok(cb);
	}

	//	"localhost:8080/IncrementaOPE"
	@RequestMapping(value="/"+ IncrementaOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> incrementaOPE(@RequestBody IncrementaCb cb) {
		return execOPE(IncrementaOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ AllStackOPE.NAME, method= RequestMethod.GET)
	public ResponseEntity<?> allStackOPE(@RequestBody AllStackCb cb) {
		return execOPE(AllStackOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ ServeNextOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> serveNext(@RequestBody ServeNextCb cb) {
		return  execOPE(ServeNextOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ AggiungiUtenteOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> aggiungiUtenteOPE(@RequestBody SignUpCb cb) {
		return execOPE(AggiungiUtenteOPE.NAME,cb);
	}


}