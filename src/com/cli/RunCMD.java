package com.cli;

public class RunCMD{

	public static void main(String[] args) {

		RootManager rootMgr = new RootManager();
		
		//init root dir at the beginning of running
		rootMgr.createRoot().getCommand();

	}
}
