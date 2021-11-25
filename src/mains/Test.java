package mains;

import java.io.IOException;
import java.net.URISyntaxException;

import tools.HDFSTools;

public class Test {

	public static void main(String[] args) throws URISyntaxException, IOException {
		String rootPath = "D:\\workspace\\eclipse_workspace\\cars_sales_statistics\\datasets\\";
		HDFSTools.uploadFile(rootPath + "shanxi_cars.csv","/datasets");
		HDFSTools.closeDFS();
	}
}

