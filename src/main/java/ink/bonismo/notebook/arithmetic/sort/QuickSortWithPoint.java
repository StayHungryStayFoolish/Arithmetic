package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/14 7:50 AM
 * @Description:
 * @Version: 1.0
 * @link https://blog.csdn.net/libaineu2004/article/details/82253412
 * 和挖坑法相比，指针交换法在partition方法中进行的元素交换次数更少。
 *
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195042&idx=1&sn=2b0915cd2298be9f2163cc90a3d464da&chksm=8c99f9f8bbee70eef627d0f5e5b80a604221abb3a1b5617b397fa178582dcb063c9fb6f904b3&scene=21#wechat_redirect
 */
public class QuickSortWithPoint {

    public static void quickSortWithPoint(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int pivotIndex = partition(arr, startIndex, endIndex);
        quickSortWithPoint(arr, startIndex, pivotIndex - 1);
        quickSortWithPoint(arr, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            // 移动右指针，当元素大于基准元素，指针左移，小于基准元素，指针停止，切换到左指针
            while (left < right && arr[right] > pivot) {
                right--;
            }

            // 移动左指针，当元素小于等于基准元素，指针右移，大于基准元素，指针停止，切换到右指针
            while (left < right && arr[left] <= pivot) {
                left++;
            }

            // 左右指针元素互换
            if (left < right) {
                int point = arr[left];
                arr[left] = arr[right];
                arr[right] = point;
            }
        }

        // 基准元素和指针重合点互换
        int point = arr[left];
        arr[left] =arr[startIndex];
        arr[startIndex] = point;
        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4,7,6,5,3,2,8,1};
        quickSortWithPoint(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
