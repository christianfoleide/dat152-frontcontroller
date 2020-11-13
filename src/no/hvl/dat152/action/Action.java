package no.hvl.dat152.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	
	int SUCCESS = 0;
	
	public int doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException;
	
}
