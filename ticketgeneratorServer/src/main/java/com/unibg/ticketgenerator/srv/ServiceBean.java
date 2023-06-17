package com.unibg.ticketgenerator.srv;

import com.unibg.ticketgenerator.srv.dto.*;
import com.unibg.ticketgenerator.srv.library.BasicSRV;
import com.unibg.ticketgenerator.srv.ope.*;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceBean extends BasicSRV {

//	@Autowired private IncrementaOPE service; //= new OperationService(); questo non serve ci pensa spring a inizializzare

	@RequestMapping(value="/"+ LoginOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> loginOPE(@RequestBody LoginCb cb) {
		return execOPE(LoginOPE.NAME,cb);
	}

	//	"localhost:8080/IncrementaOPE"
	@RequestMapping(value="/"+ IncrementaOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> incrementaOPE(@RequestBody IncrementaCb cb) {
			return execOPE(IncrementaOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ AllStackOPE.NAME, method= RequestMethod.POST)
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

	@RequestMapping(value="/"+ InsertQueueOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> InsertQueueOPE(@RequestBody InsertQueueCB cb) {
		return execOPE(InsertQueueOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ AllUsersOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> AllUsersOPE(@RequestBody AllUsersCb cb) {
		return execOPE(AllUsersOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ AllQueueInformationOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> AllQueueInformationOPE(@RequestBody AllQueueInformationCB cb) {
		return execOPE(AllQueueInformationOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ QueueTimeOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> QueueTimeOPE(@RequestBody QueueTimeCb cb) {
		return execOPE(QueueTimeOPE.NAME,cb);
	}

	@RequestMapping(value="/"+ UserQueueTimeOPE.NAME, method= RequestMethod.POST)
	public ResponseEntity<?> UserQueueTimeOPE(@RequestBody UserQueueTimeCb cb) {
		return execOPE(UserQueueTimeOPE.NAME,cb);
	}


	@GetMapping("/UpdateTickets")
	@SneakyThrows
	public String UpdateTickets(){
		return null;
	}



}