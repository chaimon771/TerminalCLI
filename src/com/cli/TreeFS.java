package com.cli;

import java.util.ArrayList;

public class TreeFS{

	private String dirName;
	private TreeFS parent;
	private ArrayList<TreeFS> childDirectories;
	private boolean isFile, isRoot;

	public TreeFS() {
		super();
	}

	public TreeFS(String dirName, TreeFS parent, boolean isRoot, boolean isFile) {
		super();
		this.dirName = dirName;
		this.parent = parent;
		this.childDirectories = new ArrayList<TreeFS>();
		this.setRoot(isRoot);
		this.isFile = isFile;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public ArrayList<TreeFS> getDirectories() {
		return childDirectories;
	}

	public void setDirectories(ArrayList<TreeFS> treeFSs) {
		this.childDirectories = treeFSs;
	}

	public TreeFS getParent() {
		return parent;
	}

	public void setParent(TreeFS parent) {
		this.parent = parent;
	}

	public void addChild(TreeFS treeFS) {
		childDirectories.add(treeFS);
	}

	public TreeFS getChild(String childName) throws Exception{
		if (!this.childDirectories.isEmpty()) {
			for (TreeFS childList : this.childDirectories) {
				if (childList.getDirName().equals(childName))
					return childList;
			}
		}
		Exception childNotFoundEx = new Exception(childName + " is not present in this directory.");
		throw childNotFoundEx;

	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	//This method executed when ls command run iterate all the current chiild dir elements 
	public void printChildren(boolean details) {
		if (details) {
			for (TreeFS childDirFile : childDirectories) {
				String end = "";
				if (!childDirFile.isFile)
					end = "/";
				System.out.println(" " + childDirFile.getDirName() + end);
				System.out.println(" ");
			}
			
		} else {
			for (TreeFS childDirFile : childDirectories) {
				String end = "";
				if (!childDirFile.isFile)
					end = "/";
				System.out.print(" " + childDirFile.getDirName() + end);
			}
			System.out.println(" ");
		}		
	}
	
}

