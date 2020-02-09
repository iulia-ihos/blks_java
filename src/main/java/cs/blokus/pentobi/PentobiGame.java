package cs.blokus.pentobi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class PentobiGame {
	private Process process;
	private BufferedReader reader;
	private BufferedReader errorReader;
	private BufferedWriter writer;
	private LinkedList<String> responses;
	
	public PentobiGame(Process process) {
		this.process = process;
		InputStream is = process.getInputStream();
		this.reader = new BufferedReader(new InputStreamReader(is));
		InputStream es = process.getErrorStream();
		this.errorReader =  new BufferedReader(new InputStreamReader(es));
		OutputStream os = process.getOutputStream();
		this.writer = new BufferedWriter(new OutputStreamWriter(os));
		responses = new LinkedList<>();
	}

	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void addResponse(String response) {
		this.responses.addLast(response);
	}
	
	public String removeResponse() {
		return this.responses.poll();
	}

	public Process getProcess() {
		return process;
	}

	public BufferedReader getErrorReader() {
		return errorReader;
	}

	
}
