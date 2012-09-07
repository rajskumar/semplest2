package semplest.dmoz.tree.process;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;

import semplest.dmoz.tree.DmozTreeNode;

public class EmptyNodesProcesser implements TreeProcesserInterface {

	HashSet<String> nodes = new HashSet<String>();
	
	@Override
	public void analyzeTree(DmozTreeNode topNode) throws Exception {
		HashMap<String,DmozTreeNode> childrenNodes = topNode.getChildrenNodes();
		if(topNode.getCategoryData() == null || topNode.getCategoryData().isEmpty()){
			nodes.add(topNode.getName());
		}
		if(childrenNodes.size() == 0 ){
			return;
		}
		else{
			for(DmozTreeNode childrenNode : childrenNodes.values()){
				analyzeTree(childrenNode);
			}
		}
	}

	@Override
	public DmozTreeNode processTree(DmozTreeNode topNode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void printReport(String path) throws Exception{
		FileWriter writer = new FileWriter(path);
		for(String node : nodes){
			writer.append(node + "\n");
		}
		writer.close();	
	}

	@Override
	public Integer getCount(DmozTreeNode topNode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
