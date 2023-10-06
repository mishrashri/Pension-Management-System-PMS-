package com.example.PensionDisbursement;

//arr = [1,0,0,0,1,2,3,-6,2,-2,3,-3]
//largest subarray sum is 0 
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1,0,0,0,-1,2,3,-6,1,2,-2,3,-3};
//		int arrnew[]=new int[arr.length];
//		for(int i=0;i<arr.length-1;i++)
//		{
//			arrnew[i]=arr[i];
//			for(int j=i+1;j<arr.length-1;j++) {
//				if(arrnew[i]+arrnew[j]!=0) {
//					return;
//				}
//				else {
//					break;
//				}
//			}
//		}
//		for(int i=0;i<arrnew.length;i++) {
//			System.out.println(arrnew[i]);
//		}
		
		int length=0;
		for(int i=0;i<arr.length;i++)
		{
			int sumzero=0;
			for(int j=i;j<arr.length;j++)
			{
				sumzero+=arr[j];
			if(sumzero==0)
			{
				length=Math.max(length, j-i+1);
			}
			}
		}
		System.out.println(length);
	}

}
