# 1. 暴力破解法

## 1.神奇算式
>由4个不同的数字，组成的一个乘法算式，它们的乘积仍然由这4个数字组成。  
>比如：  
>210 x 6 = 1260  
>8 x 473 = 3784  
>27 x 81 = 2187  
>都符合要求。  
>如果满足乘法交换律的算式算作同一种情况，那么，包含上边已列出的3种情况，一共有多少种满足要求的算式。  

---

题目分析：  
1. 这里需要解决三个问题：  
&nbsp;    a.左右数字相同     b.交换律不算     c.数字互不相同  
2. 左右数字相同：将结果排序后进行比较  
3. 交换律不算：限定前面的数小于后面的数  
4. 数字互不相同，用自身与自身进行比较  

```java
	public static void main(String[] args) {
		int count=0;
		for (int i = 1; i < 100; i++) {
			for (int j = 10; j < 1000; j++) {
				String st=i+""+j;
				String result=i*j+"";
				if(st.length()!=4||result.length()!=4||i>=j){  //限定长度		前面小于后面（防止重复）
					continue;
				}
				if(f(st,result)){
					count++;
				}	
			}			
		}
		System.out.println(count);	
	}

	public static boolean f(String st, String jieguo) {
		char[] num1=st.toCharArray();
		char[] num2=jieguo.toCharArray();
		Arrays.sort(num1);   //排序进行对比
		Arrays.sort(num2);
		for (int i = 0; i < num2.length; i++) {    //判断相同
			if(num1[i]!=num2[i]){
				return false;
			}
		}
		for (int i = 0; i < num2.length; i++) {     //各个数字不同
			for (int j = i+1; j < num2.length; j++) {
				if(num2[i]==num2[j]){
					return false;
				}
			}
		}
		return true;
	}
```
[源码](../SourceCode/MagicFormula.java)

---

## 2.熄灯问题
>请你写一个程序, 确定需要按下哪些按钮, 恰好使得所 有的灯都熄灭  
>每个案例由5行组成, 每一行包括6个数字  
>这些数字以空格隔开, 可以是0或1  
>0 表示灯的初始状态是熄灭的  
>1 表示灯的初始状态是点亮的  
>  
>接着按照该案例的输入格式输出5行  
>1 表示需要把对应的按钮按下   
>0 表示不需要按对应的按钮   
>每个数字以一个空格隔开  
>  
>输入：  
>0 1 1 0 1 0  
>1 0 0 1 1 1  
>0 0 1 0 0 1  
>1 0 0 1 0 1  
>0 1 1 1 0 0  
>  
>输出：  
>1 0 1 0 0 1  
>1 1 0 1 0 1  
>0 0 1 0 1 1  
>1 0 0 1 0 0  
>0 1 0 0 0 0   
>  
>输入：  
>0 0 1 0 1 0  
>1 0 1 0 1 1  
>0 0 1 0 1 1  
>1 0 1 1 0 0  
>0 1 0 1 0 0  
>  
>输出：  
>1 0 0 1 1 1  
>1 1 0 0 0 0  
>0 0 0 1 0 0  
>1 1 0 1 0 1  
>1 0 1 1 0 1  

---

题目分析：  
1. 为了处理外围的问题，在数组左上右包上一层  
2. 因为第一行确定，后面的也随之确定。   
所以已知第一行按钮情况，根据    n个按钮+灯亮    作用！！的灯亮情况，推出下一列按钮的情况（下一行的按钮与上一行的作用相同即可）  
3. 推出到最后一行的按钮情况，（再次根据多个按钮的作用只有按钮）最终情况与灯亮对比，按钮结果与灯亮相同则说明全部都被熄灭。  

```java
	static int[][] arr;
	static int[][] press;
	public static void main(String[] args) {
		arr=new int[6][8];
		press=new int[6][8];
		Scanner input=new Scanner(System.in);
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length-1; j++) {
				arr[i][j]=input.nextInt();
			}
		}
		for (int a1 = 0; a1 < 2; a1++) {
			for (int a2 = 0; a2 < 2; a2++) {
				for (int a3 = 0; a3 < 2; a3++) {
					for (int a4 = 0; a4 < 2; a4++) {
						for (int a5 = 0; a5 < 2; a5++) {
							for (int a6 = 0; a6 < 2; a6++) {
								press[1][1]=a1;
								press[1][2]=a2;
								press[1][3]=a3;
								press[1][4]=a4;
								press[1][5]=a5;
								press[1][6]=a6;
								if(f()){
									show();
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void show() {
		for (int i = 1; i < press.length; i++) {
			for (int j = 1; j < press[i].length-1; j++) {
				System.out.print(press[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static boolean f() {
		for (int i = 2; i < press.length; i++) {
			for (int j = 1; j < press[i].length-1; j++) {
				press[i][j]=(arr[i-1][j]+press[i-1][j]+press[i-1][j-1]+press[i-1][j+1]+press[i-2][j])%2;   //按钮和灯的共同作用
			}
		}
		for (int j = 1; j < arr[5].length-1; j++) {
			if(arr[5][j]!=(press[5][j]+press[5][j-1]+press[5][j+1]+press[4][j])%2){
				return false;
			}
		}
		return true;
	}

```
[源码](../SourceCode/BlackOut.java)

---

## 3.讨厌的青蛙  
>每只青蛙总是沿着一条直线跳跃稻田 • 且每次跳跃的距离都相同  
>请写一个程序, 确定: 在各条青蛙行走路径中, 踩踏水稻最多的那一条上, 有多 少颗水稻被踩踏   
>例如, 图4中答案是7, 因为第6行上全部水稻恰好构成一 条青蛙行走路径  
>程序输入  
>从标准输入设备上读入数据  
>第一行上两个整数R, C, 分别表示稻田中水稻的行数和 列数, 1≤R, C≤5000  
>第二行是一个整数N, 表示被踩踏的水稻数量, 3≤N≤5000  
>在剩下的N行中, 每行有两个整数, 分别是一颗被踩踏水 稻的行号(1~R)和列号(1~C), 两个整数用一个空格隔开  
>且每棵被踩踏水稻只被列出一次  
>程序输出    
>从标准输出设备上输出一个整数     
>如果在稻田中存在青蛙行走路径, 则输出包含最多水稻 的青蛙行走路径中的水稻数量, 否则输出0  
>  
>样例输入   //6 行 7 列  
>6 7   
>14  
>2 1  
>6 6  
>4 2  
>2 5  
>2 6  
>2 7  
>3 4  
>6 1  
>6 2  
>2 3  
>6 3  
>6 4  
>6 5  
>6 7  
>  
>样例输出  
>7  

---

题目分析：  
1. 首先获取数据，获取水稻位置的时候，将其存进对象里面。因为用到对象，所以需要用ArrayList存储  
2. 寻找头两个点：  
计算两个点的跨度  
3. 筛选不符合的情况：  
当走之前就已经再田里（>=1）,跳过这个循环  
当到max的前一步之前（max初始化为2），如果已经越界了跳过循环（x跳x层的，y跳y层的）  
符合的话进行计算步数  
4. 计算步数方法：  
只要没越界， 无限循环。拿出第一步的xy位置，每次都往上面添加距离。    添加完之后判断新的点有没有存在（判断存不存在函数），不存在则取消这次跳动，步数归0  
5. 筛选返回的步数  
调用步数方法之后会返回一个步数，判断其是否比最大的小，并且>2，如果符合的话，输出最大步数  

```java
public class Main {
	static ArrayList<Sign> al;
	static int r;
	static int c;
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		r=input.nextInt();
		c=input.nextInt();
		int num=input.nextInt();
		al=new ArrayList<Sign>();
		int max=2;   //初始化最大步数为2
		for (int i = 0; i < num; i++) {
			al.add(new Sign(input.nextInt(), input.nextInt()));
		}
		for (int i = 0; i < al.size(); i++) {
			for (int j = i+1; j < al.size(); j++) {
				int dX=al.get(j).x-al.get(i).x;
				int dY=al.get(j).y-al.get(i).y;
				int tX=al.get(i).x-dX;
				int tY=al.get(i).y-dY;
				if(tX>=1&&tY>=1){
					continue;
				}
				if(al.get(i).x+dX*(max-1)>r||al.get(i).y+dY*(max-1)>c){
					continue;
				}
				if(al.get(i).x==6&&al.get(i).y==1){
					System.out.println("stop");
				}
				int step=f(al.get(i),dX,dY);
				if(step>max){
					max=step;
				}
			}
		}
		System.out.println(max);
	}
	public static int f(Sign sign, int dX, int dY) {
		int x=sign.x;
		int y=sign.y;
		int step=0;
		while(x<=r&&y<=c){
			if(!isCao(x,y)){
				return 0;
			}
			x=x+dX;
			y=y+dY;
			step++;   //多处一步补起点没有计算
		}
		return step;
	}
	private static boolean isCao(int x, int y) {
		for (int i = 0; i < al.size(); i++) {
			if(al.get(i).x==x&&al.get(i).y==y){
				return true;
			}
		}
		return false;
	}

}
class Sign{
	int x;
	int y;
	public Sign(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
}
```
要点注意：

	1. 先测试小数据，再测试大数据
	2. 根据测试数据一边调整程序精度。

[源码](../SourceCode/Frog.java)  

---

## 4.四平方和  
>四平方和定理，又称为拉格朗日定理：  
>每个正整数都可以表示为至多4个正整数的平方和。  
>如果把0包括进去，就正好可以表示为4个数的平方和。  
>比如：  
>5 = 0^2 + 0^2 + 1^2 + 2^2  
>7 = 1^2 + 1^2 + 1^2 + 2^2  
>（^符号表示乘方的意思）  
>对于一个给定的正整数，可能存在多种平方和的表示法。  
>要求你对4个数排序：  
>0 <= a <= b <= c <= d  
>并对所有的可能表示法按 a,b,c,d 为联合主键升序排列，最后输出第一个表示法  
>程序输入为一个正整数N (N<5000000)  
>要求输出4个非负整数，按从小到大排序，中间用空格分开  
>  
>例如，输入：  
>5  
>则程序应该输出：  
>0 0 1 2  
>  
>再例如，输入：  
>	  
>则程序应该输出：  
>0 2 2 2  
>  
>再例如，输入：  
>773535  
>则程序应该输出：  
>1 1 267 838  
>  
>资源约定：  
>峰值内存消耗（含虚拟机） < 256M  
>CPU消耗  < 3000ms  

---

题目分析：
1. 这里使用暴力破解法，设置一个for循环，循环到5000000的开方（可知最小范围）  
2. 解决从小到大列出：每层循环都从上一个数开始  
3. 降低时间复杂度：循环出其他三个数之后，因为已知答案，第四个数可以用开方来获取  
4.  解决要求的是最大的数的问题，因为是从第一个数开始循环的，所以从小到大排列。  

```java
public static void main(String[] args) {
	Scanner input=new Scanner(System.in);
	int num=input.nextInt();
	double maxNum= Math.sqrt(5000000);   //最大不超过开方
	for (int i = 0; i <maxNum; i++) {   //从小到大for
		for (int j = i; j < maxNum; j++) {
			for (int k = j; k < maxNum; k++) {
				int l=(int)Math.sqrt(num-i*i-j*j-k*k);  //不用暴力最后一个
				if(i*i+j*j+k*k+l*l==num){
					System.out.println(i+" "+j+" "+k+" "+l);
					return;
				} 
			}
		}
	}	
}
```
[源码](../SourceCode/FourSquareSum.java)  

---

## 5.带分数  
>100 可以表示为带分数的形式：100 = 3 + 69258 / 714  
>还可以表示为：100 = 82 + 3546 / 197  
>注意特征：带分数中，数字1到9分别出现且只出现一次（不包含0）。  
>类似这样的带分数，100 有 11 种表示法。  
>题目要求：  
>从标准输入读入一个正整数N (N<1000*1000)  
>程序输出该数字用数码1到9不重复不遗漏地组成带分数表示的全部种数。  
>注意：不要求输出每个表示，只统计有多少表示法！  
>  
>例如：  
>用户输入：  
>100  
>程序输出：  
>11  
>  
>再例如：  
>用户输入：  
>105  
>程序输出：  
>6  
>  
>资源约定：  
>峰值内存消耗（含虚拟机） < 64M  
>CPU消耗  < 3000ms  

---

题目分析：  
1. 循环a与c，根据公式算出b的值     c为什么循环到10000，因为用最大的数除以10000最多也只有100  
2. 获取到abc之后要判断其出现且仅出现一次（不包括0，所以统计的时候要减去0）  
3. 判断的时候要判断，每个数出现的总和是9并且有出现的数的总和也是9  

```java
public static void main(String[] args) {
	Scanner input=new Scanner(System.in);
	int num=input.nextInt();
	int count=0;
	for (int a = 1; a < num; a++) {
		for (int b = 1; b < 1000000/num; b++) {  //从算式获得最小循环
			int c=(num-a)*b;   //转化除法为乘法
			String all=""+a+b+c;
			int[] arr=new int[10];
			int rel=0;
			for (int i = 0; i < all.length(); i++) {
				arr[all.charAt(i)-'0']=1;
				rel++;   //计算数字出现的次数
			}
			int temp=0;
			for (int i = 1; i < arr.length; i++) {
				temp+=arr[i];    //统计是否每个数字都有出现
			}
			if(rel==9&&temp==9){
				count++;
			}
			
		}
	}
	System.out.println(count);    //输出符合的次数总值
}
```
[源码](../SourceCode/BringFra.java)  

---
