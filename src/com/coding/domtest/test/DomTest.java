package com.coding.domtest.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomTest {

	public static void main(String[] args) {
		//1、创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//2、创建一个DocumentBuilder的对象
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			//3、通过DocumentBuilder对象的parse（String fileName）方法解析xml文件
			Document document = db.parse("struts.xml");//可传入绝对路径或者相对路径;注意Document要是org.w3c.dom.Document这个包下的
			//接下来进行解析
			//获取所有Action节点集合
			NodeList actionList = document.getElementsByTagName("action");
			//遍历每一个action节点
			System.out.println("共有" + actionList.getLength() + "个action节点");
			for (int i = 0; i < actionList.getLength(); i++) {
				Node actionNode = actionList.item(i);
				//获取所有属性集合
				NamedNodeMap attrs = actionNode.getAttributes();
				//遍历action属性
				for (int j = 0; j < attrs.getLength(); j++) {
					Node attrNode = attrs.item(j);
					//属性名
					String attrName = attrNode.getNodeName();
					//属性值
					String attrValue = attrNode.getNodeValue();
				}
				//解析子节点
				NodeList childNodes = actionNode.getChildNodes();//这里获取的子节点既包括属性，也包括真正的下级子节点，还有Text类型的
				System.out.println("第 " + i + " 个节点下共有 " + childNodes.getLength() + " 个子节点");
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node childNode = childNodes.item(j);
					//区分Text类型的Node和Element类型的Node
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println(childNode.getNodeName());
						//获取节点值不能直接childNode.getNodeValue()，还要继续通过上面的方式或者通过getFirstChild然后取Value或者getTextContent
						//注意getFirstChild然后取Value和getTextContent方式的区别
						
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
