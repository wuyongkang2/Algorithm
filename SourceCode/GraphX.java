import java.util.Scanner;

/*2.打印大X
小明希望用星号拼凑，打印出一个大X，他要求能够控制笔画的宽度和整个字的高度。
为了便于比对空格，所有的空白位置都以句点符来代替。
要求输入两个整数m n，表示笔的宽度，X的高度。用空格分开(0<m<n, 3<n<1000, 保证n是奇数)
要求输出一个大X

例如，用户输入：
3 9
程序应该输出：
***.....***
.***...***.
..***.***..
...*****...
....***....
...*****...
..***.***..
.***...***.
***.....***

再例如，用户输入：
4 21
程序应该输出
****................****
.****..............****.
..****............****..
...****..........****...
....****........****....
.....****......****.....
......****....****......
.......****..****.......
........********........
.........******.........
..........****..........
.........******.........
........********........
.......****..****.......
......****....****......
.....****......****.....
....****........****....
...****..........****...
..****............****..
.****..............****.
****................****

资源约定：
峰值内存消耗（含虚拟机） < 256M
CPU消耗  < 1000ms*/

public class Main {
public static void main(String[] args) {
/*	题目分析：
	1.先初始化图形背景
	2.确定一个起始点，利用高的变化打印\
	3.同上打印/，注意起始点-1*/
	Scanner input=new Scanner(System.in);
	int kuan=input.nextInt();
	int height=input.nextInt();
	int width=height-1+kuan;
	
	char[][] arr=new char[height][width];
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[i].length; j++) {
			arr[i][j]='.';
		}
	}
	
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < kuan; j++) {
			arr[i][j+i]='*';
		}
		for (int j = 0; j < kuan; j++) {
			arr[i][width-kuan-i+j]='*';
		}
	}
	//打印图案
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[i].length; j++) {
			System.out.print(arr[i][j]);
		}
		System.out.println();
	}

}
}
