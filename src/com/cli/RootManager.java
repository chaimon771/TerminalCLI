package com.cli;

import java.util.Scanner;

public class RootManager extends TreeFS{

	private TreeFS currentDir, rootDirectory;
	private Scanner cmdInput;
	private String cmdStr;

	//init in the beginning of program
	public RootManager createRoot(){
		rootDirectory = new TreeFS("/", null, true, false);
		currentDir = rootDirectory;
		return this;
	}

	//gets the input from the user in the command line
	protected void getCommand() {
		System.out.print(":> ");

		cmdInput= new Scanner(System.in);
		cmdStr = cmdInput.nextLine();

		if (!cmdStr.equals("exit")) {
			//parse the inputs from user into switch case according the command and the Dir/File name
			commandAction(cmdStr);

			//"getCommand() Recursion the same method wich always waits until user enter a value instead "exit" string
			getCommand();
		}
	}

	//Making the action regarding the command by user mkdir, mkfile, ls, pwd and cd
	protected void commandAction(String cmd) {
		String[] cmdDivider = cmd.split(" ");
		//divide the command line strings to two index of array for example: arr[0] "mkdir", arr[1] "dirname"

		switch (cmdDivider[0]) {
		case "mkdir":
			mkdirCommand(cmdDivider);
			break;

		case "mkfile":
			mkfileCommand(cmdDivider);
			break;	

		case "ls":
			lsCommand(cmdDivider);
			break;

		case "pwd":
			pwdCommand();
			break;

		case "cd":
			cdCommand(cmdDivider);
			break;

		default:
			break;
		}
	}

	//in this method will have a function that create instance of TreeFS object 
	protected void mkdirCommand(String[] cmdArr) {
		if (cmdArr.length == 1)
			System.out.println("Invalid mkdir command. Enter directory name.");
		else
			try {
				if (cmdArr.length > 2) {
				}
				currentDir.addChild(new TreeFS(cmdArr[1], currentDir, false , false));
			} catch (Exception e) {
				e.printStackTrace();
			}


	}

	//in this method will have a function that create instance of File object
	protected void mkfileCommand(String[] cmdArr) {
		//System.out.println("mkfileCommand performed "+ cmdArr[0] + " " + cmdArr[1]);

		if (cmdArr.length == 1)
			System.out.println("Invalid \"mkfile\" command. Enter filename.");
		else
			try {
				if (cmdArr.length > 2) {
				}
				currentDir.addChild(new TreeFS(cmdArr[1],currentDir, false, true));
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

	//perform this method to print the files inside current directory
	protected void lsCommand(String[] cmdArr) {
		//		System.out.println("lsCommand performed");
		if (cmdArr.length == 1) {
			currentDir.printChildren(false);
		}
		else {
			try {
				currentDir.printChildren(true);
			} catch (Exception e) {
				System.out.println("no files at current TreeFS");
			}
		}

	}

	//print the path we are standing now
	protected void pwdCommand() {
		//		System.out.println("pwdCommand() performed");

		TreeFS tempParentDir = currentDir.getParent();
		String path = currentDir.getDirName();
		while (tempParentDir != null) {
			path = tempParentDir.getDirName() + "./." + path;
			tempParentDir = tempParentDir.getParent();
		}

		System.out.println(path);

	}

	//when "cd" command entered if the command is "cd .." > go back, it its "cd <dirName>" navigate inside the directory
	protected void cdCommand(String[] cmdArr) {
		//		System.out.println("cdCommand performed " + cmdArr[0] + " " + cmdArr[1]);

		if (cmdArr.length == 1)
			System.out.println("Invalid cd command.");
		else
			try {
				switch (cmdArr[1]) {
				case "/":
					currentDir = rootDirectory;
					break;

				case "..":
					if(!currentDir.getDirName().equals("/")) {
						currentDir = currentDir.getParent();
					}else {
						System.out.println("ileagal get up ^ to \"/\" Root TreeFS ");
					}
					break;

				default:
					if (currentDir.getChild(cmdArr[1]).isFile()) {
						System.out.println("\"cd\" command is ileagal get into a file");
					}else {
						currentDir = currentDir.getChild(cmdArr[1]);
					}
					break;
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

	}


}
