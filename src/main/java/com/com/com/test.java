package com.com.com;

public class test {
	
	public static int[] arr = new int[5];
	
	public static void main(String []args) {
		
		arr = new int[]{7, 4, 8, 5, 2};

        insertionSort(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " " );
        }
        
    }
	
	public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 선택 데이터
            int key = arr[i];
            //비교 데이터
            int j = i - 1;

            // 이전의 원소가 더 크다면 데이터는 하나씩 밀려난다.
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
