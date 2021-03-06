import java.util.Scanner;

/*4.绳圈 
今有 100 根绳子，当然会有 200 个绳头。 
如果任意取绳头两两配对，把所有绳头都打结连接起来。最后会形成若干个绳圈（不考虑是否套在一起）。 
我们的问题是：请计算最后将形成多少个绳圈的概率最大？ 
---------------------------------------------------------------------------------------------------------------------
分析：
1.m个绳子n个圈
第一种情况：f(m-1,n-1)    表示一条绳子自己相连
第二种情况：2*(m-1)*f(m-1,n)   不形成一个绳子的话有2*(m-1)种连法，其结果就是绳子少了一根，但是圈数没有减少
这两种连法就形成了一段绳子的处理方法，所以想加起来就是递归的式子
2.这里因为递归比较多层，所以需要使用二维数组存储进行加速
3.结点为n=1&n=m。当n=1时，表示各自为圈，有一种结果。当n=m时表示形成一个大圈，有2*(m-1)*f(m-1,1)种结果
4.将各种可能列出来，其返回值为double*/

public class Main {
	static double[][] arr=new double[1000][1000];
	public static void main(String[] args) {
		for (int i = 1; i <=10; i++) {
			System.out.println(f(10,i));
		}
	}

	public static double f(int n, int m) {
		if(arr[n][m]!=0){
			return arr[n][m];
		}
		if(n==m){     //这里n逐渐递减，总有和m相同的时候，所以不用判断m>n
			return 1;
		}
		if(m==1){
			return arr[n][m]=2*(n-1)*f(n-1,1);
		}
		return arr[n][m]=f(n-1,m-1)+2*(n-1)*f(n-1,m);    
	}

}


