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
            // ���� ������
            int key = arr[i];
            //�� ������
            int j = i - 1;

            // ������ ���Ұ� �� ũ�ٸ� �����ʹ� �ϳ��� �з�����.
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
