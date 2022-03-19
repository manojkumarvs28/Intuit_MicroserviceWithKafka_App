package com.intuit.business.customerapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Custom {

	public static String nextGretest(String num) {
		 int n = num.length();
		 
		 //find if ascending order
		 int i=n-2;
		 
		 //218765
		 char[] arr =  num.toCharArray();
		 while(i>0) {

			int curChar =  num.charAt(i)+'a';
			int nextChar = num.charAt(i+1)+'a';
			//System.out.println("Asc check "+curChar +" "+nextChar);
			if(curChar < nextChar) {
				i--;
				continue;
			}
			else {
				break;
			}
		}
		 String res = null;
		if(i==0) {
			//replace last 2 characters
			char temp=arr[n-1];
			arr[n-1]= arr[n-2];
			arr[n-2] = temp;
			
			return String.valueOf(arr);
		}
		
		//find if in dec order 
		 i=n-2;
		 while(i>0) {
			
			int curChar =  num.charAt(i)+'a';
			int nextChar = num.charAt(i+1)+'a';
			 //System.out.println("desc "+curChar +" "+nextChar);
			if(curChar > nextChar) {
				i--;
				continue;
			}else {
				break;
			}
		}
		if(i==0) {
			return num;
		}
		
		//third case
		
		i=n-2;
		 
		while(i>=0) {
			int curChar =  num.charAt(i)+'a';
			int nextChar = num.charAt(i+1)+'a';
			 //System.out.println("last cond "+curChar +" "+nextChar);
			if(curChar < nextChar) {
				break;
			}
			i--;
		}
		
		
		char temp=arr[n-1];
		arr[n-1]= arr[i];
		arr[i] = temp;
		
		 Arrays.sort(arr, i+1, n);
		 return String.valueOf(arr);
	}
	
	
	
	
	public static void main(String[] args) {
		//1234-1243
		//218765 - 251678 
		
		//258761 - 251678
		//4321- 
		
		
//		String num= "1234";
//		
//		System.out.println(nextGretest(num));
		
		// 1, 2,3, 4,5
		// 1,2,4,5
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		
		List<Integer> indicesList = new ArrayList<>();
		indicesList.add(2);
		indicesList.add(3);
		indicesList.add(4);
		
	    // Arrays.sort((int[])indicesList);
	    
	    Iterator<Integer> itrIterator = list.iterator();
	    int counter =0;
	    while(itrIterator.hasNext()) { //n
	    	counter++;
	    	  int num = itrIterator.next();
	    	  if(indicesList.contains(counter)) {
	    		  itrIterator.remove();
	    	  }
	    }
			
			
			for(int num: list) {
				System.out.print(num+" ");
			}
			
		//}
		
		
		
	}
	
	
}
