# 图形打印  

## 1.打印十字图  
>小明为某机构设计了一个十字型的徽标（并非红十字会啊），如下所示：  
>
>对方同时也需要在电脑dos窗口中以字符的形式输出该标志，并能任意控制层数。   
>
>输入格式  
>一个正整数 n (n<30) 表示要求打印图形的层数。  
>输出格式  
>对应包围层数的该标志。  
>样例输入1  
>1  
>样例输出1  
>  
>样例输入2  
>3  
>样例输出2  
>
>提示  
>请仔细观察样例，尤其要注意句点的数量和输出位置。  

---

题目分析：  
1. 初始化图像  
2. 确立一个点，通过哪个点绕一圈  
3. 确立下一个点  

```java
static char[][] arr;
static int bian;
static int mid;
public static void main(String[] args) {
	Scanner input=new Scanner(System.in);
	int num=input.nextInt();
	bian=num*4+5;   //层数和边的关系
	mid=bian/2;    //确定中间位置
	arr=new char[bian][bian];
	
	init();
	add(num);
	show();

}

private static void add(int num) {
	int x=mid-2;
	int y=mid-2;
	int index=1;
	for (int i = 0; i < num; i++) {  //一层层画
		arr[x][y]='$';
		for (int j = 0; j < 2; j++) {
			y--;
			arr[x][y]='$';
		}
		for (int j = 0; j < index*4; j++) {   //其数量跟index有关
			x++;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			y++;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			x++;
			arr[x][y]='$';
		}
		for (int j = 0; j < index*4; j++) {
			y++;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			x--;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			y++;
			arr[x][y]='$';
		}
		for (int j = 0; j < index*4; j++) {
			x--;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			y--;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			x--;
			arr[x][y]='$';
		}
		for (int j = 0; j < index*4; j++) {
			y--;
			arr[x][y]='$';
		}
		for (int j = 0; j < 2; j++) {
			x++;
			arr[x][y]='$';
		}
		index++;
		x=x-2;    //起点变化
		y=y-2;
	}
}
//构造完数组内容之后，统一打印数组的内容
public static void show() {
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[i].length; j++) {
			System.out.print(arr[i][j]);
		}
		System.out.println();
	}
}
//初始化固定的背景
public static void init() {
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[i].length; j++) {
			arr[i][j]='.';
		}
	}
	for (int i = 0; i < 5; i++) {
		arr[mid-2+i][mid]='$';
		arr[mid][mid-2+i]='$';
	}
}
```
要点注意：  
1. 画线的方式采用定义x，y。对x，y改变位置所得   
2. 因为长度涉及到圈数，为了方便运算，定义一个独立的index随圈数的遍历进行变化(不能直接用num ),记得用index来表示当前圈数  

[源码](../SourceCode/Graph10.java)

---